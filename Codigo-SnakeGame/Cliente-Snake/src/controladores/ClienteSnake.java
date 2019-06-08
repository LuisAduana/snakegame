package controladores;

import clases.Snake;
import interfaz.ICliente;
import interfaz.IServer;
import java.awt.Color;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


/**
 * Clase encargada de manejar los atributos de la Serpiente del cliente e implementa los métdos de la interfaz de usuario.
 * 
 * @author Fernando.
 * @author Luis Bonilla.
 */
public class ClienteSnake extends UnicastRemoteObject implements ICliente {
    
    private Snake serpiente;
    private static final long  SerialVersionUID = 9090898209349823403L;
    private final int PORT = 3232;
    public  IServer server;
    
    /**
     * Constructor de la clase ClienteSnake.
     * @param server Recibe la interfaz a implementar.
     * @throws RemoteException Excepcion en caso de que no se logre una conexión.
     */
    ClienteSnake(IServer server) throws RemoteException{
        this.server =server;
    }

    public Snake getSerpiente() {
        return serpiente;
    }

    public void setSerpiente(Snake serpiente) {
        this.serpiente = serpiente;
    }

    public IServer getServer() {
        return server;
    }

    public void setServer(IServer server) {
        this.server = server;
    }

    @Override
    public void iniciarSerpiente(Color color, String nombre) throws RemoteException {
        this.serpiente = new Snake(color, this, nombre);
        System.out.println(color);
    }
    
}
