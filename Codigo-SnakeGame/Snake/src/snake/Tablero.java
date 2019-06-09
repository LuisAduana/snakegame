package snake;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

/**
 * 
 * @author Fernando
 * @author Luis Bonilla
 * @author Rodrigo
 */

public class Tablero implements Serializable{
    private static final long serialVersionUID = 232L;
    public static final int TAMANO = 10;
    public static final Color COLOR = new Color(0.1, 0.1, 0.1,1);
    
    private final int columnas;
    private final int filas;
    
    private List<Snake> snakes;
    private Comida comida;

    public Tablero(double ancho, double alto){
        columnas = (int) ancho/TAMANO;
        filas = (int) alto/TAMANO;
        snakes = new ArrayList();
        comida = new Comida(getPosicionAleatoria());
    }
    
    public Coordenada wrap(Coordenada coord){
        int x = coord.getCoordX();
        int y = coord.getCoordY();
        if(x >= filas) x=0;
        if(y >= columnas) y = 0;
        if(x < 0)x = filas -1;
        if(y < 0)y = columnas -1;
        return(new Coordenada(x, y));
    }
    
    private Coordenada getPosicionAleatoria() {
        SecureRandom random = new SecureRandom();
        Coordenada coord;
        coord = new Coordenada(random.nextInt(columnas), random.nextInt(filas));
        return coord;
    }
    
    public void actualizarPosicion(){
        
        for (Snake snake : this.snakes) {
            if(comida.getCoordenada().equals(snake.getCabeza())){
            snake.extender(wrap(snake.transferirCoordenada()));
            comida.setCoordenada(getPosicionAleatoria());
        }else{
            snake.mover(wrap(snake.transferirCoordenada()));
        }
        }
        
        
    }
    
    public int getColumnas() {
        return columnas;
    }

    public int getFilas() {
        return filas;
    }

    public int getAncho() {
        return filas * TAMANO;
    }

    public int getAltura() {
        return columnas * TAMANO;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }
    
    public void setSnakes(List<Snake> serpientes) {
        snakes = serpientes;
    }

    public Comida getComida() {
        return comida;
    }
    
    public void setComida(Comida comida) {
        this.comida = comida;
    }
}
