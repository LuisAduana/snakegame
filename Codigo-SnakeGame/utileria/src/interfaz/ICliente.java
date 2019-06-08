package interfaz;

import java.awt.Color;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz del cliente.
 * 
 * @author Fernando.
 * @author Luis Bonilla.
 */
public interface ICliente extends Remote{
   
   /**
    * Método que inicializa la serpiente en el cliente.
    * @param color El tipo de color que le tocó en el servidor.
    * @param nombre El nombre dado en el servidor.
    * @throws RemoteException En caso de no lograr la conexión con éxito.
    */
   public void iniciarSerpiente(Color color, String nombre) throws RemoteException;
   
}
