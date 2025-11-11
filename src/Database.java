import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private Connection connection;
    private static Database instance;

    private Database() throws SQLException {
        String url = "jdbc:sqlite:database/musica.db";
        connection = DriverManager.getConnection(url);
        System.out.println("Connessione al database locale stabilita.");

        String createTable = """
        CREATE TABLE IF NOT EXISTS artisti_preferiti (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nome TEXT NOT NULL,
            paese TEXT,
            genere TEXT
        );
        """;
        connection.createStatement().execute(createTable);
    }

    public static Database getInstance() throws SQLException {
        if (instance == null)
            instance = new Database();
        return instance;
    }

    // CREATE → salva artista preferito
    public boolean salvaArtista(Artista artista) {
        String query = "INSERT INTO artisti_preferiti (nome, paese, genere) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, artista.getNome());
            stmt.setString(2, artista.getPaese());
            stmt.setString(3, artista.getGenere());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Errore durante il salvataggio dell'artista: " + e.getMessage());
            return false;
        }
    }

    // READ → visualizza la collezione locale
    public List<Artista> getCollezione() {
        List<Artista> lista = new ArrayList<>();
        String query = "SELECT * FROM artisti_preferiti";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Artista a = new Artista(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("paese"),
                        rs.getString("genere"),
                        null
                );
                lista.add(a);
            }
        } catch (SQLException e) {
            System.err.println("Errore durante la lettura della collezione: " + e.getMessage());
        }
        return lista;
    }

    // DELETE → elimina artista locale per ID
    public boolean eliminaArtista(int id) {
        String query = "DELETE FROM artisti_preferiti WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Errore durante l'eliminazione dell'artista: " + e.getMessage());
            return false;
        }
    }
}
