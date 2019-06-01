package snake;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ferzo
 */
public class Snake implements Serializable{
    private static final long serialVersionUID = 8799656478674716638L;
    private String colorViva;
    public static final String MUERTA = "RED";
    
    private Coordenada cabeza;
    private List<Coordenada> cuerpo;
    private int longitud;
    private boolean vivo = true;
    private String nombre;
    private int avanceX;
    private int avanceY;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Snake(String nombre, String color, Coordenada cabeza){
        this.nombre = nombre;
        this.colorViva = color;
        cuerpo = new ArrayList<>();
        this.cabeza = cabeza;
        this.longitud = 1;
        cuerpo.add(cabeza);
        this.vivo = true;
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
    public void mover(Coordenada coord) {
        if (!estaParada()) {
            moverCoordenada(coord);
        }
    }
    
    public Coordenada transferirCoordenada(){
        return this.cabeza.transferirCoordenada(avanceX, avanceY);
    }

    /**
     * Hace crecer a la snake hacia la dirección en la que se dirige
     */
    public void extender(Coordenada coord) {
        if (!estaParada()) {
            crecer(coord);
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
   
   public String getColorViva() {
        return colorViva;
    }

    public void setColorViva(String colorViva) {
        this.colorViva = colorViva;
    }
   
}
