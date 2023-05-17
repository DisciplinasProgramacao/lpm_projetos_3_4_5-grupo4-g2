package entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
    private List<Midia> listaParaVer;

    public Midia(String id, String nome, String dataLancamento, int audiencia, String genero, String idioma) {
        this.id = id;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.audiencia = audiencia;
        this.genero = genero;
        this.idioma = idioma;
        this.avaliacoes = new ArrayList<>();
        this.listaParaVer = new ArrayList<>();
    }

    // getters e setters

   

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


    public double calcularMediaAvaliacao() {
        List<Integer> avaliacoes = new ArrayList<>();
        avaliacoes.add(4);
        avaliacoes.add(5);
        avaliacoes.add(3);

        int soma = 0;
        for (int avaliacao : avaliacoes) {
            soma += avaliacao;
        }

        if (avaliacoes.size() > 0) {
            return (double) soma / avaliacoes.size();
        } else {
            return 0.0;
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

                Midia midia = new Midia(id, nome, dataDeLancamento, audiencia, genero, idioma);

            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }
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
            //filmes.put(filme.getId().toString(), filme);
            System.out.println("Filme cadastrado com sucesso: " + filme.getNome());
        }
    }

    public void adicionarAvaliacao(Avaliacao avaliacao) {
        avaliacoes.add(avaliacao);
    }

    public List<Midia> registrarAudiencia() {
        List<Midia> midiasRegistradas = new ArrayList<>();
        for (Midia midia : listaParaVer) {
            midia.setAudiencia(midia.getAudiencia() + 1);
            midiasRegistradas.add(midia);
        }
    
        return midiasRegistradas;
    }
}

