package models;

public class Filme extends Midia {

    private int duracao;

    /**
     * Construtor da classe Filme.
     * 
     * @param id             o ID do filme
     * @param nome           o nome do filme
     * @param dataLancamento a data de lançamento do filme
     * @param duracao        a duração do filme em minutos
     */
    public Filme(String id, String nome, String dataLancamento, int duracao) {
        this.id = id;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.duracao = duracao;
        this.setGenero();
        this.setIdioma();
    }

    /**
     * Obtém a duração do filme.
     * 
     * @return a duração do filme em minutos
     */
    public int getDuracao() {
        return duracao;
    }
}