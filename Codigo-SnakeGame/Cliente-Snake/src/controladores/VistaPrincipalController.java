package controladores;

import iserver.IServer;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;

/**
 * Clase que gestiona los eventos en la VistaPrincipal.
 * @author Luis Aduana.
 */
public class VistaPrincipalController implements Initializable {
       
    @FXML
    TextField nombreJugador;
    @FXML
    Label mensajeError;
    
    private Alert dialogo;
    private IServer server;
    
    public  ClienteSnake clienteSnake;
    
    private String validarNickname() {
        if ("".equals(nombreJugador.getText().trim())) {
            return "Ingrese un nombre";
        } else if (nombreJugador.getText().trim().length() > 12) {
            return "Nombre debe ser menor a 12 caracteres";
        } else if (nombreJugador.getText().trim().length() < 3) {
            return "Nombre es demasiado corto";
        } else {
            
            Pattern patron = Pattern.compile("^([a-z]|[A-Z]|[0-9]){3,12}$");
            Matcher match = patron.matcher(nombreJugador.getText().trim());
            if (match.find()) {
                return "";
            } else 
                return "El nombre solo puede contener minúsculas, mayúsculas y/o números";
        }
    }
    
    @FXML
    private void iniciarJuego(ActionEvent event) {
        String validacion = validarNickname();
        if ("".equals(validacion)) {
            mensajeError.setText(validacion);
            try {
                if (this.server.esDisponible()) {
                    this.server.iniciarJugador(
                            this.clienteSnake, nombreJugador.getText());
                    System.out.println("ENTRO");
                } else {
                    dialogo = new Alert(AlertType.INFORMATION);
                    dialogo.setTitle("Información del sistema.");
                    dialogo.setHeaderText(null);
                    dialogo.setContentText("La sala está llena, inténtelo más tarde");
                    dialogo.initStyle(StageStyle.UTILITY);
                    dialogo.showAndWait();
                }
            } catch (RemoteException ex) {
                dialogo = new Alert(AlertType.INFORMATION);
                dialogo.setTitle("Información del sistema.");
                dialogo.setHeaderText(null);
                dialogo.setContentText("No se ha podido lograr una conexión "
                        + "con el servidor \nInténtelo más tarde.");
                dialogo.initStyle(StageStyle.UTILITY);
                dialogo.showAndWait();
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            mensajeError.setText(validacion);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String nombre = "GameServer";
        String serverName = "localhost";
        int serverPort = 3232;
        try {
            Registry registro = LocateRegistry.getRegistry(serverName, serverPort);
            server = (IServer) registro.lookup(nombre);

            this.clienteSnake = new ClienteSnake(server);

        } catch (NotBoundException | RemoteException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
