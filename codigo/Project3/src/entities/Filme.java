package entities;

public class Filme extends Midia {
    private int duracaoFilme;

    public Filme(String id, String nome, String dataLancamento, int audiencia) {
        super(id, nome, dataLancamento, audiencia);
    }

    // getters e setters

    public int getDuracaoFilme() {
        return duracaoFilme;
    }

    public void setDuracao(int duracaoFilme) {
        this.duracaoFilme = duracaoFilme;
    }

    @Override
    public String toString() {
        return "Filme: " + getNome() + ", Gênero: " + getGenero() + ", Duração: " + getDuracaoFilme() + " minutos";
    }
}