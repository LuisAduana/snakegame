/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import snake.Coordenada;
import snake.Snake;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author ferzo
 */
public class PintarJuego {
    public static void pintar(Tablero tablero, GraphicsContext contexto){
        contexto.setFill(Tablero.COLOR);
        contexto.fillRect(0, 0, tablero.getAncho(), tablero.getAltura());               
    }
    public static void pintarComida(Tablero tablero, GraphicsContext contexto){
        pintarCoordenada(tablero.getComida().getCoordenada(), contexto, Comida.COLOR);
    }
    
    public static void pintarSnake(Tablero tablero, GraphicsContext contexto) {
        ArrayList<Snake> serpientes = tablero.getSnakes();

        for (Snake snake : serpientes) {
            snake.getCuerpo().forEach(coord -> 
                    pintarCoordenada(coord, contexto, snake.getColorViva()));
            
            if (!snake.estaViva()) {
                pintarCoordenada(snake.getCabeza(), contexto, Snake.MUERTA);
            }
        }

    }
    
    private static void pintarCoordenada(Coordenada coord, GraphicsContext contexto, String color){
        contexto.setFill(Color.web(color));
        contexto.fillRect(coord.getCoordX()*Tablero.TAMANO, coord.getCoordY() * Tablero.TAMANO, 
                Tablero.TAMANO, Tablero.TAMANO);
    }
    
    public static void pintarMensajeReset(GraphicsContext contexto){
        contexto.setFill(Color.RED);
        contexto.fillText("Presiona ENTER para resetar el juego", 10, 10);
        
    }
}
