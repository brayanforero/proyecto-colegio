/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Period;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ViewNewPeriodController implements Initializable {

    @FXML
    private DatePicker txtDateGo;
    @FXML
    private DatePicker txtDateClose;
    @FXML
    private Button btnAddPeriod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addPeriod(ActionEvent event) {
        
        String dateGo = this.txtDateGo.getValue().toString();
        String dateEnd = this.txtDateClose.getValue().toString();
        
        Period period = new Period();
        period.setStarDate(dateGo);
        period.setEndDate(dateEnd);
        Alert msg = period.addPeriod();
        msg.showAndWait();
    }
    
}
