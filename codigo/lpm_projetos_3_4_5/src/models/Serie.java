package models;

public class Serie extends Midia{
    private static final int MAX_EPISIODIOS = 23;
    private int quantidadeEpisodios;

    // construtor e getters / setters
    public Serie(String id, String nome, String dataLancamento) {
        this.id = id;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.addGenero();
        this.addIdioma();
        this.addQuantidadeEpisodios();
    }

    public int getQuantidadeEpisodios() {
        return quantidadeEpisodios;
    }

    // operacoes da classe
    private void addQuantidadeEpisodios() {
        this.quantidadeEpisodios = (int) (Math.random() * (MAX_EPISIODIOS - 1) + 1);
    }
}