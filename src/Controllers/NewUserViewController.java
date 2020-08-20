/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Users;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class NewUserViewController implements Initializable {
    
    @FXML
    private TextField txtNameUser;
    @FXML
    private PasswordField txtPassUser;
    @FXML
    private Button btnNewUser;
    @FXML
    private TextField txtIdUser;
    @FXML
    private ComboBox<String> cboRolUser;
    ObservableList<String> option;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.option = FXCollections.observableArrayList();
        this.option.addAll("ADMINSTRADOR", "DOCENTE");
        this.cboRolUser.setItems(this.option);
    }    
    
    @FXML
    private void newUser(ActionEvent event) {
        
        int idWorker = Integer.parseInt(this.txtIdUser.getText());
        String userName = this.txtNameUser.getText().toUpperCase();
        String userPass = this.txtPassUser.getText().toUpperCase();
        String userRol = this.cboRolUser.getValue();
        
        Users user = new Users(idWorker, userName, userRol, userRol);
        
        Alert msg = user.newUser();
        
        msg.showAndWait();
    }
    
}
