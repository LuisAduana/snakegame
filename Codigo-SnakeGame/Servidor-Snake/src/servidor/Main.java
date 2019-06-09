package servidor;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidor.snake.ActualizarSerpientes;

/**
 * Clase que inicializa al ServidorSnake.
 * @author Fernando
 */
public class Main {
    
    /**
     * Método que inicializa la aplicación.
     * @param args the command line arguments
     */
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
