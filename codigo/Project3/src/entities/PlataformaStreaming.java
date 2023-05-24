package entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlataformaStreaming {
    private String nome;
    private static List<Midia> midias;
    private List<Cliente> clientes;
    private Cliente clienteAtual;

    public PlataformaStreaming(String nome) {
        this.nome = nome;
        PlataformaStreaming.midias = new ArrayList<>();
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
        PlataformaStreaming.midias = midias;
    }


    public Cliente getClienteAtual() {
        return clienteAtual;
    }

    public void setClienteAtual(Cliente clienteAtual) {
        this.clienteAtual = clienteAtual;
    }

  
    public void mostrarMidias(){
        for (Midia m : midias) {
            System.out.println(m.toString());
        }
    }

    public static List<Midia> getCatalogo() {
        return midias;
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

   /**public void carregarFilmesCSV(String caminhoArquivoFilmes) {
    carregarMidias(caminhoArquivoFilmes, "filme");
}

public void carregarSeriesCSV(String caminhoArquivoSeries) {
    carregarMidias(caminhoArquivoSeries, "serie");
}

private void carregarMidias(String caminhoArquivo, String tipoMidia) {
    try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
        String linha;

        while ((linha = br.readLine()) != null) {
            // Separar os campos da linha do CSV
            String[] campos = linha.split(";");

            // Verificar o tipo de mídia
            if (tipoMidia.equalsIgnoreCase("filme")) {
                // Carregar os campos do filme
                int id = Integer.parseInt(campos[0]);
                String nome = campos[1];
                String dataDeLancamento = campos[2];
                int audiencia = Integer.parseInt(campos[3]);

                // Criar o objeto Filme
                Filme filme = new Filme(id, nome, dataDeLancamento, audiencia);

                // Adicionar o filme à lista de filmes da plataforma
                adicionarMidia(filme);
            } else if (tipoMidia.equalsIgnoreCase("serie")) {
                // Carregar os campos da série
                int id = Integer.parseInt(campos[0]);
                String nome = campos[1];
                String dataDeLancamento = campos[2];

                // Criar o objeto Série
                Serie serie = new Serie(id, nome, dataDeLancamento);

                // Adicionar a série à lista de séries da plataforma
                adicionarMidia(serie);
            }
        }

        System.out.println("Dados carregados do arquivo CSV com sucesso!");
    } catch (IOException e) {
        System.out.println("Erro ao carregar os dados do arquivo CSV: " + e.getMessage());
    }
} */ 

    public void carregarCSV(String caminhoArquivoFilmes, String caminhoArquivoSeries) {
        carregarMidias(caminhoArquivoFilmes, "filme");
        carregarMidias(caminhoArquivoSeries, "serie");
    }


   private void carregarMidias(String caminhoArquivo, String tipoMidia) {
    try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
        String linha;

        while ((linha = br.readLine()) != null) {
            if (linha.charAt(0) == '\uFEFF') {
                linha = linha.substring(1);
            }

            linha = linha.trim();

            if (linha.isEmpty()) {
                continue; 
            }

            // Separar os campos da linha do CSV
            String[] campos = linha.split(";");

            // Verificar o tipo de mídia
            if (tipoMidia.equalsIgnoreCase("filme")) {
                if (campos.length < 4) {
                    System.out.println("Campos ausentes para adicionar um filme: " + linha);
                    continue;
                }

                // Carregar os campos do filme
                String id = campos[0];
                String nome = campos[1];
                String dataDeLancamento = campos[2];
                int audiencia = Integer.parseInt(campos[3]);

                // Utilizar a classe Util para adicionar os campos ausentes
                Filme filme = new Filme(id, nome, dataDeLancamento, audiencia);
                Util.adicionarVariaveisAusentesFilme(filme);

                // Adicionar o filme à lista de filmes da plataforma
                adicionarMidia(filme);
            } else if (tipoMidia.equalsIgnoreCase("serie")) {
                if (campos.length < 3) {
                    System.out.println("Campos ausentes para adicionar uma série: " + linha);
                    continue;
                }

                // Carregar os campos da série
                String id = campos[0];
                String nome = campos[1];
                String dataDeLancamento = campos[2];
                int audiencia = Util.gerarAudienciaAleatoria();

                // Utilizar a classe Util para adicionar os campos ausentes
                Serie serie = new Serie(id, nome, dataDeLancamento, audiencia);
                Util.adicionarVariaveisAusentesSerie(serie);

                // Adicionar a série à lista de séries da plataforma
                adicionarMidia(serie);
            }
        }

        System.out.println("Dados carregados do arquivo CSV com sucesso!");
    } catch (IOException e) {
        System.out.println("Erro ao carregar os dados do arquivo CSV: " + e.getMessage());
    }
}


    
    
    public void carregarEspectadoresCSV(String caminhoArquivoEspectadores) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoEspectadores))) {
            String linha;
    
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(";");
                String nome = campos[0];
                String nomeDoUsuario = campos[1];
                String senha = campos[2];
    
                // Criar o objeto Cliente
                Cliente cliente = new Cliente(nome, nomeDoUsuario, senha);
    
                // Adicionar o cliente à lista de clientes da plataforma
                adicionarCliente(cliente);
            }
    
            System.out.println("Espectadores carregados do arquivo CSV com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar os espectadores do arquivo CSV: " + e.getMessage());
        }
    }
    
    public void carregarAudienciaCSV(String caminhoArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
    
            while ((linha = br.readLine()) != null) {
                // Separar os campos da linha do CSV
                String[] campos = linha.split(";");
    
                // Verificar se a linha possui os campos esperados
                if (campos.length == 3) {
                    String nomeCliente = campos[0];
                    String tipoAudiencia = campos[1];
                    int audiencia = Integer.parseInt(campos[2]);
    
                    // Encontrar o cliente correspondente pelo nome
                    Cliente cliente = buscarClientePorNome(nomeCliente);
                    if (cliente != null) {
                        // Registrar a audiência para o cliente
                       // cliente.registrarAudiencia(tipoAudiencia, audiencia);
                    } else {
                        System.out.println("Cliente não encontrado com o nome: " + nomeCliente);
                    }
                } else {
                    System.out.println("Linha inválida: " + linha);
                }
            }
    
            System.out.println("Dados de audiência carregados do arquivo CSV com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar os dados de audiência do arquivo CSV: " + e.getMessage());
        }
    }
    
    private Cliente buscarClientePorNome(String nomeCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getNomeDoUsuario().equals(nomeCliente)) {
                return cliente;
            }
        }
        return null;
    }




  
    
}