package clases;

import interfaz.ICliente;
import java.awt.Color;

/**
 * Clase Snake que maneja sus atributos.
 * @author Fernando.
 */

public class Snake {
    
    private Color color;
    private ICliente cliente;
    private String nombre;
    
    public Snake(Color color, ICliente cliente, String nombre){
        this.color = color;
        this.cliente = cliente;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ICliente getCliente() {
        return cliente;
    }

    public void setCliente(ICliente cliente) {
        this.cliente = cliente;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
}
