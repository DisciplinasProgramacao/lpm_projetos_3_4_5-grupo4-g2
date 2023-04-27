package entities;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

	private String nomeDeUsuario;
	private String senha;
	private List<Serie> listaParaVer = new ArrayList<Serie>(0);
	private List<Serie> listaJaVistas = new ArrayList<Serie>();

	public Cliente(String string, String string2) {
	}

	/**
	 * 
	 * Adiciona uma série à lista para ver.
	 * 
	 * @param serie a série a ser adicionada
	 */
	public void adicionarNaLista(Serie serie) {
		listaParaVer.add(serie);
	}

	/**
	 * 
	 * Remove uma série da lista para ver pelo seu nome.
	 * 
	 * @param nomeSerie o nome da série a ser removida
	 */
	public void retirarDaLista(String nomeSerie) {
		for (Serie serie : listaParaVer) {
			if (serie.getNome().equals(nomeSerie)) {
				listaParaVer.remove(serie);
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

	public String getNomeDeUsuario() {
		return nomeDeUsuario;
	}

	public void setNomeDeUsuario(String nomeDeUsuario) {
		this.nomeDeUsuario = nomeDeUsuario;
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

	public void setListaParaVer(List<Serie> listaParaVer) {
		this.listaParaVer = listaParaVer;
	}

	public List<Serie> getListaJaVistas() {
		return listaJaVistas;
	}

	public void setListaJaVistas(List<Serie> listaJaVistas) {
		this.listaJaVistas = listaJaVistas;
	}

}
