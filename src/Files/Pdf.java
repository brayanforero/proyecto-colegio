package Files;

import com.mysql.jdbc.Connection;
import java.util.Map;
import javafx.scene.control.Alert;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class Pdf {

    private String name;
    private Connection connection;
    private Map params;

    public Pdf(String name, Map params, Connection connection) {
        this.name = name;
        this.connection = connection;
        this.params = params;
    }

    public void generate() {

        try {
            JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("/Reports/" + this.name + ".jasper"), this.params, this.connection);
            JasperViewer.viewReport(jp, false);
            System.out.println("Proceso Finalizado");
        } catch (JRException ex) {
            System.err.println("Error al generar el PDF: " + ex);
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Error: No se ha podido generar el archivo, verique sus datos o la conexion a la red");
            alert.showAndWait();
        }
    }
}
