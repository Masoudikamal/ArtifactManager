import java.util.Date;

public class Smykke extends FunnGjenstand {
    private int verdiestimat;
    private String filnavn;

    // Konstruktør
    public Smykke(int id, String funnSted, int finnerId, Date funnTidspunkt, int antattAArstall, Integer museumId, String type, int verdiestimat, String filnavn) {
        super(id, funnSted, finnerId, funnTidspunkt, antattAArstall, museumId, type);
        this.verdiestimat = verdiestimat;
        this.filnavn = filnavn;
    }

    // Getter og setter metoder

    public int getVerdiestimat() {
        return verdiestimat;
    }

    public void setVerdiestimat(int verdiestimat) {
        this.verdiestimat = verdiestimat;
    }

    public String getFilnavn() {
        return filnavn;
    }

    public void setFilnavn(String filnavn) {
        this.filnavn = filnavn;
    }
}
