package Controllers;

import Config.Base;
import Files.Pdf;
import Models.Students;
import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SearchStudentViewController implements Initializable {

    @FXML
    private ComboBox<String> cboDocSearch;
    private ObservableList<String> itemsDocSearch = FXCollections.observableArrayList();
    @FXML
    private TextField txtDocSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private Label itemOpenParents;
    @FXML
    private Pane PaneRight;
    @FXML
    private TextField txtNames;
    @FXML
    private TextField txtDoc;
    @FXML
    private TextField txtDateOfBirth;
    @FXML
    private TextField txtAge;
    @FXML
    private TextField txtGender;
    @FXML
    private TextField txtState;
    @FXML
    private TextField txtMun;
    @FXML
    private TextField txtLocal;
    @FXML
    private TextField txtCanaima;
    @FXML
    private TextField txtBeca;
    @FXML
    private TextField txtLastName;
    private int idStudent;
    @FXML
    private Button btnGeneratePdf;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.initCombos();
        this.PaneRight.setVisible(false);
    }

    public void initCombos() {
        this.itemsDocSearch.addAll("V-", "E-");
        this.cboDocSearch.setItems(this.itemsDocSearch);

        this.cboDocSearch.getSelectionModel().select("V-");
    }

    @FXML
    private void searchStudent(ActionEvent event) {
        String cedula = this.cboDocSearch.getValue() + this.txtDocSearch.getText();
        try {
            ResultSet rs = Students.getStudentByDoc(cedula);
            if (!rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Estudiante no Encontrado");
                alert.showAndWait();
                this.PaneRight.setVisible(false);
                return;
            }

            this.setFields(rs);
        } catch (SQLException ex) {
            System.err.println("Error al recibir los datos: " + ex);
        }
    }

    @FXML
    private void showParentsModal(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/ModalParentsView.fxml"));
            Parent root = loader.load();
            ModalParentsViewController controller = loader.getController();
            controller.initSetData(this.idStudent);
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.setResizable(false);
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.showAndWait();
        } catch (IOException e) {

            System.err.println("Error al abrir las ventana: " + e);
        }
    }

    private void setFields(ResultSet rs) {
        try {
            this.idStudent = rs.getInt("id_estudiante");
            this.txtNames.setText(rs.getString("nombre_nombres"));
            this.txtLastName.setText(rs.getString("nombre_apellidos"));
            this.txtDoc.setText(rs.getString("cedula"));
            this.txtDateOfBirth.setText(rs.getString("fecha_nacimiento"));
            this.txtAge.setText(rs.getString("edad"));
            this.txtGender.setText(rs.getString("genero"));
            this.txtCanaima.setText(rs.getString("canaima"));
            this.txtBeca.setText(rs.getString("beca"));
            this.txtState.setText(rs.getString("estado"));
            this.txtMun.setText(rs.getString("municipio"));
            this.txtLocal.setText(rs.getString("parroquia"));
            this.PaneRight.setVisible(true);
        } catch (SQLException ex) {
            System.err.println("Error al setear campos: " + ex);
        }
    }

    @FXML
    private void exportsStudent(ActionEvent event) {
        
        try {
            Map params = new HashMap();
            params.put("cedula", this.txtDoc.getText());
            Connection con = Base.getConnectionStatic();
            Pdf pdf = new Pdf("estudiante", params, con);
            pdf.generate();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(SearchStudentViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
