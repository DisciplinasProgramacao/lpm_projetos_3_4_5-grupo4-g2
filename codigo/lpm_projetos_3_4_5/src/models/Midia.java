package models;

import java.util.ArrayList;

public abstract class Midia {
    protected String id;
    protected String nome;
    protected String dataLancamento;
    protected int audiencia = 0;
    protected String genero;
    protected String idioma;
    protected ArrayList<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
    protected double media = 0;

    // getters / setters
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getAudiencia() {
        return audiencia;
    }

    public void addAudiencia() {
        this.audiencia++;
    }

    public String getGenero() {
        return genero;
    }

    public String getIdioma() {
        return idioma;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public ArrayList<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }
    
    protected void setGenero() {
        String[] generos = {"Ação", "Drama", "Anão"};
        this.genero = generos[(int) (Math.random() * generos.length)];
    }

    protected void setIdioma() {
        String[] idiomas = {"Inglês", "Português", "Francês", "Espanhol"};
        this.idioma = idiomas[(int) (Math.random() * idiomas.length)];
    }

    public double getMedia() {
        calcMedia();
        return media;
    }

    // operacoes da classe
    private void calcMedia() {
        int acumulador = 0;
        for(Avaliacao a : avaliacoes) {
            acumulador += a.getNota();
        } 

        this.media = acumulador/this.avaliacoes.size();
    }
}
