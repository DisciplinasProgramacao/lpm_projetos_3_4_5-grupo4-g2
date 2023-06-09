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

    public String getId() {
        return id;
    }
}
