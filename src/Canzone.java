public class Canzone {
    private Integer id;
    private String titolo;
    private Integer durata;
    private Integer annoPubblicazione;
    private Artista artista;

    public Canzone(Integer id, String titolo, Integer durata, Integer annoPubblicazione, Artista artista) {
        this.id = id;
        this.titolo = titolo;
        this.durata = durata;
        this.annoPubblicazione = annoPubblicazione;
        this.artista = artista;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Integer getDurata() {
        return durata;
    }

    public void setDurata(Integer durata) {
        this.durata = durata;
    }

    public Integer getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(Integer annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public Artista getArtista() {
        return artista;
    }

    public void informazioniCanzone() {
        System.out.println("Titolo: " + this.getTitolo());
        System.out.println("Durata: " + this.getDurata());
        System.out.println("Anno di pubblicazione: " + this.getAnnoPubblicazione());
    }
}
