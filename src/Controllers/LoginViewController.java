package Controllers;

import Help.OperatorWindows;
import Models.Users;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    OperatorWindows help = new OperatorWindows();

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
            
            Node src = (Node) event.getSource();
            Stage stage = (Stage) src.getScene().getWindow();
            stage.close();
            
            help.openWindow(user.getRole());
            
            
        } else {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setContentText("Estimado Usuario los Datos son Incorrectos");
            alert.showAndWait();
            this.btnLogin.setDisable(false);
        }
    }

}
