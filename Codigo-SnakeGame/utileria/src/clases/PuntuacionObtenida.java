package clases;

import java.io.Serializable;

public class PuntuacionObtenida implements Serializable {
    
    private static final long serialVersionUID = 2L;
    
    private Integer idcliente;
    private String nombre;
    private Integer puntuacion;

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
}
