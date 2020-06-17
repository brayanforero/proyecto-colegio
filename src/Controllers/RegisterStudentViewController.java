
package Controllers;

import Config.Base;
import com.mysql.jdbc.Connection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class RegisterStudentViewController implements Initializable {

    @FXML
    private TextField txtNames;
    @FXML
    private TextField txtLastNames;
    @FXML
    private TextField txtDocument;
    @FXML
    private DatePicker txtDateOfBirth;
    @FXML
    private TextField txtState;
    @FXML
    private TextField txtMun;
    @FXML
    private TextField txtLocal;
    @FXML
    private CheckBox checkCanaima;
    @FXML
    private CheckBox checkBeca;
    @FXML
    private TextField txtSalud;
    @FXML
    private TextArea txtDesSalud;
    @FXML
    private Button btnSend;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveData(ActionEvent event) {
        String doc = this.txtDocument.getText().toUpperCase();
        String names = this.txtNames.getText().toUpperCase();
        String lastNames = this.txtLastNames.getText().toUpperCase();
        String dateOfBirth = this.txtDateOfBirth.getValue().toString();
        String gender = "M";
        int state = Integer.parseInt( this.txtState.getText() );
        int mun = Integer.parseInt( this.txtMun.getText() );
        int local = Integer.parseInt( this.txtLocal.getText() );
        boolean canaima = this.checkCanaima.isSelected();
        boolean beca = this.checkBeca.isSelected();
        String salud = this.txtSalud.getText().toUpperCase();
        String desSalud = this.txtDesSalud.getText().toUpperCase();
        
        
        try {
            Connection link = Base.getConnectionStatic();
            PreparedStatement ps = link.prepareCall("call sp_registrar_estudiante(?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, doc);
            ps.setString(2, names);
            ps.setString(3, lastNames);
            ps.setString(4, dateOfBirth);
            ps.setString(5, gender);
            ps.setInt(6, state);
            ps.setInt(7, mun);
            ps.setInt(8, local);
            ps.setBoolean(9, canaima);
            ps.setBoolean(10, beca);
            ps.setString(11, salud);
            ps.setString(12, desSalud);
            
            ResultSet rs = ps.executeQuery();
            System.out.println("ID: " + rs.getString(0));
            link.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e);
            System.err.println("State: " + e.getSQLState());
            System.err.println("CODE: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            
        }
    }
    
}
