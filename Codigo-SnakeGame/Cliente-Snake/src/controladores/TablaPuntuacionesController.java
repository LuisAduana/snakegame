/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import JPA.Puntuacion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
    private TableColumn<?, ?> tbcPosicion;
    @FXML
    private TableColumn<?, ?> tbcJugador;
    @FXML
    private TableColumn<?, ?> tbcPuntuacion;

    ObservableList<Puntuacion> puntuaciones;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cliente-SnakePU");
        EntityManager em = emf.createEntityManager();
   
        em.getTransaction().begin();
        int x=0;
        while(em.find(Puntuacion.class, x)!= null){
            Puntuacion puntuacion = em.find(Puntuacion.class, x);
            puntuaciones.add(puntuacion);
            x++;
        }
        
        em.getTransaction().commit();
        em.close();
        emf.close();
    }    
    
}
