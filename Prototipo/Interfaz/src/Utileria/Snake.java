/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utileria;

import Utileria.Point;
import interfaz.Client;
import javafx.scene.paint.Color;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.scene.input.KeyCode;

/**
 *
 * @author soy-y
 */
public class Snake implements Serializable{
    
    private ArrayList<Point> cuerpo;
    private Point cabeza;
    private KeyCode direccion;
    private boolean enMovimiento;
    private String color;
    private Client cliente;

    public Client getCliente() {
        return cliente;
    }

    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public KeyCode getDireccion() {
        return direccion;
    }

    public void setDireccion(KeyCode direccion) {
        this.direccion = direccion;
    }

    public boolean isEnMovimiento() {
        return enMovimiento;
    }

    public void setEnMovimiento(boolean enMovimiento) {
        this.enMovimiento = enMovimiento;
    }

    public Snake(Point cabeza, String color, Client cliente) {
        this.cuerpo = new ArrayList();
        this.cabeza = cabeza;
        this.cuerpo.add(cabeza);
        this.cliente = cliente;
        this.color = color;
        this.direccion = KeyCode.SPACE;
        this.enMovimiento = false;
    }
    
    
    public void crecer(Point punto) {
        this.cabeza = punto;
        this.cuerpo.add(punto);
        
    }
    
    public void Mover(KeyCode direccion) {
        this.direccion = direccion;
        
        
    }
    
    public void actualizarPosicion() {
        
     Point aux;
        switch (direccion) {
            case UP:
                aux = new Point(this.getCabeza().getX(), this.getCabeza().getY() - 1);
                cabeza = aux;
                this.cuerpo.add(aux);
                this.cuerpo.remove(0);
                break;
            case DOWN:
                aux = new Point(this.getCabeza().getX(), this.getCabeza().getY() + 1);
                cabeza = aux;
                this.cuerpo.add(aux);
                this.cuerpo.remove(0);
                break;
            case LEFT:
                aux = new Point(this.getCabeza().getX() - 1, this.getCabeza().getY());
                cabeza = aux;
                this.cuerpo.add(aux);
                this.cuerpo.remove(0);
                break;
            case RIGHT:
                aux = new Point(this.getCabeza().getX() + 1, this.getCabeza().getY());
                cabeza = aux;
                this.cuerpo.add(aux);
                this.cuerpo.remove(0);
                break;
                
                
                
        }
        
        
    }

    public ArrayList<Point> getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(ArrayList<Point> cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Point getCabeza() {
        return cabeza;
    }

    public void setCabeza(Point cabeza) {
        this.cabeza = cabeza;
    }
    
   
    
    
}
