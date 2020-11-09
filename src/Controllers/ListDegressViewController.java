package Controllers;

import Config.Base;
import Files.Pdf;
import Models.ClassRoom;
import Models.Degress;
import Models.Period;
import Models.Section;
import Models.Sections;
import Models.Workers;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    @FXML
    private Button btnAddDegress;
    @FXML
    private Button btnUpdateDegress;
    @FXML
    private TextField txtIdPeriod;
    @FXML
    private TextField txtNameDegress;
    @FXML
    private ComboBox<Workers> cboWorker;
    private ObservableList<Workers> itemsWorkers = FXCollections.observableArrayList();
    @FXML
    private ComboBox<Sections> cboSection;
    private ObservableList<Sections> itemsSections = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> cboTurno;
    private ObservableList<String> itemsTurno = FXCollections.observableArrayList();
    @FXML
    private ComboBox<ClassRoom> cboClassrooms;
    private ObservableList<ClassRoom> itemsClassRooms = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.setTableDegress();
        this.setCombos();
    }

    public void setPeriod(int id, String period) {
        this.lblCodPeriod.setText(this.lblCodPeriod.getText().concat(" " + period));
        this.txtIdPeriod.setText(id + "");
    }

    public void setTableDegress() {

        this.itemsDegress.clear();

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

    public void setCombos() {
        Workers.setWorkerCombo(this.itemsWorkers);
        this.cboWorker.setItems(this.itemsWorkers);
        this.cboWorker.getSelectionModel().select(0);

        Sections.getSections(this.itemsSections);
        this.cboSection.setItems(this.itemsSections);
        this.cboSection.getSelectionModel().select(0);

        this.itemsTurno.addAll("MATUTINO", "DESPERTINO", "DIURNO");
        this.cboTurno.setItems(this.itemsTurno);
        this.cboTurno.getSelectionModel().select(0);

        ClassRoom.getClassRoomCombo(this.itemsClassRooms);
        this.cboClassrooms.setItems(this.itemsClassRooms);
        this.cboClassrooms.getSelectionModel().select(0);
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

    @FXML
    private void addPeriod(ActionEvent event) {

        int idPeriod = Integer.parseInt(this.txtIdPeriod.getText());
        Workers worker = this.cboWorker.getValue();
        ClassRoom aula = this.cboClassrooms.getValue();
        Sections section = this.cboSection.getValue();
        String name = this.txtNameDegress.getText().toUpperCase();
        String turno = this.cboTurno.getValue();

        Degress newDegress = new Degress(idPeriod, worker.getId(), aula.getId(), section.getId(), name, turno);
        Alert msg = newDegress.degressAsigned();
        msg.showAndWait();
        this.setTableDegress();
    }

}
