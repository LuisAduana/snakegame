package controladores;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Esta clase se encarga de inicializar la interfaz gráfica.
 * @author Luis Aduana.
 */
public class Main extends Application {
        
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Inicio de Sesión");
        primaryStage.setResizable(false);
        
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vistas/VistaPrincipal.fxml"));
        Pane myPane = (Pane) myLoader.load();
        VistaPrincipalController controlador = (VistaPrincipalController) myLoader.getController();
        controlador.setStage(primaryStage);
        
        Scene scene = new Scene(myPane);
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
