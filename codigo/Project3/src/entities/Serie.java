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

	public Serie(int id, String nome, String genero, String idioma, int quantidadeEpisodios) {
		this.id = id;
		this.nome = nome;
		this.genero = genero;
		this.idioma = idioma;
		this.audiencia = 0;
		this.quantidadeEpisodios = quantidadeEpisodios;
	}
	
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
	 * Retorna o nome da série.
	 * 
	 * @return String representando o nome da série.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Retorna o gênero da série.
	 * 
	 * @return String representando o gênero da série.
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * Retorna o idioma da série.
	 * 
	 * @return String representando o idioma da série.
	 */
	public String getIdioma() {
		return idioma;
	}

	/**
	 * Retorna a quantidade de episódios da série.
	 * 
	 * @return int representando a quantidade de episódios da série.
	 */
	public int getQuantidadeEpisodios() {
		return quantidadeEpisodios;
	}

	/**
	 * Retorna a audiência da série.
	 * 
	 * @return int representando a audiência da série.
	 */
	public double getAudiencia() {
		return audiencia;
	}

	/**
	 * Retorna um array com os gêneros disponíveis.
	 * 
	 * @return String[] representando os gêneros disponíveis.
	 */
	public static String[] getGeneros() {
		return GENEROS;
	}

	/**
	 * Define o nome da série.
	 * 
	 * @param nome String representando o nome da série.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Define o gênero da série.
	 * 
	 * @param genero String representando o gênero da série.
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}

	/**
	 * Define o idioma da série.
	 * 
	 * @param idioma String representando o idioma da série.
	 */
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	/**
	 * Define a quantidade de episódios da série.
	 * 
	 * @param quantidadeEpisodios int representando a quantidade de episódios da
	 *                            série.
	 */
	public void setQuantidadeEpisodios(int quantidadeEpisodios) {
		this.quantidadeEpisodios = quantidadeEpisodios;
	}

	/**
	 * Define a audiência da série.
	 * 
	 * @param audiencia2 int representando a audiência da série.
	 */
	public void setAudiencia(double audiencia2) {
		this.audiencia = audiencia2;
	}

	
	public void todasAsSeries() {
		
	}
	
	
	@Override
	public String toString() {
		return "Serie [nome=" + nome + ", genero=" + genero + ", idioma=" + idioma + ", quantidadeEpisodios="
				+ quantidadeEpisodios + "]";
	}
	
	
	
}
