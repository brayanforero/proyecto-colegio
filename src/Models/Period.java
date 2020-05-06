package Models;

public class Period {

    private int id;
    private String codePeriod;
    private String starDate;
    private String endDate;
    private boolean expire;
    private boolean  state;

    public Period(String codePeriod, String starDate, String endDate, boolean expire) {
        this.codePeriod = codePeriod;
        this.starDate = starDate;
        this.endDate = endDate;
        this.expire = expire;
    }

    public Period(int id, String codePeriod, String starDate, String endDate, boolean expire, boolean state) {
        this.id = id;
        this.codePeriod = codePeriod;
        this.starDate = starDate;
        this.endDate = endDate;
        this.expire = expire;
        this.state = state;
    }

    public Period() {
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodePeriod() {
        return codePeriod;
    }

    public void setCodePeriod(String codePeriod) {
        this.codePeriod = codePeriod;
    }

    public String getStarDate() {
        return starDate;
    }

    public void setStarDate(String starDate) {
        this.starDate = starDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isExpire() {
        return expire;
    }

    public void setExpire(boolean expire) {
        this.expire = expire;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
    
    
    
    
    
}
