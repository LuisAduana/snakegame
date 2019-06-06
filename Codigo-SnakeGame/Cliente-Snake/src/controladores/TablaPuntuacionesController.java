/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import JPA.Puntuacion;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
    private TableColumn<Puntuacion, String> tbcJugador;
    @FXML
    private TableColumn<Puntuacion, Integer> tbcPuntuacion;

    ObservableList<Puntuacion> puntuaciones;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cliente-SnakePU");
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();
            
            List<Puntuacion> query = em.createNamedQuery("Puntuacion.findSortByPuntuacion", Puntuacion.class).getResultList();
            puntuaciones = FXCollections.observableList(query);
            
            em.getTransaction().commit();
            em.close();
            emf.close();
        }catch(Exception ex){
            System.err.println("Error en la conexión con la BD: " + ex.getMessage());
        }
        tbcJugador.setCellValueFactory( new PropertyValueFactory<Puntuacion, String>("nombre"));
        tbcPuntuacion.setCellValueFactory( new PropertyValueFactory<Puntuacion, Integer>("puntuacion"));
        tbvPuntuaciones.setItems((ObservableList) puntuaciones);
    }    
    
}
