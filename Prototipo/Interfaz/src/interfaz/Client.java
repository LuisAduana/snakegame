/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import Utileria.Comida;
import Utileria.Snake;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javafx.scene.input.KeyCode;

/**
 *
 * @author soy-y
 */
public interface Client extends Remote{
   
    // public void iniciar() throws RemoteException;
    
    // public void mover() throws RemoteException;
    
    // public void iniciarMovimiento() throws RemoteException;
    
    public void mover(ArrayList<Snake> serpientes) throws RemoteException;
    public void iniciarPartida(ArrayList<Snake> serpientes, String color) throws RemoteException;
    public void iniciarMovimiento(ArrayList<Snake> serpientes) throws RemoteException;
    public void actualizar(ArrayList<Snake> serpientes) throws RemoteException;
    public void recibirComida(Comida comida) throws RemoteException;
    
    
    
    
}
