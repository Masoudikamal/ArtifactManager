import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class FilLeserUtil {

    // Metode som leser personer fra funn.txt og returnerer en liste
    public static List<Person> lesPersoner(String filePath) throws IOException {
        List<Person> personer = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Personer:")) {
                    int numberOfPersons = Integer.parseInt(br.readLine().trim());
                    for (int i = 0; i < numberOfPersons; i++) {
                        int id = Integer.parseInt(br.readLine().trim());
                        String navn = br.readLine().trim();
                        String tlf = br.readLine().trim();
                        String ePost = br.readLine().trim();
                        personer.add(new Person(id, navn, tlf, ePost));
                    }
                    break; // løkke avsluttes
                }
            }
        }
        return personer;
    }

    // Metode som leser museer fra funn.txt og returnerer en liste
    public static List<Museum> lesMuseer(String filePath) throws IOException {
        List<Museum> museer = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Museer:")) {
                    int numberOfMuseums = Integer.parseInt(br.readLine().trim());
                    for (int i = 0; i < numberOfMuseums; i++) {
                        int id = Integer.parseInt(br.readLine().trim());
                        String navn = br.readLine().trim();
                        String sted = br.readLine().trim();
                        museer.add(new Museum(id, navn, sted));
                    }
                    break; // Løkke avsluttes
                }
            }
        }
        return museer;
    }

    // Metode som leser alle gjendstandene som det har blitt gjort funn på fra funn.txt og returnerer en liste
    public static List<FunnGjenstand> lesFunn(String filePath) throws IOException {
        List<FunnGjenstand> funn = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Funn:")) {
                    while ((line = br.readLine()) != null) {
                        if (line.trim().isEmpty() || line.startsWith("-------")) { // Hopper over tomme- og sparator-linjer
                            continue;
                        }
                        try {
                            int id = Integer.parseInt(line.trim());
                            String[] coordinates = br.readLine().trim().split(", ");
                            if (coordinates.length != 2) {
                                System.err.println("Invalid coordinates: " + line);
                                continue;
                            }
                            String funnSted = coordinates[0] + ", " + coordinates[1];
                            int finnerId = Integer.parseInt(br.readLine().trim());
                            Date funnTidspunkt = Date.valueOf(br.readLine().trim());
                            int antattAArstall = Integer.parseInt(br.readLine().trim());
                            String museumLine = br.readLine().trim();
                            Integer museumId = museumLine.isEmpty() ? null : Integer.parseInt(museumLine);
                            String type = br.readLine().trim();

                            // Sjekker hvilken type funn og oppretter riktig objekt
                            switch (type) {
                                case "Mynt":
                                    int diameter = Integer.parseInt(br.readLine().trim());
                                    String metall = br.readLine().trim();
                                    funn.add(new Mynt(id, funnSted, finnerId, funnTidspunkt, antattAArstall, museumId, type, diameter, metall));
                                    break;
                                case "Smykke":
                                    String smykkeType = br.readLine().trim();
                                    int verdiestimat = Integer.parseInt(br.readLine().trim());
                                    String filnavn = br.readLine().trim();
                                    funn.add(new Smykke(id, funnSted, finnerId, funnTidspunkt, antattAArstall, museumId, smykkeType, verdiestimat, filnavn));
                                    break;
                                case "Våpen":
                                    String vaapenType = br.readLine().trim();
                                    String materiale = br.readLine().trim();
                                    int vekt = Integer.parseInt(br.readLine().trim());
                                    funn.add(new Vaapen(id, funnSted, finnerId, funnTidspunkt, antattAArstall, museumId, vaapenType, materiale, vekt));
                                    break;
                                default:
                                    throw new IllegalArgumentException("Typen er ikke kjent: " + type);
                            }
                        } catch (NumberFormatException e) {
                            System.err.println(line);
                            e.printStackTrace();
                        } catch (IllegalArgumentException e) {
                            System.err.println(line);
                            e.printStackTrace();
                        }
                    }
                    break; // avslutter løkken
                }
            }
        }
        return funn;
    }
}





































