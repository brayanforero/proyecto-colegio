package Controllers;

import Config.Utilities;
import Models.Localidad;
import Models.Municipio;
import Models.Parents;
import Models.Students;
import Models.State;
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
import javafx.scene.control.Tab;
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
    private ComboBox<State> txtSelectState;
    private ObservableList<State> itemsStates = FXCollections.observableArrayList();
    @FXML
    private ComboBox<Municipio> txtSelectMun;
    private ObservableList<Municipio> itemsMun = FXCollections.observableArrayList();
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
    private ComboBox<String> cboDadHouse;
    private int idDegress;
    @FXML
    private ComboBox<Localidad> txtSelecLocale;
    private ObservableList<Localidad> itemsLocales = FXCollections.observableArrayList();

    private State state;
    private Localidad loc;
    private Municipio mun;
    @FXML
    private Tab tabBoy;
    @FXML
    private Tab tabHealth;
    @FXML
    private Tab tabMom;
    @FXML
    private Tab tabDad;
    @FXML
    private Tab tabRegister;
    @FXML
    private Button btnToHealth;
    @FXML
    private Button btnToMom;
    @FXML
    private Button btnToDad;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.setStaticCombox();
        this.setDataCombox();
        this.initEvet();
        //this.disabledTabs();
        this.eventsToTabs();
    }

    @FXML
    private void saveData(ActionEvent event) throws SQLException {

        Students boy;
        Parents mom, dad;
        this.loc = txtSelecLocale.getValue();

        // CAPTURANDO DATOS DEL NIÑO DE LA VISTA
        String doc = this.txtDocument.getText().toUpperCase();
        String name = this.txtNames.getText().toUpperCase();
        String last_names = this.txtLastNames.getText().toUpperCase();
        String dateOfBith = this.txtDateOfBirth.getValue().toString();
        String gender = this.cboSelectGender.getValue().toUpperCase();
        int id_state = this.state.getId();
        int id_mun = this.mun.getId();
        int id_loc = this.loc.getId();
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
                ifLiveBoy_mom, type_house_mom, type_family_mom, ocp_mom, rel_mom, email_mom);

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
                ifLiveBoy_dad, type_house_dad, type_family_dad, ocp_dad, rel_dad, email_dad);

        Alert msg = boy.newStudent(phone_mom, phone_dad, mom, dad, this.idDegress);
        msg.showAndWait();
    }

    public void setIdDegressForRegister(int idDegress) {
        this.idDegress = idDegress;
    }

    public void setDataCombox() {
        State.getStates(this.itemsStates);
        this.txtSelectState.setItems(this.itemsStates);
    }

    public void setStaticCombox() {
        this.itemsGender.addAll("M", "F");
        this.cboSelectGender.setItems(itemsGender);
        this.cboSelectGender.getSelectionModel().select(0);
        this.itemsHouse.addAll("RANCHO", "CASA", "QUINTA");
        this.cboMonHouse.setItems(itemsHouse);
        this.cboDadHouse.setItems(itemsHouse);
    }

    public void disabledTabs() {

        this.tabHealth.setDisable(true);
        this.tabMom.setDisable(true);
        this.tabDad.setDisable(true);
        this.tabRegister.setDisable(true);
    }

    @FXML
    private void getMunById(ActionEvent event) {
        this.itemsMun.removeAll(itemsMun);
        this.itemsLocales.removeAll(itemsLocales);
        ComboBox target = (ComboBox) event.getTarget();
        this.state = (State) target.getValue();
        Municipio.getMunicipios(this.itemsMun, this.state.getId());
        this.txtSelectMun.setItems(this.itemsMun);
    }

    @FXML
    private void getLocaleById(ActionEvent event) {
        this.itemsLocales.removeAll(itemsLocales);
        ComboBox target = (ComboBox) event.getTarget();
        this.mun = (Municipio) target.getValue();
        Localidad.getLocales(this.itemsLocales, this.mun.getId());
        this.txtSelecLocale.setItems(this.itemsLocales);
    }

    public void initEvet() {

        /*FIELDS NUMBERS*/
        this.txtDocument.setOnKeyTyped(e -> Utilities.onlyDigit(e));
        this.txtPhoneDad.setOnKeyTyped(e -> Utilities.onlyDigit(e));
        this.txtPhoneMon.setOnKeyTyped(e -> Utilities.onlyDigit(e));
        this.txtDadDocument.setOnKeyTyped(e -> Utilities.onlyDigit(e));
        this.txtMonDocument.setOnKeyTyped(e -> Utilities.onlyDigit(e));
        this.txtDadDocument.setOnKeyTyped(e -> Utilities.onlyDigit(e));

        /*FIELDS TEXT*/
        this.txtNames.setOnKeyTyped(e -> Utilities.onlyLetter(e));
        this.txtLastNames.setOnKeyTyped(e -> Utilities.onlyLetter(e));

        this.txtDadNames.setOnKeyTyped(e -> Utilities.onlyLetter(e));
        this.txtDadLastNames.setOnKeyTyped(e -> Utilities.onlyLetter(e));
        this.txtOcupationDad.setOnKeyTyped(e -> Utilities.onlyLetter(e));
        this.txtReligeDad.setOnKeyTyped(e -> Utilities.onlyLetter(e));
        this.txtMonNames.setOnKeyTyped(e -> Utilities.onlyLetter(e));
        this.txtMonLastNames.setOnKeyTyped(e -> Utilities.onlyLetter(e));
        this.txtOcupationMon.setOnKeyTyped(e -> Utilities.onlyLetter(e));
        this.txtReligemMon.setOnKeyTyped(e -> Utilities.onlyLetter(e));

    }

    public void eventsToTabs() {

        this.btnToHealth.setOnMouseClicked(e -> {
            /*DESDE EL ESTUDIANTE A LA SECCION DE SALUD*/
            if (!this.validPaneBoy()) {
                return;
            }
            this.tabHealth.setDisable(false);
            this.tabHealth.getTabPane().getSelectionModel().selectNext();
        });
        /*DESDE EL MODULO DE SALUD HACIA MODULO DE LA MAMÁ*/
        this.btnToMom.setOnMouseClicked(e -> {

            this.tabMom.setDisable(false);
            this.tabMom.getTabPane().getSelectionModel().selectNext();
        });
        /*DESDE EL MODULO DE LA MAMÁ HACIA MODULO DEL PAPÁ*/
        this.btnToDad.setOnMouseClicked(e -> {

            if(!validaPaneMom()) return;
            
            this.tabDad.setDisable(false);
            this.tabDad.getTabPane().getSelectionModel().selectNext();
        });
    }

    public boolean validPaneBoy() {

        if (this.txtNames.getText().isEmpty() || this.txtLastNames.getText().isEmpty() || this.txtNames.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Llena todo los campos");
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }

        if (this.txtDocument.getText().length() < 8) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Ingresa una cédula valida");
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }

        if (this.txtPhoneDad.getText().length() < 7 || this.txtPhoneMon.getText().length() < 7) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Ingresa un teléfono válido");
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }

        if (this.txtSelectState.getValue() == null || this.txtSelectMun.getValue() == null || this.txtSelecLocale.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Completa los campos de seleccion");
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }
        if (this.txtDadDateOfBirth.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Completa la fecha de nacimiento");
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }
        return true;
    }

    public boolean validaPaneMom() {
        if (this.txtMonNames.getText().isEmpty() || this.txtMonLastNames.getText().isEmpty() || this.txtReligemMon.getText().isEmpty()
                || this.txtOcupationMon.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Llena todo los campos");
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }
        
        if(this.txtMonDocument.getText().length() < 5) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Ingresa una cédula valida");
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }
        
        
        if(this.txtMonDateOfBirth.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Completa la fecha de nacimiento");
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }
        
        if(this.cboMonHouse.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Completa los campos de seleccion");
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }
        
        if(!Utilities.validEmail(this.txtEmailMon.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Ingresa un email valido");
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }
   
        return true;
    }

}
