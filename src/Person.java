public class Person {
    private int id;
    private String navn;
    private String tlf;
    private String ePost;

    // Konstruktør
    public Person(int id, String navn, String tlf, String ePost) {
        this.id = id;
        this.navn = navn;
        this.tlf = tlf;
        this.ePost = ePost;
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

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public String getEPost() {
        return ePost;
    }

    public void setEPost(String ePost) {
        this.ePost = ePost;
    }
}
