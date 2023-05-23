package entities;

public class Filme extends Midia {
    private int duracaoFilme;
    private String idioma;

    public Filme(String id, String nome, String dataLancamento, int audiencia) {
        super(id, nome, dataLancamento, audiencia);
        this.duracaoFilme = duracaoFilme;
    }

    // getters e setters

    public int getDuracaoFilme() {
        return duracaoFilme;
    }

    public void setDuracao(int duracaoFilme) {
        this.duracaoFilme = duracaoFilme;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @Override
    public String toString() {
        return "Filme: " + getNome() + ", Gênero: " + getGenero() + ", Duração: " + getDuracaoFilme() + " minutos";
    }
}