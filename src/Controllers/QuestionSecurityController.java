/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Config.Base;
import Models.Users;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class QuestionSecurityController implements Initializable {

    @FXML
    private Button btnSave;
    @FXML
    private TextField txtQuestionSecurity;
    private TextField txtDocument;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtAnswer;
    
    private int idUser;
    @FXML
    private TextField txtUsername;

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void save(ActionEvent event) {
        
        if (this.txtQuestionSecurity.getText().isEmpty() || this.txtAnswer.getText().isEmpty() || this.txtUsername.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Completa los campos");
            a.setHeaderText(null);
            a.showAndWait();
            return;
        }
        
        String username = this.txtUsername.getText().toUpperCase();
        String question = this.txtQuestionSecurity.getText().toUpperCase();
        String answer = this.txtAnswer.getText().toUpperCase();
        
        Users u = new Users();
        u.setId(this.idUser);
        u.setName(username);
        u.setQuestion(question);
        u.setRequest(answer);
        
        Alert a = u.updateInfoToAccess();
        a.showAndWait();
        
        Node src = (Node) event.getSource();
        Stage stage = (Stage) src.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancel(ActionEvent event) {
        this.txtUsername.clear();
        this.txtQuestionSecurity.clear();
        this.txtAnswer.clear();
    }
    private void cancel() {
        
        this.txtDocument.clear();
        this.txtQuestionSecurity.clear();
        this.txtAnswer.clear();
    }
    
    public void setIdUser(int idUser) {
        try {
            this.idUser = idUser;
            
            
            Connection con = Base.getConnectionStatic();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT nombre, pregunta, respuesta FROM usuarios WHERE id_usuario = ?");
            ps.setInt(1, idUser);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                this.txtUsername.setText(rs.getString("nombre"));
                this.txtQuestionSecurity.setText(rs.getString("pregunta"));
                this.txtAnswer.setText(rs.getString("respuesta"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionSecurityController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
