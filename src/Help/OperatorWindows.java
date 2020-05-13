package Help;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OperatorWindows {

    public void openWindow(String rol) {

        String rolUser = rol.toUpperCase();
        Parent root;

        switch (rolUser) {
            case "COORDINADOR":
                try {
                    root = FXMLLoader.load(getClass().getResource("/Views/CoordinatorView.fxml"));
                    Scene scene = new Scene(root);
                    Stage newStg = new Stage();
                    newStg.setScene(scene);
                    newStg.setTitle("Teacher");
                    newStg.show();
                } catch (IOException ex) {
                    System.err.println("Error al Cargar la Ventan: " + ex);
                }
                break;
            case "DOCENTE":

                try {
                    root = FXMLLoader.load(getClass().getResource("/Views/TeacherView.fxml"));
                    Scene scene = new Scene(root);
                    Stage newStg = new Stage();
                    newStg.setScene(scene);
                    newStg.setTitle("Teacher");
                    newStg.show();
                } catch (IOException ex) {
                    System.err.println("Error al Cargar la Ventan: " + ex);
                }
                break;
            default:
                try {
                    root = FXMLLoader.load(getClass().getResource("/Views/AdminView.fxml"));
                    Scene scene = new Scene(root);
                    Stage newStg = new Stage();
                    newStg.setScene(scene);
                    newStg.setTitle("Teacher");
                    newStg.show();
                } catch (IOException ex) {
                    System.err.println("Error al Cargar la Ventan: " + ex);
                }
                break;
        }
    }
}
