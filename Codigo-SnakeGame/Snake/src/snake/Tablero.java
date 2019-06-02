/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import snake.Comida;
import snake.Coordenada;
import snake.Snake;
import java.util.ArrayList;
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
    
    private ArrayList<Snake> snakes;
    private Comida comida;

    public Tablero(double ancho, double alto){
        this.columnas = (int) ancho/TAMANO;
        this.filas = (int) alto/TAMANO;
        snakes = new ArrayList();
        this.comida = new Comida(getPosicionAleatoria());
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

    public double getAncho() {
        return filas * TAMANO;
    }

    public double getAltura() {
        return columnas * TAMANO;
    }

    public ArrayList<Snake> getSnakes() {
        return snakes;
    }
    
    public void setSnakes(ArrayList<Snake> serpientes) {
        this.snakes = serpientes;
    }

    public Comida getComida() {
        return comida;
    }
}
