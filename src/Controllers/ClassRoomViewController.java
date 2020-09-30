package Controllers;

import Models.ClassRoom;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.setTableClassRooms();
    }

    public void setTableClassRooms() {
        
        this.colIdClassRoom.setCellValueFactory(new PropertyValueFactory("id"));
        this.colNameClassRoom.setCellValueFactory(new PropertyValueFactory("name"));
        this.colCountClassRoom.setCellValueFactory(new PropertyValueFactory("capacidad"));
        ClassRoom.getClassRoomTable(this.itesClassRoom);
        this.tableClassRooms.setItems(this.itesClassRoom);
    }

}
