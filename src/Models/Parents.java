package Models;

import Config.Base;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Parents extends Person {
    
    private String dateOfbirth;
    private boolean ifLiveWhitBoy;
    private String typeHouse;
    private String typeFamily;
    private String ocupation;
    private String email;
    private String relige;
    private int id;

    public Parents(String identification , String names, String lastNames,  
            String dateOfbirth, boolean ifLiveWhitBoy, String typeHouse, 
            String typeFamily, String ocupation, String email, String relige) {
        
        super(names, lastNames, identification);
        this.dateOfbirth = dateOfbirth;
        this.ifLiveWhitBoy = ifLiveWhitBoy;
        this.typeHouse = typeHouse;
        this.typeFamily = typeFamily;
        this.ocupation = ocupation;
        this.email = email;
        this.relige = relige;
    }

    public Parents(int id, String names, String lastNames, String identification) {
        super(names, lastNames, identification);
        this.id = id;
    }
    
    
    
    public static Parents getByDocument(String document){
        
        try {
            Connection con = Base.getConnectionStatic();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT  id_familiar AS id ,nombre_nombres, nombre_apellidos FROM familiares WHERE cedula = ?");
            ps.setString(1, document);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return new Parents(rs.getInt("id"), rs.getString("nombre_nombres"), rs.getString("nombre_apellidos"), "");
            }
        } catch (SQLException ex) {
            System.err.println("Error al bucar los datos del familiar: " + ex);
        }
        return null;
    }

    public String getDateOfbirth() {
        return dateOfbirth;
    }

    public void setDateOfbirth(String dateOfbirth) {
        this.dateOfbirth = dateOfbirth;
    }

    public boolean isIfLiveWhitBoy() {
        return ifLiveWhitBoy;
    }

    public void setIfLiveWhitBoy(boolean ifLiveWhitBoy) {
        this.ifLiveWhitBoy = ifLiveWhitBoy;
    }

    public String getTypeHouse() {
        return typeHouse;
    }

    public void setTypeHouse(String typeHouse) {
        this.typeHouse = typeHouse;
    }

    public String getTypeFamily() {
        return typeFamily;
    }

    public void setTypeFamily(String typeFamily) {
        this.typeFamily = typeFamily;
    }

    public String getOcupation() {
        return ocupation;
    }

    public void setOcupation(String ocupation) {
        this.ocupation = ocupation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRelige() {
        return relige;
    }

    public void setRelige(String relige) {
        this.relige = relige;
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
