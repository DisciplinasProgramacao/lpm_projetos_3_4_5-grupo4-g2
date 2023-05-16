package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cliente {
    private String nomeDoUsuario;
    private String loginDoUsuario;
    private String senha;
    private List<Midia> listaParaVer;
    private List<Midia> listaJaVista;
	private HashMap<Double, Midia> avaliacoes;

    public Cliente(String nomeDoUsuario, String loginDoUsuario, String senha) {
        this.nomeDoUsuario = nomeDoUsuario;
        this.loginDoUsuario = loginDoUsuario;
        this.senha = senha;
        this.listaParaVer = new ArrayList<>();
        this.listaJaVista = new ArrayList<>();
        this.avaliacoes = new HashMap<>();
    }

    // getters e setters

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

    public List<Midia> getListaParaVer() {
        return listaParaVer;
    }

    public void setListaParaVer(List<Midia> listaParaVer) {
        this.listaParaVer = listaParaVer;
    }

    public List<Midia> getListaJaVista() {
        return listaJaVista;
    }

    public void setListaJaVista(List<Midia> listaJaVista) {
        this.listaJaVista = listaJaVista;
    }

	public HashMap<Double, Midia> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(HashMap<Double, Midia> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}


    public void cadastrarMidia(Midia midia) {
        // lógica para cadastrar uma mídia
    }

    public void carregarCSV(String arquivoCSV) {
        // lógica para carregar dados de um arquivo CSV
    }
}
