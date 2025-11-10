import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class APIClient {
    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    // 1. Metodo per ricavare il catalogo delle canzoni
    public List<Canzone> getCanzoni() {
        String url = "http://localhost:4567/api/canzoni";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(url))
                .header("Accept", "application/json")
                .GET()
                .build();
        try {
            HttpResponse<String> resp = client.send(request, HttpResponse.BodyHandlers.ofString());

            Canzone[] canzoniArray = gson.fromJson(resp.body(), Canzone[].class);
            return Arrays.asList(canzoniArray);

        } catch (Exception e) {
            System.out.println("Errore nella richiesta API: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // 2. Metodo per cercare una canzone
    public Canzone cercaCanzone(String titolo) {
        for (Canzone canzone : getCanzoni()) {
            if (canzone.getTitolo().equalsIgnoreCase(titolo)) {
                return canzone;
            }
        }
        return null;
    }

    // 3. Metodo per ricavare il catalogo degli artisti
    public List<Artista> getArtisti() {
        String url = "http://localhost:4567/api/artisti";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(url))
                .header("Accept", "application/json")
                .GET()
                .build();
        try {
            HttpResponse<String> resp = client.send(request, HttpResponse.BodyHandlers.ofString());

            Artista[] artistiArray = gson.fromJson(resp.body(), Artista[].class);
            return Arrays.asList(artistiArray);

        } catch (Exception e) {
            System.out.println("Errore nella richiesta API: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // 4. Metodo per visualizzare i dettagli di un artista
    public Artista cercaArtista(String nome) {
        for (Artista artista : getArtisti()) {
            if (artista.getNome().equalsIgnoreCase(nome)) {
                return artista;
            }
        }
        return null;
    }

    // 5. Metodo per aggiungere un artista
    public boolean addArtista(Artista artista) {
        String url = "http://localhost:4567/api/artisti";

        // Converto l'oggetto Artista in JSON
        String jsonBody = gson.toJson(artista);

        // Costruisco la richiesta POST con il body JSON
        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(url))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        try {
            HttpResponse<String> resp = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Artista creato con successo!");
            return true;
        } catch (Exception e) {
            System.out.println("Errore durante la richiesta API: " + e.getMessage());
            return false;
        }
    }

    // 6. Metodo per eliminare un artista
    public boolean delArtista(Artista artista) {
        String url = "http://localhost:4567/api/artisti/" + artista.getId();

        // Costruisco la richiesta POST con il body JSON
        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(url))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .DELETE()
                .build();
        try {
            HttpResponse<String> resp = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Artista eliminato con successo!");
            return true;
        } catch (Exception e) {
            System.out.println("Errore durante la richiesta API: " + e.getMessage());
            return false;
        }
    }

}
