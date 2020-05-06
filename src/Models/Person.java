package Models;

public abstract class Person{

    protected int id;
    protected String names;
    protected String lastNames;
    protected String identification;

    public Person(String names, String lastNames, String identification) {
        this.names = names;
        this.lastNames = lastNames;
        this.identification = identification;
    }
    
    public abstract void getAll();
    public abstract void getById();
    public abstract void getByIdentication();
    public abstract boolean newInsert();
    public abstract boolean updateById();
    public abstract boolean deleteById();
    
}
