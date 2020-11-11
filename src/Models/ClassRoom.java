package Models;

import Config.Base;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class ClassRoom {

    private int id;
    private String name;
    private int capacidad;

    public ClassRoom(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ClassRoom(int id, String name, int capacidad) {
        this.id = id;
        this.name = name;
        this.capacidad = capacidad;
    }
    
    public ClassRoom( String name, int capacidad) {
        
        this.name = name;
        this.capacidad = capacidad;
    }

    public Alert addClassRoom() {
        Alert msg = null;
        try {
            Connection con = Base.getConnectionStatic();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("INSERT INTO aulas (nombre, capacidad) VALUES(?,?)");
            ps.setString(1, this.getName());
            ps.setInt(2, this.getCapacidad());
            ps.executeUpdate();

            msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setHeaderText(null);
            msg.setContentText("Ambiente de apredizaje agregado");
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error al registrar la aula: " + ex);
            msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setHeaderText(null);
            msg.setContentText(Base.getMessage(ex));
        }
        return msg;
    }

    public static void getClassRoomCombo(ObservableList<ClassRoom> list) {

        try {
            Connection link = Base.getConnectionStatic();
            PreparedStatement ps = (PreparedStatement) link.prepareStatement("SELECT id_aula, nombre FROM aulas");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(
                        new ClassRoom(rs.getInt("id_aula"), rs.getString("nombre"))
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al llenar las aulas: " + e);
        }
    }
    
    public Alert updateClassRoom() {
        Alert msg = null;
        try {
            Connection con = Base.getConnectionStatic();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("UPDATE aulas SET nombre = ?, capacidad = ? WHERE id_aula = ? LIMIT 1");
            ps.setString(1, this.getName());
            ps.setInt(2, this.getCapacidad());
            ps.setInt(3, this.getId());
            ps.executeUpdate();

            msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setHeaderText(null);
            msg.setContentText("Ambiente de apredizaje actualizado");
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error al registrar la aula: " + ex);
            msg = new Alert(Alert.AlertType.ERROR);
            msg.setHeaderText(null);
            msg.setContentText(Base.getMessage(ex));
        }
        return msg;
    }

    public static void getClassRoomTable(ObservableList<ClassRoom> list) {

        try {
            Connection link = Base.getConnectionStatic();
            PreparedStatement ps = (PreparedStatement) link.prepareStatement("SELECT id_aula, nombre,capacidad FROM aulas");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(
                        new ClassRoom(rs.getInt("id_aula"), rs.getString("nombre"), rs.getInt("capacidad"))
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al llenar tabla de aulas: " + e);
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

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return name;
    }

}
