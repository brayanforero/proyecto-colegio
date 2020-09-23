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

public class ViewNewPeriodController implements Initializable {

    @FXML
    private DatePicker txtDateGo;
    @FXML
    private DatePicker txtDateClose;
    @FXML
    private Button btnAddPeriod;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
