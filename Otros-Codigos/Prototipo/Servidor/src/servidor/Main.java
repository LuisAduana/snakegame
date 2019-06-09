/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author soy-y
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Servidor servidor;
        try {
<<<<<<< HEAD:Otros-Codigos/Prototipo/Servidor/src/servidor/Main.java
            servidor = new Servidor();
            servidor.iniciarServidor();
=======
            ss = new ServidorSnake();
            ss.iniciarServidor();
            
            (new Thread(new ActualizarSerpientes(ss))).start();
            
>>>>>>> MovimientoSnake:Codigo-SnakeGame/Servidor-Snake/src/servidor/snake/Main.java
        } catch (RemoteException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
