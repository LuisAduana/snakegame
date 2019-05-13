/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import javafx.scene.paint.Color;

/**
 *
 * @author ferzo
 */
public class Comida {
    private Coordenada coordenada;
    public static final Color COLOR = Color.WHITE;
    
    
    Comida(Coordenada coord){
        this.coordenada = coord;
        
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }
    
}
