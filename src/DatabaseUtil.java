import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtil {

    // Metode som oppretter kobling til databasen med login-info fra db.properties
    public static Connection getConnection() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("files/db.properties")) {
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Det går ikke ann å laste ned db.properties-filen.");
            return null;
        }

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.username");
        String password = props.getProperty("db.password");

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Det går ikke ann å koble til db.properties-filen.");
            return null;
        }
    }

    // Metode for å legge til person i databasen
    public void leggTilPerson(Connection conn, Person person) throws SQLException {
        String query = "INSERT INTO Person (Id, Navn, Tlf, E_post) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, person.getId());
            stmt.setString(2, person.getNavn());
            stmt.setString(3, person.getTlf());
            stmt.setString(4, person.getEPost());
            stmt.executeUpdate();
        }
    }

    // Metode for å legge til Museum i databasen
    public void leggTilMuseum(Connection conn, Museum museum) throws SQLException {
        String query = "INSERT INTO Museum (Id, Navn, Sted) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, museum.getId());
            stmt.setString(2, museum.getNavn());
            stmt.setString(3, museum.getSted());
            stmt.executeUpdate();
        }
    }

    // Metode for å legge til Mynt i databasen
    public void leggTilMynt(Connection conn, Mynt mynt) throws SQLException {
        String query = "INSERT INTO Mynt (Id, Funnsted, Finner_id, Funntidspunkt, Antatt_AArstall, Museum_id, Diameter, Metall) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, mynt.getId());
            stmt.setString(2, mynt.getFunnSted());
            stmt.setInt(3, mynt.getFinnerId());
            stmt.setTimestamp(4, new java.sql.Timestamp(mynt.getFunnTidspunkt().getTime()));
            stmt.setInt(5, mynt.getAntattAArstall());
            if (mynt.getMuseumId() != null) {
                stmt.setInt(6, mynt.getMuseumId());
            } else {
                stmt.setNull(6, java.sql.Types.INTEGER);
            }
            stmt.setInt(7, mynt.getDiameter());
            stmt.setString(8, mynt.getMetall());
            stmt.executeUpdate();
        }
    }

    // Metode for å legge til smykke i databasen
    public void leggTilSmykke(Connection conn, Smykke smykke) throws SQLException {
        String query = "INSERT INTO Smykke (Id, Funnsted, Finner_id, Funntidspunkt, Antatt_AArstall, Museum_id, Type, Verdiestimat, filnavn) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, smykke.getId());
            stmt.setString(2, smykke.getFunnSted());
            stmt.setInt(3, smykke.getFinnerId());
            stmt.setTimestamp(4, new java.sql.Timestamp(smykke.getFunnTidspunkt().getTime()));
            stmt.setInt(5, smykke.getAntattAArstall());
            if (smykke.getMuseumId() != null) {
                stmt.setInt(6, smykke.getMuseumId());
            } else {
                stmt.setNull(6, java.sql.Types.INTEGER);
            }
            stmt.setString(7, smykke.getType());
            stmt.setInt(8, smykke.getVerdiestimat());
            stmt.setString(9, smykke.getFilnavn());
            stmt.executeUpdate();
        }
    }

    // Metode for å legge til våpen i databasen
    public void leggTilVaapen(Connection conn, Vaapen vaapen) throws SQLException {
        String query = "INSERT INTO Vaapen (Id, Funnsted, Finner_id, Funntidspunkt, Antatt_AArstall, Museum_id, Type, Materiale, Vekt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, vaapen.getId());
            stmt.setString(2, vaapen.getFunnSted());
            stmt.setInt(3, vaapen.getFinnerId());
            stmt.setTimestamp(4, new java.sql.Timestamp(vaapen.getFunnTidspunkt().getTime()));
            stmt.setInt(5, vaapen.getAntattAArstall());
            if (vaapen.getMuseumId() != null) {
                stmt.setInt(6, vaapen.getMuseumId());
            } else {
                stmt.setNull(6, java.sql.Types.INTEGER);
            }
            stmt.setString(7, vaapen.getType());
            stmt.setString(8, vaapen.getMateriale());
            stmt.setInt(9, vaapen.getVekt());
            stmt.executeUpdate();
        }
    }
}
