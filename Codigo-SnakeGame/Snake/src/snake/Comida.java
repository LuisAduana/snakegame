package snake;

import java.io.Serializable;


/**
 *
 * @author ferzo
 */
public class Comida implements Serializable{
    private static final long serialVersionUID = 17L;
    private Coordenada coordenada;
    public static final String COLOR = "WHITE";
    public static final int VALOR = 10;
    
    
    public Comida(Coordenada coord){
        this.coordenada = coord;
        
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }
    
}
