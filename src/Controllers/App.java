
package Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application{

    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/LoginView.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Escuela Bolivariana Santa Rita - Sistema de Inscripcion");
        primaryStage.setMinWidth(1024);
        primaryStage.setMinHeight(500);
        primaryStage.setMaxWidth(1024);
        primaryStage.setMaxHeight(500);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
}
