package entities;

public class Serie extends Midia {
    private int quantidadeEpisodios;

    public Serie(int id, String nome, String dataLancamento, int audiencia, String genero, int quantidadeEpisodios, String idioma) {
        super(id, nome, dataLancamento, audiencia, genero, idioma);
        this.quantidadeEpisodios = quantidadeEpisodios;
    }

    // getters e setters

    public int getQuantidadeEpisodios() {
        return quantidadeEpisodios;
    }

    public void setQuantidadeEpisodios(int quantidadeEpisodios) {
        this.quantidadeEpisodios = quantidadeEpisodios;
    }

    @Override
    public String toString() {
        return "Série: " + getNome() + ", Gênero: " + getGenero() + ", Quantidade de Episódios: " + getQuantidadeEpisodios();
    }
}
