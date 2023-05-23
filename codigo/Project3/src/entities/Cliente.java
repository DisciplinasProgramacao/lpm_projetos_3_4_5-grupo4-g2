package entities;
import java.util.ArrayList;
import java.util.List;

class Cliente {
    private String nome;
    private String nomeDoUsuario;
    private String senha;
    private List<Midia> listaParaVer;
    private List<Midia> listaAssistidas;

    public Cliente(String nome, String nomeDoUsuario, String senha) {
        this.nome = nome;
        this.nomeDoUsuario = nomeDoUsuario;
        this.senha = senha;
        this.listaParaVer = new ArrayList<>();
        this.listaAssistidas = new ArrayList<>();
    }

    public void adicionarNaListaParaVer(Midia midia) {
        listaParaVer.add(midia);
    }

    public void retirarNaListaParaVer(Midia midia) {
        listaParaVer.remove(midia);
    }

    public void retirarNaListaAssistidas(Midia midia) {
        listaAssistidas.remove(midia);
    }

    public void mostrarLista(){
        for (Midia m : listaParaVer) {
            System.out.println(m.toString());
        }
    }

    public List<Midia> filtrarPorGenero(String genero) {
        List<Midia> midiasFiltradas = new ArrayList<>();
        List<Midia> catalogo = PlataformaStreaming.getCatalogo(); // Obtenha o catálogo completo da plataforma
        for (Midia midia : catalogo) {
            if (midia.getGenero().equals(genero)) {
                midiasFiltradas.add(midia);
            }
        }
        return midiasFiltradas;
    }
    
    public List<Midia> filtrarPorIdioma(String idioma) {
        List<Midia> midiasFiltradas = new ArrayList<>();
        List<Midia> catalogo = PlataformaStreaming.getCatalogo(); // Obtenha o catálogo completo da plataforma
        for (Midia midia : catalogo) {
            if (midia.getIdioma().equals(idioma)) {
                midiasFiltradas.add(midia);
            }
        }
        return midiasFiltradas;
    }
    
    public List<Midia> filtrarPorQtdEpisodios(int quantEpisodios) {
        List<Midia> midiasFiltradas = new ArrayList<>();
        List<Midia> catalogo = PlataformaStreaming.getCatalogo(); // Obtenha o catálogo completo da plataforma
        for (Midia midia : catalogo) {
            if (midia instanceof Serie) {
                Serie serie = (Serie) midia;
                if (serie.getQuantidadeEpisodios() >= quantEpisodios) {
                    midiasFiltradas.add(midia);
                }
            }
        }
        return midiasFiltradas;
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