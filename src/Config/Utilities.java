package Config;

import javafx.scene.input.KeyEvent;

public class Utilities {

    public static final String SERVER = "127.0.0.1";
    public static final String PORT = "3306";
    public static final String BDNAME = "colegio_santa_rita";
    public static final String BDUSER = "admin";
    public static final String USERPASS = "admin123*";
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://" + SERVER + ":" + PORT + "/" + BDNAME;

    public static void onlyLetter(KeyEvent e) {

        char k = e.getCharacter().charAt(0);
        if (Character.isLetter(k)) {
            return;
        }
        e.consume();
    }
    public static void onlyLetterAndDigit(KeyEvent e) {

        char k = e.getCharacter().charAt(0);
        if (Character.isLetterOrDigit(k)) {
            return;
        }
        e.consume();
    }
}
