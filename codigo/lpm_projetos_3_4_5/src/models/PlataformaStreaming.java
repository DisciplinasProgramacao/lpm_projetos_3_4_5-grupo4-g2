package models;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import javax.sound.sampled.Line;

public class PlataformaStreaming {
    public static PlataformaStreaming instancia;
    private String nome;
    private ArrayList<Midia> midias;
    private ArrayList<Cliente> clientes;
    private Cliente clienteAtual = null;

    // construtor e getters / setters
    private PlataformaStreaming(String nome) {
        this.nome = nome;
        this.midias = new ArrayList<Midia>();
        this.clientes = new ArrayList<Cliente>();
    }

    public static PlataformaStreaming getInstance(String nome) {
        if(instancia == null) {
            instancia = new PlataformaStreaming(nome);
        }
        return instancia;
    }

    public Cliente getClienteAtual() {
        return clienteAtual;
    }

    public ArrayList<Midia> getMidias() {
        return midias;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    // operações da classe
    public Cliente login(String user, String senha) {
        for(Cliente c : clientes) {
            if(c.getUser().equals(user) && c.getSenha().equals(senha)){
                clienteAtual = new Cliente(c.getIdCliente(), c.getNome(), c.getUser(), 
                                                    c.getSenha(), c.getParaVer(), c.getAssistidas(), c.getAvaliadas());
            }
        }
        return this.clienteAtual;
    }

    public void logoff(){
        this.clienteAtual = null;
    }

    public void addCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public void filtraPorNome(String nome) {
        midias.stream().filter(m -> m.getNome().equals(nome))
        .forEach(m -> System.out.println(m.getNome()));
    }
    

    // leitura / escrita de dados
    public void preencherFilmes() throws Exception {
        Files.lines(Paths.get("/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_test/POO_Filmes.csv"))
        .skip(1)
        .map(line -> line.split(";"))
        .map(col -> new Filme(col[0], col[1], col[2], Integer.parseInt(col[3])))
        .forEach(midias::add);
    }

    public void preencherSeries() throws Exception {
        Files.lines(Paths.get("/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_test/POO_Series.csv"))
        .map(line -> line.split(";"))
        .map(col -> new Serie(col[0], col[1], col[2]))
        .forEach(midias::add);
    }

    public void preencherClientes() throws Exception {
        Files.lines(Paths.get("/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_test/POO_Espectadores.csv"))
        .map(line -> line.split(";"))
        .forEach((col) -> {
            int numeroLinha = this.clientes.size() + 1;
            String idCliente = String.valueOf(numeroLinha);
            Cliente cliente = new Cliente(idCliente, col[0], col[1], col[2]);
            this.addCliente(cliente);
        });
    }

    public void preencherAudiencia() throws Exception {
        Files.lines(Paths.get("/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_test/POO_Audiencia.csv")).
        map(lines -> lines.split(";")).
        forEach((aud) -> {
            Midia auxMidia = null;
            for(Midia m : this.midias){
                if(m.getId().equals(aud[2])) {
                    m.addAudiencia();
                    auxMidia = m;

                    for(Cliente c : clientes) {
                        if(c.getUser().equals(aud[0])) {
                            if(aud[1].equals("F")) {
                                c.addParaVer(auxMidia);
                                break;
                            } else {
                                c.addAssistidas(auxMidia);
                                break;
                            }
                        }
                    }
                }
            }
        });
    }

    // operadores para testes
    public void printAudPerMidia(){
        System.out.println("audiencia de series");
        for(Midia m : this.midias) {
            System.out.println("id: " + m.getId() + " | nome: " + m.getNome() + " | audiencia: " + m.getAudiencia());
        }
    }

    public void printAllClientes() {
        System.out.println("Listas dos clientes:");
        for(Cliente c : this.clientes) {
            System.out.println("id: " + c.getIdCliente() + " | nome: " + c.getNome() + " | lista Assistidas: " + c.getAssistidas().size() + " | lista para ver: " + c.getParaVer().size());
        }
    }
}
