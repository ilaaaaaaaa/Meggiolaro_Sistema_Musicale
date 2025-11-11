import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        APIClient apiClient = new APIClient();
        Scanner sc = new Scanner(System.in);
        Database db = null;

        try {
            db = Database.getInstance();
        } catch (SQLException e) {
            System.out.println("Errore di connessione al database!");
            return;
        }

        int scelta = -1;
        while (scelta != 0) {
            // MENU' DEL SISTEMA MUSICALE
            System.out.println("\n--- ♪ MENU' SISTEMA MUSICALE ♪ ---");
            System.out.println("1. Esplora il catalogo delle canzoni");
            System.out.println("2. Cerca una canzone");
            System.out.println("3. Consulta gli artisti");
            System.out.println("4. Visualizza i dettagli di un artista");
            System.out.println("5. Aggiungi un artista");
            System.out.println("6. Elimina un artista");
            System.out.println("7. Apri la tua collezione");
            System.out.println("0. Esci");
            System.out.print("Scegli: ");

            scelta = Integer.parseInt(sc.nextLine());

            if (scelta == 1) {
                List<Canzone> canzoni = apiClient.getCanzoni();
                for (Canzone canzone : canzoni) {
                    canzone.informazioniCanzone();
                    System.out.println("-----------------------------");
                }

            } else if (scelta == 2) {
                System.out.print("Inserisci il titolo: ");
                String inputTitolo = sc.nextLine();

                Canzone trovata = apiClient.cercaCanzone(inputTitolo);
                if (trovata != null)
                    trovata.informazioniCanzone();
                else
                    System.out.println("Nessuna canzone trovata.");

            } else if (scelta == 3) {
                List<Artista> artisti = apiClient.getArtisti();
                for (Artista artista : artisti) {
                    artista.informazioniArtista();
                    System.out.println("-----------------------------");
                }

            } else if (scelta == 4) {
                System.out.print("Inserisci il nome dell'artista: ");
                String inputNome = sc.nextLine();

                Artista trovato = apiClient.cercaArtista(inputNome);
                if (trovato != null) {
                    trovato.informazioniArtista();

                    System.out.print("Vuoi salvare questo artista nella tua collezione? (s/n): ");
                    String risposta = sc.nextLine();
                    if (risposta.equalsIgnoreCase("s")) {
                        if (db.salvaArtista(trovato))
                            System.out.println("Artista salvato nella collezione locale!");
                        else
                            System.out.println("Errore durante il salvataggio.");
                    }
                }else {
                    System.out.println("Nessun artista trovato.");
                }
            } else if (scelta == 5) {
                System.out.println("\nInserisci i dati del nuovo artista");

                System.out.print("Nome: ");
                String inputNome = sc.nextLine();

                System.out.print("Paese di provenienza: ");
                String inputPaese = sc.nextLine();

                System.out.print("Genere musicale: ");
                String inputGenere = sc.nextLine();

                Artista nuovo = new Artista(null, inputNome, inputPaese, inputGenere, null);

                boolean successo = apiClient.addArtista(nuovo);

                if (successo)
                    System.out.println("Artista aggiunto con successo!\n");
                else
                    System.out.println("Errore durante l'aggiunta dell'artista.\n");

            } else if (scelta == 6) {
                System.out.print("Inserisci il nome dell'artista da eliminare: ");
                String nomeArtista = sc.nextLine();

                Artista artistaDaEliminare = apiClient.cercaArtista(nomeArtista);

                if (artistaDaEliminare != null) {
                    boolean eliminato = apiClient.delArtista(artistaDaEliminare);
                    if (eliminato)
                        System.out.println("Artista eliminato con successo!");
                    else
                        System.out.println("Errore durante l'eliminazione.");
                } else {
                    System.out.println("Artista non trovato.");
                }

            } else if (scelta == 7) {
                List<Artista> collezione = db.getCollezione();
                if (collezione.isEmpty()) {
                    System.out.println("La tua collezione è vuota.");
                } else {
                    System.out.println("\nLa tua collezione personale:");
                    for (Artista a : collezione) {
                        a.informazioniArtista();
                        System.out.println("-----------------------------");
                    }
                }
            } else if (scelta == 0) {
                System.out.println("Uscita dal programma...");
            } else {
                System.out.println("Scelta non valida!");
            }
        }

        sc.close();
    }
}