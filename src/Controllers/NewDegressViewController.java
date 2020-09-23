package Controllers;

import Config.Base;
import Models.ClassRoom;
import Models.Degress;
import Models.Period;
import Models.Section;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.layout.Pane;

public class NewDegressViewController implements Initializable {

    @FXML
    private Button btnSearchWorker;
    @FXML
    private TextField txtPlaceNameWorker;
    @FXML
    private TextField txtPlaceLastNameWorker;
    @FXML
    private Button buttonSavedDegress;
    private TextField txtIdMatricula;
    @FXML
    private TextField txtIdAula;
    @FXML
    private ComboBox<String> cboDegress;
    @FXML
    private ComboBox<String> cboTurno;

    private ObservableList degress;
    private ObservableList turnos;
    @FXML
    private TextField txtIdWorker;
    @FXML
    private TextField txtSearchDoc;
    @FXML
    private TextField txtIdSeccion;
    @FXML
    private Pane paneShowRegisterDegress;
    @FXML
    private TextField txtIdPeriodo;
    @FXML
    private ComboBox<Period> cboPeriod;
    private ObservableList<Period> itemsPeriod = FXCollections.observableArrayList();
    @FXML
    private ComboBox<ClassRoom> cboClassRoom;
    private ObservableList<ClassRoom> itemsRoom = FXCollections.observableArrayList();
    @FXML
    private ComboBox<Section> cboSection;
    private ObservableList<Section> itemsSection = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initComboBox();
    }

    @FXML
    private void searchWorker(ActionEvent event) {

        try {
            Connection con = Base.getConnectionStatic();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT id_personal, nombre, apellido FROM personal WHERE cedula = ?");
            ps.setString(1, this.txtSearchDoc.getText().toUpperCase());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                this.txtIdWorker.setText(rs.getInt("id_personal") + "");
                this.txtPlaceNameWorker.setText(rs.getString("nombre").toUpperCase());
                this.txtPlaceLastNameWorker.setText(rs.getString("apellido").toUpperCase());
                con.close();
                this.paneShowRegisterDegress.setVisible(true);
                return;
            }

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Persona no encontrada");
            alert.showAndWait();
        } catch (SQLException e) {
            System.err.println("Error al bucar a la persona: " + e);
        }
    }

    

    @FXML
    private void newDegress(ActionEvent event) {

        Period period = this.cboPeriod.getValue();
        int worker = Integer.parseInt(this.txtIdWorker.getText());
        ClassRoom aula = this.cboClassRoom.getValue();
        Section seccion = this.cboSection.getValue();
        String grado = this.cboDegress.getValue();
        String turno = this.cboTurno.getValue();

        Degress newDegress = new Degress(period.getId(), worker, aula.getId(), seccion.getId(), grado, turno);

        Alert msg = newDegress.degressAsigned();
        msg.showAndWait();
    }
    
    public void initComboBox() {
        /*INIT GRADOS*/
        this.degress = FXCollections.observableArrayList();
        this.degress.addAll("1RO", "2DO", "3RO", "4TO", "5TO", "6TO");
        this.cboDegress.setItems(this.degress);
        /*INIT TURNOS*/
        this.turnos = FXCollections.observableArrayList();
        this.turnos.addAll("MAÑANA", "TARDE", "NOCHE");
        this.cboTurno.setItems(this.turnos);
        
        Period.getPeridCombo(this.itemsPeriod);
        this.cboPeriod.setItems(this.itemsPeriod);
        
        Section.getComboSection(this.itemsSection);
        this.cboSection.setItems(this.itemsSection);
        
        ClassRoom.getClassRoomCombo(this.itemsRoom);
        this.cboClassRoom.setItems(this.itemsRoom);
    }

}
