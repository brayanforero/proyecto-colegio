package Controllers;

import Models.Localidad;
import Models.Municipio;
import Models.Parents;
import Models.State;
import Models.Students;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RegisteStudentRegViewController implements Initializable {

    @FXML
    private TextField txtNames;
    @FXML
    private TextField txtLastNames;

    @FXML
    private TextField txtDocument;
    @FXML
    private DatePicker txtDateOfBirth;
    @FXML
    private CheckBox checkHaveCanaima;
    @FXML
    private CheckBox checkHaveBeca;
    @FXML
    private TextField txtPhoneMon;
    @FXML
    private TextField txtPhoneDad;
    @FXML
    private ComboBox<String> cboSelectGender;
    private ObservableList<String> itemsGender = FXCollections.observableArrayList();
    @FXML
    private ComboBox<State> txtSelectState;
    private ObservableList<State> itemsStates = FXCollections.observableArrayList();
    @FXML
    private ComboBox<Municipio> txtSelectMun;
    private ObservableList<Municipio> itemsMun = FXCollections.observableArrayList();
    @FXML
    private ComboBox<Localidad> txtSelecLocale;
    private ObservableList<Localidad> itemsLocales = FXCollections.observableArrayList();
    @FXML
    private TextField txtHealth;
    @FXML
    private TextArea txtRecomendations;
    @FXML
    private TextField txtMonNames;
    @FXML
    private TextField txtMonLastNames;
    @FXML
    private TextField txtMonDocument;
    @FXML
    private Button btnSaveData;
    @FXML
    private ComboBox<String> cboDocMom;
    private ObservableList<String> itemDocMom = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> cboDocDad;
    private ObservableList<String> itemDocDad = FXCollections.observableArrayList();
    @FXML
    private TextField txDadDocument;
    @FXML
    private TextField txtDadNames;
    @FXML
    private TextField txtDadLastNames;
    @FXML
    private Button btnSearchMom;
    @FXML
    private Button btnSearchDad;
    private int mom = 0;
    private int dad = 0;
    private State state;
    private Localidad loc;
    private Municipio mun;
    private int idDegress = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCombox();
        setDataCombox();
    }

    public void initCombox() {
        this.itemDocDad.addAll("V-", "E-");
        this.itemDocMom.addAll("V-", "E-");
        this.itemsGender.addAll("M", "F");

        this.cboDocDad.setItems(this.itemDocDad);
        this.cboDocDad.getSelectionModel().selectFirst();
        this.cboDocMom.setItems(this.itemDocMom);
        this.cboDocMom.getSelectionModel().selectFirst();
        
        this.cboSelectGender.setItems(this.itemsGender);
        this.cboSelectGender.getSelectionModel().selectFirst();
    }

    public void setDataCombox() {
        State.getStates(this.itemsStates);
        this.txtSelectState.setItems(this.itemsStates);
    }
    public void setIdDegressForRegister(int idDegress) {
        this.idDegress = idDegress;
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

    @FXML
    private void saveData(ActionEvent event) throws SQLException {
        Students boy;
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

        Alert msg = boy.newStudent(phone_mom, phone_dad, this.mom, this.dad, idDegress);
        msg.showAndWait();

        this.mom = 0;
        this.dad = 0;
    }

    @FXML
    private void searchMon(ActionEvent event) {

        Parents p = Parents.getByDocument(this.cboDocMom.getValue() + this.txtMonDocument.getText());

        if (p == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(null);
            a.setContentText("Persona no Encontrada");
            a.showAndWait();
            return;
        }

        this.txtMonNames.setText(p.getNames());
        this.txtMonLastNames.setText(p.getLastNames());
        this.mom = p.getId();
    }

    @FXML
    private void searchDad(ActionEvent event) {

        Parents p = Parents.getByDocument(this.cboDocDad.getValue() + this.txDadDocument.getText());

        if (p == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(null);
            a.setContentText("Persona no Encontrada");
            a.showAndWait();
            return;
        }

        this.txtDadNames.setText(p.getNames());
        this.txtDadLastNames.setText(p.getLastNames());
        this.dad = p.getId();
    }

}
