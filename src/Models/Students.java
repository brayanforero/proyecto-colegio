
package Models;


public class Students extends Person{
    
    private String dateOfBirth;
    private String directionEstate;
    private String addressOfEstate;
    private String addressOfMucipality;
    private String addressOfParish;

    public Students(String names, String lastNames, String identification,String dateOfBirth, String directionEstate, String addressOfEstate, String addressOfMucipality, String addressOfParish) {
        super(names, lastNames, identification);
        this.dateOfBirth = dateOfBirth;
        this.directionEstate = directionEstate;
        this.addressOfEstate = addressOfEstate;
        this.addressOfMucipality = addressOfMucipality;
        this.addressOfParish = addressOfParish;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDirectionEstate() {
        return directionEstate;
    }

    public void setDirectionEstate(String directionEstate) {
        this.directionEstate = directionEstate;
    }

    public String getAddressOfEstate() {
        return addressOfEstate;
    }

    public void setAddressOfEstate(String addressOfEstate) {
        this.addressOfEstate = addressOfEstate;
    }

    public String getAddressOfMucipality() {
        return addressOfMucipality;
    }

    public void setAddressOfMucipality(String addressOfMucipality) {
        this.addressOfMucipality = addressOfMucipality;
    }

    public String getAddressOfParish() {
        return addressOfParish;
    }

    public void setAddressOfParish(String addressOfParish) {
        this.addressOfParish = addressOfParish;
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

    @Override
    public void getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getById() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getByIdentication() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean newInsert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateById() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteById() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
    
    
    
}
