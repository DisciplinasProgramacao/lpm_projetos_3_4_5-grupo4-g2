package models;

public class Serie extends Midia{
    private int quantidadeEpisodios;

    public Serie(String id, String nome, String dataLancamento) {
        this.id = id;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
    }
}