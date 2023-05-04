package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente {

	Scanner sc = new Scanner(System.in);

	private String nomeDoUsuario;
	private String loginDoUsuario;
	private String senha;
	private List<Serie> listaParaVer = new ArrayList<Serie>(0);
	private List<Serie> listaJaVistas = new ArrayList<Serie>();

	public Cliente(String string, String string2) {
	}	
	
	public Cliente(String nomeDoUsuario, String loginDoUsuario, String senha) {
		this.nomeDoUsuario = nomeDoUsuario;
		this.loginDoUsuario = loginDoUsuario;
		this.senha = senha;
	}

	public Cliente(String nomeDeUsuario) {
		this.nomeDoUsuario = nomeDeUsuario;
	}

	/**
	 * 
	 * Adiciona uma série à lista para ver.
	 * 
	 * @param serie a série a ser adicionada
	 */
	public void adicionarNaListaParaVer(Serie serie) {
		listaParaVer.add(serie);
	}
	
	public void adicionarNaListaAssistidas(Serie serie) {
		listaJaVistas.add(serie);
	}

	/**
	 * 
	 * Remove uma série da lista para ver pelo seu nome.
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
	
	public void retirarDaListaAssistida(String nomeSerie) {
		for (Serie serie : listaJaVistas) {
			if (serie.getNome().equals(nomeSerie)) {
				listaJaVistas.remove(serie);
				break;
			}
		}
	}

	/**
	 * 
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
	 * 
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
	 * 
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
	 * 
	 * Registra a audiência de uma série.
	 * 
	 * @param serie a série para a qual a audiência será registrada
	 */
	public void registrarAudiencia(Serie serie) {
		serie.registrarAudiencia();
	}


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
	
	public void todasAsSeries() {
		 getListaJaVistas(); 
		 getListaParaVer();
	}
	

	public Object avaliar(String resp, int avaliacao){
		System.out.println("Deseja avaliar a midia que acabou de ver?  S || N");
		if(resp == "S"){
			System.out.println("Digite quantas estrelas você da a essa midia entre 1 a 5");
			return avaliacao;
		} else {
		return null;
		}
	}

}
