
package Controllers;

import Models.Students;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ListStudentViewController implements Initializable {

    @FXML
    private TableView<Students> tblListStudent;
    @FXML
    private TableColumn colIdUser;
    @FXML
    private TableColumn colDocBoy;
    @FXML
    private TableColumn colNamesBoy;
    @FXML
    private TableColumn colDateOfBirth;
    @FXML
    private TableColumn colAge;
    @FXML
    private TableColumn colGender;
    @FXML
    private TableColumn colRepeat;
    @FXML
    private TableColumn colIfCanaima;
    @FXML
    private TableColumn colIfBeca;
    @FXML
    private TableColumn colIdFamily;
    @FXML
    private TableColumn colNamesFamily;
    @FXML
    private TableColumn colDocFamily;
    private ObservableList<Students> listStudent;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
    }

    public void initTable(){
        this.listStudent = FXCollections.observableArrayList();
        this.colIdUser.setCellValueFactory(new PropertyValueFactory("idStudent"));
        this.colDocBoy.setCellValueFactory(new PropertyValueFactory("identification"));
        this.colNamesBoy.setCellValueFactory(new PropertyValueFactory("names"));
        this.colDateOfBirth.setCellValueFactory(new PropertyValueFactory("dateOfBirth"));
        this.colAge.setCellValueFactory(new PropertyValueFactory("age"));
        this.colGender.setCellValueFactory(new PropertyValueFactory("gender"));
        this.colRepeat.setCellFactory(new PropertyValueFactory("ifRepeat"));
        this.colIfCanaima.setCellValueFactory(new PropertyValueFactory("ifCanaima"));
        this.colIfBeca.setCellValueFactory(new PropertyValueFactory("ifBeca"));
        this.colNamesFamily.setCellValueFactory(new PropertyValueFactory("nameParent"));
        this.colDocFamily.setCellValueFactory(new PropertyValueFactory("docParent"));
        
        Students.getListStudenByDegress(1, listStudent);
        this.tblListStudent.setItems(listStudent);
    }
    
}
