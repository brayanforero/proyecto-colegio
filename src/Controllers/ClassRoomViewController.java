package Controllers;

import Config.Utilities;
import Models.ClassRoom;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ClassRoomViewController implements Initializable {

    @FXML
    private TableView<ClassRoom> tableClassRooms;
    private ObservableList<ClassRoom> itesClassRoom = FXCollections.observableArrayList();
    @FXML
    private TableColumn colIdClassRoom;
    @FXML
    private TableColumn colNameClassRoom;
    @FXML
    private TableColumn colCountClassRoom;
    @FXML
    private TableColumn colIsOcuped;
    @FXML
    private TextField txtCapacity;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtIdClassRoom;
    @FXML
    private Button btnAddClass;
    @FXML
    private Button btnUpdateClass;
    @FXML
    private Button btnReset;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.setTableClassRooms();
        this.btnUpdateClass.setVisible(false);
        this.initEvent();
    }

    public void setTableClassRooms() {
        this.itesClassRoom.clear();
        this.colIdClassRoom.setCellValueFactory(new PropertyValueFactory("id"));
        this.colNameClassRoom.setCellValueFactory(new PropertyValueFactory("name"));
        this.colCountClassRoom.setCellValueFactory(new PropertyValueFactory("capacidad"));
        ClassRoom.getClassRoomTable(this.itesClassRoom);
        this.tableClassRooms.setItems(this.itesClassRoom);
    }

    public void initEvent() {
        this.txtCapacity.setOnKeyTyped(e -> Utilities.onlyDigit(e));
    }

    @FXML
    private void addClassRoom(ActionEvent event) {
        if (!this.fieldsFormat()) {
            return;
        }
        String name = this.txtName.getText().toUpperCase();
        int capacity = Integer.parseInt(this.txtCapacity.getText());
        ClassRoom room = new ClassRoom(name, capacity);
        Alert msg = room.addClassRoom();
        msg.showAndWait();
        this.setTableClassRooms();
        this.resest();
    }

    @FXML
    private void updateClassRoom(ActionEvent event) {
        if (!this.fieldsFormat()) {
            return;
        }

        String name = this.txtName.getText().toUpperCase();
        int capacity = Integer.parseInt(this.txtCapacity.getText());
        int id = Integer.parseInt(this.txtIdClassRoom.getText());

        ClassRoom room = new ClassRoom(id, name, capacity);

        Alert msg = room.updateClassRoom();
        msg.showAndWait();

        this.setTableClassRooms();
        this.resest();
    }

    @FXML

    private void resest(ActionEvent event) {
        this.btnUpdateClass.setVisible(false);
        this.txtIdClassRoom.setText(null);
        this.btnAddClass.setVisible(true);
        this.txtName.setText(null);
        this.txtCapacity.setText(null);
    }

    private void resest() {
        this.btnUpdateClass.setVisible(false);
        this.txtIdClassRoom.setText(null);
        this.btnAddClass.setVisible(true);
        this.txtName.setText(null);
        this.txtCapacity.setText(null);
    }

    @FXML
    private void selectedItem(MouseEvent event) {

        ClassRoom room = this.tableClassRooms.getSelectionModel().getSelectedItem();

        this.txtIdClassRoom.setText(room.getId() + "");
        this.txtName.setText(room.getName());
        this.txtCapacity.setText(room.getCapacidad() + "");

        this.btnUpdateClass.setVisible(true);
        this.btnAddClass.setVisible(false);
    }

    public boolean fieldsFormat() {
        if (this.txtName.getText().isEmpty() || this.txtCapacity.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Llena todo los campos");
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }

        String name = this.txtName.getText().toUpperCase();
        int capacity = Integer.parseInt(this.txtCapacity.getText());

        if (capacity <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Ingrese una catidad valida");
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }

        return true;
    }
}
