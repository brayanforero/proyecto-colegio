package Controllers;

import Config.Base;
import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
    private Label lblPeriodo;
    @FXML
    private Label lblTurno;
    @FXML
    private ImageView btnLogout;
    @FXML
    private MenuItem openListStudent;
    @FXML
    private Pane container;
    @FXML
    private MenuItem openRegisterStudent;
    private int idUser;
    private int idDegress;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        systemDate();

    }

    public void initAtributeUser(int id, String nameUser) {

        if (id > 0 && nameUser.length() > 0) {
            this.idUser = id;
            this.lblNameUser.setText(nameUser);
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Error: No se pudo cargar sus datos");
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
                this.idDegress = rs.getInt("id_grado");
                ps.close();
            } else {
                ps.close();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Error: La solictud de sus datos no se completo con exito. Por seguridad su acceso al sistema se cerrará.");
                alert.showAndWait();
                Platform.exit();
            }

        } catch (SQLException e) {
            System.err.println("Error en traer los datos:" + e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Error: Solicitud de datos no completada");
            alert.showAndWait();
            Platform.exit();

        }
    }

    public void systemDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        this.lblDate.setText(format.format(date) + "");
    }

    @FXML
    private void logOut(MouseEvent event) {

        try {
            Node src = (Node) event.getSource();
            Stage stage = (Stage) src.getScene().getWindow();
            stage.close();

            Parent root = FXMLLoader.load(getClass().getResource("/Views/LoginView.fxml"));

            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.setTitle("Escuela Bolivariana Santa Rita - Sistema de Inscripcion");
            newStage.setMinWidth(1024);
            newStage.setMinHeight(500);
            newStage.setMaxWidth(1024);
            newStage.setMaxHeight(500);
            newStage.setResizable(false);
            newStage.show();
        } catch (IOException e) {

            System.err.println("Error al abrir las ventana: " + e);
        }
    }

    @FXML
    private void openModuleList(ActionEvent event) {
        try {
            this.container.getChildren().removeAll();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/ListStudentView.fxml"));
            Node module = loader.load();
            
            ListStudentViewController controller = loader.getController();
            controller.initTable(this.idDegress);
            
            this.container.getChildren().add(module); 
                    
        } catch (IOException ex) {
            Logger.getLogger(TeacherViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void openModuleNewStudent(ActionEvent event) {
        openModuleContainer("RegisterStudentView");
    }

    public void openModuleContainer(String nameModule) {
        try {
            this.container.getChildren().removeAll();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/"+ nameModule +".fxml"));
            Node module = loader.load();
            this.container.getChildren().add(module); 
                    
        } catch (IOException ex) {
            Logger.getLogger(TeacherViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
