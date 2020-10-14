package Models;

import Config.Base;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;

public class History {

    private int id;
    private String periodAsString;
    private String degressAsString;
    private String sectionAsString;

    public History(int id, String periodAsString, String degressAsString, String sectionAsString) {
        this.id = id;
        this.periodAsString = periodAsString;
        this.degressAsString = degressAsString;
        this.sectionAsString = sectionAsString;
    }

    public History() {
    }

    public static void getHistoryByStudent(ObservableList<History> list, int idStudent) {

        try {
            Connection con = Base.getConnectionStatic();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT h.id_historia AS id, g.nivel AS grado, s.letra AS seccion, p.codigo AS periodo FROM historial_academico AS h INNER JOIN grados AS g ON g.id_grado = h.id_grado INNER JOIN secciones AS s ON s.id_seccion = g.id_seccion INNER JOIN periodos AS p ON p.id_periodo = g.id_periodo WHERE h.id_estudiante = ?");
            ps.setInt(1, idStudent);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(
                     new History(rs.getInt("id"), rs.getString("periodo"), rs.getString("grado"), rs.getString("seccion"))
                );
            }
            
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error al setear le historial: " + ex);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPeriodAsString() {
        return periodAsString;
    }

    public void setPeriodAsString(String periodAsString) {
        this.periodAsString = periodAsString;
    }

    public String getDegressAsString() {
        return degressAsString;
    }

    public void setDegressAsString(String degressAsString) {
        this.degressAsString = degressAsString;
    }

    public String getSectionAsString() {
        return sectionAsString;
    }

    public void setSectionAsString(String sectionAsString) {
        this.sectionAsString = sectionAsString;
    }
    
    
}
