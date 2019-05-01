package controladores;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Clase que gestiona los eventos en la VistaPrincipal.
 * @author Luis Aduana.
 */
public class VistaPrincipalController implements Initializable {
       
    @FXML
    TextField nombreJugador;
    @FXML
    Label mensajeError;
    
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
            System.out.println("ENTRO");
            // TODO Se inicia el juego aquí <-----------------------------------------
        } else {
            mensajeError.setText(validacion);
            /*Alert dialogo = new Alert(AlertType.INFORMATION);
            dialogo.setTitle("Nombre incorrecto");
            dialogo.setHeaderText(null);
            dialogo.setContentText(validacion);
            dialogo.initStyle(StageStyle.UTILITY);
            dialogo.showAndWait();*/
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
