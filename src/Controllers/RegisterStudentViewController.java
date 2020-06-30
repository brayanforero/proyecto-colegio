package Controllers;

import Models.Parents;
import Models.Students;
import com.mysql.jdbc.Connection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
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
    @FXML
    private ComboBox<String> cboSelectGender;
    private ObservableList<String> itemsGender = FXCollections.observableArrayList();
    @FXML
    private TextField txtMonNames;
    @FXML
    private TextField txtMonLastNames;
    @FXML
    private TextField txtMonDocument;
    @FXML
    private DatePicker txtMonDateOfBirth;
    @FXML
    private TextField txtOcupationMon;
    @FXML
    private TextField txtReligemMon;
    @FXML
    private RadioButton rdbYes;
    @FXML
    private RadioButton rdbNo;
    private ComboBox<String> cboSelectHouse;
    private ObservableList<String> itemsHouse = FXCollections.observableArrayList();
    @FXML
    private TextField txtEmailMon;
    @FXML
    private RadioButton isAutorized;
    @FXML
    private RadioButton isNotAutoraized;
    @FXML
    private ComboBox<String> cboMonHouse;
    @FXML
    private TextField txtDadNames;
    @FXML
    private TextField txtDadLastNames;
    @FXML
    private TextField txtDadDocument;
    @FXML
    private DatePicker txtDadDateOfBirth;
    @FXML
    private TextField txtDadOcupation;
    @FXML
    private TextField txtReligeDad;
    @FXML
    private RadioButton rdbYes1;
    @FXML
    private RadioButton rdbNo1;
    @FXML
    private TextField txtEmailDad;
    @FXML
    private RadioButton isAutorized1;
    @FXML
    private RadioButton isNotAutoraized1;
    @FXML
    private ComboBox<String> cboDadHouse;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.itemsGender.addAll("M", "F");
        this.cboSelectGender.setItems(itemsGender);
        this.itemsHouse.addAll("RANCHO", "CASA", "QUINTA");
        this.cboMonHouse.setItems(itemsHouse);
        this.cboDadHouse.setItems(itemsHouse);

    }

    @FXML
    private void saveData(ActionEvent event) {

        String identification = this.txtDocument.getText().toUpperCase();
        String boyNames = this.txtNames.getText().toUpperCase();
        String dateOfBirth = this.txtDateOfBirth.getValue().toString();
        String boyLastNames = this.txtLastNames.getText().toUpperCase();
        String boyGender = this.cboSelectGender.getValue();

        int state = Integer.parseInt(this.txtSelectState.getText());
        int mun = Integer.parseInt(this.txtSelectMun.getText());
        int locale = Integer.parseInt(this.txtSelectLocale.getText());

        boolean ifHaveCanaima = this.checkHaveCanaima.isSelected();
        boolean ifHaveBeca = this.checkHaveBeca.isSelected();

        String health = this.txtHealth.getText().toUpperCase();
        String recomendations = this.txtRecomendations.getText().toUpperCase();

        String phoneMon = this.txtPhoneMon.getText().toUpperCase();
        String phoneDad = this.txtPhoneDad.getText().toUpperCase();
        
        
        String momDoc = this.txtMonDocument.getText().toUpperCase();
        String momNames = this.txtMonNames.getText().toUpperCase();
        String momLastNames = this.txtMonLastNames.getText().toUpperCase();
        String momDateOfbirth = this.txtMonDateOfBirth.getValue().toString();
        boolean momifLifeBoy = this.rdbYes.isSelected();
        String momTypeHouse = this.cboMonHouse.getValue();
        String momTypeFamily = "MAMÁ";
        String momOcupation = this.txtOcupationMon.getText().toUpperCase();
        String momRelige = this.txtReligemMon.getText().toUpperCase();
        String momEmail = this.txtEmailMon.getText().toUpperCase();
        
        Parents mom = new Parents(momDoc , momNames, momLastNames, momDateOfbirth,
                momifLifeBoy, momTypeHouse,momTypeFamily, momOcupation, momRelige, momEmail
        );

       Students boy = new Students(
                identification, boyNames, boyLastNames, dateOfBirth, boyGender,
                state, mun, locale, ifHaveCanaima, ifHaveBeca, health, recomendations);

        Alert message = boy.newStudent(phoneMon, phoneDad, mom);
        message.showAndWait();
    }

}
