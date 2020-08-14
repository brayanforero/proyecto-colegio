package Controllers;

import Models.Students;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableColumn colNamesFamily;
    @FXML
    private TableColumn colDocFamily;
    private ObservableList<Students> listStudent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initTable(int idDesgress) {

        this.listStudent = FXCollections.observableArrayList();
        this.colIdUser.setCellValueFactory(new PropertyValueFactory("idStudent"));
        this.colDocBoy.setCellValueFactory(new PropertyValueFactory("identification"));
        this.colNamesBoy.setCellValueFactory(new PropertyValueFactory("fullname"));
        this.colDateOfBirth.setCellValueFactory(new PropertyValueFactory("dateOfBirth"));
        this.colAge.setCellValueFactory(new PropertyValueFactory("age"));
        this.colGender.setCellValueFactory(new PropertyValueFactory("gender"));
        this.colRepeat.setCellValueFactory(new PropertyValueFactory("currentRepeat"));
        this.colIfCanaima.setCellValueFactory(new PropertyValueFactory("currentCanaima"));
        this.colIfBeca.setCellValueFactory(new PropertyValueFactory("currentBeca"));

        this.colNamesFamily.setCellValueFactory(new PropertyValueFactory("fullNameParent"));
        this.colDocFamily.setCellValueFactory(new PropertyValueFactory("documentParent"));

        Students.getListStudenByDegress(idDesgress, listStudent);
        if (listStudent.size() > 0) {
            this.tblListStudent.setItems(listStudent);
            return;
        }

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText("Estimado usuario no hay estudiantes inscritos");
        alert.showAndWait();
    }

}
