/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import Utileria.Comida;
import Utileria.Point;
import Utileria.Snake;
import interfaz.Client;
import interfaz.Server;
import java.awt.Color;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.input.KeyCode;

/**
 *
 * @author soy-y
 */
public class Servidor extends UnicastRemoteObject implements Server {

    private static final long SerialVersionUID = 9090898209349823403L;
    private final int PORT = 3232;
    private ArrayList<Snake> serpientes;
    private ArrayList<String> colores;
    private Comida comida;

    public Servidor() throws RemoteException {

    }

    public void iniciarServidor() {

        this.colores = new ArrayList();
        colores.add("WHITE");
        colores.add("SILVER");
        colores.add("GRAY");
        colores.add("RED");
        colores.add("YELLOW");
        this.serpientes = new ArrayList();
        comida = new Comida(getFreeRandomPosition());

        try {
            String direccion = (InetAddress.getLocalHost()).toString();
            System.out.println("Servidor iniciado en " + direccion + ":" + PORT);
            Registry registro = LocateRegistry.createRegistry(PORT);
            registro.bind("GameServer", (Server) this);
            System.out.println("Servidor Iniciado");
            System.out.println("Comida creada en posicion X : " + comida.getPoint().getX() + " Y: " + comida.getPoint().getY());
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private Point getFreeRandomPosition() {
        boolean posicionOcupada = false;
        Point point = null;
        Random aleatorio = null;
        
        if (this.serpientes.isEmpty()) {
            aleatorio = new Random(System.currentTimeMillis());
            point = new Point(aleatorio.nextInt(604 / 10), aleatorio.nextInt(442 / 10));
            return point;
        }
        
        do {
            aleatorio = new Random(System.currentTimeMillis());
            point = new Point(aleatorio.nextInt(604 / 10), aleatorio.nextInt(442 / 10));

            for (Snake s : this.serpientes) {
                if (s.getCabeza() == point || comida.getPoint() == point) {
                    posicionOcupada = false;
                    break;
                } else {
                    posicionOcupada = true;
                }
            }

        } while (!posicionOcupada);

        return point;
    }

    @Override
    public void mover(Client serpiente, KeyCode direccion) throws RemoteException {

        for (Snake s : this.serpientes) {
            if (s.getCliente().equals(serpiente)) {
                s.Mover(direccion);
                s.actualizarPosicion();
            }

        }
        for (Snake s : this.serpientes) {
            s.getCliente().mover(this.serpientes);
            System.out.println("Cliente: " + s.getColor());

        }
    }

    @Override
    public void iniciarPartida(Client serpiente) throws RemoteException {
        // Random aleatorio = new Random(System.currentTimeMillis());
        this.serpientes.add(new Snake(
                //new Point(aleatorio.nextInt(604/10),aleatorio.nextInt(442/10)), 
                getFreeRandomPosition(),
                this.colores.get(0).toString(), serpiente));
        serpiente.iniciarPartida(this.serpientes, this.colores.get(0));
        this.colores.remove(0);
        System.out.println("ENVIANDO....");
        serpiente.recibirComida(comida);

    }

    @Override
    public void iniciarMovimiento(Client serpiente, boolean enMovimiento) throws RemoteException {

        for (Snake s : this.serpientes) {
            if (s.getCliente().equals(serpiente)) {
                s.setEnMovimiento(enMovimiento);
            }

        }

        serpiente.iniciarMovimiento(this.serpientes);
    }

}
