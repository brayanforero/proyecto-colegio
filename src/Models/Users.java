package Models;

import Config.Base;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
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
    private String question;
    private String request;

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
            System.err.println("No se pudo completar el Login: " + ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(Base.getMessage(ex));
            alert.showAndWait();
        }
        return user;
    }

    public Alert newUser() {

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

    public static void getUserHabilited(ObservableList<Users> list) {
        try {
            Connection con = Base.getConnectionStatic();
            PreparedStatement ps = (PreparedStatement) con.prepareCall("CALL sp_usuarios_activos()");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("id_usuario"));
                user.setName(rs.getString("nombre"));
                user.setWorkers(rs.getString("persona"));
                list.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Error al llenar los usuarios: " + e);
        }
    }

    public static void getUserNotHabilited(ObservableList<Users> list) {
        try {
            Connection con = Base.getConnectionStatic();
            PreparedStatement ps = (PreparedStatement) con.prepareCall("CALL sp_usuarios_desactivos()");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("id_usuario"));
                user.setName(rs.getString("nombre"));
                user.setWorkers(rs.getString("persona"));
                list.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Error al llenar los usuarios: " + e);
        }
    }

    public static Users getInfoRecovery(String cedula) {
        Users u = null;
        try {

            Connection con = Base.getConnectionStatic();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT u.id_usuario, u.pregunta, u.respuesta ,per.cedula, concat(per.nombre, ' ', per.apellido) as persona \n"
                    + "FROM usuarios AS u INNER JOIN personal AS per ON per.id_personal = u.id_personal WHERE per.cedula = ?");
            ps.setString(1, cedula);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = new Users();
                u.setId(rs.getInt("id_usuario"));
                u.setWorkers(rs.getString("persona"));
                u.setQuestion(rs.getString("pregunta"));
                u.setRequest(rs.getString("respuesta"));
            }
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error al traer informacion para recuperar: " + ex);
        }
        return u;
    }
    
    public Alert changePassword(){
        Alert a = null;
        try {

            Connection con = Base.getConnectionStatic();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("UPDATE usuarios SET contrasena = md5(?) WHERE id_usuario = ? LIMIT 1");
            ps.setString(1, this.getPassword());
            ps.setInt(2, this.getId());
            ps.executeUpdate();
            a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Contraseña cambiada con exito");
            a.setHeaderText(null);
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error al actualizar la contraseña: " + ex);
            a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(Base.getMessage(ex));
            a.setHeaderText(null);
        }
        return a;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

}
