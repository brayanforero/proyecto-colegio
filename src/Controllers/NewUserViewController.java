package Controllers;

import Models.Users;
import Models.Workers;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class NewUserViewController implements Initializable {

    @FXML
    private TextField txtNameUser;
    @FXML
    private PasswordField txtPassUser;
    @FXML
    private Button btnNewUser;
    @FXML
    private ComboBox<String> cboRolUser;
    ObservableList<String> option;
    @FXML
    private ComboBox<Workers> cboWorkers;
    private ObservableList<Workers> itemsWorkers = FXCollections.observableArrayList();
    @FXML
    private Button btnReset;
    @FXML
    private TableView<Users> tableUser;
    private ObservableList<Users> itemsUser = FXCollections.observableArrayList();
    @FXML
    private TableColumn colIdUser;
    @FXML
    private TableColumn colNameUser;
    @FXML
    private TableColumn colPerson;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.initCombo();
        this.setTableUser();
    }

    public void setTableUser() {
        this.itemsUser.clear();
        this.colIdUser.setCellValueFactory(new PropertyValueFactory("id"));
        this.colNameUser.setCellValueFactory(new PropertyValueFactory("name"));
        this.colPerson.setCellValueFactory(new PropertyValueFactory("workers"));
        Users.getUserHabilited(this.itemsUser);
        this.tableUser.setItems(this.itemsUser);
    }
    
    public void initCombo(){
       this.option = FXCollections.observableArrayList();
        this.option.addAll("ADMINSTRADOR", "DOCENTE");
        this.cboRolUser.setItems(this.option);
        this.setWorkersCombo();

        this.cboRolUser.getSelectionModel().select(0);
        this.cboWorkers.getSelectionModel().select(0); 
    }

    @FXML
    private void newUser(ActionEvent event) {

        Workers worker = this.cboWorkers.getValue();
        String userName = this.txtNameUser.getText().toUpperCase();
        String userPass = this.txtPassUser.getText().toUpperCase();
        String userRol = this.cboRolUser.getValue();

        if (userName.isEmpty() || userPass.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Debe rellenar los campos vacios");
            alert.setTitle("ERROR");
            alert.showAndWait();

            return;
        }

        Users user = new Users(worker.getId(), userName, userPass, userRol);

        Alert msg = user.newUser();

        msg.showAndWait();
        this.reset();
        this.setTableUser();
    }

    public void setWorkersCombo() {

        Workers.setWorkerCombo(this.itemsWorkers);
        this.cboWorkers.setItems(this.itemsWorkers);
    }

    @FXML
    private void reset(ActionEvent event) {

        this.txtNameUser.setText("");
        this.txtPassUser.setText("");
        this.cboRolUser.getSelectionModel().select(0);
        this.cboWorkers.getSelectionModel().select(0);
    }
    
    private void reset() {

        this.txtNameUser.setText("");
        this.txtPassUser.setText("");
        this.cboRolUser.getSelectionModel().select(0);
        this.cboWorkers.getSelectionModel().select(0);
    }

}
