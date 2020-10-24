package Controllers;

import Models.Users;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginViewController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnLogin;

    Alert alert;
    Users method = new Users();
    Users user;
    @FXML
    private Label lblAlert;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.lblAlert.setVisible(false);
    }

    @FXML
    private void login(ActionEvent event) {
        this.btnLogin.setDisable(true);
        String nameUser = this.txtUsername.getText().toUpperCase().trim();
        String passUser = this.txtPassword.getText().toUpperCase().trim();

        this.user = method.login(nameUser, passUser);

        if (this.user.getId() == 0) {
            /*alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Estimado Usuario los Datos son Incorrectos");
            alert.showAndWait();*/
            this.btnLogin.setDisable(false);
            this.lblAlert.setVisible(true);
            this.lblAlert.setText("Estimado Usuario los Datos son Incorrectos");
            return;
        }

        Node src = (Node) event.getSource();
        Stage stage = (Stage) src.getScene().getWindow();
        stage.close();
        this.openWindow(this.user.getRole());
    }

    public void openWindow(String rol) {
        String rolUser = rol.toUpperCase();
        switch (rolUser) {

            case "DOCENTE":

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/TeacherView.fxml"));
                    Parent root = loader.load();

                    TeacherViewController controller = loader.getController();
                    controller.initAtributeUser(this.user.getId(), this.user.getWorkers());
                    controller.initDegressAsigned(this.user.getIdWorker());

                    Scene scene = new Scene(root);

                    Stage primaryStage = new Stage();
                    primaryStage.setScene(scene);
                    primaryStage.setMinWidth(1024);
                    primaryStage.setMinHeight(500);
                    primaryStage.setMaxWidth(1024);
                    primaryStage.setMaxHeight(500);
                    primaryStage.setResizable(false);
                    primaryStage.show();

                } catch (IOException ex) {
                    System.err.println("Error al cargar la ventana: " + ex);
                }
                break;
            default:
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/AdminView.fxml"));
                    Parent root = loader.load();

                    AdminViewController controller = loader.getController();
                    controller.initAtributeUser(this.user.getId(), this.user.getWorkers());
                    Scene scene = new Scene(root);
                    Stage primaryStage = new Stage();
                    primaryStage.setScene(scene);
                    primaryStage.setMinWidth(1024);
                    primaryStage.setMinHeight(500);
                    primaryStage.setMaxWidth(1024);
                    primaryStage.setMaxHeight(500);
                    primaryStage.setResizable(false);
                    primaryStage.show();

                } catch (IOException ex) {
                    System.err.println("Error al cargar la ventana: " + ex);
                }
                break;

        }
    }

}
