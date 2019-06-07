/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author luisb
 */
@Entity
@Table(name = "PUNTUACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Puntuacion.findAll", query = "SELECT p FROM Puntuacion p"),
    @NamedQuery(name = "Puntuacion.findByIdcliente", query = "SELECT p FROM Puntuacion p WHERE p.idcliente = :idcliente"),
    @NamedQuery(name = "Puntuacion.findByNombre", query = "SELECT p FROM Puntuacion p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Puntuacion.findByPuntuacion", query = "SELECT p FROM Puntuacion p WHERE p.puntuacion = :puntuacion"),
    @NamedQuery(name = "Puntuacion.findSortByPuntuacion", query = "SELECT p FROM Puntuacion p ORDER BY p.puntuacion DESC" )})
public class Puntuacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDCLIENTE")
    private Integer idcliente;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "PUNTUACION")
    private Integer puntuacion;

    public Puntuacion() {
    }

    public Puntuacion(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Puntuacion(Integer idcliente, String nombre) {
        this.idcliente = idcliente;
        this.nombre = nombre;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcliente != null ? idcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Puntuacion)) {
            return false;
        }
        Puntuacion other = (Puntuacion) object;
        if ((this.idcliente == null && other.idcliente != null) || (this.idcliente != null && !this.idcliente.equals(other.idcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "objetos.Puntuacion[ idcliente=" + idcliente + " ]";
    }
    
}
