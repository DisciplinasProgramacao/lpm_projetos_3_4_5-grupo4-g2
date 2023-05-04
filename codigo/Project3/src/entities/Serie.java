package entities;

public class Serie {

	public static final String[] GENEROS = { "Ação", "Comédia", "Drama", "Terror", "Ficção científica" };
	private int id;
	private String nome;
	private String genero;
	private String idioma;
	private int quantidadeEpisodios;
	private double audiencia;
	private int dataDeLancamento;

	/**
	 * Construtor da classe Serie que recebe o id, o nome, o gênero, o idioma e a
	 * quantidade de episódios da série e os atribui ao objeto instanciado.
	 * 
	 * @param ido,                 id da série.
	 * @param nome,                o nome da série.
	 * @param genero,              o gênero da série.
	 * @param idioma,              o idioma da série.
	 * @param quantidadeEpisodios, a quantidade de episódios da série.
	 */

	public Serie(int id, String nome, String genero, String idioma, int quantidadeEpisodios) {
		this.id = id;
		this.nome = nome;
		this.genero = genero;
		this.idioma = idioma;
		this.audiencia = 0;
		this.quantidadeEpisodios = quantidadeEpisodios;
	}

	/**
	 * Construtor da classe Serie que recebe o id, o nome e a data de lançamento da
	 * série e os atribui ao objeto instanciado.
	 * 
	 * @param id               o id da série.
	 * @param nome             o nome da série.
	 * @param dataDeLancamento a data de lançamento da série.
	 */
	public Serie(int id, String nome, int dataDeLancamento) {
		this.id = id;
		this.nome = nome;
		this.dataDeLancamento = dataDeLancamento;
	}

	/**
	 * Método que registra uma nova audiência para a série.
	 */
	public void registrarAudiencia() {
		this.audiencia++;
	}

	/**
	 * Método criado para retornar todas as séries
	 */
	public void todasAsSeries() {

	}

	/**
	 * Retorna uma representação em formato de string do objeto instanciado da
	 * classe Serie, incluindo informações sobre o nome, o gênero, o idioma e a
	 * quantidade de episódios da série.
	 * 
	 * @return uma string contendo informações sobre o objeto instanciado da classe
	 *         Serie.
	 */
	@Override
	public String toString() {
		return "Serie [nome=" + nome + ", genero=" + genero + ", idioma=" + idioma + ", quantidadeEpisodios="
				+ quantidadeEpisodios + "]";
	}

	// Abaixo estão todos os getters e setters necessários

	public String getNome() {
		return nome;
	}

	public String getGenero() {
		return genero;
	}

	public String getIdioma() {
		return idioma;
	}

	public int getQuantidadeEpisodios() {
		return quantidadeEpisodios;
	}

	public double getAudiencia() {
		return audiencia;
	}

	public static String[] getGeneros() {
		return GENEROS;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public void setQuantidadeEpisodios(int quantidadeEpisodios) {
		this.quantidadeEpisodios = quantidadeEpisodios;
	}

	public void setAudiencia(double audiencia2) {
		this.audiencia = audiencia2;
	}

}
