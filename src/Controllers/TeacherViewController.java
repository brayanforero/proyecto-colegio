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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Brayan
 */
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
                this.lblDegress.setText( rs.getString("nivel") );
                this.lblSeccion.setText( rs.getString("seccion") );
            }
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error en traer los datos:" + e);
        }
    }

}
