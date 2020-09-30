package Models;

import Config.Base;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;

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

    public static void getClassRoomTable(ObservableList<ClassRoom> list) {

        try {
            Connection link = Base.getConnectionStatic();
            PreparedStatement ps = (PreparedStatement) link.prepareStatement("SELECT id_aula, nombre,capacidad FROM aulas");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(
                        new ClassRoom(rs.getInt("id_aula"), rs.getString("nombre"),rs.getInt("capacidad"))
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
