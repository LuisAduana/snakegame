/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import Utileria.Comida;
import Utileria.Snake;
import javafx.scene.paint.Color;

/**
 *
 * @author soy-y
 */
public class Loop extends Thread {

    FXMLDocumentController escena;

    public Loop(FXMLDocumentController escena) {
        this.escena = escena;
    }

    public void run() {
        while (true) {
            try {
                //System.out.println("Actualizar pantalla");
                
                this.escena.context.setFill(Color.BLACK);
                this.escena.context.fillRect(0, 0, 604, 442);
                
                this.escena.pintarNodo(this.escena.getCliente().getComida().getPoint(), "RED");

                for (Snake s : this.escena.getCliente().getSerpientes()) {
                    if (s.isEnMovimiento()) {
                        this.escena.pintarSerpiente(s);
                    }

                }

                Thread.sleep(100);

            } catch (InterruptedException ex) {
                System.out.println("InterruptedException LienzoRefresh");
            }
        }
    }
}
