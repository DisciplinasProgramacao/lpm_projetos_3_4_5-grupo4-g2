package entities;
import entities.Filme;
import entities.Midia;
import entities.Serie;

import java.util.Random;

public class Util {
    private static final String[] GENEROS = {"Ação", "Comédia", "Terror", "Suspense", "Romance"};
    private static final String[] IDIOMAS = {"Inglês", "Português", "Espanhol", "Francês", "Alemão"};
    private static final Random RANDOM = new Random();

    public static void adicionarVariaveisAusentesFilme(Filme filme) {
        filme.setGenero(gerarGeneroAleatorio());
        filme.setDuracao(gerarDuracaoAleatoria());
        filme.setIdioma(gerarIdiomaAleatorio());
    }

    public static void adicionarVariaveisAusentesSerie(Serie serie) {
        serie.setGenero(gerarGeneroAleatorio());
        serie.setQuantidadeEpisodios(gerarQuantidadeEpisodiosAleatoria());
        serie.setIdioma(gerarIdiomaAleatorio());
    }

    private static String gerarGeneroAleatorio() {
        int indice = RANDOM.nextInt(GENEROS.length);
        return GENEROS[indice];
    }

    public static int gerarDuracaoAleatoria() {
        // Gerar uma duração aleatória entre 60 e 180 minutos
        return RANDOM.nextInt(121) + 60;
    }

    public static String gerarIdiomaAleatorio() {
        int indice = RANDOM.nextInt(IDIOMAS.length);
        return IDIOMAS[indice];
    }

    public static int gerarQuantidadeEpisodiosAleatoria() {
        // Gerar uma quantidade de episódios aleatória entre 1 e 20
        return RANDOM.nextInt(20) + 1;
    }

    public static int gerarAudienciaAleatoria() {
        // Gerar uma audiência aleatória entre 1 e 100
        return RANDOM.nextInt(100) + 1;
    }
}
