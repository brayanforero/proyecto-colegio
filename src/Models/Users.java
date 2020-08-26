package Models;

import Config.Base;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.control.Alert;

public class Users extends Base {

    private int id;
    private int idWorkers;
    private String name;
    private String password;
    private String role;
    private boolean estate;
    private int idWorker;
    private String workers;

    public Users(int id, int idWorkers, String name, String password, String role, boolean estate) {
        this.id = id;
        this.idWorkers = idWorkers;
        this.name = name;
        this.password = password;
        this.role = role;
        this.estate = estate;
    }
    public Users(int idWorkers, String name, String password, String role) {
        this.idWorkers = idWorkers;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public Users() {
    }

    public Users login(String nameUser, String passUser) {
        Users user = new Users();
        try {
            Connection con = this.getConnection();
            this.ps = con.prepareStatement("call sp_login_user(?,?)");
            ps.setString(1, nameUser);
            ps.setString(2, passUser);
            this.rs = ps.executeQuery();
 
            if (rs.next()) {
                user.setId(rs.getInt("id_usuario"));
                user.setName(rs.getString("nombre"));
                user.setPassword(rs.getString("contrasena"));
                user.setRole(rs.getString("rol"));
                user.setWorkers(rs.getString("personal"));
                user.setIdWorker(rs.getInt("id_personal"));
            }
            
            ps.close();
        } catch (SQLException ex) {
            /*Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);*/
            System.err.println("No se pudo completar el Login: " + ex);
        }
        return user;
    }
    
    public Alert newUser(){
        
        Alert msg = null;
        try {
            Connection con = this.getConnection();
            PreparedStatement ps = (PreparedStatement) con.prepareCall("call sp_registro_usuario(?,?,?,?)");
            ps.setInt(1, this.getIdWorkers());
            ps.setString(2, this.getName());
            ps.setString(3, this.getPassword());
            ps.setString(4, this.getRole());
            ps.executeUpdate();
            
            msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle(null);
            msg.setHeaderText(null);
            msg.setContentText("Registro Exitoso");
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error al registar el ususario: " + e);
            msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle(null);
            msg.setHeaderText(null);
            msg.setContentText("Error: No se pudo completar su operacion");
        }
        
        return msg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdWorkers() {
        return idWorkers;
    }

    public void setIdWorkers(int idWorkers) {
        this.idWorkers = idWorkers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEstate() {
        return estate;
    }

    public void setEstate(boolean estate) {
        this.estate = estate;
    }

    public String getWorkers() {
        return workers;
    }

    public void setWorkers(String workers) {
        this.workers = workers;
    }

    public int getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(int idWorker) {
        this.idWorker = idWorker;
    }
    
    

}
