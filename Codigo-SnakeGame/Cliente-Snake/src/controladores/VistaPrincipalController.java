package controladores;


import java.io.IOException;
import interfaces.IServer;
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
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import snake.PuntuacionObtenida;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.scene.layout.StackPane;
import logica.CicloJuego;
import snake.Tablero;

/**
 * Clase que gestiona los eventos en la VistaPrincipal.
 * 
 * @author Fernando.
 * @author Luis Aduana.
 * @author Rodrigo.
 */
public class VistaPrincipalController implements Initializable {
    
    private static final int ANCHO_VENTANA = 500;
    private static final int ALTURA_VENTANA = 500;
    private Stage stageActual;
    private CicloJuego ciclo;
    private GraphicsContext contexto;
    
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
    private IServer server;
    private VistaPrincipalController vistaPrincipalControler;
    private List<PuntuacionObtenida> puntuaciones;
    private  ClienteSnake clienteSnake;
    public static final String MENSAJEINFO = "Información del sistema.";
    
    /**
     * Método que controla el evento del botón de la interfaz al presionarse.
     * @param event Evento del boton presionado
     */
    @FXML
    private void iniciarJuego(ActionEvent event) {
        String validacion = validarNickname();
        if ("".equals(validacion)) {
            mensajeError.setText(validacion);
            try {
                intentoConexion();
                if (server.esDisponible()) {
                    this.clienteSnake.iniciarJugador(nombreJugador.getText());
                    iniciarJuego();
                } else {
                    Alert dialogo = new Alert(AlertType.INFORMATION);
                    dialogo.setTitle(MENSAJEINFO);
                    dialogo.setHeaderText(null);
                    dialogo.setContentText("La sala está llena, inténtelo más tarde");
                    dialogo.initStyle(StageStyle.UTILITY);
                    dialogo.showAndWait();
                }
            } catch (NotBoundException | RemoteException ex) {
                Alert dialogo = new Alert(AlertType.INFORMATION);
                dialogo.setTitle(MENSAJEINFO);
                dialogo.setHeaderText(null);
                dialogo.setContentText("No se ha podido lograr una conexión "
                        + "con el servidor \nInténtelo más tarde.");
                dialogo.initStyle(StageStyle.UTILITY);
                dialogo.showAndWait();
                Logger.getLogger(VistaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            mensajeError.setText(validacion);
        }
    }
    
    /**
     * Método que maneja el evento al pulsar el botón ver puntuaciones.
     * @param event Evento de ActionEvent
     **/
    
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
        } catch (IOException ex) {
            informacionSistema(MENSAJE_ERROR_CONEXION);
            Logger.getLogger(VistaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void prepararConexion() throws RemoteException {
        try {
            
            Registry registro = LocateRegistry.getRegistry(NOMBRE_SERVER, PUERTO_SERVER);
            server = (IServer) registro.lookup(NOMBRE_REGISTRO);

        } catch (NotBoundException ex) {
            informacionSistema(MENSAJE_ERROR_CONEXION);
            Logger.getLogger(VistaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
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
        Alert dialogo = new Alert(AlertType.INFORMATION);
        dialogo.setTitle(MENSAJEINFO);
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
    
    
    
    private void iniciarJuego() {
        Canvas canvas = new Canvas(ANCHO_VENTANA, ALTURA_VENTANA);
        contexto = canvas.getGraphicsContext2D();
        StackPane root = new StackPane();
        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(e ->{
           
            try {
                    this.clienteSnake.moverSerpiente(e.getCode());
                } catch (RemoteException ex) {
                    Logger.getLogger(VistaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
        });
        
        try {
            resetGame();
        } catch (RemoteException ex) {
            Logger.getLogger(VistaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        
        stageActual.setTitle("Snake Game");
        stageActual.setOnCloseRequest(e -> System.exit(0));
        stageActual.setScene(scene);
        
        (new Thread(ciclo)).start();
    }
    
    /**
     * Este método intenta realizar la conexión al servidor.
     * @throws RemoteException Si no se logra conectar al servidor lanza la excepción al método padre.
     * @throws NotBoundException Si no se encuentra el puerto disponible lanza la excepción al méotodo padre.
     */
    
    private void intentoConexion() throws RemoteException, NotBoundException {
            Registry registro = LocateRegistry.getRegistry(NOMBRE_SERVER, PUERTO_SERVER);
            server = (IServer) registro.lookup(NOMBRE_REGISTRO);

            this.clienteSnake = new ClienteSnake(server);

    }
    

    private void resetGame() throws RemoteException{
        Tablero tablero = new Tablero(ANCHO_VENTANA, ALTURA_VENTANA);
        tablero.setSnakes(clienteSnake.recuperarSerpientes());
        ciclo = new CicloJuego(tablero, contexto, this.clienteSnake);     
    }
    
    void setStage(Stage stage) {
        this.stageActual = stage;
    }
}
