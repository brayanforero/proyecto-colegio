/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Config.Utilities;
import Models.Users;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Edwin
 */
public class ForgotPasswordController implements Initializable {

    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private ComboBox<String> cboDocSearch;
    private ObservableList<String> items = FXCollections.observableArrayList();
    @FXML
    private Pane paneRecovery;
    @FXML
    private Label lblQuestion;
    @FXML
    private TextField txtResQuestion;
    @FXML
    private Button btnReset;
    @FXML
    private Label lblHello;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtRePassword;
    @FXML
    private Button btnSave;
    Users u = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.initCombo();
        this.paneRecovery.setVisible(false);
        this.txtSearch.setOnKeyTyped(e -> Utilities.onlyDigit(e));
    }

    @FXML
    private void search(ActionEvent event) {

        String cedula = this.cboDocSearch.getValue() + this.txtSearch.getText();

        this.u = Users.getInfoRecovery(cedula);

        if (u == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Cedula no encontrada");
            a.setHeaderText(null);
            a.showAndWait();
            return;
        }

        this.lblQuestion.setText(this.u.getQuestion());
        this.lblHello.setText("Hola " + this.u.getWorkers());
        this.paneRecovery.setVisible(true);
    }

    public void initCombo() {

        this.items.addAll("V-", "E-");
        this.cboDocSearch.setItems(this.items);
        this.cboDocSearch.getSelectionModel().select(0);
    }

    @FXML
    private void reset(ActionEvent event) {

        this.paneRecovery.setVisible(false);
        this.txtSearch.setText("");
    }

    @FXML
    private void saveData(ActionEvent event) {

        if (!this.txtPassword.getText().equalsIgnoreCase(this.txtRePassword.getText())) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Las contraseñas no conciden");
            a.setHeaderText(null);
            a.showAndWait();
            return;
        }

        if (!this.txtResQuestion.getText().equalsIgnoreCase(this.u.getRequest())) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Respuesta erronea");
            a.setHeaderText(null);
            a.showAndWait();
            return;
        }

        Users user = new Users();
        user.setId(this.u.getId());
        user.setPassword(this.txtPassword.getText());

        Alert a = user.changePassword();
        a.showAndWait();

        Node src = (Node) event.getSource();
        Stage stage = (Stage) src.getScene().getWindow();
        stage.close();

    }

}
