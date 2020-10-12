package Models;

import Config.Base;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;
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

    /* DATOS PARA PINTAR EN TABLA*/
    private int idStudent;
    private String fullname;
    private String currentCanaima;
    private String currentBeca;
    private String currentRepeat;
    private int age;
    private String fullNameParent;
    private String documentParent;

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

    public Students(String names, String lastNames, String identification) {
        super(names, lastNames, identification);
    }

    public Alert newStudent(String phoneMom, String phoneDad, Parents mom, Parents dad, int idDegress) throws SQLException {
        Alert message = null;
        PreparedStatement ps_boy, ps_mom, ps_dad;
        ResultSet rs;
        int idBoy = 0, idMom = 0, idDad = 0;
        Connection link = null;

        try {
            // INSERTANDO AL NIÑO(A)
            link = Base.getConnectionStatic();
            link.setAutoCommit(false);
            ps_boy = (PreparedStatement) link.prepareStatement("INSERT INTO estudiantes (cedula,nombre_nombres,nombre_apellidos,fecha_nacimiento,genero,dir_estado,dir_municipio,dir_parroquia,posee_canaima,posee_beca,info_salud,recomendaciones)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            ps_boy.setString(1, this.getIdentification());
            ps_boy.setString(2, this.getNames());
            ps_boy.setString(3, this.getLastNames());
            ps_boy.setString(4, this.getDateOfBirth());
            ps_boy.setString(5, this.getGender());
            ps_boy.setInt(6, this.getAddressOfEstate());
            ps_boy.setInt(7, this.getAddressOfMucipality());
            ps_boy.setInt(8, this.getAddressOfMucipality());
            ps_boy.setBoolean(9, this.isCanaima());
            ps_boy.setBoolean(10, this.isBeca());
            ps_boy.setString(11, this.getSalud());
            ps_boy.setString(12, this.getDesSalud());
            ps_boy.executeUpdate();

            // OBTENIENDO ID DE NIÑO(A)
            PreparedStatement last_id_boy = (PreparedStatement) link.prepareStatement("SELECT id_estudiante FROM estudiantes ORDER BY id_estudiante DESC LIMIT 1");
            rs = last_id_boy.executeQuery();
            rs.next();
            idBoy = rs.getInt("id_estudiante");

            // INSERTANO TELEFONOS
            PreparedStatement phones = (PreparedStatement) link.prepareStatement("INSERT INTO telefonos_de_estudiantes (id_estudiante, telefono) VALUES (?,?),(?,?)");
            phones.setInt(1, idBoy);
            phones.setString(2, phoneMom);
            phones.setInt(3, idBoy);
            phones.setString(4, phoneDad);
            phones.executeUpdate();

            // INSERTANDO A LA MAMÁ
            ps_mom = (PreparedStatement) link.prepareStatement("INSERT INTO familiares (cedula, nombre_nombres, nombre_apellidos, fecha_nacimiento, si_vive, tipo_vivienda, tipo_familiar, oficio, religion,email)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?)");
            ps_mom.setString(1, mom.getIdentification());
            ps_mom.setString(2, mom.getNames());
            ps_mom.setString(3, mom.getLastNames());
            ps_mom.setString(4, mom.getDateOfbirth());
            ps_mom.setBoolean(5, mom.isIfLiveWhitBoy());
            ps_mom.setString(6, mom.getTypeHouse());
            ps_mom.setString(7, mom.getTypeFamily());
            ps_mom.setString(8, mom.getOcupation());
            ps_mom.setString(9, mom.getRelige());
            ps_mom.setString(10, mom.getEmail());
            ps_mom.executeUpdate();

            //OBTENIENDO ID DE LA MAMÁ
            PreparedStatement last_id_mom = (PreparedStatement) link.prepareStatement("SELECT id_familiar FROM familiares ORDER BY id_familiar DESC LIMIT 1");
            rs = last_id_mom.executeQuery();
            rs.next();
            idMom = rs.getInt("id_familiar");

            // INSERTANDO A LA PAPÁ
            ps_dad = (PreparedStatement) link.prepareStatement("INSERT INTO familiares (cedula, nombre_nombres, nombre_apellidos, fecha_nacimiento, si_vive, tipo_vivienda, tipo_familiar, oficio, religion,email)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?)");
            ps_dad.setString(1, dad.getIdentification());
            ps_dad.setString(2, dad.getNames());
            ps_dad.setString(3, dad.getLastNames());
            ps_dad.setString(4, dad.getDateOfbirth());
            ps_dad.setBoolean(5, dad.isIfLiveWhitBoy());
            ps_dad.setString(6, dad.getTypeHouse());
            ps_dad.setString(7, dad.getTypeFamily());
            ps_dad.setString(8, dad.getOcupation());
            ps_dad.setString(9, dad.getRelige());
            ps_dad.setString(10, dad.getEmail());
            ps_dad.executeUpdate();

            // OBTENIENDO ID DE LA PAPÁ
            PreparedStatement last_id_dad = (PreparedStatement) link.prepareStatement("SELECT id_familiar FROM familiares ORDER BY id_familiar DESC LIMIT 1");
            rs = last_id_dad.executeQuery();
            rs.next();
            idDad = rs.getInt("id_familiar");

            // RELACION MADRE, PADRE E HIJO
            PreparedStatement relation_sun = (PreparedStatement) link.prepareStatement("INSERT INTO estudiante_y_familia VALUES(?,?),(?,?)");
            relation_sun.setInt(1, idBoy);
            relation_sun.setInt(2, idMom);
            relation_sun.setInt(3, idBoy);
            relation_sun.setInt(4, idDad);
            relation_sun.executeUpdate();

            // INSCRIPCION EN EL GRADO RECIBIDO
            PreparedStatement inscription = (PreparedStatement) link.prepareStatement("INSERT INTO inscripciones (id_estudiante, id_grado, repitiente)"
                    + "VALUES (?,?,?)");

            inscription.setInt(1, idBoy);
            inscription.setInt(2, idDegress);
            inscription.setBoolean(3, false);
            inscription.executeUpdate();

            link.commit();

            message = new Alert(Alert.AlertType.INFORMATION);
            message.setHeaderText(null);
            message.setContentText("Resgistro completado con exito");
            link.close();
        } catch (SQLException ex) {

            link.rollback();
            System.err.println("Error en la inscripcion: " + ex);
            message = new Alert(Alert.AlertType.ERROR);
            message.setHeaderText(null);
            message.setContentText(Base.getMessage(ex));
        }
        return message;
    }

    public static void getListStudenByDegress(int idDegress, ObservableList<Students> list) {

        try {
            Connection con = Base.getConnectionStatic();
            PreparedStatement ps = (PreparedStatement) con.prepareCall("call sp_lista_estudiante_grado(?)");
            ps.setInt(1, idDegress);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Students student = new Students(rs.getString("nombres"), rs.getString("nombres"), rs.getString("cedula"));
                student.setIdStudent(rs.getInt("id_estudiante"));
                student.setFullname(student.getNames());
                student.setDateOfBirth(rs.getString("nacimiento"));
                student.setAge(rs.getInt("edad"));
                student.setGender(rs.getString("genero"));
                student.setCurrentRepeat(rs.getString("repitiente"));
                student.setCurrentCanaima(rs.getString("canaima"));
                student.setCurrentBeca(rs.getString("beca"));
                student.setFullNameParent(rs.getString("nombres_mama"));
                student.setDocumentParent(rs.getString("cedula_mama"));
                list.add(student);
            }
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }
    }

    public static ResultSet getStudentByDoc(String cedula) {
        
        ResultSet rs = null;
        try {
            Connection con = Base.getConnectionStatic();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("CALL sp_consulta_estudiante(?)");
            ps.setString(1, cedula);
            rs = ps.executeQuery();
        } catch (Exception e) {
            System.err.println("Error al buscar el estudiante: " + e);
        }
        return rs;
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

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCurrentCanaima() {
        return currentCanaima;
    }

    public void setCurrentCanaima(String currentCanaima) {
        this.currentCanaima = currentCanaima;
    }

    public String getCurrentBeca() {
        return currentBeca;
    }

    public void setCurrentBeca(String currentBeca) {
        this.currentBeca = currentBeca;
    }

    public String getCurrentRepeat() {
        return currentRepeat;
    }

    public void setCurrentRepeat(String currentRepeat) {
        this.currentRepeat = currentRepeat;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFullNameParent() {
        return fullNameParent;
    }

    public void setFullNameParent(String fullNameParent) {
        this.fullNameParent = fullNameParent;
    }

    public String getDocumentParent() {
        return documentParent;
    }

    public void setDocumentParent(String documentParent) {
        this.documentParent = documentParent;
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
