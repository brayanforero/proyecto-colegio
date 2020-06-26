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
            String identification,String names, String lastNames,
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

    public Alert newStudent(String phoneMom, String phoneDad) {
        Alert message = null;
        try {
            this.link = Base.getConnectionStatic();
            PreparedStatement ps = link.prepareCall("call sp_registrar_estudiante"
                    + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, this.getIdentification());
            ps.setString(2, this.getNames());
            ps.setString(3, this.getLastNames());
            ps.setString(4, this.getDateOfBirth());
            ps.setString(5, this.getGender());
            ps.setInt(6, this.getAddressOfEstate());
            ps.setInt(7, this.getAddressOfMucipality());
            ps.setInt(8, this.getAdressOfLocality());
            ps.setBoolean(9, this.isCanaima());
            ps.setBoolean(10, this.isBeca());
            ps.setString(11, this.getSalud());
            ps.setString(12, this.getDesSalud());
            ps.setString(13, phoneMom);
            ps.setString(14, phoneDad);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                message = new Alert(Alert.AlertType.INFORMATION);
                message.setHeaderText(null);
                message.setContentText("Resgistro Completado con Exito!");
            }

            this.link.close();

        } catch (SQLException e) {
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
