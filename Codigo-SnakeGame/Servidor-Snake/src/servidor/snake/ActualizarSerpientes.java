/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.snake;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author soy-y
 */
public class ActualizarSerpientes implements Runnable{

    private ServidorSnake server;

    
    public ActualizarSerpientes(ServidorSnake server) {
        this.server = server;
    }
    
    
    
    
    @Override
    public void run() {
        while (true) {
            System.out.println("Serpientes: ");
            System.out.println(this.server.getTablero().getSnakes().size());

            System.out.println("");
            if (!this.server.getTablero().getSnakes().isEmpty()) {
                this.server.getTablero().actualizarPosicion();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ActualizarSerpientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }
    
}
