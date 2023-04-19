package entities;

import java.util.List;

public class Cliente {
	
	private String nomeUsuario;
	private String senha;
	private List<Serie> listaParaVer;
	private List<Serie> listaJaVistas;
	
	
	public Cliente(String nomeUsuario, String senha) {
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
	}


	public String getNomeUsuario() {
		return nomeUsuario;
	}


	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void adicionarNaLista(Serie serie) {}
	
	public void retirarDaLista(String serie) {}
	
	public List<Serie> filtrarPorGenero(String genero) {
		return null;
	}
	
	public List<Serie> filtrarPorIdioma(String idioma) {
		return null;
	}
	
	public List<Serie> filtrarPorQtdEpisodios(int qtdEpisodios) {
		return null;
	}
	
	public void regristrarAudiencia (Serie serie) {}
	
	
	
	

}
