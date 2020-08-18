/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Workers;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class NewWorkerViewController implements Initializable {

    @FXML
    private TextField txtDocWorker;
    @FXML
    private TextField txtNameWorker;
    @FXML
    private TextField txtLastNameWorker;
    @FXML
    private TextField txtEmailWorker;
    @FXML
    private TextField txtPhoneTelefono;
    @FXML
    private Button btnSaveWorker;
    @FXML
    private TextField txtCargoWorker;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void savedWorker(ActionEvent event) {
        
        String doc = this.txtDocWorker.getText().toUpperCase();
        String name = this.txtNameWorker.getText().toUpperCase();
        String lastName = this.txtLastNameWorker.getText().toUpperCase();
        String email = this.txtEmailWorker.getText().toUpperCase();
        String cargo = this.txtCargoWorker.getText().toUpperCase();
        String phone = this.txtPhoneTelefono.getText();
        
        Workers worker = new Workers(name, lastName, doc);
        worker.setEmail(email);
        worker.setCargo(cargo);
        worker.setPhone(phone);
        Alert msg  = worker.saveWorker();
        msg.showAndWait();
    }
    
}
