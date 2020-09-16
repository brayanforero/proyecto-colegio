package Models;

import Config.Base;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;

public class Localidad {
    
    private int id;
    private String name;

    public Localidad(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name.toUpperCase();
    }
    
    public static void getLocales(ObservableList list, int mun){
        try {
            Connection link = Base.getConnectionStatic();
            PreparedStatement ps = (PreparedStatement) link.prepareStatement("SELECT id, nombre FROM parroquia WHERE municipio_id = ? ORDER BY nombre");
            ps.setInt(1, mun);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                list.add(
                        new Localidad(rs.getInt("id"), rs.getString("nombre"))
                );
            }
            
            link.close();
        } catch (SQLException e) {
            System.err.println("Error al setear las localidades: " + e);
        }
    }
}
