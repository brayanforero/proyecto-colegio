package Config;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Base {

    public Connection getConnection() throws SQLException {
        Connection con = null;

        try {
            Class.forName(Utilities.DRIVER);
            con = (Connection) DriverManager.getConnection(Utilities.URL, Utilities.BDUSER, Utilities.USERPASS);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("ERROR EN LA CONEXION: " + ex);
        }

        return con;
    }
}
