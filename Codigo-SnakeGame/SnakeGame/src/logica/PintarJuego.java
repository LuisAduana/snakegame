/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import logica.Tablero;

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
        contexto.setFill(Comida.COLOR);
        pintarCoordenada(tablero.getComida().getCoordenada(), contexto);
    }
    
    public static void pintarSnake(Tablero tablero,  GraphicsContext contexto){
        Snake snake = tablero.getSnake();
        contexto.setFill((Paint)Snake.VIVA);
        snake.getCuerpo().forEach(coord -> pintarCoordenada(coord, contexto));
        if(!snake.estaViva()){
            contexto.setFill(Snake.MUERTA);
            pintarCoordenada(snake.getCabeza(), contexto);
        }
        
    }
    
    private static void pintarCoordenada(Coordenada coord, GraphicsContext contexto){
        contexto.fillRect(coord.getCoordX()*Tablero.TAMANO, coord.getCoordY() * Tablero.TAMANO, 
                Tablero.TAMANO, Tablero.TAMANO);
    }
    
    public static void pintarMensajeReset(GraphicsContext contexto){
        contexto.setFill(Color.RED);
        contexto.fillText("Presiona ENTER para resetar el juego", 10, 10);
        
    }
}
