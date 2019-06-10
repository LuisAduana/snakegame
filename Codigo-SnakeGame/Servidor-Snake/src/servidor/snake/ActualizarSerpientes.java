package servidor.snake;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidor.ConexionBD;
import servidor.ServidorSnake;
import snake.Snake;
/**
 *
 * @author Fernando
 */
public class ActualizarSerpientes implements Runnable {

    private final ServidorSnake server;
    private List<Snake> listaPuntuaciones;
    
    public ActualizarSerpientes(ServidorSnake server) {
        this.server = server;
    }

    @Override
    public void run() {
        long limiteCiclo = 0;
        while (true) {
            System.out.flush(); // NOSONAR");
            if (!server.getTablero().getSnakes().isEmpty()) {
                listaPuntuaciones = server.getTablero().calcularChoques();
                registrarPuntuaciones();
                server.getTablero().actualizarPosicion();
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

    private void registrarPuntuaciones() {
        if (!listaPuntuaciones.isEmpty()) {
            ConexionBD conexion = new ConexionBD();
            listaPuntuaciones = conexion.registrarPuntuaciones(listaPuntuaciones);
        }
    }
    
}
