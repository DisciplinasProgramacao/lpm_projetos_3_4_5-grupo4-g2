import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlataformaStreaming {
    
    // Variáveis de instância
    private String nome;
    private HashMap<String, Serie> series;
    private HashMap<String, Cliente> clientes;
    private Cliente clienteAtual;
    
    // Construtor
    public PlataformaStreaming(String nome) {
        this.nome = nome;
        this.series = new HashMap<>();
        this.clientes = new HashMap<>();
        this.clienteAtual = null;
    }
    
    // Métodos
    public void login(String NomeDeUsuario, String senha) {
        for (Cliente cliente : clientes.values()) {
            if (cliente.getNomeDeUsuario().equals(NomeDeUsuario) && cliente.getSenha().equals(senha)) {
                clienteAtual = cliente;
                System.out.println("Login efetuado com sucesso!");
                return;
            }
        }
        System.out.println("Email ou senha incorretos!");
    }
    
    public void adicionarSerie(Serie serie) {
        series.put(serie.getNome(), serie);
        System.out.println("Série adicionada com sucesso!");
    }
    
    public void adicionarCliente(Cliente cliente) {
        clientes.put(cliente.getNomeDeUsuario(), cliente);
        System.out.println("Cliente adicionado com sucesso!");
    }
    
    public List<Serie> filtrarPorGenero(String genero) {
        List<Serie> seriesFiltradas = new ArrayList<>();
        for (Serie serie : series.values()) {
            if (serie.getGenero().equals(genero)) {
                seriesFiltradas.add(serie);
            }
        }
        return seriesFiltradas;
    }
    
    public List<Serie> filtrarPorIdioma(String idioma) {
        List<Serie> seriesFiltradas = new ArrayList<>();
        for (Serie serie : series.values()) {
            if (serie.getIdioma().equals(idioma)) {
                seriesFiltradas.add(serie);
            }
        }
        return seriesFiltradas;
    }
    
    public List<Serie> filtrarPorQntEpisodios(int qntEpisodios) {
        List<Serie> seriesFiltradas = new ArrayList<>();
        for (Serie serie : series.values()) {
            if (serie.getQuantidadeEpisodios() == qntEpisodios) {
                seriesFiltradas.add(serie);
            }
        }
        return seriesFiltradas;
    }
    
    public void registrarAudiencia(String nomeSerie) {
        Serie serie = series.get(nomeSerie);
        if (serie != null) {
            serie.setAudiencia(1);
            System.out.println("Audiência registrada com sucesso!");
        } else {
            System.out.println("Série não encontrada!");
        }
    }
    
    public void logoff() {
        clienteAtual = null;
        System.out.println("Logout efetuado com sucesso!");
    }
    
    public Serie buscarSerie(String nome) {
        return series.get(nome);
    }
    
}