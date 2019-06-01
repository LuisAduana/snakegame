package logica;

import snake.Coordenada;
import javafx.scene.paint.Color;

/**
 *
 * @author ferzo
 */
public class Comida {
    private Coordenada coordenada;
    public static final String COLOR = "WHITE";
    
    
    Comida(Coordenada coord){
        this.coordenada = coord;
        
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }
    
}
