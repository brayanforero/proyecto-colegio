
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

/**
 * FXML Controller class
 *
 * @author Brayan
 */
public class AdminViewController implements Initializable {

    @FXML
    Label lblDate;
    @FXML
    private Label lblPeriodo;
    @FXML
    private Label lblNameUser;
    @FXML
    private ImageView btnLogout;
    @FXML
    private Pane container;

    private int idUser;
    private int idPeriod;
    @FXML
    private MenuItem itemOpenNewPeriod;
    @FXML
    private MenuItem itemNewWorker;
    @FXML
    private MenuItem itemNewUser;
    @FXML
    private MenuItem itemNewDegress;
    @FXML
    private MenuItem itemListWorkers;
    @FXML
    private MenuItem itemUpdateWorker;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        systemDate();
        getInfoPeriod();
    }

    public void systemDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        this.lblDate.setText(format.format(date) + "");
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

    public void getInfoPeriod() {

        try {
            Connection con = Base.getConnectionStatic();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT id_periodo, codigo FROM periodos WHERE vigencia = 1 LIMIT 1");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.lblPeriodo.setText(rs.getString("codigo"));
                this.idPeriod = rs.getInt("id_periodo");
            } else {
                this.lblPeriodo.setText("N/A");
                this.idPeriod = 0;
            }
        } catch (SQLException ex) {
            System.err.println("Error al traer el periodo");
        }
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
    private void newPeriod(ActionEvent event) {

        openModuleContainer("ViewNewPeriod");
    }

    @FXML
    private void openNewWorker(ActionEvent event) {
        openModuleContainer("NewWorkerView");
    }

    @FXML
    private void openNewUser(ActionEvent event) {
        openModuleContainer("NewUserView");
    }

    @FXML
    private void openNewDegress(ActionEvent event) {
        openModuleContainer("NewDegressView");
    }
    @FXML
    private void openLIstWorkers(ActionEvent event) {
        
        openModuleContainer("ListWorkersView");
    }

    public void openModuleContainer(String nameModule) {
        ObservableList<Node> nodes =  this.container.getChildren();
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
    private void openUpdateWorker(ActionEvent event) {
        this.openModuleContainer("UpdateWorkerView");
    }


}
