package Models;

import Config.Base;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class Sections {

    private int id;
    private String name;

    public Sections(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Sections(String name) {
        this.name = name;
    }

    public static void getSections(ObservableList<Sections> list) {
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

    public Alert addSecion() {
        Alert msg = null;
        try {
            Connection con = Base.getConnectionStatic();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("INSERT INTO secciones VALUES(NULL, ?)");
            ps.setString(1, this.getName());
            ps.executeUpdate();

            msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setHeaderText(null);
            msg.setContentText("Seccion agregada");
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error al registrar la seccion: " + ex);
            msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setHeaderText(null);
            msg.setContentText(Base.getMessage(ex));
        }
        return msg;
    }
    
    public Alert updateSection() {
        Alert msg = null;
        try {
            Connection con = Base.getConnectionStatic();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("UPDATE secciones SET letra = ? WHERE id_seccion = ? LIMIT 1");
            ps.setString(1, this.getName());
            ps.setInt(2, this.getId());
            ps.executeUpdate();

            msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setHeaderText(null);
            msg.setContentText("Seccion actualizada");
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error al registrar la seccion: " + ex);
            msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setHeaderText(null);
            msg.setContentText(Base.getMessage(ex));
        }
        return msg;
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
