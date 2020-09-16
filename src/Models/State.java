package Models;

import Config.Base;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;

public class State {
    private int id;
    private String name;

    public State(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public static void getStates(ObservableList list){
        try {
            Connection link = Base.getConnectionStatic();
            PreparedStatement ps = (PreparedStatement) link.prepareStatement("SELECT id, nombre FROM estado ORDER BY nombre");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                list.add(
                        new State(rs.getInt("id"), rs.getString("nombre"))
                );
            }
            
            link.close();
        } catch (SQLException e) {
            System.err.println("Error al setear los estados: " + e);
        }
    }

    @Override
    public String toString() {
        return name.toUpperCase();
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
    
    
    
}


