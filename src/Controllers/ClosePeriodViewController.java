
package Controllers;

import Models.Period;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
public class ClosePeriodViewController implements Initializable {

    @FXML
    private Button btnClosePeriod;
    private String period;
    @FXML
    public Label labelnfo;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
    }    

    @FXML
    private void closePeriod(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("¿Estas seguro de Cerrar el periodo " + this.getPeriod() + "?");
        Optional<ButtonType> action = alert.showAndWait();
        
        if (action.get() == ButtonType.CANCEL) {
            return;
        }
        
        Alert info = Period.closePeriod(this.period);
        info.showAndWait();
    }

    public Button getBtnClosePeriod() {
        return btnClosePeriod;
    }

    public void setBtnClosePeriod(Button btnClosePeriod) {
        this.btnClosePeriod = btnClosePeriod;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
    
    
    
}
