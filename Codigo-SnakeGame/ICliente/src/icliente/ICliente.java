/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icliente;

import java.rmi.Remote;
import java.rmi.RemoteException;
import snake.Snake;

/**
 *
 * @author soy-y
 */
public interface ICliente extends Remote{
    
   public void iniciarSerpiente(Snake snake) throws RemoteException;
}
