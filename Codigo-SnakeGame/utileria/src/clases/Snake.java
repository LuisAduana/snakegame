package clases;

import interfaz.ICliente;
import java.awt.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author soy-y
 */

public class Snake {
    
    private Color color;
    private ICliente cliente;
    private String nombre;

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
    
    
    public Snake(Color color, ICliente cliente, String nombre){
        this.color = color;
        this.cliente = cliente;
        this.nombre = nombre;
    }
    
    
    
    
}
