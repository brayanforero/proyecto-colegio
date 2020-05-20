/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Config.Base;
import com.mysql.jdbc.Connection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class TeacherViewController implements Initializable {

    @FXML
    private Label lblDegress;
    @FXML
    private Label lblSeccion;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblNameUser;
    @FXML
    private Label lblidUser;
    @FXML
    private Label lblPeriodo;
    @FXML
    private Label lblTurno;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Date date = new Date();SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        this.lblDate.setText(format.format(date) + "");
    }

    public void initAtributeUser(int id, String nameUser) {

        if (id > 0 && nameUser.length() > 0) {
            this.lblNameUser.setText(nameUser);
            this.lblidUser.setText(id + "");
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Estimado Usuario los Datos son Incorrectos");
            alert.showAndWait();
            Platform.exit();

        }

    }

    public void initDegressAsigned(int id) {

        try {
            Base base = new Base();
            Connection con = base.getConnectionStatic();
            PreparedStatement ps = con.prepareCall("call sp_grado_asignado(?)");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                this.lblDegress.setText(rs.getString("nivel"));
                this.lblSeccion.setText(rs.getString("seccion"));
                this.lblTurno.setText(rs.getString("turno"));
                this.lblPeriodo.setText(rs.getString("periodo"));

                ps.close();
            } else {
                ps.close();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Error: Solitud de datos no puedo ser procesada");
                alert.showAndWait();
                Platform.exit();
            }

        } catch (SQLException e) {
            System.err.println("Error en traer los datos:" + e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Error: Solitud de datos no puedo ser procesada");
            alert.showAndWait();
            Platform.exit();
        }
    }

}
