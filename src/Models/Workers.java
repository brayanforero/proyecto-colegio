package Models;

import Config.Base;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.control.Alert;

public class Workers extends Person {
    private String cargo;
    private String email;
    private String phone;
    public Workers(String names, String lastNames, String identification) {
        super(names, lastNames, identification);
    }
    
    
    public Alert saveWorker(){
        Alert msg;
        
        try {
            Connection con = Base.getConnectionStatic();
            PreparedStatement ps = con.prepareCall("call sp_registro_personal(?,?,?,?,?,?)");
            ps.setString(1, this.getIdentification());
            ps.setString(2, this.getNames());
            ps.setString(3, this.getLastNames());
            ps.setString(4, this.getEmail());
            ps.setString(5, this.getCargo());
            ps.setString(6, this.getPhone());
            ps.executeUpdate();
            
            msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle(null);
            msg.setHeaderText(null);
            msg.setContentText("Registro Exitoso");
        } catch (SQLException e) {
            System.err.println("Error al registrar el personal: " + e);
            msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle(null);
            msg.setHeaderText(null);
            msg.setContentText("Error: No se pudo completar la operacion");
        }
        
        return msg;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }
    
    

    
    
    

}
