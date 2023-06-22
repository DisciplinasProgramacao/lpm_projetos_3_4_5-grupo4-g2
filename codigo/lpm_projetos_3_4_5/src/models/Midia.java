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

    /**
     * Obtém o ID da mídia.
     * 
     * @return O ID da mídia.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtém o nome da mídia.
     * 
     * @return O nome da mídia.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém a audiência da mídia.
     * 
     * @return A audiência da mídia.
     */
    public int getAudiencia() {
        return audiencia;
    }

    /**
     * Incrementa a audiência da mídia em 1.
     */
    public void addAudiencia() {
        this.audiencia++;
    }

    /**
     * Obtém o gênero da mídia.
     * 
     * @return O gênero da mídia.
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Obtém o idioma da mídia.
     * 
     * @return O idioma da mídia.
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * Obtém a data de lançamento da mídia.
     * 
     * @return A data de lançamento da mídia.
     */
    public String getDataLancamento() {
        return dataLancamento;
    }

    /**
     * Obtém as avaliações da mídia.
     * 
     * @return As avaliações da mídia.
     */
    public ArrayList<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    /**
     * Define o gênero da mídia aleatoriamente.
     */
    protected void setGenero() {
        String[] generos = { "Ação", "Anime", "Aventura", "Comédia", "Documentário", "Drama", "Policial", "Romance",
                "Suspense" };
        this.genero = generos[(int) (Math.random() * generos.length)];
    }

    /**
     * Define o idioma da mídia aleatoriamente.
     */
    protected void setIdioma() {
        String[] idiomas = { "Inglês", "Português", "Francês", "Espanhol" };
        this.idioma = idiomas[(int) (Math.random() * idiomas.length)];
    }

    /**
     * Obtém a média das avaliações da mídia.
     * 
     * @return A média das avaliações da mídia.
     */
    public double getMedia() {
        calcMedia();
        return media;
    }

    /**
     * Define a audiência da mídia.
     * 
     * @param audiencia A audiência da mídia.
     */
    public void setAudiencia(int audiencia) {
        this.audiencia = audiencia;
    }

    /**
     * Calcula a média das avaliações da mídia.
     */
    private void calcMedia() {
        double acumulador = 0.0;
        for (Avaliacao a : avaliacoes) {
            acumulador += a.getNota();
        }

        if (this.avaliacoes.size() == 0) {
            this.media = acumulador / 1;
        } else {
            this.media = acumulador / this.avaliacoes.size();
        }
    }

    /**
     * Verifica se a mídia foi lançada nos últimos 7 dias.
     * 
     * @return true se a mídia foi lançada nos últimos 7 dias, caso contrário,
     *         false.
     */
    public boolean verificaLancamento() {
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

        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }
}