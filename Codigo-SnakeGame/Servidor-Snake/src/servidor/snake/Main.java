/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.snake;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author soy-y
 */
public class Main {
    
    public static void main(String[] args) {
        ServidorSnake ss;
        try {
            ss = new ServidorSnake();
            ss.iniciarServidor();
            
            (new Thread(new ActualizarSerpientes(ss))).start();
            
        } catch (RemoteException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
}
