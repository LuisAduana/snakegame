/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.snake;

import interfaces.ICliente;
import snake.Snake;
import interfaces.IServer;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.KeyCode;
import snake.Comida;
import snake.Coordenada;
import snake.Tablero;

/**
 *
 * @author luisb
 * @author FerC
 */

public class ServidorSnake extends UnicastRemoteObject  implements IServer{
    private ArrayList<String> colores;
    private ArrayList<Snake> serpientes;
    public static final int PORT = 3232;
    private Tablero tablero;
    private static final int ANCHO_VENTANA = 500;
    private static final int ALTURA_VENTANA = 500;
    
    public ServidorSnake() throws RemoteException {
        // Constructor de la clase ServidorSnake no recibe ni hace nada.
    }
    
    /**
     * Permite registrar un jugador nuevo si colores disponibles 
     * @param cliente del jugador que es iniciado.
     * @param nombre nombre del jugador registrado.
     * @throws RemoteException 
     */
    
    @Override
    public void iniciarJugador(ICliente cliente, String nombre) throws RemoteException {
        if (!colores.isEmpty()) {
            String color = colores.get(0);
            this.colores.remove(0);
            SecureRandom random = new SecureRandom();
            Snake serpiente = new Snake(nombre, color,
                    new Coordenada(random.nextInt(Tablero.TAMANO),
                            random.nextInt(
                            Tablero.TAMANO)));
            serpientes.add(serpiente);
            actualizarTablero();
            cliente.definirColor(color);
        }

    }

    /**
     * Inicializa el sevidor.
     */
    
    private void actualizarTablero() {
        this.tablero.setSnakes(this.serpientes);
    }
    
    public void iniciarServidor() {
        this.iniciarListaDeColores();
        this.serpientes = new ArrayList();
        this.tablero = new Tablero(ANCHO_VENTANA, ALTURA_VENTANA);
        try {
            Registry registro = LocateRegistry.createRegistry(PORT);
            registro.bind("GameServer", (IServer) this);
        } catch (Exception ex) {
            Logger.getLogger(ServidorSnake.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void iniciarListaDeColores() {
        this. colores = new ArrayList();
        colores.add("CYAN");
        colores.add("PURPLE");
        colores.add("GREEN");
        colores.add("YELLOW");
        colores.add("PINK");
        colores.add("ORANGE");
        colores.add("WHITE");
        colores.add("GRAY");
        colores.add("SALMON");
        colores.add("BLUE");
        
    }

    /**
     * Permite saber si hay lugares disponibles para un jugador nuevo.
     * @return true si hay menos de 10 jugadores registrado
     * @throws RemoteException 
     */
    
    @Override
    public boolean esDisponible() throws RemoteException {
        boolean dispoibilidad = true;
        if (colores.isEmpty()) {
            dispoibilidad = false;
        } 
        return dispoibilidad;
    }

    
    
    @Override
    public void eliminarSerpiente(String color) throws RemoteException {
        for(Snake snake : this.serpientes){
            if (snake.getColorViva() == color) {
                colores.add(color);
                serpientes.remove(snake);
            }
        }
    }

    /**
     * Recupera las serpientes registradas en el servidor.
     * @return lista vacía si no se ha registrado ningun jugador.
     */
    
    @Override
    public ArrayList<Snake> recuperarSerpientes() {
        return this.serpientes;
    }

    /**
     * Recupera la dirección nueva para cambiar la dirección de una serpiente con un color determinado
     * @param direccion dirección nueva de la serpiente
     * @param color color de la serpiente que se va a mover
     * @throws RemoteException 
     */
    
    @Override
    public void moverSerpiente(KeyCode direccion, String color) throws RemoteException {
        for (Snake snake : this.serpientes) {
            if (snake.getColorViva().equalsIgnoreCase(color)) {

                switch (direccion) {

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
                    default:
                        break;

                }
            }
        }

    }

    public Tablero getTablero() {
        return this.tablero;
    }

    @Override
    public Comida generarComida() throws RemoteException {
        return this.tablero.getComida();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ServidorSnake)) {
            return false;
        } else {
            return super.equals(obj);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.colores);
        hash = 29 * hash + Objects.hashCode(this.serpientes);
        hash = 29 * hash + Objects.hashCode(this.tablero);
        return hash;
    }
    
    
    
}
