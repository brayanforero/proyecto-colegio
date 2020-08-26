package Controllers;

import Models.Parents;
import Models.Students;
import com.mysql.jdbc.Connection;
import java.net.URL;
import java.sql.SQLException;
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
    private final ObservableList<String> itemsGender = FXCollections.observableArrayList();
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
    private final ObservableList<String> itemsHouse = FXCollections.observableArrayList();
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
    private TextField txtReligeDad;
    @FXML
    private RadioButton rdbYes1;
    @FXML
    private RadioButton rdbNo1;
    @FXML
    private TextField txtEmailDad;
    @FXML
    private TextField txtOcupationDad;
    @FXML
    private RadioButton isAutorized1;
    @FXML
    private RadioButton isNotAutoraized1;
    @FXML
    private ComboBox<String> cboDadHouse;
    private int idDegress;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.itemsGender.addAll("M", "F");
        this.cboSelectGender.setItems(itemsGender);
        this.itemsHouse.addAll("RANCHO", "CASA", "QUINTA");
        this.cboMonHouse.setItems(itemsHouse);
        this.cboDadHouse.setItems(itemsHouse);

    }

    @FXML
    private void saveData(ActionEvent event) throws SQLException {
        
        Students boy;
        Parents mom,dad;
        
        
        // CAPTURANDO DATOS DEL NIÑO DE LA VISTA
        String doc = this.txtDocument.getText().toUpperCase();
        String name = this.txtNames.getText().toUpperCase();
        String last_names = this.txtLastNames.getText().toUpperCase();
        String dateOfBith = this.txtDateOfBirth.getValue().toString();
        String gender = this.cboSelectGender.getValue().toUpperCase();
        int id_state = Integer.parseInt( this.txtSelectState.getText() );
        int id_mun = Integer.parseInt( this.txtSelectMun.getText() );
        int id_loc = Integer.parseInt( this.txtSelectLocale.getText() );
        boolean have_Canaima = this.checkHaveCanaima.isSelected();
        boolean have_Beca = this.checkHaveBeca.isSelected();
        String health = this.txtNames.getText().toUpperCase();
        String des_health = this.txtNames.getText().toUpperCase();
        String phone_mom = this.txtPhoneMon.getText();
        String phone_dad = this.txtPhoneDad.getText();
        boy = new Students(doc, name, last_names, dateOfBith, gender, 
                id_state, id_mun, id_loc, have_Canaima, have_Beca, health, des_health);
        
        // CAPTURANDO DATOS DE La MAMÁ DE LA VISTA
        String doc_mom = this.txtMonDocument.getText().toUpperCase();
        String name_mom = this.txtMonNames.getText().toUpperCase();
        String last_names_mom = this.txtMonLastNames.getText().toUpperCase();
        String dateOfBith_mom = this.txtMonDateOfBirth.getValue().toString();
        boolean ifLiveBoy_mom = this.rdbYes.isSelected() ? true : false;
        String type_house_mom = this.cboMonHouse.getValue().toUpperCase();
        String type_family_mom = "MAMÁ";
        String ocp_mom = this.txtOcupationMon.getText().toUpperCase();
        String rel_mom = this.txtReligemMon.getText().toUpperCase();
        String email_mom = this.txtEmailMon.getText().toUpperCase();
        mom = new Parents(doc_mom, name_mom, last_names_mom, dateOfBith_mom, 
                ifLiveBoy_mom, type_house_mom, type_family_mom, ocp_mom, rel_mom,email_mom);
        
        // CAPTURANDO DATOS DE La MAMÁ DE LA VISTA
        String doc_dad = this.txtDadDocument.getText().toUpperCase();
        String name_dad = this.txtDadNames.getText().toUpperCase();
        String last_names_dad = this.txtDadLastNames.getText().toUpperCase();
        String dateOfBith_dad = this.txtDadDateOfBirth.getValue().toString();
        boolean ifLiveBoy_dad = this.rdbYes1.isSelected() ? true : false;
        String type_house_dad = this.cboDadHouse.getValue().toUpperCase();
        String type_family_dad = "PAPÁ";
        String ocp_dad = this.txtOcupationDad.getText().toUpperCase();
        String rel_dad = this.txtReligeDad.getText().toUpperCase();
        String email_dad = this.txtEmailDad.getText().toUpperCase();
        dad = new Parents(doc_dad, name_dad, last_names_dad, dateOfBith_dad, 
                ifLiveBoy_dad, type_house_dad, type_family_dad, ocp_dad, rel_dad,email_dad);
        
        Alert msg = boy.newStudent(phone_mom, phone_dad, mom, dad, this.idDegress);
        msg.showAndWait();
    }
    
    public void setIdDegressForRegister(int idDegress){
        this.idDegress = idDegress;
    }

}
