/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 *
 * @author soy-y
 */
public interface ICliente extends Remote{
    

    /**
    * Método que inicializa la serpiente en el cliente.
    * @param color El tipo de color que le tocó en el servidor.
    * @throws RemoteException En caso de no lograr la conexión con éxito.
    */
    
    public void definirColor(String color) throws RemoteException;
   
   
}
