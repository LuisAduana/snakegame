/*
 */
package snakegame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import logica.CicloJuego;
import logica.PintarJuego;
import logica.Snake;
import logica.Tablero;

/**
 *
 * @author ferzo
 */
public class SnakeGame extends Application {
    private static  final int ANCHO = 500;
    private static final int ALTURA = 500;
    
    private CicloJuego ciclo;
    private Tablero tablero;
    private GraphicsContext contexto;

    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        Canvas canvas = new Canvas(ANCHO, ALTURA);
        contexto =  canvas.getGraphicsContext2D();
        StackPane root = new StackPane();
        
        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(e ->{
            Snake snake = tablero.getSnake();
            if(ciclo.teclaPresionada()){
                return;
            }
            switch (e.getCode()){
                case UP:
                   snake.setArriba();
                   break;
                case DOWN:
                    snake.setAbajo();
                    break;
                case LEFT:
                    snake.setIzq();
                    break;
                case RIGHT:
                    snake.setDer();
                    break;
                case ENTER: 
                    if(ciclo.estaPausado()){
                        reset();
                        (new Thread(ciclo)).start();
                    }
            }
        });
        
        reset();
        
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        
        primaryStage.setResizable(false);
        primaryStage.setTitle("Snake Game");
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.setScene(scene);
        primaryStage.show();
        
        (new Thread(ciclo)).start();
    }
    
    public void reset(){
        tablero = new Tablero(ANCHO, ALTURA);
        ciclo = new CicloJuego(tablero, contexto);
        PintarJuego.pintar(tablero, contexto);
        
        
    }
}
