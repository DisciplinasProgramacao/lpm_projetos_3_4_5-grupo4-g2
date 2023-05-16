package entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
        if (midia instanceof Serie) {
            Serie serie = (Serie) midia;
            List<Midia> series = new ArrayList<>();
            series.add(serie);
            System.out.println("Série cadastrada com sucesso: " + serie.getNome());
        } else if (midia instanceof Filme) {
            Filme filme = (Filme) midia;
            String chave = Double.toString(filme.getAudiencia()); 
            HashMap<Double, Midia> filmes;
            filmes.put(filme.getId().toString(), filme);
            System.out.println("Filme cadastrado com sucesso: " + filme.getNome());
        }
    }

    public void carregarCSV(String arquivoCSV) {
        String linha;
        String separador = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))) {
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(separador);
                String id = dados[0];
                String nome = dados[1];
                String dataDeLancamento = dados[2];
                int audiencia = Integer.parseInt(dados[3]);
                String genero = dados[4];

                Midia midia = new Midia(id, nome, dataDeLancamento, audiencia, genero);

            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }
    }
}
