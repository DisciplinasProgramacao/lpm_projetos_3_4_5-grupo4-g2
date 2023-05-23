package entities;

public class Serie extends Midia {
    private int quantidadeEpisodios;

    public Serie(String id, String nome, String dataLancamento, int audiencia) {
        super(id, nome, dataLancamento, audiencia);
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
