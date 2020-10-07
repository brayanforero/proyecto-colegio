package Models;

import Config.Base;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class Degress extends Base {

    private int id;
    private int idEnrollmnet;
    private int idWorkers;
    private int idClassroom;
    private int idSection;
    private String level;
    private String turn;
    private String classRoomString;
    private String workerString;
    private String periodCode;
    private String sectionString;

    public Degress(int id, int idEnrollmnet, int idWorkers, int idClassroom, int idSection, String level, String turn) {
        this.id = id;
        this.idEnrollmnet = idEnrollmnet;
        this.idWorkers = idWorkers;
        this.idClassroom = idClassroom;
        this.idSection = idSection;
        this.level = level;
        this.turn = turn;
    }

    public Degress(int idEnrollmnet, int idWorkers, int idClassroom, int idSection, String level, String turn) {
        this.idEnrollmnet = idEnrollmnet;
        this.idWorkers = idWorkers;
        this.idClassroom = idClassroom;
        this.idSection = idSection;
        this.level = level;
        this.turn = turn;
    }

    public Degress(int id, String level, String section,String turn,String workerString, String classRoomString,  String periodCode) {
        this.id = id;
        this.level = level;
        this.turn = turn;
        this.classRoomString = classRoomString;
        this.workerString = workerString;
        this.periodCode = periodCode;
        this.sectionString = section;
    }
    
    
    public Degress() {
    }

    public Alert degressAsigned() {
        Alert msg;
        try {
            Connection con = this.getConnection();
            PreparedStatement ps = (PreparedStatement) 
                    con.prepareCall("INSERT INTO grados (id_periodo, id_personal, id_aula,id_seccion, nivel,turno)"
                    + "VALUES (?,?,?,?,?,?)");
            ps.setInt(1, this.getIdEnrollmnet());
            ps.setInt(2, this.getIdWorkers());
            ps.setInt(3, this.getIdClassroom());
            ps.setInt(4, this.getIdSection());
            ps.setString(5, this.getLevel());
            ps.setString(6, this.getTurn());
            ps.executeUpdate();

            msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setHeaderText(null);
            msg.setContentText("Grado asignado con exito");
            con.close();
        } catch (SQLException e) {
            msg = new Alert(Alert.AlertType.ERROR);
            System.err.println("Error al asignar el grado: " + e);
            msg.setHeaderText(null);
            msg.setContentText(Base.getMessage(e));
        }

        return msg;
    }
    
    public static void getDegressForTable(ObservableList<Degress> list){
        
        try {
            Connection con = Base.getConnectionStatic();
            PreparedStatement ps = (PreparedStatement) con.prepareCall("CALL sp_consulta_grados()");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                list.add(
                    new Degress(rs.getInt("id_grado"), rs.getString("nivel"), rs.getString("letra"),
                            rs.getString("turno"), rs.getString("docente"), rs.getString("aula"),
                            rs.getString("periodo"))
                );
            }
            con.close();
        } catch (SQLException e) {
            System.err.println("Error al setear la tabla grados: " + e);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEnrollmnet() {
        return idEnrollmnet;
    }

    public void setIdEnrollmnet(int idEnrollmnet) {
        this.idEnrollmnet = idEnrollmnet;
    }

    public int getIdWorkers() {
        return idWorkers;
    }

    public void setIdWorkers(int idWorkers) {
        this.idWorkers = idWorkers;
    }

    public int getIdClassroom() {
        return idClassroom;
    }

    public void setIdClassroom(int idClassroom) {
        this.idClassroom = idClassroom;
    }

    public int getIdSection() {
        return idSection;
    }

    public void setIdSection(int idSection) {
        this.idSection = idSection;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public String getClassRoomString() {
        return classRoomString;
    }

    public void setClassRoomString(String classRoomString) {
        this.classRoomString = classRoomString;
    }

    public String getWorkerString() {
        return workerString;
    }

    public void setWorkerString(String workerString) {
        this.workerString = workerString;
    }

    public String getPeriodCode() {
        return periodCode;
    }

    public void setPeriodCode(String periodCode) {
        this.periodCode = periodCode;
    }

    public String getSectionString() {
        return sectionString;
    }

    public void setSectionString(String sectionString) {
        this.sectionString = sectionString;
    }
    
    
    
}
