package models;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PlataformaStreaming {
    public static PlataformaStreaming instancia;
    private String nome;
    private ArrayList<Midia> midias = new ArrayList<Midia>();
    private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private Cliente clienteAtual = null;

    /**
     * Constrói uma nova instância da classe PlataformaStreaming.
     *
     * @param nome o nome da plataforma de streaming
     */
    private PlataformaStreaming(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém a instância única da classe PlataformaStreaming usando o padrão
     * Singleton.
     * Se a instância ainda não existir, cria uma nova.
     *
     * @param nome o nome da plataforma de streaming
     * @return a instância única da classe PlataformaStreaming
     */
    public static PlataformaStreaming getInstance(String nome) {
        if (instancia == null) {
            instancia = new PlataformaStreaming(nome);
        }
        return instancia;
    }

    /**
     * Obtém o cliente atualmente logado na plataforma.
     *
     * @return o cliente atual
     */
    public Cliente getClienteAtual() {
        return clienteAtual;
    }

    /**
     * Obtém a lista de mídias disponíveis na plataforma.
     *
     * @return a lista de mídias
     */
    public ArrayList<Midia> getMidias() {
        return midias;
    }

    /**
     * Obtém a lista de clientes cadastrados na plataforma.
     *
     * @return a lista de clientes
     */
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    /**
     * Obtém o nome da plataforma de streaming.
     *
     * @return o nome da plataforma
     */
    public String getNome() {
        return nome;
    }

    /**
     * Realiza o login de um cliente na plataforma.
     * Atualiza o clienteAtual para o cliente logado.
     *
     * @param user  o nome de usuário do cliente
     * @param senha a senha do cliente
     */
    public void login(String user, String senha) {
        for (Cliente c : clientes) {
            if (c.getUser().equals(user) && c.getSenha().equals(senha)) {
                clienteAtual = new Cliente(c.getNome(), c.getUser(), c.getSenha(),
                        c.getParaVer(), c.getAssistidas(), c.getAvaliadas(), c.getProfissao());
            }
        }
    }

    /**
     * Realiza o logoff do cliente atual.
     * Define o clienteAtual como null.
     */
    public void logoff() {
        this.clienteAtual = null;
    }

    /**
     * Imprime os detalhes de uma mídia, incluindo nome e média de avaliações.
     *
     * @param m a mídia a ser impressa
     */
    private void printMidiaModelo(Midia m) {
        System.out.println("Nome: " + m.getNome() + " | média: " + m.getMedia());
    }

    /**
     * Mostra o catálogo de todas as mídias disponíveis na plataforma.
     * Para cada mídia, imprime os detalhes usando printMidiaModelo.
     */
    public void mostrarCatalogo() {
        midias.forEach(m -> printMidiaModelo(m));
    }

    /**
     * Filtra as mídias por nome e mostra as correspondentes no catálogo.
     * Para cada mídia filtrada, imprime os detalhes usando printMidiaModelo.
     *
     * @param nome o nome a ser filtrado
     */
    public void filtraPorNome(String nome) {
        midias.stream().filter(m -> m.getNome().contains(nome))
                .forEach(m -> printMidiaModelo(m));
    }

    /**
     * Filtra as mídias por gênero e mostra as correspondentes no catálogo.
     * Para cada mídia filtrada, imprime os detalhes usando printMidiaModelo.
     *
     * @param genero o gênero a ser filtrado
     */
    public void filtrarPorGenero(String genero) {
        midias.stream().filter(m -> m.getGenero().equals(genero))
                .forEach(m -> printMidiaModelo(m));
    }

    /**
     * Filtra as mídias por idioma e mostra as correspondentes no catálogo.
     * Para cada mídia filtrada, imprime os detalhes usando printMidiaModelo.
     *
     * @param idioma o idioma a ser filtrado
     */
    public void filtrarPorIdioma(String idioma) {
        midias.stream().filter(m -> m.getIdioma().equals(idioma))
                .forEach(m -> printMidiaModelo(m));
    }

    /**
     * Adiciona uma mídia à lista de "Para Ver" do cliente atual.
     * Se a mídia ainda não estiver na lista, a adiciona e cadastra a audiência.
     *
     * @param nome o nome da mídia a ser adicionada
     */
    public void addParaVer(String nome) {
        for (Midia m : midias) {
            if (m.getNome().equals(nome)) {
                if (!clienteAtual.getParaVer().contains(m)) {
                    clienteAtual.getParaVer().add(m);
                    cadastrarAudiencia(clienteAtual.getUser(), "F", m.getId());
                }
            }
        }
    }

    /**
     * Assistir a uma mídia da plataforma.
     * Remove a mídia da lista "Para Ver" do cliente atual e registra a audiência.
     *
     * @param nome o nome da mídia a ser assistida
     * @return a mídia assistida, ou null se não encontrada
     */
    public Midia assistirMidia(String nome) {
        for (Midia m : midias) {
            if (m.getNome().equals(nome)) {
                clienteAtual.getParaVer().remove(m);
                try {
                    removerParaVer(m.getId());
                } catch (Exception e) {
                    e.getMessage();
                }

                if (!clienteAtual.getAssistidas().contains(m)) {
                    clienteAtual.getAssistidas().add(m);
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

    /**
     * Mostra a lista de mídias assistidas pelo cliente atual.
     * Para cada mídia assistida, imprime os detalhes usando printMidiaModelo.
     */
    public void mostrarListaAssistidas() {
        clienteAtual.getAssistidas().forEach(m -> printMidiaModelo(m));
    }

    /**
     * Mostra a lista de mídias avaliadas pelo cliente atual.
     * Para cada mídia avaliada, imprime o nome da mídia e sua nota de avaliação.
     */
    public void mostrarListaAvaliadas() {
        clienteAtual.getAvaliadas().forEach(a -> {
            for (Midia m : midias) {
                if (a.getIdMidia().equals(m.getId())) {
                    System.out.println("Nome: " + m.getNome() + " Nota: " + a.getNota());
                }
            }
        });
    }

    /**
     * Mostra a lista de mídias na lista "Para Ver" do cliente atual.
     * Para cada mídia na lista, imprime os detalhes usando printMidiaModelo.
     */
    public void mostrarListaParaVer() {
        clienteAtual.getParaVer().forEach(m -> printMidiaModelo(m));
    }

    /**
     * Preenche a lista de mídias da plataforma a partir de arquivos.
     * Chama os métodos preencherFilmes() e preencherSeries().
     *
     * @throws Exception se ocorrer um erro durante a leitura dos arquivos
     */
    public void preencherMidias() throws Exception {
        preencherFilmes();
        preencherSeries();
    }

    /**
     * Preenche a lista de mídias com filmes a partir de um arquivo CSV.
     * Limpa a lista antes de adicionar os filmes.
     * Se o cliente atual não for profissional, filtra os filmes por lançamento.
     *
     * @throws Exception se ocorrer um erro durante a leitura do arquivo
     */
    private void preencherFilmes() throws Exception {
        midias.clear();
        if (clienteAtual != null && !clienteAtual.ehProfissional()) {
            List<Midia> filmes = Files.lines(Paths.get(
                    "/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_stable/POO_Filmes.csv"))
                    .map(line -> line.split(";"))
                    .map(col -> new Filme(col[0], col[1], col[2], Integer.parseInt(col[3])))
                    .filter(m -> !m.verificaLancamento())
                    .collect(Collectors.toList());
            midias.addAll(filmes);
        } else {
            Files.lines(Paths.get(
                    "/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_stable/POO_Filmes.csv"))
                    .map(line -> line.split(";"))
                    .map(col -> new Filme(col[0], col[1], col[2], Integer.parseInt(col[3])))
                    .forEach(midias::add);
        }
    }

    /**
     * Preenche as informações das séries a partir de um arquivo CSV, adicionando as
     * séries à lista de mídias.
     * Caso o cliente atual seja nulo ou seja um cliente profissional, as séries
     * serão adicionadas diretamente à lista de mídias.
     * Caso contrário, apenas as séries que não são lançamentos serão adicionadas.
     *
     * @throws Exception se ocorrer algum erro ao ler o arquivo CSV.
     */
    private void preencherSeries() throws Exception {
        if (clienteAtual != null && !clienteAtual.ehProfissional()) {
            List<Midia> series = Files.lines(Paths.get(
                    "/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_stable/POO_Series.csv"))
                    .map(line -> line.split(";"))
                    .map(col -> new Serie(col[0], col[1], col[2]))
                    .filter(m -> !m.verificaLancamento())
                    .collect(Collectors.toList());
            midias.addAll(series);
        } else {
            Files.lines(Paths.get(
                    "/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_stable/POO_Series.csv"))
                    .map(line -> line.split(";"))
                    .map(col -> new Serie(col[0], col[1], col[2]))
                    .forEach(midias::add);
        }
    }

    /**
     * Preenche as informações dos clientes a partir de um arquivo CSV,
     * adicionando-os à lista de clientes.
     *
     * @throws Exception se ocorrer algum erro ao ler o arquivo CSV.
     */
    public void preencherClientes() throws Exception {
        clientes.clear();
        Files.lines(Paths.get(
                "/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_stable/POO_Espectadores.csv"))
                .map(line -> line.split(";"))
                .forEach((col) -> {
                    Cliente cliente;
                    if (col.length == 3) {
                        cliente = new Cliente(col[0], col[1], col[2]);
                    } else {
                        cliente = new Cliente(col[0], col[1], col[2], col[3]);
                    }
                    this.clientes.add(cliente);
                });
    }

    /**
     * Preenche as informações da audiência a partir de um arquivo CSV, atualizando
     * a lista de mídias, clientes e suas respectivas audiências.
     * A quantidade de audiência de cada mídia é definida com base no arquivo CSV.
     *
     * @throws Exception se ocorrer algum erro ao ler o arquivo CSV.
     */
    public void preencherAudiencia() throws Exception {
        if (clienteAtual != null) {
            clienteAtual.getAssistidas().clear();
        }
        clientes.forEach(c -> c.getAssistidas().clear());
        midias.forEach(m -> m.setAudiencia(0));
        Files.lines(Paths.get(
                "/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_stable/POO_Audiencia.csv"))
                .map(lines -> lines.split(";"))
                .forEach((aud) -> {
                    Midia auxMidia;
                    for (Midia m : midias) {
                        if (m.getId().equals(aud[2])) {
                            auxMidia = m;

                            for (Cliente c : clientes) {
                                if (c.getUser().equals(aud[0])) {
                                    if (aud[1].equals("F")) {
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

    /**
     * Preenche as informações das avaliações a partir de um arquivo CSV,
     * atualizando a lista de mídias e clientes com as avaliações correspondentes.
     *
     * @throws Exception se ocorrer algum erro ao ler o arquivo CSV.
     */
    public void preencherAvaliacoes() throws Exception {
        if (clienteAtual != null) {
            clienteAtual.getAvaliadas().clear();
        }
        clientes.forEach(c -> c.getAvaliadas().clear());
        Files.lines(Paths.get(
                "/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_stable/POO_Avaliacoes.csv"))
                .map(lines -> lines.split(";"))
                .forEach(aval -> {
                    for (Midia m : midias) {
                        for (Cliente c : clientes) {
                            if (c.getUser().equals(aval[0]) && m.getId().equals(aval[1])) {
                                Avaliacao avaliacao = new Avaliacao(aval[0], aval[1], aval[2],
                                        Integer.parseInt(aval[3]));
                                m.getAvaliacoes().add(avaliacao);
                                c.getAvaliadas().add(avaliacao);
                            }
                        }
                    }
                });
    }

    /**
     * Escreve uma string em um arquivo, especificado pelo caminho do arquivo.
     *
     * @param str  a string a ser escrita no arquivo.
     * @param path o caminho do arquivo.
     */
    private void escrever(String str, String path) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path),
                StandardOpenOption.APPEND, StandardOpenOption.CREATE)) {
            writer.write(str);
            writer.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Cadastra um filme, adicionando-o à lista de mídias e escrevendo as
     * informações em um arquivo CSV.
     *
     * @param filme o filme a ser cadastrado.
     */
    public void cadastrarFilme(Filme filme) {
        String str = filme.getId() + ";" +
                filme.getNome() + ";" +
                filme.getDataLancamento() + ";" +
                filme.getDuracao();

        escrever(str,
                "/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_stable/POO_Filmes.csv");
        this.midias.add(filme);
    }

    /**
     * Cadastra uma série, adicionando-a à lista de mídias e escrevendo as
     * informações em um arquivo CSV.
     *
     * @param serie a série a ser cadastrada.
     */
    public void cadastrarSerie(Serie serie) {
        String str = serie.getId() + ";" +
                serie.getNome() + ";" +
                serie.getDataLancamento();

        escrever(str,
                "/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_stable/POO_Series.csv");
        this.midias.add(serie);
    }

    /**
     * Cadastra um cliente, adicionando-o à lista de clientes e escrevendo as
     * informações em um arquivo CSV.
     *
     * @param cliente o cliente a ser cadastrado.
     */
    public void cadastrarCliente(Cliente cliente) {
        String str;
        if (cliente.getProfissao() == null) {
            str = cliente.getNome() + ";" +
                    cliente.getUser() + ";" +
                    cliente.getSenha();
        } else {
            str = cliente.getNome() + ";" +
                    cliente.getUser() + ";" +
                    cliente.getSenha() + ";" +
                    cliente.getProfissao();
        }

        escrever(str,
                "/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_stable/POO_Espectadores.csv");
        this.clientes.add(cliente);
    }

    /**
     * Cadastra uma avaliação para uma mídia e um cliente específicos.
     * A avaliação é adicionada à lista de avaliações da mídia e do cliente, e as
     * informações são escritas em um arquivo CSV.
     * A avaliação só é cadastrada se o cliente atual não tiver avaliado
     * anteriormente a mesma mídia.
     *
     * @param avaliacao a avaliação a ser cadastrada.
     * @param midia     a mídia avaliada.
     */
    public void cadastrarAvaliacao(Avaliacao avaliacao, Midia midia) {
        if (!clienteAtual.getAvaliadas().contains(avaliacao)) {
            clienteAtual.getAvaliadas().add(avaliacao);
            midia.getAvaliacoes().add(avaliacao);

            String str = avaliacao.getUser() + ";" +
                    avaliacao.getIdMidia() + ";" +
                    avaliacao.getComentario() + ";" +
                    avaliacao.getNota();

            escrever(str,
                    "/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_stable/POO_Avaliacoes.csv");
        }
    }

    /**
     * Cadastra a audiência de uma mídia para um determinado usuário.
     * As informações são escritas em um arquivo CSV.
     * 
     * @param user    o nome do usuário.
     * @param fa      a flag de audiência (F: para ver, A: assistida).
     * @param idMidia o ID da mídia.
     */
    private void cadastrarAudiencia(String user, String fa, String idMidia) {
        String str = user + ";" + fa + ";" + idMidia;

        escrever(str,
                "/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_stable/POO_Audiencia.csv");
    }

    /**
     * Remove uma mídia da lista "Para Ver" de um cliente com base no ID da mídia.
     * 
     * @param idMidia o ID da mídia a ser removida da lista "Para Ver".
     * @throws Exception se ocorrer algum erro ao ler ou escrever o arquivo CSV.
     */
    private void removerParaVer(String idMidia) throws Exception {
        ArrayList<String> oldLines = new ArrayList<String>(Files.readAllLines(Path.of(
                "/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_stable/POO_Audiencia.csv")));
        ArrayList<String> newLines = new ArrayList<String>();
        oldLines.forEach((l) -> newLines.add(l));

        for (String s : oldLines) {
            String[] aux = s.split(";");
            if (aux[2].equals(idMidia) && aux[1].equals("F")) {
                newLines.remove(s);
            }
        }
        Files.write(Path.of(
                "/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_stable/POO_Audiencia.csv"),
                newLines, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    /**
     * Gera um relatório do cliente que mais assistiu mídias na plataforma.
     * 
     * @return O cliente que mais assistiu mídias.
     */
    public void gerarRelatorioClienteQueMaisAssistiuMidias() {
        List<Cliente> clientesOrdenados = clientes.stream()
                .sorted(Comparator.comparingInt(cliente -> cliente.getAssistidas().size()))
                .collect(Collectors.toList());

        Collections.reverse(clientesOrdenados);

        Cliente queMaisAssistiu = clientesOrdenados.get(0);

        System.out.println("O cliente que mais assistiu mídias na plataforma foi: " + queMaisAssistiu.getUser());
        System.out.println("A quantidade de mídias assistidas foi: " + queMaisAssistiu.getAssistidas().size());
    }

    /**
     * Gera um relatório do cliente que mais avaliou mídias na plataforma.
     * 
     * @return O cliente que mais avaliou mídias.
     */
    public void gerarRelatorioClienteQueMaisAvaliouMidias() {
        List<Cliente> clientesOrdenados = clientes.stream()
                .sorted(Comparator.comparingInt(cliente -> cliente.getAvaliadas().size()))
                .collect(Collectors.toList());

        Collections.reverse(clientesOrdenados);

        Cliente queMaisAvaliou = clientesOrdenados.get(0);

        System.out.println("O cliente que mais avaliou mídias na plataforma foi: " + queMaisAvaliou.getUser());
        System.out.println("A quantidade de mídias avaliadas foi: " + queMaisAvaliou.getAssistidas().size());
    }

    /**
     * Gera um relatório dos clientes que possuem 15 ou mais avaliações.
     * O relatório inclui a porcentagem de usuários com 15 ou mais avaliações em
     * relação ao total de espectadores.
     */
    public void gerarRelatorioClientesCom15MaisAvaliacoes() {
        List<Cliente> apenasEspectadores = new ArrayList<Cliente>();
        List<Cliente> apenas15MaisAvaliacoes = new ArrayList<Cliente>();

        for (Cliente c : clientes) {
            if (!"Admin".equals(c.getProfissao())) {
                apenasEspectadores.add(c);
                if (c.getAvaliadas().size() >= 15) {
                    apenas15MaisAvaliacoes.add(c);
                }
            }
        }

        double porcentagem;
        if (apenasEspectadores.size() < 1) {
            porcentagem = ((double) apenas15MaisAvaliacoes.size() / 1) * 100;
        } else {
            porcentagem = ((double) apenas15MaisAvaliacoes.size() / apenasEspectadores.size()) * 100;
        }

        System.out.println("Relatório de porcentagem de usuários com 15 ou mais avaliações:");
        System.out.println("Atualmente o sistema possui " + apenas15MaisAvaliacoes.size()
                + " usuários que se enquadram nesse critério.\n");
        System.out.printf("Esse valor corresponde a %.2f por cento dos usuários.%n", porcentagem);
    }

    /**
     * Gera um relatório das 10 mídias com melhores votos.
     * O relatório inclui o nome e a média de votos de cada mídia.
     */
    public void gerarRelatorio10MidiasMelhoresVotos() {
        List<Midia> midiasOrdenadas = midias.stream()
                .filter(midia -> midia.getAvaliacoes().size() > 100)
                .sorted(Comparator.comparingDouble(midia -> midia.getMedia()))
                .collect(Collectors.toList());

        Collections.reverse(midiasOrdenadas);

        List<Midia> top10Midias = midiasOrdenadas.stream().limit(10)
                .collect(Collectors.toList());

        System.out.println("Top 10 de midias com melhores votos:");

        top10Midias.forEach(t -> {
            System.out.println(t.getNome() + " | " + t.getMedia());
        });
    }

    /**
     * Gera um relatório das 10 mídias mais visualizadas.
     * O relatório inclui o nome e a audiência de cada mídia.
     */
    public void gerarRelatorio10MidiasMaisVisualizadas() {
        List<Midia> midiasOrdenadas = midias.stream()
                .sorted(Comparator.comparingDouble(midia -> midia.getAudiencia()))
                .collect(Collectors.toList());

        Collections.reverse(midiasOrdenadas);

        List<Midia> top10Midias = midiasOrdenadas.stream().limit(10)
                .collect(Collectors.toList());

        System.out.println("Top 10 de midias mais vistas:");

        top10Midias.forEach(t -> {
            System.out.println(t.getNome() + " | " + t.getAudiencia());
        });
    }

    /**
     * Gera um relatório das 10 mídias com melhores votos em um gênero específico.
     * O relatório inclui o nome e a audiência de cada mídia.
     * 
     * @param genero O gênero das mídias a serem consideradas no relatório.
     */
    public void gerarRelatorio10MidiasMelhoresVotosPorGenero(String genero) {
        List<Midia> midiasOrdenadas = midias.stream()
                .filter(midia -> midia.getAvaliacoes().size() > 100)
                .filter(midia -> genero.equals(midia.getGenero()))
                .sorted(Comparator.comparingDouble(midia -> midia.getAudiencia()))
                .collect(Collectors.toList());

        Collections.reverse(midiasOrdenadas);

        List<Midia> top10Midias = midiasOrdenadas.stream().limit(10)
                .collect(Collectors.toList());

        System.out.println("Top 10 de midias de " + genero + " com melhores votos:");

        top10Midias.forEach(t -> {
            System.out.println(t.getNome() + " | " + t.getAudiencia());
        });
    }

    /**
     * Gera um relatório das 10 mídias mais visualizadas em um gênero específico.
     * O relatório inclui o nome e a audiência de cada mídia.
     * 
     * @param genero O gênero das mídias a serem consideradas no relatório.
     */
    public void gerarRelatorio10MidiasMaisVisualizadasPorGenero(String genero) {
        List<Midia> midiasOrdenadas = midias.stream()
                .filter(midia -> genero.equals(midia.getGenero()))
                .sorted(Comparator.comparingDouble(midia -> midia.getAudiencia()))
                .collect(Collectors.toList());

        Collections.reverse(midiasOrdenadas);

        List<Midia> top10Midias = midiasOrdenadas.stream().limit(10)
                .collect(Collectors.toList());

        System.out.println("Top 10 de midias de " + genero + " mais vistas:");

        top10Midias.forEach(t -> {
            System.out.println(t.getNome() + " | " + t.getAudiencia());
        });
    }

    /**
     * Gera um relatório geral das mídias.
     * O relatório inclui informações como nome, gênero, média de votos, audiência e
     * quantidade de avaliações de cada mídia.
     */
    public void gerarRelatorioGeralMidias() {
        System.out.println("Nome | Gênero | Média | Audiência | qnt.Avaliações");
        midias.forEach(m -> System.out.println(
                m.getNome() + " | " +
                        m.getGenero() + " | " +
                        m.getMedia() + " | " +
                        m.getAudiencia() + " | " +
                        m.getAvaliacoes().size()));
    }
}
