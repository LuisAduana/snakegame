package logica;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

/**
 *
 * @author ferzo
 */
public class Snake {
    public static final Color VIVA = Color.GREEN;
    public static final Color MUERTA = Color.RED;
    private Tablero tablero;
    
    private Coordenada cabeza;
    private List<Coordenada> cuerpo;
    private int longitud;
    private boolean vivo = true;
    
    private int avanceX;
    private int avanceY;
    
    public Snake(Tablero tablero, Coordenada cabeza){
        cuerpo = new ArrayList<>();
        this.cabeza = cabeza;
        this.longitud = 1;
        cuerpo.add(cabeza);
        this.vivo = true;
        this.tablero = tablero;
        this.avanceX = 0;
        this.avanceY = 0;
        
    }
    /**
     * Método que elimina el punto más viejo del cuerpo de la
     * snake y añade el nuevo punto.
     * @param coord 
     */
    public void moverCoordenada(Coordenada coord){
        validarMovimiento(coord);
        cuerpo.remove(0);
    }
    /**
     * Método que incrementa el cuerpo de la serpiente en uno, es 
     * llamado después de que se ha consumido comida.
     * @param coord 
     */
    public void crecer(Coordenada coord){
        longitud++;
        validarMovimiento(coord);
    }
    /**
     * Método que valida si hay una intersección en el camino de la
     * serpiente
     * @param coord el punto al cual se esta moviendo la snake
     */
    public void validarMovimiento(Coordenada coord){
        coord = tablero.wrap(coord);
        vivo &= !cuerpo.contains(coord);
        cuerpo.add(coord);
        this.cabeza = coord;
    }
    
    /**
     * @return El cuerpo de la snake expresado en coordenadas
     */
    public List<Coordenada> getCuerpo() {
        return cuerpo;
    }

    public boolean estaViva() {
        return vivo || longitud == 1;
    }

    private boolean estaParada() {
        return avanceX == 0 & avanceY == 0;
    }

    /**
     * Mueve a la snake en 1 en la dirección que tenia.
     */
    public void mover() {
        if (!estaParada()) {
            moverCoordenada(cabeza.transferirCoordenada(avanceX, avanceY));
        }
    }

    /**
     * Hace crecer a la snake hacia la dirección en la que se dirige
     */
    public void extender() {
        if (!estaParada()) {
            crecer(cabeza.transferirCoordenada(avanceX, avanceY));
        }
    }

    public void setArriba() {
        if (avanceY == 1 && longitud > 1) return;
        avanceX = 0;
        avanceY = -1;
    }

    public void setAbajo() {
        if (avanceY == -1 && longitud > 1) return;
        avanceX = 0;
        avanceY = 1;
    }

    public void setIzq() {
        if (avanceX == 1 && longitud > 1) return;
        avanceX = -1;
        avanceY = 0;
    }

    public void setDer() {
        if (avanceX == -1 && longitud > 1) return;
        avanceX = 1;
        avanceY = 0;
    }
//GETTERS Y SETTERS-------------------------------------------------------------
   public void setCabeza(Coordenada cabeza){
       this.cabeza = cabeza;
   }
   
   public void setLongitud(int longitud){
       this.longitud = longitud;
   }
   
   public Coordenada getCabeza(){
       return this.cabeza;
   }
}
