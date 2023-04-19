package entities;

import java.util.List;

public class PlataformaStreaming {

	private String nome;
	//private Hash<Serie> serie;
	//private Hash<Cliente> cliente;
	private Cliente clienteAtual;
	
	
	public PlataformaStreaming(String nome, Cliente clienteAtual) {
		this.nome = nome;
		this.clienteAtual = clienteAtual;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Cliente getClienteAtual() {
		return clienteAtual;
	}


	public void setClienteAtual(Cliente clienteAtual) {
		this.clienteAtual = clienteAtual;
	}
	
	
	public Cliente login(String usuario, String senha) {
		return null;
	}
	
	public void adicionaSerie(Serie serie) {}
	
	public void adicionaCliente(Cliente cliente) {}
	
	public List<Serie> filtrarPorGenero(String genero) {
		return null;
	}
	
	public List<Serie> filtrarPorIdioma(String idioma) {
		return null;
	}
	
	public List<Serie> filtrarPorQtdEpisodios(int qtdEpisodios) {
		return null;
	}
	
	public void registrarAudiencia(Serie serie) {}
	
	public void logOff() {}
	
	public Serie buscarSerie(String nomeSerie) {
		return null;
	}
}
