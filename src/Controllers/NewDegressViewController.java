/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Config.Base;
import Models.Degress;
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

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class NewDegressViewController implements Initializable {

    @FXML
    private Button btnSearchWorker;
    @FXML
    private TextField txtPlaceNameWorker;
    @FXML
    private TextField txtPlaceLastNameWorker;
    @FXML
    private Button buttonSavedDegress;
    @FXML
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

    /**
     * Initializes the controller class.
     */
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

    public void initComboBox() {
        /*INIT GRADOS*/
        this.degress = FXCollections.observableArrayList();
        this.degress.addAll("1RO", "2DO", "3RO", "4TO", "5TO", "6TO");
        this.cboDegress.setItems(this.degress);
        /*INIT TURNOS*/
        this.turnos = FXCollections.observableArrayList();
        this.turnos.addAll("MAÑANA", "TARDE", "NOCHE");
        this.cboTurno.setItems(this.turnos);
    }

    @FXML
    private void newDegress(ActionEvent event) {
        
       int matricula = Integer.parseInt(this.txtIdMatricula.getText());
       int worker = Integer.parseInt(this.txtIdWorker.getText());
       int aula = Integer.parseInt(this.txtIdAula.getText());
       int seccion =  Integer.parseInt(this.txtIdSeccion.getText());
       String grado = this.cboDegress.getValue();
       String turno = this.cboTurno.getValue();
       
       Degress newDegress = new Degress(matricula, worker, aula, seccion, grado, turno);
       
       Alert msg = newDegress.degressAsigned();
       msg.showAndWait();
    }

}
