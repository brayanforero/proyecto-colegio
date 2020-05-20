/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Brayan
 */
public class TeacherViewController implements Initializable {

    @FXML
    private Label lblDegress;
    @FXML
    private Label lblSeccion;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblNameUser;
    @FXML
    private Label lblidUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initAtribute(int id, String nameUser) {

        this.lblNameUser.setText(nameUser);
        this.lblidUser.setText(id + "");

    }

}
