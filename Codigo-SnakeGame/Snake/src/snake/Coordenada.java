package snake;

import java.awt.*;
import java.io.Serializable;

/**
 *
 * @author ferzo
 */
public class Coordenada extends Point implements Serializable{
    private static final long serialVersionUID = 879965647867471663L;
    private int coordX;
    private int coordY;
    
    public Coordenada(){}
    
    public Coordenada(int x, int y){
        this.coordX = x;
        this.coordY = y;
    }

    public Coordenada(int nextInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * 
     * @param dx incremento en x
     * @param dy incremento en y
     * @return Regresa una nueva Coordenada con el incremento computado
     */
    public Coordenada transferirCoordenada(int dx, int dy){
        return (new Coordenada(coordX+dx, coordY+dy));
    }
    
    @Override
    public boolean equals(Object other){
        if(!(other instanceof Coordenada)) return false;
        Coordenada coord = (Coordenada) other;
        return(coordX == coord.coordX)
                && (coordY == coord.coordY);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.coordX;
        hash = 29 * hash + this.coordY;
        return hash;
    }
    
//GETTERS & SETTERS-------------------------------------------------------------
    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }
    

}
