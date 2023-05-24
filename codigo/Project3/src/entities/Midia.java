package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//Essa classe será a superclasse da classe Filme e Serie
public class Midia {
    private String id;
    private String nome;
    private String dataLancamento;
    private int audiencia;
    private String genero;
    private String idioma;
    private List<Avaliacao> avaliacoes;

    public Midia(String id, String nome, String dataLancamento, int audiencia) {
        this.id = id;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.audiencia = audiencia;
        this.avaliacoes = new ArrayList<>();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public int getAudiencia() {
        return audiencia;
    }

    public void setAudiencia(int audiencia) {
        this.audiencia = audiencia;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }



    public double calcularMediaAvaliacoes() {
        if (avaliacoes.isEmpty()) {
            return 0.0;
        }
    
        double somaAvaliacoes = 0.0;
        for (Avaliacao avaliacao : avaliacoes) {
            somaAvaliacoes += avaliacao.getNota();
        }
    
        return somaAvaliacoes / avaliacoes.size();
    }
    

    public void cadastrarMidia(Midia midia) {
        if (midia instanceof Serie) {
            Serie serie = (Serie) midia;
            List<Midia> series = new ArrayList<>();
            series.add(serie);
            System.out.println("Série cadastrada com sucesso: " + serie.getNome());
        } else if (midia instanceof Filme) {
            Filme filme = (Filme) midia;
            List<Midia> filmes = new ArrayList<>();;
            filmes.add(filme);
            System.out.println("Filme cadastrado com sucesso: " + filme.getNome());
        }
    }

    public void adicionarAvaliacao(Avaliacao avaliacao) {
        avaliacoes.add(avaliacao);
    }

   

    public void registrarAudiencia() {
        audiencia++;
    }

    public LocalDate getDataAssistida() {
        return null;
    }

    public Avaliacao getAvaliacao() {
        return null;
    }
    
    
}



