import java.util.Date;

public class Vaapen extends FunnGjenstand {
    private String materiale;
    private int vekt;

    // Konstruktør
    public Vaapen(int id, String funnSted, int finnerId, Date funnTidspunkt, int antattAArstall, Integer museumId, String type, String materiale, int vekt) {
        super(id, funnSted, finnerId, funnTidspunkt, antattAArstall, museumId, type);
        this.materiale = materiale;
        this.vekt = vekt;
    }

    // Getter og setter metoder
    public String getMateriale() {
        return materiale;
    }

    public void setMateriale(String materiale) {
        this.materiale = materiale;
    }

    public int getVekt() {
        return vekt;
    }

    public void setVekt(int vekt) {
        this.vekt = vekt;
    }
}
