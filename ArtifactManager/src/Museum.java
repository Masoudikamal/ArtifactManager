public class Museum {
    private int id;
    private String navn;
    private String sted;

    // Konstruktør
    public Museum(int id, String navn, String sted) {
        this.id = id;
        this.navn = navn;
        this.sted = sted;
    }

    // Getter og setter metoder

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getSted() {
        return sted;
    }

    public void setSted(String sted) {
        this.sted = sted;
    }
}
