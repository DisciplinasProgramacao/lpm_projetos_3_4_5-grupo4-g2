package models;

public class Filme extends Midia{
    private int duracao;

    public Filme(String id, String nome, String dataLancamento, int duracao) {
        this.id = id;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.duracao = duracao;
        this.addGenero();
        this.addIdioma();
    }
}
