package controladores;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Esta clase se encarga de inicializar la interfaz gráfica.
 * @author Luis Bonilla.
 */
public class Main extends Application {
        
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/VistaPrincipal.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Método que inicializa la aplicación.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
