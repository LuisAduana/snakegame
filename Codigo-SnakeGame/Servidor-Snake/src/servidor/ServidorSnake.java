  package servidor;

import snake.PuntuacionObtenida;
import interfaces.ICliente;
import snake.PuntuacionObtenida;
import snake.Snake;
import interfaces.IServer;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.security.SecureRandom;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.KeyCode;
import snake.Comida;
import snake.Coordenada;
import snake.Tablero;

/**
 * Clase que implementa la interfaz y los eventos dentro del juego.
 *
 * @author Luis Bonilla
 * @author Fernando
 */

public class ServidorSnake extends UnicastRemoteObject  implements IServer {
    protected ArrayList<String> colores;
    private ArrayList<Snake> serpientes;
  public ArrayList<Snake> getSerpientes() {
    return this.serpientes;
  }
    
    
    public static final int PORT = 3232;
    private static Coordenada puntoPosible;
    private Snake jugador;
    private String colorSerpienteNueva;
    private  Tablero tablero;
    private static final int ANCHO_VENTANA = 500;
    private static final int ALTURA_VENTANA = 500;
    private static final int CABEZA = 0;

    public ServidorSnake() throws RemoteException {
        // Constructor de la clase ServidorSnake no recibe ni hace nada.
    }

 
  private void eliminaSerpiente(Snake serpiente){
    colores.add(serpiente.getColorViva());
                this.serpientes.remove(serpiente);
  }
  
  private Snake obtieneSerpiente(String colorSerpiente){
    for (Snake snake : this.serpientes) {
            if (snake.getColorViva().equalsIgnoreCase(colorSerpiente)) {
                return snake;
            }
  }
    return null;
  }

 
    /**
     * Permite registrar un jugador nuevo si colores disponibles
     *
     * @param cliente del jugador que es iniciado.
     * @param nombre nombre del jugador registrado.
     * @throws RemoteException
     */
  
  @Override
  public void iniciarJugador(ICliente cliente, String nombre) throws RemoteException {
      jugador = new Snake(nombre, colorSerpiente(), posicionValida());
      serpientes.add(jugador);
      actualizarTablero();
      cliente.definirColor(colorSerpienteNueva);
  }
  
  private Coordenada posicionValida(){
    if (getCoordenadasLibres() != null) {
      for (Coordenada punto : getCoordenadasLibres()) {
        puntoPosible = creacionPosicionSnake();
        if (!puntoPosible.equals(punto.getLocation())) {
          return puntoPosible;
        }
      }
    }
    return creacionPosicionSnake();
  }
  
  private String colorSerpiente(){
    colorSerpienteNueva = colores.get(CABEZA);
    this.colores.remove(CABEZA);
    return colorSerpienteNueva;
  }
  
  private Coordenada creacionPosicionSnake(){
    SecureRandom random = new SecureRandom();
    Coordenada puntoCreacionSerpiente = new Coordenada();
    puntoCreacionSerpiente.setCoordY(random.nextInt(Tablero.columnas));
    puntoCreacionSerpiente.setCoordX(random.nextInt(Tablero.filas));
    return puntoCreacionSerpiente;
  } 
    
  private List<Coordenada> getCoordenadasLibres() {
    List<Coordenada> coordenadasLibres = new ArrayList();
    if (!serpientes.isEmpty()) {
      for (Snake serpiente : serpientes) {
        for (Coordenada punto : serpiente.getCuerpo()) {
          coordenadasLibres.add(punto);
        }
      }
      return coordenadasLibres;
    }
    return null;
  }
    
    
    /**
     * Inicializa el sevidor.
     */
    private void actualizarTablero() {
        this.tablero.setSnakes(this.serpientes);
    }

    /**
     * Inicializa el servidor
     */
    protected void iniciarServidor() {
        this.iniciarListaDeColores();
        this.serpientes = new ArrayList();
        this.tablero = new Tablero(ANCHO_VENTANA, ALTURA_VENTANA);
        try {
            Registry registro = LocateRegistry.createRegistry(PORT);
            registro.bind("GameServer", (IServer) this);
        } catch (AlreadyBoundException | RemoteException ex) {
            Logger.getLogger(ServidorSnake.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void iniciarListaDeColores() {
        this.colores = new ArrayList();
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
        Collections.shuffle(colores);
    }

    /**
     * Permite saber si hay lugares disponibles para un jugador nuevo.
     *
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

    /**
     * Elimina la serpiente del servidor.
     *
     * @param color color de la serpiente a eliminar
     * @throws RemoteException
     */
    @Override
  public void eliminarSerpiente(String color) throws RemoteException {
    Snake serpienteEliminar = encontrarSerpienteEliminar(color);
    if (serpienteEliminar != null) {
      try {
        serpientes.remove(serpienteEliminar);
      } catch (ConcurrentModificationException ex) {
        serpientes.remove(serpienteEliminar);
        Logger.getLogger(ServidorSnake.class.getName()).log(Level.SEVERE, null, ex);
      } finally {
        colores.add(color);
        serpientes.remove(serpienteEliminar);
      }
    }
  }
        
  private Snake encontrarSerpienteEliminar(String color) {
    for (Snake serpiente : this.serpientes) {
      if (serpiente.getColorViva().equals(color)) {
        return serpiente;
      }
    }
    return null;
  }

    /**
     * Consulta el historial de puntuaciones.
     *
     * @return regresa una lista con todas las puntuaciones guardadas.
     * @throws RemoteException
     */
    @Override
    public List<PuntuacionObtenida> consultarPuntuaciones() throws RemoteException {
        ConexionBD conexion = new ConexionBD();
        return conexion.consultarPuntiaciones();
    }

    /**
     * Recupera las serpientes registradas en el servidor.
     *
     * @return lista vacía si no se ha registrado ningun jugador.
     */
    @Override
    public ArrayList<Snake> recuperarSerpientes() {
        return this.serpientes;
    }

    /**
     * Recupera la dirección nueva para cambiar la dirección de una serpiente
     * con un color determinado
     *
     * @param direccion dirección nueva de la serpiente
     * @param color color de la serpiente que se va a mover
     * @throws RemoteException
     */
    @Override
    public KeyCode moverSerpiente(KeyCode direccion, String color) throws RemoteException {
        for (Snake snake : this.serpientes) {
            if (snake.getColorViva().equalsIgnoreCase(color)) {
                switch (direccion) {

                    case UP:
                        snake.setArriba();
                        return direccion;
                    case DOWN:
                        snake.setAbajo();
                        return direccion;
                    case LEFT:
                        snake.setIzq();
                        return direccion;
                    case RIGHT:
                        snake.setDer();
                        return direccion;
                    default:
                        return direccion;

                }
            }
        }
        return direccion;
    }

    public Tablero getTablero() {
        return this.tablero;
    }
    
    public ArrayList<String> getColores() {
    return this.colores;
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

    public void recuperarColores(ArrayList<Snake> serpientes) { 
        for (Snake s : serpientes) {
            this.colores.add(s.getColorViva());
        }
    }
            
}
