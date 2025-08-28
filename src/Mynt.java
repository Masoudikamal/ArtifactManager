import java.util.Date;

public class Mynt extends FunnGjenstand {
    private int diameter;
    private String metall;

    // Konstruktør
    public Mynt(int id, String funnSted, int finnerId, Date funnTidspunkt, int antattAArstall, Integer museumId, String type, int diameter, String metall) {
        super(id, funnSted, finnerId, funnTidspunkt, antattAArstall, museumId, type);
        this.diameter = diameter;
        this.metall = metall;
    }

    // Getter og setter metoder

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public String getMetall() {
        return metall;
    }

    public void setMetall(String metall) {
        this.metall = metall;
    }
}
