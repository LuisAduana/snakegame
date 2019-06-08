package interfaz;

import clases.PuntuacionObtenida;
import java.awt.Color;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Interfaz del servidor.
 * 
 * @author Fernando.
 * @author Luis Bonilla.
 */
public interface IServer extends Remote {
    
    /**
     * Método que iniciar al cliente en el servidor.
     * @param cliente Interfaz del cliente.
     * @param nombre Nombre del cliente.
     * @throws RemoteException En caso de no lograr la conexión con éxito.
     */
    public void iniciarJugador(ICliente cliente, String nombre) throws RemoteException;
    
    /**
     * Consulta en el servidor si existe un espacio libre en el juego.
     * @return True si existe un espacio libre, false de lo contrario.
     * @throws RemoteException En caso de no lograr la conexión con éxito.
     */
    public boolean esDisponible() throws RemoteException;
    
    /**
     * Se elimina la serpiente del servidor.
     * @param color Envía el color de la serpiente para liberar el espacio del color.
     * @throws RemoteException En caso de no lograr la conexión con éxito.
     */
    public void eliminarSerpiente(Color color) throws RemoteException;
    
    /**
     * Consulta las puntuaciones en la base de datos del servidor.
     * @return List<PuntuacionObtenida> Regresa una lista de puntuaciones obtenidas en el servidor.
     * @throws RemoteException En caso de no lograr la conexión con éxito.
     */
    public List<PuntuacionObtenida> consultarPuntuaciones() throws RemoteException;
    
}
