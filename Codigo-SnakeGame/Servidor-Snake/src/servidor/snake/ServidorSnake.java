/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.snake;

import icliente.ICliente;
import snake.Snake;
import iserver.IServer;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import snake.Coordenada;

/**
 *
 * @author luisb
 * @author FerC
 */

public class ServidorSnake extends UnicastRemoteObject  implements IServer{

    private ArrayList<String> colores;
    private ArrayList<Snake> serpientes;
    private static final long  SerialVersionUID = 9090898209349823403L;
    private final int PORT = 3232;
    
    public ServidorSnake() throws RemoteException {
        
    }
    
    @Override
    public void iniciarJugador(ICliente cliente, String nombre) throws RemoteException {
        String color = colores.get(0);
        colores.remove(0);
        Snake serpiente =  new Snake(color, nombre, 
                new Coordenada((int) (Math.random() * 10), (int) (Math.random() * 10)));
        serpientes.add(serpiente);
        cliente.iniciarSerpiente(serpiente);        
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
        colores.add("CYAN");
        colores.add("BLUE");
        colores.add("GREEN");
        colores.add("YELLOW");
        colores.add("PINK");
        colores.add("ORANGE");
        colores.add("WHITE");
        colores.add("GRAY");
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
    public void eliminarSerpiente(String color) throws RemoteException {
        for(Snake snake : this.serpientes){
            if (snake.getColorViva() == color) {
                colores.add(color);
                serpientes.remove(snake);
            }
        }
    }

    
    
}
