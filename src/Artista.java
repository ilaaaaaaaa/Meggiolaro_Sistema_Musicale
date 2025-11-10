import java.util.List;

public class Artista {
    private Integer id;
    private String nome;
    private String paese;
    private String genere;
    private List<Canzone> canzoni;

    public Artista(Integer id, String nome, String paese, String genere,  List<Canzone> canzoni) {
        this.id = id;
        this.nome = nome;
        this.paese = paese;
        this.genere = genere;
        this.canzoni = canzoni;
    }

    // Metodi get e set
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPaese() {
        return paese;
    }

    public void setPaese(String paese) {
        this.paese = paese;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public List<Canzone> getCanzoni() {
        return canzoni;
    }

    public void informazioniArtista() {
        System.out.println("Artista: " + this.getNome());
        System.out.println("Paese: " + this.getPaese());
        System.out.println("Genere: " + this.getGenere());
    }
}
