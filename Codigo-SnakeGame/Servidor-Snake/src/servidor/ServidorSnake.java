package servidor;

import clases.PuntuacionObtenida;
import interfaz.ICliente;
import interfaz.IServer;
import clases.Snake;
import java.awt.Color;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que implementa la interfaz y los eventos dentro del juego.
 * @author Luis Bonilla
 * @author Fernando
 */

public class ServidorSnake extends UnicastRemoteObject implements IServer {

    private ArrayList<Color> colores;
    private ArrayList<Snake> serpientes;
    private static final long  serialVersionUID = 9090898209349823403L;
    private static final int PORT = 3232;
    
    public ServidorSnake() throws RemoteException {
        // Constructor de la clase ServidorSnake no recibe ni hace nada.
    }
    
    @Override
    public void iniciarJugador(ICliente cliente, String nombre) throws RemoteException {
        Color color = colores.get(0);
        colores.remove(0);
        serpientes.add(new Snake(color, cliente, nombre));
        cliente.iniciarSerpiente(color, nombre);        
    }
    
    public void iniciarServidor() {
        this.iniciarListaDeColores();
        this.serpientes = new ArrayList();
        try {
            Registry registro = LocateRegistry.createRegistry(PORT);
            registro.bind("GameServer", (IServer) this);
        } catch (Exception ex) {
            Logger.getLogger(ServidorSnake.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    private void iniciarListaDeColores() {
        this. colores = new ArrayList();
        colores.add(Color.red);
        colores.add(Color.blue);
        colores.add(Color.green);
        colores.add(Color.yellow);
        colores.add(Color.pink);
        colores.add(Color.black);
        colores.add(Color.cyan);
        colores.add(Color.orange);
        colores.add(Color.white);
        colores.add(Color.gray);
    }

    @Override
    public boolean esDisponible() throws RemoteException {
        boolean dispoibilidad = true;
        if (colores.isEmpty()) {
            dispoibilidad = false;
        } 
        return dispoibilidad;
    }

    @Override
    public void eliminarSerpiente(Color color) throws RemoteException {
        for(Snake snake : this.serpientes){
            if (snake.getColor() == color) {
                colores.add(color);
                serpientes.remove(snake);
            }
        }
    }

    @Override
    public List<PuntuacionObtenida> consultarPuntuaciones() throws RemoteException {
        ConexionBD conexion = new ConexionBD();
        return conexion.consultarPuntiaciones();
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
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.colores);
        hash = 37 * hash + Objects.hashCode(this.serpientes);
        return hash;
    }

    
    
}
