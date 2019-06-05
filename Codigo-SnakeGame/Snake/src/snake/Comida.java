package snake;

import java.awt.*;


/**
 *
 * @author ferzo
 */
public class Comida {
    private Point coordenada;
    public static final String COLOR = "WHITE";
    
    
    public Comida(Point coord){
        this.coordenada = coord;
        
    }

    public Point getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Point coordenada) {
        this.coordenada = coordenada;
    }
    
}
