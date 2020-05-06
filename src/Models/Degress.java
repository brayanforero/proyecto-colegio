package Models;

public class Degress {
    
    private int id;
    private int idEnrollmnet;
    private int idWorkers;
    private int idClassroom;
    private int idSection;
    private String level;
    private String turn;

    public Degress(int id, int idEnrollmnet, int idWorkers, int idClassroom, int idSection, String level, String turn) {
        this.id = id;
        this.idEnrollmnet = idEnrollmnet;
        this.idWorkers = idWorkers;
        this.idClassroom = idClassroom;
        this.idSection = idSection;
        this.level = level;
        this.turn = turn;
    }

    public Degress() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEnrollmnet() {
        return idEnrollmnet;
    }

    public void setIdEnrollmnet(int idEnrollmnet) {
        this.idEnrollmnet = idEnrollmnet;
    }

    public int getIdWorkers() {
        return idWorkers;
    }

    public void setIdWorkers(int idWorkers) {
        this.idWorkers = idWorkers;
    }

    public int getIdClassroom() {
        return idClassroom;
    }

    public void setIdClassroom(int idClassroom) {
        this.idClassroom = idClassroom;
    }

    public int getIdSection() {
        return idSection;
    }

    public void setIdSection(int idSection) {
        this.idSection = idSection;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }
    
}
