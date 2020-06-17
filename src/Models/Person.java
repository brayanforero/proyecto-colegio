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
    
    
    
}
