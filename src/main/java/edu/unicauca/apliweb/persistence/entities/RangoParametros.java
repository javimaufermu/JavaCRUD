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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Javier Fernandez
 */
@Entity
@Table(name = "rango_parametros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RangoParametros.findAll", query = "SELECT r FROM RangoParametros r"),
    @NamedQuery(name = "RangoParametros.findById", query = "SELECT r FROM RangoParametros r WHERE r.id = :id"),
    @NamedQuery(name = "RangoParametros.findByTemperaturamax", query = "SELECT r FROM RangoParametros r WHERE r.temperaturamax = :temperaturamax"),
    @NamedQuery(name = "RangoParametros.findByTemperaturamin", query = "SELECT r FROM RangoParametros r WHERE r.temperaturamin = :temperaturamin"),
    @NamedQuery(name = "RangoParametros.findByHumedadmax", query = "SELECT r FROM RangoParametros r WHERE r.humedadmax = :humedadmax"),
    @NamedQuery(name = "RangoParametros.findByHumedadmin", query = "SELECT r FROM RangoParametros r WHERE r.humedadmin = :humedadmin"),
    @NamedQuery(name = "RangoParametros.findByIntensidadluzmax", query = "SELECT r FROM RangoParametros r WHERE r.intensidadluzmax = :intensidadluzmax"),
    @NamedQuery(name = "RangoParametros.findByIntensidadluzmin", query = "SELECT r FROM RangoParametros r WHERE r.intensidadluzmin = :intensidadluzmin"),
    @NamedQuery(name = "RangoParametros.findByHumedadambientemax", query = "SELECT r FROM RangoParametros r WHERE r.humedadambientemax = :humedadambientemax"),
    @NamedQuery(name = "RangoParametros.findByHumedadambientemin", query = "SELECT r FROM RangoParametros r WHERE r.humedadambientemin = :humedadambientemin")})
public class RangoParametros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Temperatura_max")
    private float temperaturamax;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Temperatura_min")
    private float temperaturamin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Humedad_max")
    private float humedadmax;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Humedad_min")
    private float humedadmin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Intensidad_luz_max")
    private float intensidadluzmax;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Intensidad_luz_min")
    private float intensidadluzmin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Humedad_ambiente_max")
    private float humedadambientemax;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Humedad_ambiente_min")
    private float humedadambientemin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idrango")
    private List<Dispositivo> dispositivoList;

    public RangoParametros() {
    }

    public RangoParametros(Integer id) {
        this.id = id;
    }

    public RangoParametros(Integer id, float temperaturamax, float temperaturamin, float humedadmax, float humedadmin, float intensidadluzmax, float intensidadluzmin, float humedadambientemax, float humedadambientemin) {
        this.id = id;
        this.temperaturamax = temperaturamax;
        this.temperaturamin = temperaturamin;
        this.humedadmax = humedadmax;
        this.humedadmin = humedadmin;
        this.intensidadluzmax = intensidadluzmax;
        this.intensidadluzmin = intensidadluzmin;
        this.humedadambientemax = humedadambientemax;
        this.humedadambientemin = humedadambientemin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getTemperaturamax() {
        return temperaturamax;
    }

    public void setTemperaturamax(float temperaturamax) {
        this.temperaturamax = temperaturamax;
    }

    public float getTemperaturamin() {
        return temperaturamin;
    }

    public void setTemperaturamin(float temperaturamin) {
        this.temperaturamin = temperaturamin;
    }

    public float getHumedadmax() {
        return humedadmax;
    }

    public void setHumedadmax(float humedadmax) {
        this.humedadmax = humedadmax;
    }

    public float getHumedadmin() {
        return humedadmin;
    }

    public void setHumedadmin(float humedadmin) {
        this.humedadmin = humedadmin;
    }

    public float getIntensidadluzmax() {
        return intensidadluzmax;
    }

    public void setIntensidadluzmax(float intensidadluzmax) {
        this.intensidadluzmax = intensidadluzmax;
    }

    public float getIntensidadluzmin() {
        return intensidadluzmin;
    }

    public void setIntensidadluzmin(float intensidadluzmin) {
        this.intensidadluzmin = intensidadluzmin;
    }

    public float getHumedadambientemax() {
        return humedadambientemax;
    }

    public void setHumedadambientemax(float humedadambientemax) {
        this.humedadambientemax = humedadambientemax;
    }

    public float getHumedadambientemin() {
        return humedadambientemin;
    }

    public void setHumedadambientemin(float humedadambientemin) {
        this.humedadambientemin = humedadambientemin;
    }

    @XmlTransient
    public List<Dispositivo> getDispositivoList() {
        return dispositivoList;
    }

    public void setDispositivoList(List<Dispositivo> dispositivoList) {
        this.dispositivoList = dispositivoList;
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
        if (!(object instanceof RangoParametros)) {
            return false;
        }
        RangoParametros other = (RangoParametros) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.unicauca.apliweb.persistence.entities.RangoParametros[ id=" + id + " ]";
    }
    
}
