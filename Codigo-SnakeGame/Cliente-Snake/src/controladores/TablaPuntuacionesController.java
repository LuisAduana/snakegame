/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clases.PuntuacionObtenida;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
 * FXML Controller class
 *
 * @author ferzo
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

    ObservableList<PuntuacionObtenida> puntuaciones;
    //List<PuntuacionObtenida> listaPuntuaciones;
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*List<PuntuacionObtenida> listaPuntuaciones = new ArrayList<>();
        PuntuacionObtenida punto = new PuntuacionObtenida();
        punto.setIdcliente(1);
        punto.setNombre("Luis");
        punto.setPuntuacion(100);
        listaPuntuaciones.add(punto);
        puntuaciones = FXCollections.observableList(listaPuntuaciones);*/
        
        tbcJugador.setCellValueFactory( new PropertyValueFactory<PuntuacionObtenida, String>("nombre"));
        tbcPuntuacion.setCellValueFactory( new PropertyValueFactory<PuntuacionObtenida, Integer>("puntuacion"));
        tbvPuntuaciones.setItems((ObservableList) puntuaciones);
        
    }

    public void setListaPuntuaciones(List<PuntuacionObtenida> listaPuntuaciones) {
        puntuaciones = FXCollections.observableList(listaPuntuaciones);
    }
    
}
