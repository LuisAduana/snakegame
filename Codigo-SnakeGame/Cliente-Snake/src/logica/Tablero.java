/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.Random;
import javafx.scene.paint.Color;

/**
 *
 * @author ferzo
 */
public class Tablero {
    public static final int TAMANO = 10;
    public static final Color COLOR = new Color(0.1, 0.1, 0.1,1);
    
    private final int columnas;
    private final int filas;
    
    private Snake snake;
    private Comida comida;

    public Tablero(double ancho, double alto){
        this.columnas = (int) ancho/TAMANO;
        this.filas = (int) alto/TAMANO;
        
        snake = new Snake(this, new Coordenada(columnas/2, filas/2));
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
    
    private Coordenada getPosicionAleatoria(){
        Random random = new Random();
        Coordenada coord;
        
        coord =  new Coordenada(random.nextInt(columnas), random.nextInt(filas));
        
        return coord;
    }
    
    public void actualizarPosicion(){
        if(comida.getCoordenada().equals(snake.getCabeza())){
            snake.extender();
            comida.setCoordenada(getPosicionAleatoria());
        }else{
            snake.mover();
        }
    }
    
    public int getColumnas() {
        return columnas;
    }

    public int getFilas() {
        return filas;
    }

    public double getAncho() {
        return filas * TAMANO;
    }

    public double getAltura() {
        return columnas * TAMANO;
    }

    public Snake getSnake() {
        return snake;
    }

    public Comida getComida() {
        return comida;
    }
}
