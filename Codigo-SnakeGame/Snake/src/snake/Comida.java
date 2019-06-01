package snake;


/**
 *
 * @author ferzo
 */
public class Comida {
    private Coordenada coordenada;
    public static final String COLOR = "WHITE";
    
    
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
