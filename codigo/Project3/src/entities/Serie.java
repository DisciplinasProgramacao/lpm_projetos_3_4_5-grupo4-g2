package entities;

public class Serie extends Midia {
    private int quantidadeEpisodios;
    private String idioma;

    public Serie(String id, String nome, String dataLancamento, int audiencia) {
        super(id, nome, dataLancamento, audiencia);
        this.quantidadeEpisodios = quantidadeEpisodios;
    }

    // getters e setters

    public int getQuantidadeEpisodios() {
        return quantidadeEpisodios;
    }

    public void setQuantidadeEpisodios(int quantidadeEpisodios) {
        this.quantidadeEpisodios = quantidadeEpisodios;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }


    @Override
    public String toString() {
        return "Série: " + getNome() + ", Gênero: " + getGenero() + ", Quantidade de Episódios: " + getQuantidadeEpisodios();
    }
}
