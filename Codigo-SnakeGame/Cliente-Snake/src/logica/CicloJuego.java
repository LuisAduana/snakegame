/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import snake.Snake;
import javafx.scene.canvas.GraphicsContext;
/**
 *
 * @author ferzo
 */
public class CicloJuego implements Runnable{
    //Framerate
    private int framerate;
    private float tiempo;
    //Variable para saber si está en ejecución
    private boolean ejecucion;
    //Variable para saber si hay una tecla presionada
    private boolean teclaPresionada;
    //Variable para saber si se ha pausado el juego
    private boolean pausado;
    //El Tablero se encarga de guardar las posiciones y cambios
    private final Tablero tablero;
    //El contexto se encarga de renderizar la parte gráfica del tablero
    private final GraphicsContext contexto;
    
    private Snake serpiente;
    
    
    public CicloJuego(Tablero tablero, GraphicsContext contexto, Snake serpiente){
        this.contexto = contexto;
        this.tablero = tablero;
        framerate = 20;
        tiempo = 1000.0f / framerate;
        ejecucion = true;
        teclaPresionada = false;    
        this.serpiente = serpiente;
        
    }
    
    
    public boolean estaViva() {
        if(!this.serpiente.estaViva()){
                pausar();
                PintarJuego.pintarMensajeReset(contexto);
                return false;
            }
        return true;
    }
    
    @Override
    public void run(){
        while(ejecucion && !pausado){
            float t = System.currentTimeMillis();
            
            teclaPresionada = false;
            tablero.actualizarPosicion();
            PintarJuego.pintar(tablero, contexto);
            PintarJuego.pintarComida(tablero, contexto);
            PintarJuego.pintarSnake(tablero, contexto);
            
            
            //Si el jugador ha muerto se muestra el mensaje para reseteat
            // el juego
            
            if (estaViva()) {
                break;
            }
            
            t = System.currentTimeMillis() - t;
            
            //Fragmento para ajustar el tiempo correctamente
            if(t<tiempo){
                try{
                    Thread.sleep((long) (tiempo - t));
                    
                }catch(InterruptedException ex){
                    
                }
            }
        }
    }
            
            public void pararJuego(){
                ejecucion = false;
            }

            public boolean teclaPresionada() {
                return teclaPresionada;
            }

            public void setTeclaPresionada() {
                teclaPresionada = true;
            }

            public void reanudar() {
                pausado = false;
            }

            public void pausar() {
                pausado = true;
            }

            public boolean estaPausado() {
                return pausado;
            }

            public int getFramerate() {
                return framerate;
            }

            public void setAct(int act) {
                this.framerate = act;
            }
        
    
}
