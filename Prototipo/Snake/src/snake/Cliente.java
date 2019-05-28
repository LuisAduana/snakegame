/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import Utileria.Comida;
import Utileria.Snake;
import interfaz.Client;
import interfaz.Server;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import javafx.scene.input.KeyCode;

/**
 *
 * @author soy-y
 */
public class Cliente extends UnicastRemoteObject implements Client {

    private static final long SerialVersionUID = 9090898209349823403L;
    private ArrayList<Snake> serpientes;
    private String color;
    private Server servidor;
    private Comida comida;

    public Cliente(Server servidor) throws RemoteException {
        this.servidor = servidor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ArrayList<Snake> getSerpientes() {
        return serpientes;
    }

    public void setSerpientes(ArrayList<Snake> serpientes) {
        this.serpientes = serpientes;
    }

    public Server getServidor() {
        return servidor;
    }

    public void setServidor(Server servidor) {
        this.servidor = servidor;
    }
    
    public Comida getComida () {
        return comida;
    }

    /*@Override
    public void iniciar() throws RemoteException{
        this.servidor.iniciarPartida(this);
        System.out.println("Hola");
    }*/
 /*@Override
    public void mover() throws RemoteException {
        this.servidor.mover(this, KeyCode.PERIOD);
    }*/
 /*@Override
    public void iniciarMovimiento() throws RemoteException {
    this.servidor.iniciarMovimiento(this, true);
    }*/
    @Override
    public void mover(ArrayList<Snake> serpientes) throws RemoteException {
        this.serpientes = serpientes;
    }

    @Override
    public void iniciarPartida(ArrayList<Snake> serpientes, String color) throws RemoteException {
        for (Snake s : serpientes) {
            if (s.getCliente().equals(this)) {
                System.out.println("Jugador iniciado");
                System.out.println("----------------------------------");
                System.out.println(s.getColor());
            }
        }
        this.serpientes = serpientes;
        this.color = color;
    }

    @Override
    public void iniciarMovimiento(ArrayList<Snake> serpientes) throws RemoteException {
        this.serpientes = serpientes;
    }

    public Snake getSerpiente() {
        for (Snake s : this.serpientes) {
            if (s.getColor().equalsIgnoreCase(this.color)) {
                return s;
            }

        }
        return null;
    }

    @Override
    public void actualizar(ArrayList<Snake> serpientes) throws RemoteException {
        this.serpientes = serpientes;
    }

    @Override
    public void recibirComida(Comida comida) throws RemoteException {
        System.out.println("COMIDA RECIBIDA....");
        this.comida = comida;
        System.out.println("Comida en X: " + comida.getPoint().getX() + " Y: " + comida.getPoint().getY());
    }

}
