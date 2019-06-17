package controladores;


import interfaces.ICliente;
import interfaces.IServer;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Objects;
import javafx.scene.input.KeyCode;
import snake.Comida;
import snake.Snake;

/**
 *
 * @author Fernando
 * @author Luis Bonilla
 * @author Rodrigo
 */
public class ClienteSnake extends UnicastRemoteObject implements ICliente {

    private transient  IServer server;
    private String color;
    
    /**
     * Constructor de la clase ClienteSnake.
     * @param server Recibe la interfaz a implementar.
     * @throws RemoteException Excepcion en caso de que no se logre una conexión.
     */
    public ClienteSnake(IServer server) throws RemoteException {
        this.server =server;
    }
    
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public IServer getServer() {
        return server;
    }

    public void setServer(IServer server) {
        this.server = server;
    }
    
    /**
    * Define el color del cliente.
    * @param color String.
    * @throws RemoteException 
    */
    @Override
    public void definirColor(String color) throws RemoteException {
        this.color = color;
    }
    
    
    /**
    * permite recuperar las serpientes registradas en el servidor.
    * @return null si no hay jugadores registrados.
    * @throws RemoteException 
    */
    
   public List<Snake> recuperarSerpientes() throws RemoteException {
       return server.recuperarSerpientes();
    }
   
   /**
   * Permite registrar un jugador nuevo.
   * @param nombre del jugador.
   * @throws RemoteException 
   */
   
   public void iniciarJugador(String nombre) throws RemoteException {
       this.server.iniciarJugador(this, nombre);
   }
    
    public void moverSerpiente (KeyCode direcion) throws RemoteException {
        server.moverSerpiente(direcion, this.color);
    }

    
    public Comida generarComida() throws RemoteException {
       return server.generarComida();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ClienteSnake)) {
            return false;
        } else {
            return super.equals(obj);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.color);
        hash = 97 * hash + Objects.hashCode(this.server);
        return hash;
    }
    
    
   
}
