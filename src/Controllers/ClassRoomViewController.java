package Controllers;

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
    }

    public void setTableClassRooms() {
        this.itesClassRoom.clear();
        this.colIdClassRoom.setCellValueFactory(new PropertyValueFactory("id"));
        this.colNameClassRoom.setCellValueFactory(new PropertyValueFactory("name"));
        this.colCountClassRoom.setCellValueFactory(new PropertyValueFactory("capacidad"));
        ClassRoom.getClassRoomTable(this.itesClassRoom);
        this.tableClassRooms.setItems(this.itesClassRoom);
    }

    @FXML
    private void addClassRoom(ActionEvent event) {
        
        String name = this.txtName.getText().toUpperCase();
        int capacity = Integer.parseInt(this.txtCapacity.getText());
        
        ClassRoom room = new ClassRoom(name, capacity);
        Alert msg = room.addClassRoom();
        msg.showAndWait();
        this.setTableClassRooms();
        
    }

    @FXML
    private void updateClassRoom(ActionEvent event) {
    }

    @FXML
    private void resest(ActionEvent event) {
    }

}
