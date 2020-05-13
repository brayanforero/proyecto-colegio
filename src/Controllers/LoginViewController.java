package Controllers;

import Models.Users;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginViewController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button btnLogin;

    Alert alert;
    Users method = new Users();
    Users user;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void login(ActionEvent event) {
        this.btnLogin.setDisable(true);

        String nameUser = this.txtUsername.getText().toUpperCase().trim();
        String passUser = this.txtPassword.getText().toUpperCase().trim();

        this.user = method.login(nameUser, passUser);

        if (this.user.getId() != 0) {
            System.out.println("Usuario ok");
            System.out.println("Id: " + this.user.getId());
            System.out.println("Usuario: " + this.user.getName());
            System.out.println("Password: " + this.user.getPassword());
            System.out.println("Rol: " + this.user.getRole());
            System.out.println("Persona: " + this.user.getWorkers());

        } else {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setContentText("Los Datos Ingresados son Incorrectos");
            alert.showAndWait();
            this.btnLogin.setDisable(false);
        }
    }

}
