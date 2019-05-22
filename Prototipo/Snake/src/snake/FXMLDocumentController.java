/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import Utileria.Snake;
import Utileria.Point;
import interfaz.Client;
import interfaz.Server;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author soy-y
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Pane pane;
    private Cliente cliente;

    private Server server;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public GraphicsContext getContext() {
        return context;
    }

    public void setContext(GraphicsContext context) {
        this.context = context;
    }

    Canvas canvas = new Canvas(604, 442);
    GraphicsContext context;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        String nombre = "GameServer";
        String serverName = "localhost";
        int serverPort = 3232;
        try {
            Registry registro = LocateRegistry.getRegistry(serverName, serverPort);
            this.server = (Server) registro.lookup(nombre);

            this.cliente = new Cliente(this.server);

            this.cliente.iniciar();
            System.out.println("Iniciado");
            System.out.println("----------------");
            System.out.println(this.cliente.getSerpientes().size());

        } catch (NotBoundException | RemoteException ex) {
            ex.printStackTrace();
        }

        context = canvas.getGraphicsContext2D();
        Random r = new Random();
        pane.getChildren().add(canvas);

        for (Snake s : this.cliente.getSerpientes()) {

            context.setFill(Color.web(s.getColor()));
            context.fillRect(s.getCabeza().getX() * 10,
                    s.getCabeza().getY() * 10, 10, 10);
        }

        new Thread( new Loop(this)).start();
        canvas.setFocusTraversable(true);

        canvas.setOnKeyPressed(e -> {

            try {
                mover(e);
            } catch (RemoteException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    private void mover(KeyEvent e) throws RemoteException {
        iniciarMovimiento();

        System.out.println("Movimiento:");
        if (this.cliente.getSerpiente().isEnMovimiento()) {
            System.out.println("true");
        }

        switch (e.getCode()) {
            case UP:
                if (this.cliente.getSerpiente().getDireccion() != KeyCode.DOWN) {
                    System.out.println("m");
                    this.cliente.getServidor().mover(this.cliente, e.getCode());
                }
                break;
            case DOWN:
                if (this.cliente.getSerpiente().getDireccion() != KeyCode.UP) {
                    System.out.println("m");
                    this.cliente.getServidor().mover(this.cliente, e.getCode());
                }
                break;
            case LEFT:
                if (this.cliente.getSerpiente().getDireccion() != KeyCode.RIGHT) {
                    System.out.println("m");
                    this.cliente.getServidor().mover(this.cliente, e.getCode());
                }
                break;
            case RIGHT:
                if (this.cliente.getSerpiente().getDireccion() != KeyCode.LEFT) {
                    System.out.println("m");
                    this.cliente.getServidor().mover(this.cliente, e.getCode());
                }
                break;

        }

        System.out.println("------------");

        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void iniciarMovimiento() {

        if (!this.cliente.getSerpiente().isEnMovimiento()) {
            try {
                this.cliente.getServidor().iniciarMovimiento(this.cliente, true);
            } catch (RemoteException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void pintarSerpiente(Snake snake) {
        
        
        snake.getCuerpo().forEach(punto -> pintarNodo(punto, snake.getColor()));
    }

    public void pintarNodo(Point punto, String color) {
        context.setFill(Color.web(color));
        context.fillRect(punto.getX() * 10, punto.getY() * 10, 10, 10);
        System.out.println("X: " + punto.getX());
        System.out.println("Y: " + punto.getY());
    }

    public void eliminarUltimo(Snake serpiente) {
        context.setFill(Color.BLACK);
        context.fillRect(serpiente.getCuerpo().get(0).getX() * 10, serpiente.getCuerpo().get(0).getY() * 10, 10, 10);

    }

}
