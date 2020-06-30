package Models;

import Config.Base;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import javafx.scene.control.Alert;

public class Students extends Person {

    private String dateOfBirth;
    private String gender;
    private int addressOfEstate;
    private int addressOfMucipality;
    private int adressOfLocality;
    private boolean canaima;
    private boolean beca;
    private String salud;
    private String desSalud;

    private Connection link;

    public Students(
            String identification, String names, String lastNames,
            String dateOfBirth, String gender, int addressOfEstate,
            int addressOfMucipality, int adressOfLocality, boolean canaima, boolean beca,
            String salud, String desSalud) {

        super(names, lastNames, identification);
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.addressOfEstate = addressOfEstate;
        this.addressOfMucipality = addressOfMucipality;
        this.adressOfLocality = adressOfLocality;
        this.canaima = canaima;
        this.beca = beca;
        this.salud = salud;
        this.desSalud = desSalud;
    }

    public Alert newStudent(String phoneMom, String phoneDad, Parents mom) {
        Alert message = null;
        
        int idBoy = 0;
        int idMom = 0;
        ResultSet rs;
        try {
            
            link = Base.getConnectionStatic();
            link.setAutoCommit(false);
            
            PreparedStatement psBoy = this.link.prepareCall("call sp_registrar_estudiante"
                    + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            psBoy.setString(1, this.getIdentification());
            psBoy.setString(2, this.getNames());
            psBoy.setString(3, this.getLastNames());
            psBoy.setString(4, this.getDateOfBirth());
            psBoy.setString(5, this.getGender());
            psBoy.setInt(6, this.getAddressOfEstate());
            psBoy.setInt(7, this.getAddressOfMucipality());
            psBoy.setInt(8, this.getAdressOfLocality());
            psBoy.setBoolean(9, this.isCanaima());
            psBoy.setBoolean(10, this.isBeca());
            psBoy.setString(11, this.getSalud());
            psBoy.setString(12, this.getDesSalud());
            psBoy.setString(13, phoneMom);
            psBoy.setString(14, phoneDad);
            rs = psBoy.executeQuery();
            rs.next();
            idBoy = rs.getInt("id");
            
            PreparedStatement psMom = link.prepareCall("call sp_registrar_familiar(?,?,?,?,?,?,?,?,?,?)");
            psMom.setString(1, mom.getIdentification());
            psMom.setString(2, mom.getNames());
            psMom.setString(3, mom.getLastNames());
            psMom.setString(4, mom.getDateOfbirth());
            psMom.setBoolean(5, mom.isIfLiveWhitBoy());
            psMom.setString(6, mom.getTypeHouse());
            psMom.setString(7, mom.getTypeFamily());
            psMom.setString(8, mom.getOcupation());
            psMom.setString(9, mom.getRelige());
            psMom.setString(10, mom.getEmail());
            rs = psMom.executeQuery();
            rs.next();
            idMom = rs.getInt("id");
            
            PreparedStatement psMomBoy = link.prepareCall("call sp_registrar_est_fami(?,?)");
            psMomBoy.setInt(1, idBoy);
            psMomBoy.setInt(2, idMom);
            psMomBoy.executeQuery();
            link.commit();
            
            message = new Alert(Alert.AlertType.INFORMATION);
            message.setHeaderText(null);
            message.setContentText("Registro Completado");

            this.link.close();
        } catch (SQLException e) {

            try {
                this.link.rollback();
            } catch (SQLException ex) {
                System.err.println("Error rollback: " + ex);
                message = new Alert(Alert.AlertType.ERROR);
                message.setHeaderText(null);
                message.setContentText("Error: No se pudo completar su Operación");
            }
            
            System.err.println("Error: " + e);
            message = new Alert(Alert.AlertType.ERROR);
            message.setHeaderText(null);
            message.setContentText("Error: No se pudo completar su Operación");
        }

        return message;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAddressOfEstate() {
        return addressOfEstate;
    }

    public void setAddressOfEstate(int addressOfEstate) {
        this.addressOfEstate = addressOfEstate;
    }

    public int getAddressOfMucipality() {
        return addressOfMucipality;
    }

    public void setAddressOfMucipality(int addressOfMucipality) {
        this.addressOfMucipality = addressOfMucipality;
    }

    public int getAdressOfLocality() {
        return adressOfLocality;
    }

    public void setAdressOfLocality(int adressOfLocality) {
        this.adressOfLocality = adressOfLocality;
    }

    public boolean isCanaima() {
        return canaima;
    }

    public void setCanaima(boolean canaima) {
        this.canaima = canaima;
    }

    public boolean isBeca() {
        return beca;
    }

    public void setBeca(boolean beca) {
        this.beca = beca;
    }

    public String getSalud() {
        return salud;
    }

    public void setSalud(String salud) {
        this.salud = salud;
    }

    public String getDesSalud() {
        return desSalud;
    }

    public void setDesSalud(String desSalud) {
        this.desSalud = desSalud;
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
