/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Config.Utilities;
import Models.Sections;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ListSectionsViewController implements Initializable {

    @FXML
    private TextField txtNameSection;
    @FXML
    private Button btnAddSection;
    @FXML
    private TableView<Sections> tblSections;
    ObservableList<Sections> items = FXCollections.observableArrayList();
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colName;
    @FXML
    private Button btnUpdateSection;
    @FXML
    private TextField txtIdSection;
    @FXML
    private Button btnReset;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.initTable();
        this.btnUpdateSection.setVisible(false);
        this.txtNameSection.setOnKeyTyped(e->Utilities.onlyLetterAndDigit(e));
    }    
    
    
    public void initTable(){
        
        this.colId.setCellValueFactory(new PropertyValueFactory("id"));
        this.colName.setCellValueFactory(new PropertyValueFactory("name"));
        this.items.clear();
        Sections.getSections(this.items);
        this.tblSections.setItems(this.items);
    }
    @FXML
    private void addSection(ActionEvent event) {
        
        if(this.txtNameSection.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Completa los campos");
            a.setHeaderText(null);
            a.showAndWait();
            return;
        }
        String name = this.txtNameSection.getText().toUpperCase();
        
        Sections section = new Sections(name);
        Alert msg = section.addSecion();
        
        msg.showAndWait();
        this.initTable();
        this.resetFields();
    }

    @FXML
    private void selectedItem(MouseEvent event) {
        
        Sections section = this.tblSections.getSelectionModel().getSelectedItem();
        
        this.txtNameSection.setText(section.getName());
        this.txtIdSection.setText(section.getId() + "");
        this.btnAddSection.setVisible(false);
        this.btnUpdateSection.setVisible(true);
    }

    @FXML
    private void updateSection(ActionEvent event) {
        if(this.txtNameSection.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Completa los campos");
            a.setHeaderText(null);
            a.showAndWait();
            return;
        }
        String name = this.txtNameSection.getText().toUpperCase();
        int id = Integer.parseInt(this.txtIdSection.getText());
        Sections section = new Sections(id, name);
        Alert msg = section.updateSection();
        msg.showAndWait();
        this.initTable();
        this.resetFields();
    }

    @FXML
    private void resetFields(ActionEvent event) {
        this.txtNameSection.setText("");
        this.btnAddSection.setVisible(true);
        this.btnUpdateSection.setVisible(false);
    }
    private void resetFields() {
        this.txtNameSection.setText("");
        this.btnAddSection.setVisible(true);
        this.btnUpdateSection.setVisible(false);
    }
    
}
