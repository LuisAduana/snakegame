package servidor.snake;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando
 */
public class ActualizarSerpientes implements Runnable{

    private final ServidorSnake server;
    
    public ActualizarSerpientes(ServidorSnake server) {
        this.server = server;
    }

    @Override
    public void run() {
        long limiteCiclo = 0;
        while (true) {
            System.out.flush(); // NOSONAR
            if (!this.server.getTablero().getSnakes().isEmpty()) {
                this.server.getTablero().actualizarPosicion();
                
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ActualizarSerpientes.class.getName()).log(Level.SEVERE, null, ex);
                    Thread.currentThread().interrupt();
                }
            }
            limiteCiclo ++;
            if (limiteCiclo == Integer.MIN_VALUE) {
                break;
            }
        }
    }
    
}
