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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void login(ActionEvent event) {
        this.btnLogin.setDisable(true);
        Users method = new Users();
        Users user;
        String nameUser = this.txtUsername.getText().toUpperCase().trim();
        String passUser = this.txtPassword.getText().toUpperCase().trim();

        user = method.login(nameUser, passUser);
        
        if(user.getId() != 0){
            System.out.println("Usuario ok");
            System.out.println("Id: " + user.getId());
            System.out.println("Usuario: " + user.getName());
            System.out.println("Password: " + user.getPassword());
            System.out.println("Rol: " + user.getRole());
            System.out.println("Persona: " + user.getWorkers());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setContentText("Los Datos Ingresados son Incorrectos");
            alert.showAndWait();
            this.btnLogin.setDisable(false);
        }
    }

}
