package Controllers;

import Models.Users;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListUserNotHabilitedViewController implements Initializable {

    @FXML
    private TableView<Users> tableUser;
    private ObservableList<Users> itemsUser = FXCollections.observableArrayList();
    @FXML
    private TableColumn colIdUser;
    @FXML
    private TableColumn colNameUser;
    @FXML
    private TableColumn colPerson;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.setTableUser();
    }
    
    public void setTableUser(){
        
        this.colIdUser.setCellValueFactory(new PropertyValueFactory("id"));
        this.colNameUser.setCellValueFactory(new PropertyValueFactory("name"));
        this.colPerson.setCellValueFactory(new PropertyValueFactory("workers"));
        Users.getUserNotHabilited(this.itemsUser);
        this.tableUser.setItems(this.itemsUser);
    }

}
