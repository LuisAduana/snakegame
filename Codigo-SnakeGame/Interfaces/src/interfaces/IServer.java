package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import javafx.scene.input.KeyCode;
import snake.Comida;
import snake.Snake;

/**
 *
 * @author Fernando
 * @author Luis Bonilla
 */
public interface IServer extends Remote{
    
    public void iniciarJugador(ICliente cliente, String nombre) throws RemoteException;
    public boolean esDisponible() throws RemoteException;
    public void moverSerpiente(KeyCode direccion, String color) throws RemoteException;
    public void eliminarSerpiente(String color) throws RemoteException;
    public List<Snake> recuperarSerpientes() throws RemoteException;
    public Comida generarComida() throws RemoteException;
}
