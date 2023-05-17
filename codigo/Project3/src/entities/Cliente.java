package entities;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Cliente {
    private String nomeDoUsuario;
    private String senha;
    private boolean especialista;
    private List<Midia> listaParaVer;
    private List<Midia> listaAssistidas;
    private List<Avaliacao> listaAvaliadas = new ArrayList<>();

    public Cliente(String nomeDoUsuario, String senha) {
        this.nomeDoUsuario = nomeDoUsuario;
        this.senha = senha;
        this.listaParaVer = new ArrayList<>();
        this.listaAssistidas = new ArrayList<>();
    }

    //Atualiza o status do cliente para tipo Especialista

    /**
     * É utilizado a classe padrão LocalDate para obter a data atual e a data do mês anterior. 
     * Em seguida, contamos a quantidade de mídias assistidas pelo cliente no mês anterior e atualizamos seu status 
     * de especialista se necessário.
     */
    public void atualizarStatusEspecialista() {
        // Obtém a data atual
        LocalDate dataAtual = LocalDate.now();
        // Define a data de referência como sendo o mês anterior
        LocalDate dataReferencia = dataAtual.minusMonths(1);
        
        // Conta quantas mídias o cliente assistiu no mês anterior
        int quantidadeAssistidas = 0;
        for (Midia midia : listaAssistidas) {
            LocalDate dataAssistida = midia.getDataAssistida();
            if (dataAssistida != null && dataAssistida.isAfter(dataReferencia) && dataAssistida.isBefore(dataAtual)) {
                quantidadeAssistidas++;
            }
        }
        
        // Atualiza o status de especialista se necessário
        if (quantidadeAssistidas >= 5) {
            especialista = true;
            System.out.println("Parabéns! Você atingiu o status de especialista.");
        }
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
        for (Midia midia : listaParaVer) {
            if (midia.getGenero().equals(genero)) {
                midiasFiltradas.add(midia);
            }
        }
        return midiasFiltradas;
    }
    
    public List<Midia> filtrarPorIdioma(String idioma) {
        List<Midia> midiasFiltradas = new ArrayList<>();
        for (Midia midia : listaParaVer) {
            if (midia.getIdioma().equals(idioma)) {
                midiasFiltradas.add(midia);
            }
        }
        return midiasFiltradas;
    }

    public List<Midia> filtrarPorQtdEpisodios(int quantEpisodios) {
        List<Midia> midiasFiltradas = new ArrayList<>();
        for (Midia midia : listaParaVer) {
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


    //Lista de avaliações

    public void adicionarAvaliacao(Avaliacao avaliacao) {
        listaAvaliadas.add(avaliacao);
    }
    
    public List<Avaliacao> getListaAvaliadas() {
        return listaAvaliadas;
    }
    
    public double calcularMediaAvaliacoes() {
        double soma = 0.0;
        for (Avaliacao avaliacao : listaAvaliadas) {
            soma += avaliacao.getNota();
        }
        if (listaAvaliadas.size() == 0) {
            return 0.0;
        }
        return soma / listaAvaliadas.size();
    }
    

    //Metodo para avaliar atraves de um comentario 

    public void avaliarComentario(Midia midia, int avaliacao, String comentario) {
        if (especialista) {
            avaliar(midia, avaliacao);
            if (listaAvaliadas.contains(midia)) {
                Avaliacao avaliacaoAtual = midia.getAvaliacao();
                avaliacaoAtual.setComentario(comentario);
                System.out.println("Avaliação com comentário registrada com sucesso!");
            }
        } else {
            System.out.println("Você não possui permissão para adicionar comentários.");
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