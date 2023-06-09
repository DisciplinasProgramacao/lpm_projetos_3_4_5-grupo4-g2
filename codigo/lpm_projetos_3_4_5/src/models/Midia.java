package models;

import java.util.ArrayList;

public abstract class Midia {
    protected String id;
    protected String nome;
    protected String dataLancamento;
    protected int audiencia = 0;
    protected String genero;
    protected String idioma;
    protected ArrayList<Avaliacao> avaliacoes;
    protected double media;

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

    // operacoes da classe
    protected void addGenero() {
        String[] generos = {"Ação", "Drama", "Anão"};
        this.genero = generos[(int) (Math.random() * generos.length)];
    }

    protected void addIdioma() {
        String[] idiomas = {"Inglês", "Português", "Francês", "Espanhol"};
        this.idioma = idiomas[(int) (Math.random() * idiomas.length)];
    }
}
