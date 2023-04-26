package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlataformaStreaming {
	private String nome;
	private HashMap<String, Serie> series;
	private HashMap<String, Cliente> clientes;
	private Cliente clienteAtual;

	/**
	 * Construtor da classe PlataformaStreaming. Cria uma nova plataforma de
	 * streaming com um nome específico, sem séries ou clientes cadastrados e sem
	 * cliente atualmente logado.
	 * 
	 * @param nome o nome da nova plataforma de streaming
	 */
	public PlataformaStreaming(String nome) {
		this.nome = nome;
		this.series = new HashMap<String, Serie>();
		this.clientes = new HashMap<String, Cliente>();
		this.clienteAtual = null;
	}

	/**
	 * Tenta fazer login na plataforma com um nome de usuário e senha específicos.
	 * Caso o login seja bem-sucedido, define o cliente como o cliente atual da
	 * plataforma.
	 * 
	 * @param nomeUsuario o nome de usuário do cliente que deseja fazer login
	 * @param senha       a senha do cliente que deseja fazer login
	 * @return o cliente que fez login, caso o login tenha sido bem-sucedido; null,
	 *         caso contrário
	 */
	public Cliente login(String nomeUsuario, String senha) {
		Cliente cliente = clientes.get(nomeUsuario);
		if (cliente != null && cliente.getSenha().equals(senha)) {
			this.clienteAtual = cliente;
			return cliente;
		}
		return null;
	}

	/**
	 * Adiciona uma nova série à plataforma.
	 * 
	 * @param serie a série a ser adicionada à plataforma
	 */
	public void adicionarSerie(Serie serie) {
		this.series.put(serie.getNome(), serie);
	}

	/**
	 * Adiciona um novo cliente à plataforma.
	 * 
	 * @param cliente o cliente a ser adicionado à plataforma
	 */
	public void adicionarCliente(Cliente cliente) {
		this.clientes.put(cliente.getNomeDeUsuario(), cliente);
	}

	/**
	 * Filtra as séries cadastradas na plataforma pelo gênero especificado.
	 * 
	 * @param genero o gênero pelo qual as séries devem ser filtradas
	 * @return uma lista contendo todas as séries cadastradas na plataforma que
	 *         possuem o gênero especificado
	 */
	public List<Serie> filtrarPorGenero(String genero) {
		List<Serie> listaFiltrada = new ArrayList<Serie>();
		for (Serie serie : this.series.values()) {
			if (serie.getGenero().equals(genero)) {
				listaFiltrada.add(serie);
			}
		}
		return listaFiltrada;
	}

	/**
	 * Filtra as séries cadastradas na plataforma pelo idioma especificado.
	 * 
	 * @param idioma o idioma pelo qual as séries devem ser filtradas
	 * @return uma lista contendo todas as séries cadastradas na plataforma que
	 *         possuem o idioma especificado
	 */
	public List<Serie> filtrarPorIdioma(String idioma) {
		List<Serie> listaFiltrada = new ArrayList<Serie>();
		for (Serie serie : this.series.values()) {
			if (serie.getIdioma().equals(idioma)) {
				listaFiltrada.add(serie);
			}
		}
		return listaFiltrada;
	}

	/**
	 * Retorna uma lista de séries que possuem a quantidade de episódios informada.
	 * 
	 * @param quantEpisodios a quantidade de episódios que as séries devem ter.
	 * @return uma lista contendo as séries que possuem a quantidade de episódios
	 *         informada.
	 */
	public List<Serie> filtrarPorQtdEpisodios(int quantEpisodios) {
		List<Serie> listaFiltrada = new ArrayList<Serie>();
		for (Serie serie : this.series.values()) {
			if (serie.getQuantidadeEpisodios() == quantEpisodios) {
				listaFiltrada.add(serie);
			}
		}
		return listaFiltrada;
	}

	/**
	 * Registra uma audiência para a série informada, adicionando 1 ao total de
	 * audiência.
	 * 
	 * @param serie a série que terá sua audiência registrada.
	 */
	public void registrarAudiencia(Serie serie) {
		Serie serie1 = series.get(serie);
		if (serie1 != null) {
			serie1.setAudiencia(1);
			System.out.println("Audiência registrada com sucesso!");
		} else {
			System.out.println("Série não encontrada!");
		}
	}

	/**
	 * Realiza o logoff do cliente atual, setando-o para null.
	 */
	public void logoff() {
		clienteAtual = null;
		System.out.println("Logout efetuado com sucesso!");
	}

	/**
	 * Busca e retorna a série com o nome informado.
	 * 
	 * @param nomeSerie o nome da série a ser buscada.
	 * @return a série com o nome informado, ou null caso não seja encontrada.
	 */
	public Serie buscarSerie(String nomeSerie) {
		return this.series.get(nomeSerie);

	}
}
