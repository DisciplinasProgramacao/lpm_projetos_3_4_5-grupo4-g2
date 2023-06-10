package models;

public class Filme extends Midia{
    private int duracao;

    // construtores e getters / setters
    public Filme(String id, String nome, String dataLancamento, int duracao) {
        this.id = id;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.duracao = duracao;
        this.setGenero();
        this.setIdioma();
    }

    public int getDuracao() {
        return duracao;
    }
}
