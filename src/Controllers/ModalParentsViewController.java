package Controllers;

import Models.History;
import Models.Phone;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ModalParentsViewController implements Initializable {

    @FXML
    private TableView<?> tableParents;
    ObservableList<Object> itemsParents = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> tblDocParents;
    @FXML
    private TableColumn<?, ?> tblNameParents;
    @FXML
    private TableColumn<?, ?> tblParents;
    @FXML
    private TableView<Phone> tablePhone;
    ObservableList<Phone> itemsPhones = FXCollections.observableArrayList();
    @FXML
    private TableColumn tblPhone;
    @FXML
    private TableView<History> tableHistory;
    ObservableList<History> itemsHistory = FXCollections.observableArrayList();
    @FXML
    private TableColumn tblPeriod;
    @FXML
    private TableColumn tblDegress;
    @FXML
    private TableColumn tblSection;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public void initSetData(int id){
        Phone.setTablePhone(this.itemsPhones, id);
        this.tblPhone.setCellValueFactory(new PropertyValueFactory("number"));
        this.tablePhone.setItems(this.itemsPhones);
        
        
        History.getHistoryByStudent(this.itemsHistory, id);
        this.tblPeriod.setCellValueFactory(new PropertyValueFactory("periodAsString"));
        this.tblDegress.setCellValueFactory(new PropertyValueFactory("degressAsString"));
        this.tblSection.setCellValueFactory(new PropertyValueFactory("sectionAsString"));
        this.tableHistory.setItems(this.itemsHistory);
    }
    
    

}
