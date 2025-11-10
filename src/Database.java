import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private Connection connection;
    private static Database instance; // metto un oggetto database nella classe Database

    private Database() throws SQLException {
        String url = "jdbc:sqlite:database/my_collection.db";
        connection = DriverManager.getConnection(url);
        System.out.println("Connessione al database");
    }

    // Metodo get
    public static Database getInstance() throws SQLException {
        if(instance == null)
            instance = new Database();
        return instance;
    }

    // Metodo per salvare l'artista scelto dall'utente


    // Metodo per aprire la collezione locale dell'utente
}
