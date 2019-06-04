/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.snake;

import java.util.ArrayList;
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
            System.out.println(this.server.getTablero().getSnakes().size());
            if (this.server.getTablero().getSnakes().size() >= 1 ) {
                this.server.getTablero().actualizarPosicion();
                System.out.println("actualizar");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ActualizarSerpientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }
    
}
