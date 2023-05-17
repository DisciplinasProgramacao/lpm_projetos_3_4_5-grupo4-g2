package entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlataformaStreaming {
    private String nome;
    private List<Midia> midias;
    private List<Cliente> clientes;
    private Cliente clienteAtual;

    public PlataformaStreaming(String nome) {
        this.nome = nome;
        this.midias = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.clienteAtual = null;
    }


    // getters and setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Midia> getMidias() {
        return midias;
    }

    public void setMidias(List<Midia> midias) {
        this.midias = midias;
    }


    public Cliente getClienteAtual() {
        return clienteAtual;
    }

    public void setClienteAtual(Cliente clienteAtual) {
        this.clienteAtual = clienteAtual;
    }

  
    public void mostrarFilmes(){
        for (Midia m : midias) {
            System.out.println(m.toString());
        }
    }

    public Cliente login(String nomeUsuario, String senha) {
        for (Cliente cliente : clientes) {
            if (cliente.getNomeDoUsuario().equals(nomeUsuario) && cliente.getSenha().equals(senha)) {
                clienteAtual = cliente;
                return cliente;
            }
        }
        return null;
    }


    public void adicionarMidia(Midia midia) {
        midias.add(midia);
    }

    public void adicionarCliente(Cliente cliente1) {
        clientes.add(cliente1);
    }

    public List<Midia> filtrarPorQtdEpisodios(int quantEpisodios) {
        List<Midia> midiasFiltradas = new ArrayList<>();
        for (Midia midia : midias) {
            if (midia instanceof Serie) {
                Serie serie = (Serie) midia;
                if (serie.getQuantidadeEpisodios() >= quantEpisodios) {
                    midiasFiltradas.add(midia);
                }
            }
        }
        return midiasFiltradas;
    }

    public List<Filme> filtrarPorGenero(String genero) {
        List<Filme> filmesFiltrados = new ArrayList<>();
        for (Midia midia : midias) {
            if (midia instanceof Filme && midia.getGenero().equals(genero)) {
                filmesFiltrados.add((Filme) midia);
            }
        }
        return filmesFiltrados;
    }

    public List<Midia> filtrarPorIdioma(String idioma) {
        List<Midia> midiasFiltradas = new ArrayList<>();
        for (Midia midia : midias) {
            if (midia.getIdioma().equals(idioma)) {
                midiasFiltradas.add(midia);
            }
        }
        return midiasFiltradas;
    }

    public void registrarAudiencia(Midia midia) {
        if (midia instanceof Serie) {
            midia.registrarAudiencia();
        }
    }

    public void logoff() {
        clienteAtual = null;
    }

    public Midia buscarMidia(String nomeMidia) {
        for (Midia midia : midias) {
            if (midia.getNome().equals(nomeMidia)) {
                return midia;
            }
        }
        return null;
    }

    public void carregarCSV(String nomeArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                // Separar os campos da linha do CSV
                String[] campos = linha.split(";");

                // Verificar o tipo de mídia (filme ou série)
                String tipoMidia = campos[0];

                PlataformaStreaming plataforma = new PlataformaStreaming(nomeArquivo);

                if (tipoMidia.equalsIgnoreCase("filme")) {
                    // Criar um objeto Filme com os dados do CSV
                    int id = Integer.parseInt(campos[1]);
                    String nome = campos[2];
                    String dataDeLancamento = campos[3];
                    int audiencia = Integer.parseInt(campos[4]);
                    String genero = campos[5];
                    int duracao = Integer.parseInt(campos[6]);
                    String idioma = campos[7];

                    Midia filme = new Filme(id, nome, dataDeLancamento, audiencia, genero, duracao, idioma);

                    // Adicionar o filme à plataforma
                    plataforma.adicionarMidia(filme);
                } else if (tipoMidia.equalsIgnoreCase("serie")) {
                    // Criar um objeto Série com os dados do CSV
                    int id = Integer.parseInt(campos[1]);
                    String nome = campos[2];
                    String dataDeLancamento = campos[3];
                    int audiencia = Integer.parseInt(campos[4]);
                    String genero = campos[5];
                    int quantidadeEpisodios = Integer.parseInt(campos[6]);
                    String idioma = campos[7];

                    Midia serie = new Serie(id, nome, dataDeLancamento, audiencia, genero, quantidadeEpisodios, idioma);

                    // Adicionar a série à plataforma
                    plataforma.adicionarMidia(serie);
                }
            }

            System.out.println("Dados carregados do arquivo CSV com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar os dados do arquivo CSV: " + e.getMessage());
        }
    }
}