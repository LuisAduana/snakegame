/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import clases.PuntuacionObtenida;
import java.awt.Color;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author soy-y
 */
public interface IServer extends Remote {
    
    public void iniciarJugador(ICliente cliente, String nombre) throws RemoteException;
    public boolean esDisponible() throws RemoteException;
    public void eliminarSerpiente(Color color) throws RemoteException;
    public List<PuntuacionObtenida> consultarPuntuaciones() throws RemoteException;
    
}
