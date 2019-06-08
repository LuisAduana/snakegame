/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import snake.Comida;


/**
 *
 * @author soy-y
 */
public interface ICliente extends Remote{
    
   public void definirColor(String color) throws RemoteException;
   
}
