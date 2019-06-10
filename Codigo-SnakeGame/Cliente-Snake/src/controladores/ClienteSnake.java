/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Interfaces.ICliente;
import Interfaces.IServer;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import javafx.scene.input.KeyCode;
import snake.Snake;

/**
 *
 * @author soy-y
 */
public class ClienteSnake extends UnicastRemoteObject implements ICliente {
    
    private String color;
    private static final long  SerialVersionUID = 9090898209349823403L;
    private final int PORT = 3232;
    public  IServer server;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    

    public IServer getServer() {
        return server;
    }

    public void setServer(IServer server) {
        this.server = server;
    }
    
    ClienteSnake(IServer server) throws RemoteException{
        this.server =server;
    }
    
    
    /**
     * Define el color del cliente.
     * @param color String.
     * @throws RemoteException 
     */
    @Override
    public void definirColor(String color) throws RemoteException {
        this.color = color;
        System.out.println(this.color);
    }
    
    
    /**
     * permite recuperar las serpientes registradas en el servidor.
     * @return null si no hay jugadores registrados.
     * @throws RemoteException 
     */
    
   public ArrayList<Snake> recuperarSerpientes() throws RemoteException {
       return this.server.recuperarSerpientes();
    }
   
   /**
    * Permite registrar un jugador nuevo.
    * @param nombre del jugador.
    * @throws RemoteException 
    */
   
   public void iniciarJugador(String nombre) throws RemoteException {
       this.server.iniciarJugador(this, nombre);
   }
    
   
   public void moverSerpiente (KeyCode direcion) throws RemoteException {
       this.server.moverSerpiente(direcion, this.color);
   }
   
}
