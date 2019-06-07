/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.awt.Color;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author soy-y
 */
public interface ICliente extends Remote{
    
   public void iniciarSerpiente(Color color, String nombre) throws RemoteException;
   
}
