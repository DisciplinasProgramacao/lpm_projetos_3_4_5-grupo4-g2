package entities;

public class Filme extends Midia {
    private int duracaoFilme;

    public Filme(String id, String nome, String dataLancamento, int audiencia, String genero, int duracaoFilme, String idioma) {
        super(id, nome, dataLancamento, audiencia, genero, idioma);
        this.duracaoFilme = duracaoFilme;
    }

    // getters e setters

    public int getDuracaoFilme() {
        return duracaoFilme;
    }

    public void setDuracaoFilme(int duracaoFilme) {
        this.duracaoFilme = duracaoFilme;
    }
}