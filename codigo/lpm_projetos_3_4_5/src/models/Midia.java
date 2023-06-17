package models;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public abstract class Midia {
    protected String id;
    protected String nome;
    protected String dataLancamento;
    protected int audiencia = 0;
    protected String genero;
    protected String idioma;
    protected ArrayList<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
    protected double media = 0.0;

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
        String[] generos = {"Ação", "Anime", "Aventura", "Comédia", "Documentário", "Drama", "Policial", "Romance", "Suspense"};
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

    public void setAudiencia(int audiencia) {
        this.audiencia = audiencia;
    }

    
    @Override
    public String toString() {
        return "Nome: " + this.nome + " | Média: " + this.media;
    }

    // operacoes da classe
    private void calcMedia() {
        double acumulador = 0.0;
        for(Avaliacao a : avaliacoes) {
            acumulador += a.getNota();
        } 

        if(this.avaliacoes.size() == 0) {
            this.media = acumulador/1;
        } else {
            this.media = acumulador/this.avaliacoes.size();
        }
        
    }

    public boolean verificaLancamento(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date data = dateFormat.parse(dataLancamento);

            Calendar hoje = Calendar.getInstance();
            hoje.set(Calendar.HOUR_OF_DAY, 0);
            hoje.set(Calendar.MINUTE, 0);
            hoje.set(Calendar.SECOND, 0);
            hoje.set(Calendar.MILLISECOND, 0);

            Calendar dataFilme = Calendar.getInstance();
            dataFilme.setTime(data);
            dataFilme.set(Calendar.HOUR_OF_DAY, 0);
            dataFilme.set(Calendar.MINUTE, 0);
            dataFilme.set(Calendar.SECOND, 0);
            dataFilme.set(Calendar.MILLISECOND, 0);

            Calendar futuro = Calendar.getInstance();
            futuro.setTime(dataFilme.getTime());
            futuro.add(Calendar.DAY_OF_YEAR, 7);

            return hoje.compareTo(dataFilme) >= 0 && hoje.compareTo(futuro) <= 0;

        } catch(Exception e) {
            e.getMessage();
        }
        return false;
    }
}
