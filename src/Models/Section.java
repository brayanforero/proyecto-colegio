package Models;

import Config.Base;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class Section {
    private int id;
    private String letter;

    public Section(int id, String letter) {
        this.id = id;
        this.letter = letter;
    }

    public Section(String letter) {
        this.letter = letter;
    }
    
    
    public static void getComboSection(ObservableList<Section> list){
        
        try {
            Connection link = Base.getConnectionStatic();
            PreparedStatement ps = (PreparedStatement) link.prepareStatement("SELECT id_seccion, letra FROM secciones");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                list.add(
                    new Section(rs.getInt("id_seccion"), rs.getString("letra"))
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al llenar las secciones: " + e);
        }
    }
    
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    @Override
    public String toString() {
        return letter;
    }

    
    
    
    
}
