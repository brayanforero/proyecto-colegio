package Models;

import Config.Base;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class Period extends Base{

    private int id;
    private String codePeriod;
    private String starDate;
    private String endDate;
    private boolean expire;
    private boolean  state;

    public Period(String codePeriod, String starDate, String endDate, boolean expire) {
        this.codePeriod = codePeriod;
        this.starDate = starDate;
        this.endDate = endDate;
        this.expire = expire;
    }

    public Period(int id, String codePeriod, String starDate, String endDate, boolean expire, boolean state) {
        this.id = id;
        this.codePeriod = codePeriod;
        this.starDate = starDate;
        this.endDate = endDate;
        this.expire = expire;
        this.state = state;
    }

    public Period(int id, String codePeriod) {
        this.id = id;
        this.codePeriod = codePeriod;
    }
    
    public Period() {
        
    }
    
    public static void getPeridCombo(ObservableList<Period> list){
        
        try {
            Connection link = Base.getConnectionStatic();
            PreparedStatement ps = (PreparedStatement) link.prepareStatement("SELECT id_periodo, codigo FROM periodos WHERE vigencia = 1");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(
                    new Period(rs.getInt("id_periodo"), rs.getString("codigo"))
                );
            }
            link.close();
        } catch (SQLException e) {
            System.err.println("Error al llenar los periodos: " + e);
        }
    }
    
    public Alert addPeriod(){
        Alert message = null;
        try {
            Connection con = this.getConnection();
            PreparedStatement ps = (PreparedStatement) con.prepareCall("call sp_registro_periodo(?,?)");
            ps.setString(1, this.getStarDate());
            ps.setString(2, this.getEndDate());
            ps.executeUpdate();
            
            message = new Alert(Alert.AlertType.INFORMATION);
            message.setContentText("Periodo Registrado con exito");
            message.setHeaderText(null);
            ps.close();
        } catch (SQLException ex) {
            System.err.println("Error al registrar el periodo: " + ex );
            message = new Alert(Alert.AlertType.ERROR);
            message.setContentText(Base.getMessage(ex));
            message.setHeaderText(null);
        }
        
        return message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodePeriod() {
        return codePeriod;
    }

    public void setCodePeriod(String codePeriod) {
        this.codePeriod = codePeriod;
    }

    public String getStarDate() {
        return starDate;
    }

    public void setStarDate(String starDate) {
        this.starDate = starDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isExpire() {
        return expire;
    }

    public void setExpire(boolean expire) {
        this.expire = expire;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return codePeriod;
    }
    
    
    
    
    
}
