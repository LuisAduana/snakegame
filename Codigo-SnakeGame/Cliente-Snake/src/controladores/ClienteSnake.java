/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import interfaz.ICliente;
import interfaz.IServer;
import java.awt.Color;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import interfaz.Snake;

/**
 *
 * @author soy-y
 */
public class ClienteSnake extends UnicastRemoteObject implements ICliente {
    
    private Snake serpiente;
    private static final long  SerialVersionUID = 9090898209349823403L;
    private final int PORT = 3232;
    public  IServer server;

    public Snake getSerpiente() {
        return serpiente;
    }

    public void setSerpiente(Snake serpiente) {
        this.serpiente = serpiente;
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
    
    @Override
    public void iniciarSerpiente(Color color, String nombre) throws RemoteException {
        this.serpiente = new Snake(color, this, nombre);
        System.out.println(color);
    }
    
}
