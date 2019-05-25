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
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logica.CicloJuego;
import logica.PintarJuego;
import logica.Snake;
import logica.Tablero;

/**
 * Clase que gestiona los eventos en la VistaPrincipal.
 * @author Luis Aduana.
 */
public class VistaPrincipalController implements Initializable {
    
    private static final int ANCHO_VENTANA = 500;
    private static final int ALTURA_VENTANA = 500;
    private static final String NOMBRE_REGISTRO = "GameServer";
    private static final String NOMBRE_SERVIDOR = "localhost";
    private static final int PUERTO_SERVIDOR = 3232;
    private Registry registro;
    private Stage stageActual;
    private CicloJuego ciclo;
    private Tablero tablero;
    private GraphicsContext contexto;
    
    @FXML
    TextField nombreJugador;
    @FXML
    Label mensajeError;
    
    private Alert dialogo;
    private IServer server;
    
    private  ClienteSnake clienteSnake;
    
    /**
     * Valida el nombre de usuario antes de ser enviado al servidor para registrarse.
     * @return 
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
                    server.iniciarJugador(
                            clienteSnake, nombreJugador.getText());
                    iniciarJuego();
                    // System.out.println("ENTRO");
                } else {
                    dialogo = new Alert(AlertType.INFORMATION);
                    dialogo.setTitle("Información del sistema.");
                    dialogo.setHeaderText(null);
                    dialogo.setContentText("La sala está llena, inténtelo más tarde");
                    dialogo.initStyle(StageStyle.UTILITY);
                    dialogo.showAndWait();
                }
            } catch (NotBoundException | RemoteException ex) {
                dialogo = new Alert(AlertType.INFORMATION);
                dialogo.setTitle("Información del sistema.");
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
    
    private void iniciarJuego() {
        Canvas canvas = new Canvas(ANCHO_VENTANA, ALTURA_VENTANA);
        contexto = canvas.getGraphicsContext2D();
        StackPane root = new StackPane();
        
        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(e ->{
            Snake snake = tablero.getSnake();
            if(ciclo.teclaPresionada()){
                return;
            }
            switch (e.getCode()){
                case UP:
                   snake.setArriba();
                   break;
                case DOWN:
                    snake.setAbajo();
                    break;
                case LEFT:
                    snake.setIzq();
                    break;
                case RIGHT:
                    snake.setDer();
                    break;
                case ENTER: 
                    if(ciclo.estaPausado()){
                        resetGame();
                        (new Thread(ciclo)).start();
                    }
            }
        });
        
        resetGame();
        
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
        //try {
            registro = LocateRegistry.getRegistry(NOMBRE_SERVIDOR, PUERTO_SERVIDOR);
            server = (IServer) registro.lookup(NOMBRE_REGISTRO);

            this.clienteSnake = new ClienteSnake(server);

        //} catch (NotBoundException | RemoteException ex) {
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        //}
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private void resetGame(){
        tablero = new Tablero(ANCHO_VENTANA, ALTURA_VENTANA);
        ciclo = new CicloJuego(tablero, contexto);
        PintarJuego.pintar(tablero, contexto);        
    }
    
    void setStage(Stage stage) {
        this.stageActual = stage;
    }
    
}
