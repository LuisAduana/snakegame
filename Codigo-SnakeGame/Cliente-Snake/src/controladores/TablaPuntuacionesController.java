package controladores;

import clases.PuntuacionObtenida;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Clase que gestiona los eventos en la TablaPuntuaciones.
 *
 * @author Luis Bonilla
 * @author Rodrigo
 */
public class TablaPuntuacionesController implements Initializable {

    @FXML
    private Button btnAtras;
    @FXML
    private TableView<?> tbvPuntuaciones;
    @FXML
    private TableColumn<PuntuacionObtenida, String> tbcJugador;
    @FXML
    private TableColumn<PuntuacionObtenida, Integer> tbcPuntuacion;
   
    /**
     * Método que pinta la lista en la ventana.
     * @param vistaPrincipalController Recibe la instancia que lo invoca para acceder a sus parámetros.
     */
    public void setListaPuntuaciones(VistaPrincipalController vistaPrincipalController) {
        VistaPrincipalController vistaPrincipalControler = vistaPrincipalController;
        ObservableList<PuntuacionObtenida> puntuacionesObtenidas = FXCollections
                .observableList(vistaPrincipalControler.getPuntuaciones());
        
        tbcJugador.setCellValueFactory( new PropertyValueFactory<PuntuacionObtenida, String>("nombre"));
        tbcPuntuacion.setCellValueFactory( new PropertyValueFactory<PuntuacionObtenida, Integer>("puntuacion"));
        tbvPuntuaciones.setItems((ObservableList) puntuacionesObtenidas);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Este método inicializa la clase sin ninguna operación, ya que los elementos se cargan en el método setListaPuntuaciones.
    }
    
    
    
    
}
