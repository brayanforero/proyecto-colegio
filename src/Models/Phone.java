

package Models;

import Config.Base;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;

public class Phone {
    private int id;
    private String number;
    public Phone() {
    }

    public Phone(int id, String number) {
        this.id = id;
        this.number = number;
    }
    
    public static void setTablePhone(ObservableList<Phone> list, int id){
        
        try {
            Connection con = Base.getConnectionStatic();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT ph.id, ph.telefono AS numero FROM telefonos_de_estudiantes AS ph WHERE ph.id_estudiante = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                list.add(
                  new Phone(rs.getInt("id"), rs.getString("numero"))
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al traer los telefonos: " + e);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    
    
}
