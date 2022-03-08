/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicauca.apliweb.control;

import edu.unicauca.apliweb.persistence.entities.Dispositivo;
import edu.unicauca.apliweb.persistence.entities.RangoParametros;
import edu.unicauca.apliweb.persistence.entities.Usuario;
import edu.unicauca.apliweb.persistence.jpa.DispositivoJpaController;
import edu.unicauca.apliweb.persistence.jpa.RangoParametrosJpaController;
import edu.unicauca.apliweb.persistence.jpa.UsuarioJpaController;
import edu.unicauca.apliweb.persistence.jpa.exceptions.IllegalOrphanException;
import edu.unicauca.apliweb.persistence.jpa.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Javier Fernandez
 */
@WebServlet("/")
public class ServletAppProperseed extends HttpServlet {

    private UsuarioJpaController usuarioJPA;
    private DispositivoJpaController dispositivoJPA;
    private RangoParametrosJpaController rangoJPA;
    private final static String PU = "edu.unicauca.apliweb_JavaCRUD_war_1.0-SNAPSHOTPU";

    @Override
    public void init() throws ServletException {
        super.init();
        //creamos una instancia de la clase EntityManagerFactory
        //esta clase se encarga de gestionar la construcción de entidades y
        //permite a los controladores JPA ejecutar las operaciones CRUD
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        //creamos una instancia del controldor JPA para la clase clients y le
        //pasamos el gestor de entidades
        usuarioJPA = new UsuarioJpaController(emf);
        dispositivoJPA = new DispositivoJpaController(emf);
        rangoJPA = new RangoParametrosJpaController(emf);
        //esta parte es solamente para realizar la prueba:
        //listamos todos los clientes de la base de datos y los imprimimos en consola 
        /*List<Dispositivo> listaDispositivos = dispositivoJPA.findDispositivoEntities();
        for (Dispositivo dispositivo : listaDispositivos) {
            System.out.println("Nombre: " + dispositivo.getNombre() + " Estado:" + dispositivo.getEstado() + " Usuario: " + dispositivo.getIdusuario().getNombres() + dispositivo.getIdusuario().getApellidos());            
        }*/
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, IllegalOrphanException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getServletPath();
        //toma la acción solicitada desde la petición enviada al Servlet
        try {
            switch (action) {
                case "/new": //Muestra el formulario para crear un nuevo cliente
                    showNewForm(request, response);
                    break;
                case "/insert": //ejecuta la creación de un nuevo cliente en la BD
                    insertDispositivo(request, response);
                    break;
                case "/delete": //Ejecuta la eliminación de un cliente de la BD
                    deleteDispositivo(request, response);
                    break;
                case "/edit": //Muestra el formulario para editar un cliente
                    showEditForm(request, response);
                    break;
                case "/update": //Ejecuta la edición de un cliente de la BD
                    updateDispositivo(request, response);
                    break;
                default:
                    listDispositivos(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listDispositivos(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Dispositivo> listaDispositivos = dispositivoJPA.findDispositivoEntities();
        
        request.setAttribute("listDispositivos", listaDispositivos);
        

        RequestDispatcher dispatcher = request.getRequestDispatcher("list-dispositivos.jsp");

        dispatcher.forward(request, response);
    }
//muestra el formulario para crear un nuevo usuario

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("dispositivo-form.jsp");
        List<RangoParametros> listaRangos = rangoJPA.findRangoParametrosEntities();
        request.setAttribute("listRangos", listaRangos);
            //request.setAttribute("idRango", existingDispositivo.getIdrango().getId());
        dispatcher.forward(request, response);
    }
//muestra el formulario para editar un usuario

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
//toma el id del cliente a ser editaro
        int id = Integer.parseInt(request.getParameter("id"));
//busca al cliente en la base de datos
        Dispositivo existingDispositivo = dispositivoJPA.findDispositivo(id);
        List<RangoParametros> listaRangos = rangoJPA.findRangoParametrosEntities();
        RequestDispatcher dispatcher = null;
        if (existingDispositivo != null) {
//si lo encuentra lo envía al formulario
            dispatcher = request.getRequestDispatcher("dispositivo-form.jsp");
            request.setAttribute("dispositivo", existingDispositivo);
            request.setAttribute("listRangos", listaRangos);
            request.setAttribute("idRango", existingDispositivo.getIdrango().getId());
        } else {
            //si no lo encuentra regresa a la página con la lista de los clientes 
            dispatcher = request.getRequestDispatcher("list-dispositivos.jsp");
        }
        dispatcher.forward(request, response);
    }
//método para crear un cliente en la base de datos

    private void insertDispositivo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
//toma los datos del formulario de clientes
        String nombre = request.getParameter("nombre");
        String estado = request.getParameter("estado");
        String latitud = request.getParameter("latitud");
        String longitud = request.getParameter("longitud");
        int id_rango = Integer.parseInt(request.getParameter("rango"));
//crea un objeto de tipo Clients vacío y lo llena con los datos obtenidos 
        Dispositivo ds = new Dispositivo();
        RangoParametros rango = rangoJPA.findRangoParametros(id_rango);
        Usuario usuario = usuarioJPA.findUsuario(4);
        ds.setNombre(nombre);
        ds.setEstado(estado);
        ds.setLatitud(latitud);
        ds.setLongitud(longitud);
        ds.setIdrango(rango);
        ds.setIdusuario(usuario);
//Crea el cliente utilizando el objeto controlador JPA
        dispositivoJPA.create(ds);
//solicita al Servlet que muestre la página actualizada con la lista de clientes 
        response.sendRedirect("list");
    }
//Método para editar un cliente

    private void updateDispositivo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
//toma los datos enviados por el formulario de clientes
        int id = Integer.parseInt(request.getParameter("id"));
        //int id=29;
        String nombre = request.getParameter("nombre");
        String estado = request.getParameter("estado");
        String latitud = request.getParameter("latitud");
        String longitud = request.getParameter("longitud");
        int id_rango = Integer.parseInt(request.getParameter("rango"));
//crea un objeto vacío y lo llena con los datos del cliente
        //Dispositivo ds = new Dispositivo();
        Dispositivo ds = dispositivoJPA.findDispositivo(id);        
        RangoParametros rango = rangoJPA.findRangoParametros(id_rango);
        //ds.setId(id_dispositivo);
        ds.setNombre(nombre);
        ds.setEstado(estado);
        ds.setLatitud(latitud);
        ds.setLongitud(longitud);
        ds.setIdrango(rango);
        try {
//Edita el cliente en la BD
            dispositivoJPA.edit(ds);
        } catch (Exception ex) {
            Logger.getLogger(ServletAppProperseed.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("list");
    }
//Elimina un cliente de la BD

    private void deleteDispositivo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, IllegalOrphanException {
//Recibe el ID del cliente que se espera eliminar de la BD
        int id = Integer.parseInt(request.getParameter("id"));
        try {
//Elimina el cliente con el id indicado
            dispositivoJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ServletAppProperseed.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("list");
    }

    /**
     * Procesa las peticiones HTTP <code>GET</code>.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException excepción que se lanza si ocurre un error en el
     * servlet
     * @throws IOException excepción por si hay errores de tipo I/O
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(ServletAppProperseed.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Procesa las peticiones HTTP <code>POST</code>.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException excepción que se lanza si ocurre un error en el
     * servlet
     * @throws IOException excepción por si hay errores de tipo I/O
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(ServletAppProperseed.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Entrega una descripción corta del servlet * @return Una cadena de texto
     * String containing con una descripción del servlet
     */
    @Override
    public String getServletInfo() {
        return "Descripción del ServletAppProperseed ";
    }
}
