package Controllers;

import Config.Utilities;
import Models.Workers;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class UpdateWorkerViewController implements Initializable {

    @FXML
    private TextField txtSearchWorker;
    private ComboBox<String> cboSearchWorker;
    private ObservableList<String> itemsCbo = FXCollections.observableArrayList();
    @FXML
    private TextField txtIdWorker;
    @FXML
    private ComboBox<String> cboDocWorker;
    @FXML
    private TextField txtDocWorker;
    @FXML
    private TextField txtNameWorker;
    @FXML
    private TextField txtLastNameWorker;
    @FXML
    private TextField txtPhoneWorker;
    @FXML
    private TextField txtEmailWorker;
    private TextField txtCargoWorker;
    @FXML
    private Button btnSave;
    @FXML
    private ComboBox<String> cboCargo;
    private ObservableList<String> itemsCargo = FXCollections.observableArrayList();
    @FXML
    private Pane paneData;
    @FXML
    private TableView<Workers> tblWorkersFilters;
    ObservableList<Workers> listFilter = FXCollections.observableArrayList();
    private TableColumn colId;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colLastName;
    @FXML
    private Button btnReset;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.setCombox();
        this.paneData.setVisible(false);
        this.setTable();
        this.initEvet();
    }

    public void setCombox() {
        this.itemsCbo.addAll("V-", "E-");
        this.cboDocWorker.setItems(this.itemsCbo);

        this.itemsCargo.addAll("DOCENTE", "DIRECTOR", "OBRERO", "SECRETARIA");
        this.cboCargo.setItems(this.itemsCargo);
    }

    public void setTable() {
        this.listFilter.clear();

        if (this.txtSearchWorker.getText().isEmpty()) {
            this.listFilter.clear();
            this.colName.setCellValueFactory(new PropertyValueFactory("names"));
            this.colLastName.setCellValueFactory(new PropertyValueFactory("lastNames"));
            Workers.getWorkerAll(this.listFilter);
            this.tblWorkersFilters.setItems(this.listFilter);
            return;
        }
        
        this.colName.setCellValueFactory(new PropertyValueFactory("names"));
        this.colLastName.setCellValueFactory(new PropertyValueFactory("lastNames"));
        Workers.getWorkerAll(this.listFilter, this.txtSearchWorker.getText().toUpperCase());
        this.tblWorkersFilters.setItems(this.listFilter);
    }

    @FXML
    private void saveData(ActionEvent event) {
        
        if (this.txtDocWorker.getText().length() < 8) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Formato no valido para una cédula");
            a.setHeaderText(null);
            a.showAndWait();
            return;
        }
        if (this.txtPhoneWorker.getText().length() < 7) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Formato no valido para un número telefonico");
            a.setHeaderText(null);
            a.showAndWait();
            return;
        }
        
        if (!Utilities.validEmail(this.txtEmailWorker.getText())) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Formato de email no valido");
            a.setHeaderText(null);
            a.showAndWait();
            return;
        }
        if (this.txtNameWorker.getText().isEmpty() || this.txtNameWorker.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Completa los campos");
            a.setHeaderText(null);
            a.showAndWait();
            return;
        }

        int id = Integer.parseInt(this.txtIdWorker.getText());
        String doc = this.cboDocWorker.getValue() + this.txtDocWorker.getText().toUpperCase();
        String name = this.txtNameWorker.getText().toUpperCase();
        String lastName = this.txtLastNameWorker.getText().toUpperCase();
        String email = this.txtEmailWorker.getText().toUpperCase();
        String phone = this.txtPhoneWorker.getText();
        String cargo = this.cboCargo.getValue();
        Workers worker = new Workers(id, cargo, email, phone, name, lastName, doc);
        Alert msg = worker.UpdateWorker();
        msg.showAndWait();
        this.txtSearchWorker.setText("");
        this.paneData.setVisible(false);
        this.setTable();
    }

   
    @FXML
    private void selectItem(MouseEvent event) {

       Workers worker = this.tblWorkersFilters.getSelectionModel().getSelectedItem();
        
        this.txtIdWorker.setText(worker.getId() + "");
        this.txtDocWorker.setText(worker.getIdentification().substring(2));
        this.txtNameWorker.setText(worker.getNames());
        this.txtLastNameWorker.setText(worker.getLastNames());
        this.txtPhoneWorker.setText(worker.getPhone());
        this.txtEmailWorker.setText(worker.getEmail());
        this.cboDocWorker.getSelectionModel().select(worker.getIdentification().substring(0, 2));
        this.cboCargo.getSelectionModel().select(worker.getCargo());
        this.paneData.setVisible(true);
    }


    private void searchWorkerFilter(KeyEvent event) {
        setTable();
    }
    
    public void initEvet(){
        this.txtSearchWorker.setOnKeyTyped(e->Utilities.onlyLetter(e));
        this.txtDocWorker.setOnKeyTyped(e->Utilities.onlyDigit(e));
        this.txtPhoneWorker.setOnKeyTyped(e->Utilities.onlyDigit(e));
        this.txtNameWorker.setOnKeyTyped(e->Utilities.onlyLetter(e));
        this.txtLastNameWorker.setOnKeyTyped(e->Utilities.onlyLetter(e));
        
        this.txtSearchWorker.setOnKeyTyped(e->{
            this.setTable();
        });
    }

    @FXML
    private void reset(ActionEvent event) {
        this.paneData.setVisible(false);
    }

}
