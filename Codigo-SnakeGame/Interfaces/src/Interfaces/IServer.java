/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iserver;

import icliente.ICliente;
import java.awt.Color;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author soy-y
 */
public interface IServer extends Remote{
    
    public void iniciarJugador(ICliente cliente, String nombre) throws RemoteException;
    public boolean esDisponible() throws RemoteException;
    public void eliminarSerpiente(String color) throws RemoteException;
}
