package Config;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Base {
    protected PreparedStatement ps;
    protected ResultSet rs;
    
    protected Connection getConnection() throws SQLException {
        Connection con = null;

        try {
            Class.forName(Utilities.DRIVER);
            con = (Connection) DriverManager.getConnection(Utilities.URL, Utilities.BDUSER, Utilities.USERPASS);
        } catch (ClassNotFoundException ex) {
            System.err.println("ERROR EN LA CONEXION: " + ex);
        }

        return con;
    }
    
    public static Connection getConnectionStatic() throws SQLException {
        Connection con = null;

        try {
            Class.forName(Utilities.DRIVER);
            con = (Connection) DriverManager.getConnection(Utilities.URL, Utilities.BDUSER, Utilities.USERPASS);
        } catch (ClassNotFoundException ex) {
            System.err.println("ERROR EN LA CONEXION: " + ex);
        }

        return con;
    }
}
