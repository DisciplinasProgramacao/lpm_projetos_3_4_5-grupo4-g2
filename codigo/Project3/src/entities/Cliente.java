package entities;
import java.util.ArrayList;
import java.util.List;

class Cliente {
    private String nomeDoUsuario;
    private String senha;
    private List<Midia> listaParaVer;
    private List<Midia> listaAssistidas;
    private List<Serie> listaAssistidas1;

    public Cliente(String nomeDoUsuario, String senha) {
        this.nomeDoUsuario = nomeDoUsuario;
        this.senha = senha;
        this.listaParaVer = new ArrayList<>();
        this.listaAssistidas = new ArrayList<>();
    }

    public void adicionarNaListaParaVer(Serie serie) {
        listaParaVer.add(serie);
    }

    public void retirarNaListaParaVer(Serie serie) {
        listaParaVer.remove(serie);
    }

    public void retirarNaListaAssistidas(Serie serie) {
        listaAssistidas.remove(serie);
    }

    public List<Midia> filtrarPorGenero(String genero) {
        List<Midia> seriesFiltradas = new ArrayList<>();
        for (Midia midia : listaAssistidas) {
            if (midia.getGenero().equals(genero)) {
                seriesFiltradas.add(midia);
            }
        }
        return seriesFiltradas;
    }

    public List<Midia> filtrarPorIdioma(String idioma) {
        List<Midia> seriesFiltradas = new ArrayList<>();
        for (Midia midia : listaAssistidas) {
            if (midia.getIdioma().equals(idioma)) {
                seriesFiltradas.add(midia);
            }
        }
        return seriesFiltradas;
    }

    public List<Serie> filtrarPorQtdEpisodios(int quantidadeEpisodios) {
        List<Serie> seriesFiltradas = new ArrayList<>();
        for (Serie serie : listaAssistidas1) {
            if (serie.getQuantidadeEpisodios() >= quantidadeEpisodios) {
                seriesFiltradas.add(serie);
            }
        }
        return seriesFiltradas;
    }

    public void avaliar(Midia midia, int avaliacao) {
        if (avaliacao >= 1 && avaliacao <= 5) {
            Avaliacao novaAvaliacao = new Avaliacao(avaliacao);
            midia.adicionarAvaliacao(novaAvaliacao);
        } else {
            System.out.println("Avaliação inválida. A avaliação deve ser um número entre 1 e 5.");
        }
    }


    // Getters e setters...
    public String getNomeDoUsuario() {
        return nomeDoUsuario;
    }

    public String getSenha() {
        return senha;
    }
}