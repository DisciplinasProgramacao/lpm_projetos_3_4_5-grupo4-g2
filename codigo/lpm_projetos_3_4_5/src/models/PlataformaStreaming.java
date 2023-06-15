package models;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public String getNome() {
        return nome;
    }

    // operações da classe
    public void login(String user, String senha) {
        for(Cliente c : clientes) {
            if(c.getUser().equals(user) && c.getSenha().equals(senha)){
                clienteAtual = new Cliente(c.getIdCliente(), c.getNome(), c.getUser(), 
                                                    c.getSenha(), c.getParaVer(), c.getAssistidas(), c.getAvaliadas());
            }
        }
    }

    public void logoff(){
        this.clienteAtual = null;
    }

    private void printMidiaModelo(Midia m) {
        System.out.println("Nome: " + m.getNome() + " | média: " + m.getMedia());
    }

    public void mostrarCatalogo() {
        midias.forEach(m -> printMidiaModelo(m));
    }

    public void filtraPorNome(String nome) {
        midias.stream().filter(m -> m.getNome().contains(nome))
        .forEach(m -> printMidiaModelo(m));
    }

    public void filtrarPorGenero(String genero) {
        midias.stream().filter(m -> m.getGenero().equals(genero))
        .forEach(m -> printMidiaModelo(m));
    }

    public void filtrarPorIdioma(String idioma) {
        midias.stream().filter(m -> m.getIdioma().equals(idioma))
        .forEach(m -> printMidiaModelo(m));
    }

    public void addParaVer(String nome) {
        for(Midia m : midias) {
            if(m.getNome().equals(nome)) {
                if(!clienteAtual.getParaVer().contains(m)) {
                    clienteAtual.getParaVer().add(m);
                    cadastrarAudiencia(clienteAtual.getUser(), "F", m.getId());
                }
            }
        }
    }

    public Midia assistirMidia(String nome) {
        
        for(Midia m : midias) {
            if(m.getNome().equals(nome)) {
                clienteAtual.getParaVer().remove(m);
                try{
                    removerParaVer(m.getId());
                } catch(Exception e) {
                    e.getMessage();
                }
                
                if(!clienteAtual.getAssistidas().contains(m)) {
                    //clienteAtual.getAssistidas().add(m);
                    //m.addAudiencia();
                    cadastrarAudiencia(clienteAtual.getUser(), "A", m.getId());                  
                    System.out.println("Assistido.");
                    return m;
                } else {
                    System.out.println("Assistido.");
                    return m;
                }
            }
        }
        System.out.println("Não encontrado.");
        return null;
    }

    public void mostrarListaAssistidas() {
        clienteAtual.getAssistidas().forEach(m -> printMidiaModelo(m));
    }

    public void mostrarListaAvaliadas(){
        clienteAtual.getAvaliadas().forEach(a -> {
            for(Midia m : midias) {
                if(a.getIdMidia().equals(m.getId())) {
                    System.out.println("Nome: " + m.getNome() + " Nota: " + a.getNota());
                }
            }
        });
    }

    public void mostrarListaParaVer() {
        clienteAtual.getParaVer().forEach(m -> printMidiaModelo(m));
    }

    // leitura de arquivos
    public void preencherFilmes() throws Exception {
        midias.clear();
        if(clienteAtual != null && !clienteAtual.ehEspecialista()){
            List<Midia> filmes = Files.lines(Paths.get("/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_test/POO_Filmes.csv"))
                .skip(1)
                .map(line -> line.split(";"))
                .map(col -> new Filme(col[0], col[1], col[2], Integer.parseInt(col[3])))
                .filter(m -> !m.verificaLancamento())
                .collect(Collectors.toList());
            midias.addAll(filmes);
        } else {
            Files.lines(Paths.get("/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_test/POO_Filmes.csv"))
            .skip(1)
            .map(line -> line.split(";"))
            .map(col -> new Filme(col[0], col[1], col[2], Integer.parseInt(col[3])))
            .forEach(midias::add);
        }
    }

    public void preencherSeries() throws Exception {
        midias.clear();
        if(clienteAtual != null && !clienteAtual.ehEspecialista()){
            List<Midia> series = Files.lines(Paths.get("/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_test/POO_Series.csv"))
                .map(line -> line.split(";"))
                .map(col -> new Serie(col[0], col[1], col[2]))
                .filter(m -> !m.verificaLancamento())
                .collect(Collectors.toList());
            midias.addAll(series);
        } else {
            Files.lines(Paths.get("/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_test/POO_Series.csv"))
            .map(line -> line.split(";"))
            .map(col -> new Serie(col[0], col[1], col[2]))
            .forEach(midias::add);
        }
    }

    public void preencherClientes() throws Exception {
        clientes.clear();
        Files.lines(Paths.get("/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_test/POO_Espectadores.csv"))
        .map(line -> line.split(";"))
        .forEach((col) -> {
            int numeroLinha = this.clientes.size() + 1;
            String idCliente = String.valueOf(numeroLinha);
            Cliente cliente = new Cliente(idCliente, col[0], col[1], col[2]);
            this.clientes.add(cliente);
        });
    }

    public void preencherAudiencia() throws Exception {
        clienteAtual.getAssistidas().clear();
        midias.forEach(m -> m.setAudiencia(0));
        Files.lines(Paths.get("/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_test/POO_Audiencia.csv"))
        .map(lines -> lines.split(";"))
        .forEach((aud) -> {
            Midia auxMidia;
            for(Midia m : midias){
                if(m.getId().equals(aud[2])) {
                    auxMidia = m;

                    for(Cliente c : clientes) {
                        if(c.getUser().equals(aud[0])) {
                            if(aud[1].equals("F")) {
                                c.getParaVer().add(auxMidia);
                                break;
                            } else {
                                c.getAssistidas().add(auxMidia);
                                m.addAudiencia();
                                break;
                            }
                        }
                    }
                }
            }
        });
    }

    public void preencherAvaliacoes() throws Exception {
        clientes.forEach(c -> c.getAvaliadas().clear());
        Files.lines(Paths.get("/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_test/POO_Avaliacoes.csv"))
        .map(lines -> lines.split(";"))
        .forEach(aval -> {
            for(Midia m : midias) {
                for(Cliente c : clientes) {
                    if(c.getIdCliente().equals(aval[0]) && m.getId().equals(aval[1])) {
                        Avaliacao avaliacao = new Avaliacao(aval[0], aval[1], aval[2], Integer.parseInt(aval[3]));
                        m.getAvaliacoes().add(avaliacao);
                        c.getAvaliadas().add(avaliacao);
                    }
                }
            }
        });
    }

    // escrita de arquivos
    private void escrever(String str, String path) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path), 
        StandardOpenOption.APPEND, StandardOpenOption.CREATE)) {
            writer.write(str);
            writer.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cadastrarFilme(Filme filme) {
        String str = filme.getId()+";"+
                    filme.getNome()+";"+
                    filme.getDataLancamento()+";"+
                    filme.getDuracao();

        escrever(str, "/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_test/POO_Filmes.csv");
        this.midias.add(filme);
    }

    public void cadastrarSerie(Serie serie) {
        String str = serie.getId()+";"+
                    serie.getNome()+";"+
                    serie.getDataLancamento();
        
        escrever(str, "/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_test/POO_Series.csv");
        this.midias.add(serie);
    }

    public void cadastrarCliente(Cliente cliente) {
        String str = cliente.getNome()+";"+
                    cliente.getUser()+";"+
                    cliente.getSenha();

        escrever(str, "/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_test/POO_Espectadores.csv");
        this.clientes.add(cliente);
    }

    public void cadastrarAvaliacao(Avaliacao avaliacao, Midia midia) {
        if(!clienteAtual.getAvaliadas().contains(avaliacao)) {
            clienteAtual.getAvaliadas().add(avaliacao);
            midia.getAvaliacoes().add(avaliacao);

            String str = avaliacao.getIdCliente()+";"+
            avaliacao.getIdMidia()+";"+
            avaliacao.getComentario()+";"+
            avaliacao.getNota();

                    
            escrever(str, "/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_test/POO_Avaliacoes.csv");
        }
    }

    private void cadastrarAudiencia(String user, String fa, String idMidia) {
        String str = user + ";" + fa + ";" + idMidia;

        escrever(str, "/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_test/POO_Audiencia.csv");
    }

    private void removerParaVer(String idMidia) throws Exception {
        ArrayList<String> oldLines = new ArrayList<String>(Files.readAllLines(Path.of("/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_test/POO_Audiencia.csv")));
        ArrayList<String> newLines =  new ArrayList<String>();

        oldLines.forEach((l) -> newLines.add(l));

        for(String s : oldLines) {
            String[] aux = s.split(";");
            if(aux[2].equals(idMidia) && aux[1].equals("F")) {
                newLines.remove(s);
            }
        }

        Files.write(Path.of("/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_test/POO_Audiencia.csv"), newLines, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    // operacoes para debug
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
