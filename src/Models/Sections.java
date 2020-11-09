

package Models;

import Config.Base;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;

public class Sections {
    private int id;
    private String name;

    public Sections(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public static void getSections(ObservableList<Sections> list){
        try {
            Connection link = Base.getConnectionStatic();
            PreparedStatement ps = (PreparedStatement) link.prepareStatement("Select id_seccion AS id, letra FROM secciones");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Sections(rs.getInt("id"), rs.getString("letra"))
                );
            }
            link.close();
        } catch (SQLException e) {
            System.err.println("Error al cargar las secciones" + e);
        }
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
        return name;
    }
    
    
}
