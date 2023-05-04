package entities;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

	private String nomeDoUsuario;
	private String loginDoUsuario;
	private String senha;
	private List<Serie> listaParaVer = new ArrayList<Serie>(0);
	private List<Serie> listaJaVistas = new ArrayList<Serie>();

	/**
	 * Construtor da classe Cliente que recebe o nome de usuário, o login e a senha
	 * do cliente e os atribui ao objeto instanciado.
	 * 
	 * @param nomeDoUsuario  o nome de usuário do cliente.
	 * @param loginDoUsuario o login do cliente.
	 * @param senha          a senha do cliente.
	 * 
	 */
	public Cliente(String nomeDoUsuario, String loginDoUsuario, String senha) {
		this.nomeDoUsuario = nomeDoUsuario;
		this.loginDoUsuario = loginDoUsuario;
		this.senha = senha;
	}

	/**
	 * Construtor da classe Cliente que recebe um nome de usuário e o atribui ao
	 * objeto instanciado.
	 * 
	 * @param nomeDeUsuario o nome de usuário do cliente.
	 */
	public Cliente(String nomeDeUsuario) {
		this.nomeDoUsuario = nomeDeUsuario;
	}

	/**
	 * Adiciona uma série à lista para ver.
	 * 
	 * @param serie a série a ser adicionada
	 */
	public void adicionarNaListaParaVer(Serie serie) {
		listaParaVer.add(serie);
	}

	/**
	 * Adiciona a séria na lista de séries assistidas.
	 * 
	 * @param serie a série a ser adicionada
	 */
	public void adicionarNaListaAssistidas(Serie serie) {
		listaJaVistas.add(serie);
	}

	/**
	 * Recebe o nome de uma seria e remove a série da lista para ver (séries não
	 * assistidas).
	 * 
	 * @param nomeSerie o nome da série a ser removida
	 */
	public void retirarDaListaParaver(String nomeSerie) {
		for (Serie serie : listaParaVer) {
			if (serie.getNome().equals(nomeSerie)) {
				listaParaVer.remove(serie);
				break;
			}
		}
	}

	/**
	 * Método que recebe o nome de uma série e remove a série correspondente da
	 * lista de séries já vistas.
	 * 
	 * @param nomeSerie o nome da série a ser removida da lista de séries já vistas.
	 * @return void
	 */
	public void retirarDaListaAssistida(String nomeSerie) {
		for (Serie serie : listaJaVistas) {
			if (serie.getNome().equals(nomeSerie)) {
				listaJaVistas.remove(serie);
				break;
			}
		}
	}

	/**
	 * Filtra as séries já vistas por gênero.
	 * 
	 * @param genero o gênero a ser utilizado para filtrar
	 * @return uma lista de séries que correspondem ao gênero fornecido
	 */
	public List<Serie> filtrarPorGenero(String genero) {
		List<Serie> seriesFiltradas = new ArrayList<>();
		for (Serie serie : listaJaVistas) {
			if (serie.getGenero().equals(genero)) {
				seriesFiltradas.add(serie);
			}
		}
		return seriesFiltradas;
	}

	/**
	 * Filtra as séries já vistas por idioma.
	 * 
	 * @param idioma o idioma a ser utilizado para filtrar
	 * @return uma lista de séries que correspondem ao idioma fornecido
	 */
	public List<Serie> filtrarPorIdioma(String idioma) {
		List<Serie> seriesFiltradas = new ArrayList<>();
		for (Serie serie : listaJaVistas) {
			if (serie.getIdioma().equals(idioma)) {
				seriesFiltradas.add(serie);
			}
		}
		return seriesFiltradas;
	}

	/**
	 * Filtra as séries já vistas por quantidade de episódios.
	 * 
	 * @return uma lista de séries que têm mais de 10 episódios
	 */
	public List<Serie> filtrarPorQtdEpisodios() {

		List<Serie> seriesFiltradas = new ArrayList<>();
		for (Serie serie : listaJaVistas) {
			if (serie.getQuantidadeEpisodios() > 10) {
				seriesFiltradas.add(serie);
			}
		}
		return seriesFiltradas;
	}

	/**
	 * Registra a audiência de uma série.
	 * 
	 * @param serie a série para a qual a audiência será registrada
	 */
	public void registrarAudiencia(Serie serie) {
		serie.registrarAudiencia();
	}

	/**
	 * Método que invoca os métodos getListaJaVistas() e getListaParaVer() para
	 * apresentar todas as séries armazenadas nas listas correspondentes.
	 * 
	 * @return void
	 */
	public void todasAsSeries() {
		getListaJaVistas();
		getListaParaVer();
	}

	/**
	 * Método para avaliar uma mídia assistida.
	 * @param resp Resposta do usuário indicando se deseja ou não avaliar a mídia. Deve ser "S" ou "N".
	 * @param avaliacao Avaliação numérica da mídia, de 1 a 5 estrelas. 
	 * @return Retorna a avaliação numérica da mídia (de 1 a 5 estrelas) se o usuário escolher avaliar a mídia, caso contrário, retorna null.
	 */
	public Object avaliar(String resp, int avaliacao){
		System.out.println("Deseja avaliar a midia que acabou de ver?  S || N");
		if(resp == "S"){
			System.out.println("Digite quantas estrelas você da a essa midia entre 1 a 5");
			return avaliacao;
		} else {
		return null;
		}
	}

	// Abaixo estãos os métodos getters e setters necessários

	public String getNomeDoUsuario() {
		return nomeDoUsuario;
	}

	public void setNomeDoUsuario(String nomeDoUsuario) {
		this.nomeDoUsuario = nomeDoUsuario;
	}

	public String getLoginDoUsuario() {
		return loginDoUsuario;
	}

	public void setLoginDoUsuario(String loginDoUsuario) {
		this.loginDoUsuario = loginDoUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Serie> getListaParaVer() {
		return listaParaVer;
	}

	public List<Serie> getListaJaVistas() {
		return listaJaVistas;
	}

}
