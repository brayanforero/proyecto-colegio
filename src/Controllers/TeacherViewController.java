package Controllers;

import Config.Base;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
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
    @FXML
    private MenuItem itemSearchStudent;
    @FXML
    private MenuItem openRegisterRegStudent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        systemDate();

    }

    public void initAtributeUser(int id, String nameUser) {

        if (id == 0 && nameUser.length() == 0) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Error: No se pudo cargar sus datos. Por seguridad sera cerrado el sistema");
            alert.showAndWait();
            Platform.exit();
            return;
        }

        this.idUser = id;
        this.lblNameUser.setText(nameUser);
    }

    public void initDegressAsigned(int id) {

        try {

            Connection con = Base.getConnectionStatic();
            PreparedStatement ps = (PreparedStatement) con.prepareCall("call sp_grado_asignado(?)");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Error: Estimado usuario(a) no se le asignado una grado. Por seguridad su acceso se cerrará");
                alert.showAndWait();
                Platform.exit();
                return;
            }

            this.lblDegress.setText(rs.getString("nivel"));
            this.lblSeccion.setText(rs.getString("seccion"));
            this.lblTurno.setText(rs.getString("turno"));
            this.lblPeriodo.setText(rs.getString("periodo"));
            this.idDegress = rs.getInt("id_grado");
            ps.close();

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
        ObservableList<Node> nodes = this.container.getChildren();
        this.container.getChildren().removeAll(nodes);
        try {

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
        ObservableList<Node> nodes = this.container.getChildren();
        this.container.getChildren().removeAll(nodes);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/RegisterStudentView.fxml"));
            Node module = loader.load();

            RegisterStudentViewController controller = loader.getController();
            controller.setIdDegressForRegister(this.idDegress);
            this.container.getChildren().add(module);

        } catch (IOException ex) {
            Logger.getLogger(TeacherViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void openModuleContainer(String nameModule) {
        ObservableList<Node> nodes = this.container.getChildren();
        this.container.getChildren().removeAll(nodes);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/" + nameModule + ".fxml"));
            Node module = loader.load();
            this.container.getChildren().add(module);

        } catch (IOException ex) {
            Logger.getLogger(TeacherViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void openSearchStudent(ActionEvent event) {
        openModuleContainer("SearchStudentView");
    }

    @FXML
    private void openModuleRegStudent(ActionEvent event) {
        ObservableList<Node> nodes = this.container.getChildren();
        this.container.getChildren().removeAll(nodes);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/RegisterStudentRegView.fxml"));
            Node module = loader.load();
            
            RegisteStudentRegViewController controller = loader.getController();
            controller.setIdDegressForRegister(this.idDegress);
            this.container.getChildren().add(module);

        } catch (IOException ex) {
            Logger.getLogger(TeacherViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
