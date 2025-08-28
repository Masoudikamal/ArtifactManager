
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

// DEL I (NB! Programmet som ikke skal kjøres må kommenteres ut!)
/*
public class HovedProgram {
    public static void main(String[] args) {
        String filePath = "files/funn.txt";

        try {
            Connection conn = DatabaseUtil.getConnection();
            if (conn == null) {
                System.out.println("Det går ikke ann å koble til db.properties filen.");
                return;
            }

            // Leser inn data fra funn.txt
            List<Person> personer = FilLeserUtil.lesPersoner(filePath);
            List<Museum> museer = FilLeserUtil.lesMuseer(filePath);
            List<FunnGjenstand> funn = FilLeserUtil.lesFunn(filePath);

            DatabaseUtil db = new DatabaseUtil();
            for (Person person : personer) {
                if (!exists(conn, "Person", person.getId())) {
                    db.insertPerson(conn, person);
                }
            }

            //Setter inn museer i databasen
            for (Museum museum : museer) {
                if (!exists(conn, "Museum", museum.getId())) {
                    db.insertMuseum(conn, museum);
                }
            }

            // Setter inn funngjenstander i databasen
            for (FunnGjenstand funnGjenstand : funn) {
                if (!exists(conn, "Mynt", funnGjenstand.getId()) && funnGjenstand instanceof Mynt) {
                    db.insertMynt(conn, (Mynt) funnGjenstand);
                } else if (!exists(conn, "Smykke", funnGjenstand.getId()) && funnGjenstand instanceof Smykke) {
                    db.insertSmykke(conn, (Smykke) funnGjenstand);
                } else if (!exists(conn, "Vaapen", funnGjenstand.getId()) && funnGjenstand instanceof Vaapen) {
                    db.insertVaapen(conn, (Vaapen) funnGjenstand);
                }
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    //Metode for å sjekke etter duplikater, eller andre oppføringer allerede eksisterer i databasen
    private static boolean exists(Connection conn, String tableName, int id) throws SQLException {
        String query = "SELECT 1 FROM " + tableName + " WHERE Id = ?";
        try (var stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (var rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }
}
*/

// DEL II (NB! Programmet som ikke skal kjøres må kommenteres ut!)

public class HovedProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int valg = scanner.nextInt();
            brukerInput(valg, scanner);
        }
    }

    // Printer ut menyen for Funn databasen.
    private static void printMenu() {
        System.out.println("\nFunn Databasen!");
        System.out.println("1. Se informasjon om alle funngjenstander.");
        System.out.println("2. Se informasjon om alle funngjenstander eldre enn et gitt år.");
        System.out.println("3. Få informasjon om antall funngjenstander registrert.");
        System.out.println("4. Avslutte programmet.");
        System.out.print("Velg et alternativ (1-4): ");
    }

    // Håndterer bruker-input og menyvalget gjennom switch/case
    private static void brukerInput(int valg, Scanner scanner) {
        switch (valg) {
            case 1:
                visAlleFunn();
                break;
            case 2:
                System.out.print("Velg årstall: ");
                int årstall = scanner.nextInt();
                visFunnEldreEnn(årstall);
                break;
            case 3:
                visAntallFunn();
                break;
            case 4:
                System.out.println("Avslutter programmet.");
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.println("Valget er ugyldig, prøv igjen.");
        }
    }

    // Metode som viser til historikk bak funnet av alle funngjenstander.
    private static void visAlleFunn() {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "SELECT Id, Funnsted, Finner_id, Funntidspunkt, Antatt_AArstall, Museum_id, 'Mynt' AS Type, Diameter, Metall, NULL AS Verdiestimat, NULL AS Filnavn, NULL AS Materiale, NULL AS Vekt " +
                    "FROM Mynt " +
                    "UNION " +
                    "SELECT Id, Funnsted, Finner_id, Funntidspunkt, Antatt_AArstall, Museum_id, 'Smykke' AS Type, NULL, NULL, Verdiestimat, Filnavn, NULL, NULL " +
                    "FROM Smykke " +
                    "UNION " +
                    "SELECT Id, Funnsted, Finner_id, Funntidspunkt, Antatt_AArstall, Museum_id, 'Vaapen' AS Type, NULL, NULL, NULL, NULL, Materiale, Vekt " +
                    "FROM Vaapen";
            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    int id = rs.getInt("Id");
                    String funnsted = rs.getString("Funnsted");
                    int finnerId = rs.getInt("Finner_id");
                    Date funntidspunkt = rs.getDate("Funntidspunkt");
                    int antattÅrstall = rs.getInt("Antatt_AArstall");
                    Integer museumId = rs.getInt("Museum_id");
                    String type = rs.getString("Type");

                    System.out.printf("Id: %d, Funnsted: %s, Finner Id: %d, Funntidspunkt: %s, Antatt årstall: %d, Museum Id: %s, Type: %s\n",
                            id, funnsted, finnerId, funntidspunkt, antattÅrstall, museumId != 0 ? museumId.toString() : "N/A", type);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Viser funn av gjenstander eldre enn et gitt år.
    private static void visFunnEldreEnn(int aarstall) {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "SELECT Id, Funnsted, Finner_id, Funntidspunkt, Antatt_AArstall, Museum_id, 'Mynt' AS Type, Diameter, Metall, NULL AS Verdiestimat, NULL AS Filnavn, NULL AS Materiale, NULL AS Vekt " +
                    "FROM Mynt WHERE Antatt_AArstall < ? " +
                    "UNION " +
                    "SELECT Id, Funnsted, Finner_id, Funntidspunkt, Antatt_AArstall, Museum_id, 'Smykke' AS Type, NULL, NULL, Verdiestimat, Filnavn, NULL, NULL " +
                    "FROM Smykke WHERE Antatt_AArstall < ? " +
                    "UNION " +
                    "SELECT Id, Funnsted, Finner_id, Funntidspunkt, Antatt_AArstall, Museum_id, 'Vaapen' AS Type, NULL, NULL, NULL, NULL, Materiale, Vekt " +
                    "FROM Vaapen WHERE Antatt_AArstall < ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, aarstall);
                stmt.setInt(2, aarstall);
                stmt.setInt(3, aarstall);
                try (ResultSet rs = stmt.executeQuery()) {

                    while (rs.next()) {
                        int id = rs.getInt("Id");
                        String funnsted = rs.getString("Funnsted");
                        int finnerId = rs.getInt("Finner_id");
                        Date funntidspunkt = rs.getDate("Funntidspunkt");
                        int antattÅrstall = rs.getInt("Antatt_AArstall");
                        Integer museumId = rs.getInt("Museum_id");
                        String type = rs.getString("Type");

                        System.out.printf("Id: %d, Funnsted: %s, Finner Id: %d, Funntidspunkt: %s, Antatt årstall: %d, Museum Id: %s, Type: %s\n",
                                id, funnsted, finnerId, funntidspunkt, antattÅrstall, museumId != 0 ? museumId.toString() : "N/A", type);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Viser antall gjenstander som det har blitt gjort funn av.
    private static void visAntallFunn() {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "SELECT COUNT(*) AS total FROM (" +
                    "SELECT Id FROM Mynt UNION " +
                    "SELECT Id FROM Smykke UNION " +
                    "SELECT Id FROM Vaapen) AS all_funn";
            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    int total = rs.getInt("total");
                    System.out.printf("Antall funngjenstander registrert: %d\n", total);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
