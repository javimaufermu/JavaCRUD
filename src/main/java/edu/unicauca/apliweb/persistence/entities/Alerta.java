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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Javier Fernandez
 */
@Entity
@Table(name = "alerta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alerta.findAll", query = "SELECT a FROM Alerta a"),
    @NamedQuery(name = "Alerta.findById", query = "SELECT a FROM Alerta a WHERE a.id = :id"),
    @NamedQuery(name = "Alerta.findByVariable", query = "SELECT a FROM Alerta a WHERE a.variable = :variable"),
    @NamedQuery(name = "Alerta.findByTipo", query = "SELECT a FROM Alerta a WHERE a.tipo = :tipo"),
    @NamedQuery(name = "Alerta.findByValor", query = "SELECT a FROM Alerta a WHERE a.valor = :valor"),
    @NamedQuery(name = "Alerta.findByMaximo", query = "SELECT a FROM Alerta a WHERE a.maximo = :maximo"),
    @NamedQuery(name = "Alerta.findByMinimo", query = "SELECT a FROM Alerta a WHERE a.minimo = :minimo"),
    @NamedQuery(name = "Alerta.findByFecha", query = "SELECT a FROM Alerta a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "Alerta.findByHora", query = "SELECT a FROM Alerta a WHERE a.hora = :hora"),
    @NamedQuery(name = "Alerta.findByRevisada", query = "SELECT a FROM Alerta a WHERE a.revisada = :revisada")})
public class Alerta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Variable")
    private String variable;
    @Size(max = 4)
    @Column(name = "Tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Valor")
    private int valor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Maximo")
    private Float maximo;
    @Column(name = "Minimo")
    private Float minimo;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "Revisada")
    private boolean revisada;
    @JoinColumn(name = "Id_dispositivo", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Dispositivo iddispositivo;

    public Alerta() {
    }

    public Alerta(Integer id) {
        this.id = id;
    }

    public Alerta(Integer id, String variable, int valor, Date fecha, Date hora, boolean revisada) {
        this.id = id;
        this.variable = variable;
        this.valor = valor;
        this.fecha = fecha;
        this.hora = hora;
        this.revisada = revisada;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Float getMaximo() {
        return maximo;
    }

    public void setMaximo(Float maximo) {
        this.maximo = maximo;
    }

    public Float getMinimo() {
        return minimo;
    }

    public void setMinimo(Float minimo) {
        this.minimo = minimo;
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

    public boolean getRevisada() {
        return revisada;
    }

    public void setRevisada(boolean revisada) {
        this.revisada = revisada;
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
        if (!(object instanceof Alerta)) {
            return false;
        }
        Alerta other = (Alerta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.unicauca.apliweb.persistence.entities.Alerta[ id=" + id + " ]";
    }
    
}
