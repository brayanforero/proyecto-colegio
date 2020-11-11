
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
import javafx.scene.control.TextField;

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

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
        
        if(userName.isEmpty() || userPass.isEmpty() ){
            
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
        
        
    }
    
    public void setWorkersCombo(){
        
        Workers.setWorkerCombo(this.itemsWorkers);
        this.cboWorkers.setItems(this.itemsWorkers);
    }
    
}
