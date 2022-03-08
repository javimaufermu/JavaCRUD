/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicauca.apliweb.persistence.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Javier Fernandez
 */
@Entity
@Table(name = "dispositivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dispositivo.findAll", query = "SELECT d FROM Dispositivo d"),
    @NamedQuery(name = "Dispositivo.findById", query = "SELECT d FROM Dispositivo d WHERE d.id = :id"),
    @NamedQuery(name = "Dispositivo.findByEstado", query = "SELECT d FROM Dispositivo d WHERE d.estado = :estado"),
    @NamedQuery(name = "Dispositivo.findByNombre", query = "SELECT d FROM Dispositivo d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "Dispositivo.findByLatitud", query = "SELECT d FROM Dispositivo d WHERE d.latitud = :latitud"),
    @NamedQuery(name = "Dispositivo.findByLongitud", query = "SELECT d FROM Dispositivo d WHERE d.longitud = :longitud")})
public class Dispositivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "Estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Latitud")
    private String latitud;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Longitud")
    private String longitud;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddispositivo")
    private List<Alerta> alertaList;
    @JoinColumn(name = "Id_usuario", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Usuario idusuario;
    @JoinColumn(name = "Id_rango", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private RangoParametros idrango;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddispositivo")
    private List<Parametros> parametrosList;

    public Dispositivo() {
    }

    public Dispositivo(Integer id) {
        this.id = id;
    }

    public Dispositivo(Integer id, String estado, String nombre, String latitud, String longitud) {
        this.id = id;
        this.estado = estado;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    @XmlTransient
    public List<Alerta> getAlertaList() {
        return alertaList;
    }

    public void setAlertaList(List<Alerta> alertaList) {
        this.alertaList = alertaList;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    public RangoParametros getIdrango() {
        return idrango;
    }

    public void setIdrango(RangoParametros idrango) {
        this.idrango = idrango;
    }

    @XmlTransient
    public List<Parametros> getParametrosList() {
        return parametrosList;
    }

    public void setParametrosList(List<Parametros> parametrosList) {
        this.parametrosList = parametrosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dispositivo)) {
            return false;
        }
        Dispositivo other = (Dispositivo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.unicauca.apliweb.persistence.entities.Dispositivo[ id=" + id + " ]";
    }
    
}
