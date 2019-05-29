/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import Utileria.Snake;
import java.rmi.Remote;
import java.rmi.RemoteException;
import javafx.scene.input.KeyCode;

/**
 *
 * @author soy-y
 */
public interface Server extends Remote{
    public void iniciarMovimiento(Client serpiente, boolean enMovimiento) throws RemoteException;
    public void mover(Client serpiente, KeyCode direccion) throws RemoteException; 
    public void iniciarPartida(Client serpiente) throws RemoteException;
    
}
