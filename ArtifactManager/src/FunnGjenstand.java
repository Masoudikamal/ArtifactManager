import java.util.Date;

public abstract class FunnGjenstand {
    private int id;
    private String funnSted;
    private int finnerId;
    private Date funnTidspunkt;
    private int antattAArstall;
    private Integer museumId;
    private String type;

    // Konstruktør
    public FunnGjenstand(int id, String funnSted, int finnerId, Date funnTidspunkt, int antattAArstall, Integer museumId, String type) {
        this.id = id;
        this.funnSted = funnSted;
        this.finnerId = finnerId;
        this.funnTidspunkt = funnTidspunkt;
        this.antattAArstall = antattAArstall;
        this.museumId = museumId;
        this.type = type;
    }

    // Getter og setter metoder

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFunnSted() {
        return funnSted;
    }

    public void setFunnSted(String funnSted) {
        this.funnSted = funnSted;
    }

    public int getFinnerId() {
        return finnerId;
    }

    public void setFinnerId(int finnerId) {
        this.finnerId = finnerId;
    }

    public Date getFunnTidspunkt() {
        return funnTidspunkt;
    }

    public void setFunnTidspunkt(Date funnTidspunkt) {
        this.funnTidspunkt = funnTidspunkt;
    }

    public int getAntattAArstall() {
        return antattAArstall;
    }

    public void setAntattAArstall(int antattAArstall) {
        this.antattAArstall = antattAArstall;
    }

    public Integer getMuseumId() {
        return museumId;
    }

    public void setMuseumId(Integer museumId) {
        this.museumId = museumId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
