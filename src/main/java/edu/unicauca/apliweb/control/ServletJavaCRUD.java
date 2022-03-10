/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicauca.apliweb.control;

import edu.unicauca.apliweb.persistence.entities.RangoParametros;
import edu.unicauca.apliweb.persistence.jpa.RangoParametrosJpaController;
import edu.unicauca.apliweb.persistence.jpa.exceptions.IllegalOrphanException;
import edu.unicauca.apliweb.persistence.jpa.exceptions.NonexistentEntityException;
import java.io.IOException;
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
 * @author natal
 */
//@WebServlet("/")
public class ServletJavaCRUD extends HttpServlet {

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
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
    rangoJPA = new RangoParametrosJpaController(emf);
    //esta parte es solamente para realizar la prueba:
    //listamos todos los clientes de la base de datos y los imprimimos en consola
    /*List<Dispositivo> listaDispositivos = dispositivoJPA.findDispositivoEntities();
    //imprimimos los clientes en consola
    for (Dispositivo dispositivo : listaDispositivos) {
      System.out.println("Nombre " + dispositivo.getNombre() + " Estado " + dispositivo.getEstado());
    }*/
  }

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException, IllegalOrphanException {
    response.setContentType("text/html;charset=UTF-8");
    String action = request.getServletPath();
    try {
      switch (action) {
        case "/newRango": //Muestra el formulario para crear un nuevo cliente
          showNewForm(request, response);
          break;
        case "/insertRango": //ejecuta la creación de un nuevo cliente en la BD
          insertRangos(request, response);
          break;
        case "/deleteRango": //Ejecuta la eliminación de un cliente de la BD
          deleteRangos(request, response);
          break;
        case "/editRango": //Muestra el formulario para editar un cliente
          showEditForm(request, response);
          break;
        case "/updateRango": //Ejecuta la edición de un cliente de la BD
          updateRangos(request, response);
          break;
        case "/rangoParametros":
          listRangos(request, response);
          break;
        default:
          listRangos(request, response);
          break;
      }
    } catch (SQLException ex) {
      throw new ServletException(ex);
    }
  }

  private void listRangos(HttpServletRequest request, HttpServletResponse response)
          throws SQLException, IOException, ServletException {
    List<RangoParametros> listaRangoParametros = rangoJPA.findRangoParametrosEntities();
    request.setAttribute("listRangos", listaRangoParametros);

    RequestDispatcher dispatcher = request.getRequestDispatcher("list-rangos.jsp");

    dispatcher.forward(request, response);
  }

  //muestra el formulario para crear un nuevo usuario
  private void showNewForm(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    RequestDispatcher dispatcher = request.getRequestDispatcher("rangos-form.jsp");
    dispatcher.forward(request, response);
  }

  //muestra el formulario para editar un usuario
  private void showEditForm(HttpServletRequest request, HttpServletResponse response)
          throws SQLException, ServletException, IOException {
    //toma el id del cliente a ser editaro
    int id = Integer.parseInt(request.getParameter("id"));
    //busca al cliente en la base de datos
    RangoParametros existingRango = rangoJPA.findRangoParametros(id);
    RequestDispatcher dispatcher = null;
    if (existingRango != null) {
      //si lo encuentra lo envía al formulario
      dispatcher = request.getRequestDispatcher("rangos-form.jsp");
      request.setAttribute("rango", existingRango);
    } else {
      //si no lo encuentra regresa a la página con la lista de los rano parametros
      dispatcher = request.getRequestDispatcher("list-rangos.jsp");
    }
    dispatcher.forward(request, response);
  }

  //método para crear un cliente en la base de datos
  private void insertRangos(HttpServletRequest request, HttpServletResponse response)
          throws SQLException, IOException {
    //toma los datos del formulario de clientes
    String temp_min = request.getParameter("Temperatura_Min");
    String temp_max = request.getParameter("Temperatura_Max");
    String humedad_min = request.getParameter("Humedad_Min");
    String humedad_max = request.getParameter("Humedad_Max");
    String luz_min = request.getParameter("Intensidad_Luz_Min");
    String luz_max = request.getParameter("Intensidad_Luz_Max");
    String hum_amb_min = request.getParameter("Humedad_Ambiente_Min");
    String hum_amb_max = request.getParameter("Humedad_Ambiente_Max");
    //crea un objeto de tipo Clients vacío y lo llena con los datos obtenidos
    RangoParametros rp = new RangoParametros();
    rp.setTemperaturamin(Float.parseFloat(temp_min));
    rp.setTemperaturamax(Float.parseFloat(temp_max));
    rp.setHumedadmin(Float.parseFloat(humedad_min));
    rp.setHumedadmax(Float.parseFloat(humedad_max));
    rp.setIntensidadluzmin(Float.parseFloat(luz_min));
    rp.setIntensidadluzmax(Float.parseFloat(luz_max));
    rp.setHumedadambientemin(Float.parseFloat(hum_amb_min));
    rp.setHumedadambientemax(Float.parseFloat(hum_amb_max));
    //Crea el cliente utilizando el objeto controlador JPA
    rangoJPA.create(rp);
    //solicita al Servlet que muestre la página actualizada con la lista de clientes
    response.sendRedirect("rangoParametros");
  }

  //Método para editar un cliente
  private void updateRangos(HttpServletRequest request, HttpServletResponse response)
          throws SQLException, IOException {
    //toma los datos enviados por el formulario de clientes
    Integer id = Integer.parseInt(request.getParameter("id"));
    float temp_min = Float.parseFloat(request.getParameter("Temperatura_Min"));
    float temp_max = Float.parseFloat(request.getParameter("Temperatura_Max"));
    float humedad_min = Float.parseFloat(request.getParameter("Humedad_Min"));
    float humedad_max = Float.parseFloat(request.getParameter("Humedad_Max"));
    float luz_min = Float.parseFloat(request.getParameter("Intensidad_Luz_Min"));
    float luz_max = Float.parseFloat(request.getParameter("Intensidad_Luz_Max"));
    float hum_amb_min = Float.parseFloat(request.getParameter("Humedad_Ambiente_Min"));
    float hum_amb_max = Float.parseFloat(request.getParameter("Humedad_Ambiente_Max"));
    //crea un objeto vacío y lo llena con los datos del cliente
    RangoParametros rp = new RangoParametros();
    rp.setId(id);
    rp.setTemperaturamin(temp_min);
    rp.setTemperaturamax(temp_max);
    rp.setHumedadmin(humedad_min);
    rp.setHumedadmax(humedad_max);
    rp.setIntensidadluzmin(luz_min);
    rp.setIntensidadluzmax(luz_max);
    rp.setHumedadambientemin(hum_amb_min);
    rp.setHumedadambientemax(hum_amb_max);
    try {
      //Edita el cliente en la BD
      rangoJPA.edit(rp);
    } catch (Exception ex) {
      Logger.getLogger(ServletJavaCRUD.class.getName()).log(Level.SEVERE, null, ex);
    }
    response.sendRedirect("rangoParametros");
  }

  //Elimina un cliente de la BD
  private void deleteRangos(HttpServletRequest request, HttpServletResponse response)
          throws SQLException, IOException, IllegalOrphanException {
    //Recibe el ID del cliente que se espera eliminar de la BD
    int id = Integer.parseInt(request.getParameter("id"));
    try {
      //Elimina el cliente con el id indicado
      rangoJPA.destroy(id);
    } catch (NonexistentEntityException ex) {
      Logger.getLogger(ServletJavaCRUD.class.getName()).log(Level.SEVERE, null, ex);
    }
    response.sendRedirect("rangoParametros");
  }
  
  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    try {
      processRequest(request, response);
    } catch (IllegalOrphanException ex) {
      Logger.getLogger(ServletJavaCRUD.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    try {
      processRequest(request, response);
    } catch (IllegalOrphanException ex) {
      Logger.getLogger(ServletJavaCRUD.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "\"Descripción del ServletJavaCRUD";
  }// </editor-fold>

}
