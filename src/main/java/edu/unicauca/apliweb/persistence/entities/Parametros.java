/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicauca.apliweb.persistence.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Javier Fernandez
 */
@Entity
@Table(name = "parametros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parametros.findAll", query = "SELECT p FROM Parametros p"),
    @NamedQuery(name = "Parametros.findById", query = "SELECT p FROM Parametros p WHERE p.id = :id"),
    @NamedQuery(name = "Parametros.findByTemperatura", query = "SELECT p FROM Parametros p WHERE p.temperatura = :temperatura"),
    @NamedQuery(name = "Parametros.findByHumedadsuelo", query = "SELECT p FROM Parametros p WHERE p.humedadsuelo = :humedadsuelo"),
    @NamedQuery(name = "Parametros.findByHumedadambiente", query = "SELECT p FROM Parametros p WHERE p.humedadambiente = :humedadambiente"),
    @NamedQuery(name = "Parametros.findByPrecipitaciones", query = "SELECT p FROM Parametros p WHERE p.precipitaciones = :precipitaciones"),
    @NamedQuery(name = "Parametros.findByIntensidadluzsolar", query = "SELECT p FROM Parametros p WHERE p.intensidadluzsolar = :intensidadluzsolar"),
    @NamedQuery(name = "Parametros.findByFecha", query = "SELECT p FROM Parametros p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "Parametros.findByHora", query = "SELECT p FROM Parametros p WHERE p.hora = :hora")})
public class Parametros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Temperatura")
    private float temperatura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Humedad_suelo")
    private float humedadsuelo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Humedad_ambiente")
    private float humedadambiente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Precipitaciones")
    private boolean precipitaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Intensidad_luz_solar")
    private float intensidadluzsolar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @JoinColumn(name = "Id_dispositivo", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Dispositivo iddispositivo;

    public Parametros() {
    }

    public Parametros(Integer id) {
        this.id = id;
    }

    public Parametros(Integer id, float temperatura, float humedadsuelo, float humedadambiente, boolean precipitaciones, float intensidadluzsolar, Date fecha, Date hora) {
        this.id = id;
        this.temperatura = temperatura;
        this.humedadsuelo = humedadsuelo;
        this.humedadambiente = humedadambiente;
        this.precipitaciones = precipitaciones;
        this.intensidadluzsolar = intensidadluzsolar;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public float getHumedadsuelo() {
        return humedadsuelo;
    }

    public void setHumedadsuelo(float humedadsuelo) {
        this.humedadsuelo = humedadsuelo;
    }

    public float getHumedadambiente() {
        return humedadambiente;
    }

    public void setHumedadambiente(float humedadambiente) {
        this.humedadambiente = humedadambiente;
    }

    public boolean getPrecipitaciones() {
        return precipitaciones;
    }

    public void setPrecipitaciones(boolean precipitaciones) {
        this.precipitaciones = precipitaciones;
    }

    public float getIntensidadluzsolar() {
        return intensidadluzsolar;
    }

    public void setIntensidadluzsolar(float intensidadluzsolar) {
        this.intensidadluzsolar = intensidadluzsolar;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Dispositivo getIddispositivo() {
        return iddispositivo;
    }

    public void setIddispositivo(Dispositivo iddispositivo) {
        this.iddispositivo = iddispositivo;
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
        if (!(object instanceof Parametros)) {
            return false;
        }
        Parametros other = (Parametros) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.unicauca.apliweb.persistence.entities.Parametros[ id=" + id + " ]";
    }
    
}
