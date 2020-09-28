package Controllers;

import Models.Workers;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListWorkersViewController implements Initializable {

    @FXML
    private TableView<Workers> tableWorkers;
    private ObservableList itemsWorkers = FXCollections.observableArrayList();
    @FXML
    private TableColumn colIdWorker;
    @FXML
    private TableColumn colDocWorker;
    @FXML
    private TableColumn colNameWorker;
    @FXML
    private TableColumn colLastNameWorker;
    @FXML
    private TableColumn colEmailWorker;
    @FXML
    private TableColumn colCargoWorker;
    @FXML
    private TableColumn colPhoneWorker;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.setTableWorker();
    }



    public void setTableWorker(){
        
        
        this.colIdWorker.setCellValueFactory(new PropertyValueFactory("id"));
        this.colDocWorker.setCellValueFactory(new PropertyValueFactory("identification"));
        this.colNameWorker.setCellValueFactory(new PropertyValueFactory("names"));
        this.colLastNameWorker.setCellValueFactory(new PropertyValueFactory("lastNames"));
        this.colEmailWorker.setCellValueFactory(new PropertyValueFactory("email"));
        this.colCargoWorker.setCellValueFactory(new PropertyValueFactory("cargo"));
        this.colPhoneWorker.setCellValueFactory(new PropertyValueFactory("phone"));
        Workers.getWorkerAll(this.itemsWorkers);
        this.tableWorkers.setItems(this.itemsWorkers);
    }

    
    
}
