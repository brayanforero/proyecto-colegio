package Controllers;

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
import javafx.scene.control.TextField;

public class UpdateWorkerViewController implements Initializable {

    @FXML
    private TextField txtSearchWorker;
    @FXML
    private ComboBox<String> cboSearchWorker;
    private ObservableList<String> itemsCbo = FXCollections.observableArrayList();
    @FXML
    private Button btnSearch;
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
    @FXML
    private TextField txtCargoWorker;
    @FXML
    private Button btnSave;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.setCombox();
    }

    public void setCombox() {
        this.itemsCbo.addAll("V-", "E-");
        this.cboDocWorker.setItems(this.itemsCbo);
        this.cboSearchWorker.setItems(this.itemsCbo);
    }

    @FXML
    private void searchWorker(ActionEvent event) {
        String doc = this.cboSearchWorker.getValue() + this.txtSearchWorker.getText();
        Workers worker = Workers.getWorkerByDoc(doc);

        if (worker == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Persona no encontrada");
            alert.showAndWait();
            return;
        }

        this.txtIdWorker.setText(worker.getId() + "");
        this.txtDocWorker.setText(worker.getIdentification());
        this.txtNameWorker.setText(worker.getNames());
        this.txtLastNameWorker.setText(worker.getLastNames());
        this.txtPhoneWorker.setText(worker.getPhone());
        this.txtEmailWorker.setText(worker.getEmail());
        this.txtCargoWorker.setText(worker.getCargo());
    }

    @FXML
    private void saveData(ActionEvent event) {
        int id = Integer.parseInt(this.txtIdWorker.getText());
        String doc = this.txtDocWorker.getText().toUpperCase();
        String name = this.txtNameWorker.getText().toUpperCase();
        String lastName = this.txtLastNameWorker.getText().toUpperCase();
        String email = this.txtEmailWorker.getText().toUpperCase();
        String cargo = this.txtCargoWorker.getText().toUpperCase();
        String phone = this.txtPhoneWorker.getText();
        
        Workers worker = new Workers(id, cargo, email, phone, name, lastName, doc);
        Alert msg = worker.UpdateWorker();
        msg.showAndWait();
    }

    @FXML
    private void setNewValueCombo(ActionEvent event) {
        this.cboDocWorker.setSelectionModel(this.cboSearchWorker.getSelectionModel());
    }

}
