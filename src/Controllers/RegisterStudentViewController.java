package Controllers;

import Models.Students;
import com.mysql.jdbc.Connection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RegisterStudentViewController implements Initializable {

    
    @FXML
    private TextField txtNames;
    @FXML
    private TextField txtLastNames;
    @FXML
    private TextField txtDocument;
    @FXML
    private DatePicker txtDateOfBirth;
    @FXML
    private TextField txtState;
    @FXML
    private TextField txtMun;
    @FXML
    private TextField txtLocal;
    @FXML
    private CheckBox checkCanaima;
    @FXML
    private CheckBox checkBeca;
    private TextField txtSalud;
    private TextArea txtDesSalud;
    
    private Connection link;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void saveData(ActionEvent event) {

        String doc = this.txtDocument.getText().toUpperCase();
        String names = this.txtNames.getText().toUpperCase();
        String lastNames = this.txtLastNames.getText().toUpperCase();
        String dateOfBirth = this.txtDateOfBirth.getValue().toString();
        String gender = "M";
        int state = Integer.parseInt(this.txtState.getText());
        int mun = Integer.parseInt(this.txtMun.getText());
        int local = Integer.parseInt(this.txtLocal.getText());
        boolean canaima = this.checkCanaima.isSelected();
        boolean beca = this.checkBeca.isSelected();
        String salud = this.txtSalud.getText().toUpperCase();
        String desSalud = this.txtDesSalud.getText().toUpperCase();
        
        Students boy = new Students(dateOfBirth, gender, state, mun, local, canaima, beca, salud, desSalud, names, lastNames, doc);
        boy.newStudent();
    }

}
