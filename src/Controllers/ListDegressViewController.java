package Controllers;

import Config.Base;
import Files.Pdf;
import Models.Degress;
import com.mysql.jdbc.Connection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListDegressViewController implements Initializable {
    
     
    @FXML
    private Label lblCodPeriod;
    @FXML
    private TableView<Degress> tblDegress;
    ObservableList<Degress> itemsDegress = FXCollections.observableArrayList();
    @FXML
    private TableColumn colIdDegress;
    @FXML
    private TableColumn colDegress;
    @FXML
    private TableColumn colSection;
    @FXML
    private TableColumn colTurno;
    @FXML
    private TableColumn colClassRoom;
    @FXML
    private TableColumn colTeacher;
    @FXML
    private TableColumn colPeriod;
    @FXML
    private Button btnExportsInfo;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.setTableDegress();
    }
    
    public void setPeriod(String period){
        this.lblCodPeriod.setText(this.lblCodPeriod.getText().concat(" "+ period));
    }
    
    public void setTableDegress() {
        
        this.colIdDegress.setCellValueFactory(new PropertyValueFactory("id"));
        this.colDegress.setCellValueFactory(new PropertyValueFactory("level"));
        this.colSection.setCellValueFactory(new PropertyValueFactory("sectionString"));
        this.colTurno.setCellValueFactory(new PropertyValueFactory("turn"));
        this.colTeacher.setCellValueFactory(new PropertyValueFactory("workerString"));
        this.colClassRoom.setCellValueFactory(new PropertyValueFactory("classRoomString"));
        this.colPeriod.setCellValueFactory(new PropertyValueFactory("periodCode"));
        Degress.getDegressForTable(this.itemsDegress);
        this.tblDegress.setItems(this.itemsDegress);
    }

    @FXML
    private void exportsDegress(ActionEvent event) {
        
        try {
            Connection con = Base.getConnectionStatic();
            Pdf pdf = new Pdf("lista_grados", null, con);
            pdf.generate();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ListDegressViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
