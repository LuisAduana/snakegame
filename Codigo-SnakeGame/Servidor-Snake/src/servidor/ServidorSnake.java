/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import clases.PuntuacionObtenida;
import interfaz.ICliente;
import interfaz.IServer;
import clases.Snake;
import java.awt.Color;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luisb
 * @author FerC
 */

public class ServidorSnake extends UnicastRemoteObject implements IServer {

    private ArrayList<Color> colores;
    private ArrayList<Snake> serpientes;
    private static final long  SerialVersionUID = 9090898209349823403L;
    private final int PORT = 3232;
    
    public ServidorSnake() throws RemoteException {
        
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
            String direccion = (InetAddress.getLocalHost()).toString();
            System.out.println("Servidor iniciado en " + direccion + ":" + PORT);
            Registry registro = LocateRegistry.createRegistry(PORT);
            registro.bind("GameServer", (IServer) this);
            System.out.println("Servidor Iniciado");
        } catch (Exception ex) {
            System.out.println(ex);
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

    
    
}
