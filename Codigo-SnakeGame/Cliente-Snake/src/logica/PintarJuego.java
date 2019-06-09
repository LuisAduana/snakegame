package logica;

import snake.Tablero;
import snake.Comida;
import snake.Coordenada;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Fernando.
 * @author Luis Bonilla.
 */

public class PintarJuego {
        
    private PintarJuego() {
        throw new IllegalStateException();
    }
    
    
    /**
     * Pinta el tablero del juego.
     * @param tablero
     * @param contexto 
     */
    public static void pintar(Tablero tablero, GraphicsContext contexto){
        contexto.setFill(Tablero.COLOR);
        contexto.fillRect(0, 0, tablero.getAncho(), tablero.getAltura());               
    }
    
    /**
     * Pinta la comida en el tablero.
     * @param tablero
     * @param contexto 
     */
    public static void pintarComida(Tablero tablero, GraphicsContext contexto){
        
        pintarCoordenada(tablero.getComida().getCoordenada(), contexto, Comida.COLOR);
    }
    
    /**
     * Pinta cada una de las serpiente en el tablero.
     * @param tablero
     * @param contexto 
     */
    
    public static void pintarSnake(Tablero tablero, GraphicsContext contexto) {
        tablero.getSnakes().forEach(snake -> 
            snake.getCuerpo().forEach(coord -> 
                    pintarCoordenada(coord, contexto, snake.getColorViva()))
            );

    }
    
    /**
     * pinta una coordenada en el tablero.
     * @param coord
     * @param contexto
     * @param color 
     */
    
    private static void pintarCoordenada(Coordenada coord, GraphicsContext contexto, String color){
        contexto.setFill(Color.web(color));
        contexto.fillRect(coord.getCoordX()*Tablero.TAMANO, coord.getCoordY() * Tablero.TAMANO, 
                Tablero.TAMANO, Tablero.TAMANO);
    }
    
}
