package Controllers;

import Models.Students;
import com.mysql.jdbc.Connection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RegisterStudentViewController implements Initializable {

    
    
    
    private Connection link;
    
    @FXML
    private Button btnSaveData;
    @FXML
    private TextField txtNames;
    @FXML
    private TextField txtLastNames;
    @FXML
    private TextField txtDocument;
    @FXML
    private DatePicker txtDateOfBirth;
    @FXML
    private TextField txtSelectState;
    @FXML
    private TextField txtSelectMun;
    @FXML
    private TextField txtSelectLocale;
    @FXML
    private CheckBox checkHaveCanaima;
    @FXML
    private CheckBox checkHaveBeca;
    @FXML
    private TextField txtPhoneMon;
    @FXML
    private TextField txtPhoneDad;
    @FXML
    private TextField txtHealth;
    @FXML
    private TextArea txtRecomendations;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void saveData(ActionEvent event) {
        
        String identification = this.txtDocument.getText().toUpperCase();
        String boyNames = this.txtNames.getText().toUpperCase();
        String dateOfBirth = this.txtDateOfBirth.getValue().toString();
        String boyLastNames = this.txtLastNames.getText().toUpperCase();
        String boyGender = "M";
        
        int state = Integer.parseInt( this.txtSelectState.getText() ) ;
        int mun = Integer.parseInt( this.txtSelectMun.getText() ) ;
        int locale = Integer.parseInt( this.txtSelectLocale.getText() ) ;
        
        boolean ifHaveCanaima = this.checkHaveCanaima.isSelected();
        boolean ifHaveBeca = this.checkHaveBeca.isSelected();
        
        String health = this.txtHealth.getText().toUpperCase();
        String recomendations = this.txtRecomendations.getText().toUpperCase();
        
        String phoneMon = this.txtPhoneMon.getText().toUpperCase();
        String phoneDad = this.txtPhoneDad.getText().toUpperCase();
        
        Students boy = new Students(
            identification, boyNames, boyLastNames, dateOfBirth, boyGender, 
            state, mun, locale, ifHaveCanaima, ifHaveBeca, health, recomendations);
        
        Alert message = boy.newStudent(phoneMon, phoneDad);
        message.showAndWait();
    }

}
