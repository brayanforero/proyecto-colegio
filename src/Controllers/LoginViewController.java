package Controllers;

import Models.Users;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
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
    @FXML
    private Label btnRecovery;
    @FXML
    private Label btnShowPassword;
    @FXML
    private TextField txtShowPassword;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.lblAlert.setVisible(false);
        this.txtShowPassword.setVisible(false);
        this.btnShowPassword.setVisible(false);
        this.handdlerShowPassword();
    }
    
    public void handdlerShowPassword(){
        this.txtPassword.setOnKeyReleased(e->{
            TextField target = (TextField) e.getTarget();
            
            if (this.txtPassword.getText().isEmpty()) this.btnShowPassword.setVisible(false);
            if (!this.txtPassword.getText().isEmpty()) this.btnShowPassword.setVisible(true);
            
            if (!this.txtPassword.getText().isEmpty()) {
                this.btnShowPassword.setVisible(true);
            }
            this.txtShowPassword.setText(target.getText());
        });
        
        this.btnShowPassword.setOnMouseClicked(e->{
            
            if (this.txtShowPassword.isVisible()) {
                this.btnShowPassword.setText("Ver");
                this.txtShowPassword.setVisible(false);
                this.txtPassword.setVisible(true);
                return;
            }
            
            if (!this.txtShowPassword.isVisible()) {
                this.btnShowPassword.setText("Ocultar");
                this.txtShowPassword.setVisible(true);
                this.txtPassword.setVisible(false);
                return;
            }
        });
    }
    @FXML
    private void login(ActionEvent event) {
        String nameUser = this.txtUsername.getText().toUpperCase().trim();
        String passUser = this.txtPassword.getText().toUpperCase().trim();

        if (nameUser.isEmpty() || passUser.isEmpty()) {

            this.lblAlert.setVisible(true);
            this.lblAlert.setText("Estimado usuario completa todos los campos");
            return;
        }

        this.btnLogin.setDisable(true);
        this.user = method.login(nameUser, passUser);

        if (this.user.getId() == 0) {
            this.btnLogin.setDisable(false);
            this.lblAlert.setVisible(true);
            this.lblAlert.setText("Estimado Usuario los Datos son Incorrectos");
            return;
        }

        this.lblAlert.setVisible(false);
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

    @FXML
    private void openRecovery(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/ForgotPasswordView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.setResizable(false);
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.showAndWait();
        } catch (IOException e) {

            System.err.println("Error al abrir las ventana: " + e);
        }
    }

}
