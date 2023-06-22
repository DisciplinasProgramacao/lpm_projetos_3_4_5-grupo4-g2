package models;

public class Serie extends Midia {

    private static final int MAX_EPISODIOS = 23;
    private int quantidadeEpisodios;

    /**
     * 
     * Construtor da classe Serie.
     * 
     * @param id             o identificador da série
     * @param nome           o nome da série
     * @param dataLancamento a data de lançamento da série
     */
    public Serie(String id, String nome, String dataLancamento) {
        this.id = id;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.setGenero();
        this.setIdioma();
        this.setQuantidadeEpisodios();
    }

    /**
     * 
     * Obtém a quantidade de episódios da série.
     * 
     * @return a quantidade de episódios
     */
    public int getQuantidadeEpisodios() {
        return quantidadeEpisodios;
    }

    /**
     * 
     * Define a quantidade de episódios da série de forma aleatória,
     * dentro do limite estabelecido por MAX_EPISODIOS.
     */
    private void setQuantidadeEpisodios() {
        this.quantidadeEpisodios = (int) (Math.random() * (MAX_EPISODIOS - 1) + 1);
    }
}