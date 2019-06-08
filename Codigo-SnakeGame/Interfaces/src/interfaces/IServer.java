/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;



import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javafx.scene.input.KeyCode;
import snake.Comida;
import snake.Snake;

/**
 *
 * @author soy-y
 */
public interface IServer extends Remote{
    
    public void iniciarJugador(ICliente cliente, String nombre) throws RemoteException;
    public boolean esDisponible() throws RemoteException;
    public void moverSerpiente(KeyCode direccion, String color) throws RemoteException;
    public void eliminarSerpiente(String color) throws RemoteException;
    public ArrayList<Snake> recuperarSerpientes() throws RemoteException;
    public Comida generarComida() throws RemoteException;
}
