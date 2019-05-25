package logica;

/**
 *
 * @author ferzo
 */
public class Coordenada {
    private int coordX;
    private int coordY;
    
    public Coordenada(){}
    
    public Coordenada(int x, int y){
        this.coordX = x;
        this.coordY = y;
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
        return coordX == coord.coordX & coordY == coord.coordY;
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
