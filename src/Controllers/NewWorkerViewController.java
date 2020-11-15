/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Config.Utilities;
import Models.Workers;
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
import javafx.scene.control.TextField;

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
    private TextField txtCargoWorker;
    @FXML
    private ComboBox<String> cboTypeDoc;
    ObservableList<String> items = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> cboPosition;
    ObservableList<String> itemsPositions = FXCollections.observableArrayList();
    @FXML
    private Button btnReset;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       initCombo();
       initEvent();
    }    
    
    public void initCombo (){
        
        this.items.addAll("V-", "E-");
        this.cboTypeDoc.setItems(this.items);
        
        this.cboTypeDoc.getSelectionModel().select("V-");
        
        this.itemsPositions.addAll("DIRECTOR", "COORDINADOR", "DOCENTE", "OBRERO", "SECRETARIA");
        this.cboPosition.setItems(this.itemsPositions);
        
        this.cboPosition.getSelectionModel().select(0);
    }
    @FXML
    private void savedWorker(ActionEvent event) {
        
       
        String doc = this.cboTypeDoc.getValue() + this.txtDocWorker.getText().toUpperCase();
        String name = this.txtNameWorker.getText().toUpperCase();
        String lastName = this.txtLastNameWorker.getText().toUpperCase();
        String email = this.txtEmailWorker.getText().toUpperCase();
        String cargo = this.cboPosition.getValue();
        String phone = this.txtPhoneTelefono.getText();
        
        Workers worker = new Workers(name, lastName, doc);
        worker.setEmail(email);
        worker.setCargo(cargo);
        worker.setPhone(phone);
        Alert msg  = worker.saveWorker();
        msg.showAndWait();
    }

    @FXML
    private void reset(ActionEvent event) {
        this.cboTypeDoc.getSelectionModel().select(0);
        this.cboPosition.getSelectionModel().select(0);
        
        this.txtDocWorker.setText("");
        this.txtNameWorker.setText("");
        this.txtLastNameWorker.setText("");
        this.txtPhoneTelefono.setText("");
        this.txtDocWorker.setText("");
        this.txtEmailWorker.setText(""); 
    }
    
    public void initEvent(){
        
        this.txtNameWorker.setOnKeyTyped(e->Utilities.onlyLetter(e));
        this.txtLastNameWorker.setOnKeyTyped(e->Utilities.onlyLetter(e));
        this.txtPhoneTelefono.setOnKeyTyped(e->Utilities.onlyDigit(e));
        this.txtDocWorker.setOnKeyTyped(e->Utilities.onlyDigit(e));
        
    }
    
}
