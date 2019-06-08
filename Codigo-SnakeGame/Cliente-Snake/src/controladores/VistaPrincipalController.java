package controladores;

import interfaz.IServer;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import clases.PuntuacionObtenida;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;

/**
 * Clase que gestiona los eventos en la VistaPrincipal.
 * 
 * @author Fernando.
 * @author Luis Aduana.
 * @author Rodrigo.
 */
public class VistaPrincipalController implements Initializable {
       
    @FXML
    TextField nombreJugador;
    @FXML
    Label mensajeError;
    @FXML
    Button btnVerPuntuaciones;
    
    private static final String NOMBRE_REGISTRO = "GameServer";
    private static final String NOMBRE_SERVER = "localhost";
    private static final int PUERTO_SERVER = 3232;
    private static final String MENSAJE_ERROR_CONEXION = "No se ha podido "
            + "lograr una conexión con el servidor \nInténtelo más tarde.";
    private Registry registro;
    private Alert dialogo;
    private IServer server;
    private VistaPrincipalController vistaPrincipalControler;
    private ClienteSnake clienteSnake;
    private List<PuntuacionObtenida> puntuaciones;
    
    /**
     * Método que maneja el evento al pulsar el botón iniciar juego.
     * @param event Evento de ActionEvent
     */
    @FXML
    private void iniciarJuego(ActionEvent event) {
        String validacion = validarNickname();
        if ("".equals(validacion)) {
            mensajeError.setText(validacion);
            try {
                prepararConexion();
                if (server.esDisponible()) {
                    clienteSnake = new ClienteSnake(server);
                    server.iniciarJugador(clienteSnake, nombreJugador.getText());
                } else {
                    informacionSistema("La sala está llena, inténtelo más tarde");
                }
            } catch (RemoteException ex) {
                informacionSistema(MENSAJE_ERROR_CONEXION);
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            mensajeError.setText(validacion);
        }
    }
    
    /**
     * Método que maneja el evento al pulsar el botón ver puntuaciones.
     * @param event Evento de ActionEvent
     */
    @FXML
    private void verPuntuaciones(ActionEvent event) {
        try {
            
            prepararConexion();
            puntuaciones = server.consultarPuntuaciones();
            
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = (AnchorPane)loader.load(getClass().getResource("/vistas/TablaPuntuaciones.fxml").openStream());
            
            TablaPuntuacionesController puntuacionesController = (TablaPuntuacionesController) loader.getController();
            puntuacionesController.setListaPuntuaciones(vistaPrincipalControler);
                     
            Stage stageTablaPuntuaciones = new Stage();
            Scene scene = new Scene(root);
            stageTablaPuntuaciones.setScene(scene);
            stageTablaPuntuaciones.alwaysOnTopProperty();
            stageTablaPuntuaciones.setResizable(false);
            stageTablaPuntuaciones.initModality(Modality.APPLICATION_MODAL);
                
            stageTablaPuntuaciones.show();
        } catch (RemoteException ex) {
            informacionSistema(MENSAJE_ERROR_CONEXION);
            Logger.getLogger(VistaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            informacionSistema(MENSAJE_ERROR_CONEXION);
            Logger.getLogger(VistaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void prepararConexion() throws RemoteException {
        try {
            
            registro = LocateRegistry.getRegistry(NOMBRE_SERVER, PUERTO_SERVER);
            server = (IServer) registro.lookup(NOMBRE_REGISTRO);

        } catch (NotBoundException ex) {
            informacionSistema(MENSAJE_ERROR_CONEXION);
            Logger.getLogger(VistaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Método que valida el nombre del usuario.
     * @return Regresa el resultado de la validacion (Si la validación es correcta regresa una cadena vacía)
     */
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
    
    /**
     * Este método despliega un mensaje de información del sistema.
     * @param informacionMensajeError Mensaje que se muestra en la información del dialogo.
     */
    private void informacionSistema(String informacionMensajeError) {
        dialogo = new Alert(AlertType.INFORMATION);
        dialogo.setTitle("Información del sistema.");
        dialogo.setHeaderText(null);
        dialogo.setContentText(informacionMensajeError);
        dialogo.initStyle(StageStyle.UTILITY);
        dialogo.showAndWait();
    }
    
    public List<PuntuacionObtenida> getPuntuaciones() {
        return puntuaciones;
    }
    
    /**
     * 
     * @param url
     * @param resourceBundle 
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vistaPrincipalControler = this;
    }    
    
}
