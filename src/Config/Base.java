package Config;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Base {

    private final String SERVER = "127.0.0.1";
    private final String PORT = "3306";
    private final String BDNAME = "colegio_santa_rita";
    private final String BDUSER = "admin";
    private final String USERPASS = "admin123*";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://" + this.SERVER + ":" + this.PORT + "/" + this.BDNAME;

    public Connection getConnection() throws SQLException {
        Connection con = null;

        try {
            Class.forName(DRIVER);
            con = (Connection) DriverManager.getConnection(this.URL, this.BDUSER, this.USERPASS);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("ERROR EN LA CONEXION: " + ex);
        }

        return con;
    }
}
