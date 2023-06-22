import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

import models.Avaliacao;
import models.Cliente;
import models.Filme;
import models.Midia;
import models.PlataformaStreaming;
import models.Serie;

public class App {
    public static void main(String[] args) throws Exception {

        // Inicia o sistema
        PlataformaStreaming plataforma = PlataformaStreaming.getInstance("Meu Streaming");

        // Fluxo de menus
        menuDeAcesso(plataforma);
    }

    // UTILIDADE TERMINAL
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void spendTime(int time) {
        try {
            Thread.sleep(time);
        } catch(Exception e){
            e.getMessage();
        }
    }

    // MENU DE ACESSO
    public static void menuDeAcesso(PlataformaStreaming plataforma) throws Exception {

        plataforma.preencherMidias();
        plataforma.preencherClientes();
        plataforma.preencherAudiencia();
        plataforma.preencherAvaliacoes();

        Scanner scanner = new Scanner(System.in);
        String nome, user, senha, profissao;
        Cliente cliente; 

        int opcao = -1;
        while (opcao != 0) {
            clearScreen();
            System.out.println();
            System.out.println("-------- MENU DE ACESSO -----------");
            System.out.println("0. Sair");
            System.out.println("1. Cadastrar-se");
            System.out.println("2. Fazer login");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
    
            switch (opcao) {
                case 0:
                    clearScreen();
                    System.out.println("Encerrando o programa. Até mais!");
                    break;
                case 1:
                    System.out.println("Para cadastrar digite:");
                    System.out.print("Nome: ");
                    nome = scanner.nextLine();
                    System.out.print("User: ");
                    user = scanner.nextLine();
                    System.out.print("Senha: ");
                    senha = scanner.nextLine();

                    profissao = menuProfissaoDoCliente();

                    if(profissao == null) {
                        cliente = new Cliente(nome, user, senha);
                    } else {
                        cliente = new Cliente(nome, user, senha, profissao);
                    }
                    
                    plataforma.cadastrarCliente(cliente);
                    break;
                case 2:
                    System.out.println("Para logar digite:");
                    System.out.print("User: ");
                    user = scanner.nextLine();
                    System.out.print("Senha: ");
                    senha = scanner.nextLine();

                    plataforma.login(user, senha);

                    Cliente clienteAtual = plataforma.getClienteAtual();

                    try {
                        if(clienteAtual != null) {
                            if("Admin".equals(clienteAtual.getProfissao())) {
                                menuAdiministrador(plataforma);
                            } else if(clienteAtual.ehEspecialista() || clienteAtual.ehProfissional()) {
                                menuClienteEspecialista(plataforma);
                            } else {
                                menuCliente(plataforma);
                            }
                        } else {
                            clearScreen();
                            System.out.println("Usuario ou senha invalidos!");
                            spendTime(5000);
                        }
                    } catch(Exception e){
                        e.getMessage();
                    }
                    break;
                default:
                    System.out.println("Opção inválida. Digite novamente.");
                    spendTime(3000);
                    break;
            }
        }
        scanner.close();
    }

    // MENU DE CLIENTE
    public static void menuCliente(PlataformaStreaming plataforma) throws Exception{
        String nome, idioma, genero;
        Midia midia;
        Cliente clienteAtual = plataforma.getClienteAtual();

        plataforma.preencherMidias();
        plataforma.preencherAudiencia();
        plataforma.preencherAvaliacoes();

        Scanner scanner = new Scanner(System.in);
        int opcao = -1;
        while (opcao != 0) {
            clearScreen();
            System.out.println();
            System.out.println("-------- MENU CLIENTE -----------");
            System.out.println("Olá, " + plataforma.getClienteAtual().getNome());
            System.out.println("Bem vindo(a) à " + plataforma.getNome());
            System.out.println("0. Deslogar");
            System.out.println("1. Mostrar catalogo");
            System.out.println("2. Filtrar por nome");
            System.out.println("3. Filtrar por idioma");
            System.out.println("4. Filtrar por gênero");
            System.out.println("5. Adicionar aos ver depois");
            System.out.println("6. Mostrar minhas lista");
            System.out.println("7. Assistir algo");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            
            switch (opcao) {
                case 0:
                    clearScreen();
                    System.out.println("Deslogando usuário.");

                    spendTime(1500);
                    plataforma.logoff();
                    break;
                case 1:
                    System.out.println("Todos:");
                    plataforma.mostrarCatalogo();
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.print("Insira o nome: ");
                    nome = scanner.nextLine();

                    System.out.println("Filtro por nome:");
                    plataforma.filtraPorNome(nome);
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.print("Insira o idioma: ");
                    idioma = scanner.nextLine();

                    System.out.println("Filtro por idioma:");
                    plataforma.filtrarPorIdioma(idioma);
                    scanner.nextLine();
                    break;
               case 4:
                    genero = menuSelecionaGenero();

                    System.out.println("Filtro por gênero:");
                    plataforma.filtrarPorGenero(genero);
                    scanner.nextLine();
                    break;
                case 5:
                    System.out.println("O que você quer assistir mais tarde?");
                    nome = scanner.nextLine();

                    plataforma.addParaVer(nome);
                    break;
                case 6:
                    menuListasDoCliente(plataforma);
                    break;
                case 7:
                    System.out.println("O que você quer assistir?");
                    nome = scanner.nextLine();

                    clearScreen();
                    midia = plataforma.assistirMidia(nome);
                    spendTime(4000);
                    boolean naoAvaliado = true;
                    
                    if(midia != null) {
                        if(clienteAtual.getAvaliadas().size() > 0) {
                            for(Avaliacao a : plataforma.getClienteAtual().getAvaliadas()) {
                                if(a.getIdMidia().equals(midia.getId())) {
                                    naoAvaliado = false;
                                }
                            }
                        }

                        if(naoAvaliado) {
                            menuAvaliar(plataforma, midia);
                        }
                    }
                    break;
                default:
                    System.out.println("Opção inválida. Digite novamente.");
                    spendTime(3000);
                    break;
            }
        }
    }

    // MENU DE CLIENTE ESPECIALISTA
    public static void menuClienteEspecialista(PlataformaStreaming plataforma) throws Exception {
        
        String nome, idioma, genero;
        Midia midia;
        Cliente clienteAtual = plataforma.getClienteAtual();

        plataforma.preencherMidias();
        plataforma.preencherAudiencia();
        plataforma.preencherAvaliacoes();

        Scanner scanner = new Scanner(System.in);
        int opcao = -1;
        while (opcao != 0) {
            clearScreen();
            System.out.println();
            System.out.println("-------- MENU CLIENTE ESPECIALISTA -----------");
            System.out.println("Olá, " + plataforma.getClienteAtual().getNome());
            System.out.println("Bem vindo(a) à " + plataforma.getNome());
            System.out.println("0. Delogar");
            System.out.println("1. Mostrar catalogo");
            System.out.println("2. Filtrar por nome");
            System.out.println("3. Filtrar por idioma");
            System.out.println("4. Filtrar por gênero");
            System.out.println("5. Adicionar aos ver depois");
            System.out.println("6. Mostrar minhas lista");
            System.out.println("7. Assistir algo");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            
            switch (opcao) {
                case 0:
                    clearScreen();
                    System.out.println("Deslogando usuário.");

                    spendTime(1500);
                    plataforma.logoff();
                    break;
                case 1:
                    System.out.println("Todos:");
                    plataforma.mostrarCatalogo();
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.print("Insira o nome: ");
                    nome = scanner.nextLine();

                    System.out.println("Filtro por nome:");
                    plataforma.filtraPorNome(nome);
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.print("Insira o idioma: ");
                    idioma = scanner.nextLine();

                    System.out.println("Filtro por idioma:");
                    plataforma.filtrarPorIdioma(idioma);
                    scanner.nextLine();
                    break;
               case 4:
                    genero = menuSelecionaGenero();

                    System.out.println("Filtro por gênero:");
                    plataforma.filtrarPorGenero(genero);
                    scanner.nextLine();
                    break;
                case 5:
                    System.out.println("O que você quer assistir mais tarde?");
                    nome = scanner.nextLine();

                    plataforma.addParaVer(nome);
                    break;
                case 6:
                    menuListasDoCliente(plataforma);
                    break;
                case 7:
                    System.out.println("O que você quer assistir?");
                    nome = scanner.nextLine();

                    clearScreen();
                    midia = plataforma.assistirMidia(nome);
                    spendTime(4000);
                    boolean naoAvaliado = true;

                    if(midia != null) {
                        if(clienteAtual.getAvaliadas().size() > 0) {
                            for(Avaliacao a : plataforma.getClienteAtual().getAvaliadas()) {
                                if(a.getIdMidia().equals(midia.getId())) {
                                    naoAvaliado = false;
                                }
                            }
                        }

                        if(naoAvaliado) {
                            menuAvaliarEspecialista(plataforma, midia);
                        }
                    }
                    break;
                default:
                    System.out.println("Opção inválida. Digite novamente.");
                    spendTime(3000);
                    break;
            }
        }
    }

    public static void menuAdiministrador(PlataformaStreaming plataforma) throws Exception {

        String dataLancamento = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

        String nome, idioma, genero, id;
        int duracao;

        Midia midia;
        Cliente clienteAtual = plataforma.getClienteAtual();

        plataforma.preencherMidias();
        plataforma.preencherAudiencia();
        plataforma.preencherAvaliacoes();

        Scanner scanner = new Scanner(System.in);
        int opcao = -1;
        while (opcao != 0) {
            clearScreen();
            System.out.println();
            System.out.println("-------- MENU ADMINISTRADOR -----------");
            System.out.println("0. Delogar");
            System.out.println("1. Cadastrar filme");
            System.out.println("2. Cadastrar serie");
            System.out.println("3. Gerar relatórios");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch(opcao) {
                case 0:
                    clearScreen();
                    System.out.println("Deslogando usuário.");

                    spendTime(1500);
                    plataforma.logoff();
                    break;
                case 1:
                    System.out.print("Nome do filme: ");
                    nome = scanner.nextLine();
                    System.out.print("duracao do filme: ");
                    duracao = scanner.nextInt();
                    
                    id = String.valueOf(plataforma.getMidias().size() + 1);

                    Filme filme = new Filme(id, nome, dataLancamento, duracao);
                    plataforma.cadastrarFilme(filme);
                    break;
                case 2:
                    System.out.print("Nome da serie: ");
                    nome = scanner.nextLine();
                    
                    id = String.valueOf(plataforma.getMidias().size() + 1);
                    
                    Serie serie = new Serie(id, nome, dataLancamento);
                    plataforma.cadastrarSerie(serie);
                    break;
                case 3:
                    menuListaDeRelatorios(plataforma);
                    break;
                default:
                    System.out.println("Opção inválida. Digite novamente.");
                    spendTime(3000);
                    break;
            }
        }
    }

    public static void menuListasDoCliente(PlataformaStreaming plataforma) {

        Scanner scanner = new Scanner(System.in);
        int opcao = -1;
        while (opcao != 0) {
            clearScreen();
            System.out.println();
            System.out.println("-------- VISUALIZAR LISTAS -----------");
            System.out.println("Olá, " + plataforma.getClienteAtual().getNome());
            System.out.println("0. Voltar");
            System.out.println("1. Assistidas");
            System.out.println("2. Avaliações");
            System.out.println("3. Para ver mais tarde");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
    
            switch (opcao) {
                case 0:
                    clearScreen();
                    break;
                case 1:
                    System.out.println("Essa é sua lista de midias assistidas:");
                    plataforma.mostrarListaAssistidas();
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.println("Essa é sua lista de Avaliações:");
                    plataforma.mostrarListaAvaliadas();
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.println("Essa é sua lista de midias para ver mais tarde:");
                    plataforma.mostrarListaParaVer();
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("Opção inválida. Digite novamente.");
                    break;
            }
        }
    }

    public static void menuListaDeRelatorios(PlataformaStreaming plataforma) {

        String genero;
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;
        while (opcao != 0) {
            clearScreen();
            System.out.println();
            System.out.println("-------- RELATORIOS -----------");
            System.out.println("Olá, " + plataforma.getClienteAtual().getNome());
            System.out.println("0. Voltar");
            System.out.println("1. Cliente que assistiu mais midias");
            System.out.println("2. Cliente que tem mais avaliacoes");
            System.out.println("3. Porcentagem de clientes com mais de 15 avaliações");
            System.out.println("4. Top 10 midias com melhores votos");
            System.out.println("5. Top 10 midias mais visualizadas");
            System.out.println("6. Top 10 midias com melhores votos por genero");
            System.out.println("7. Top 10 midias mais visualizadas por genero");
            System.out.println("8. Geral todas as midias");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
    
            switch (opcao) {
                case 0:
                    clearScreen();
                    break;
                case 1:
                    plataforma.gerarRelatorioClienteQueMaisAssistiuMidias();
                    scanner.nextLine();
                    break;
                case 2:
                    plataforma.gerarRelatorioClienteQueMaisAvaliouMidias();
                    scanner.nextLine();
                    break;
                case 3:
                    plataforma.gerarRelatorioClientesCom15MaisAvaliacoes();
                    scanner.nextLine();
                    break;
                case 4:
                    plataforma.gerarRelatorio10MidiasMelhoresVotos();
                    scanner.nextLine();
                    break;
                case 5:
                    plataforma.gerarRelatorio10MidiasMaisVisualizadas();
                    scanner.nextLine();
                    break;
                case 6:
                    genero = menuSelecionaGenero();

                    if(genero != null) {
                        plataforma.gerarRelatorio10MidiasMelhoresVotosPorGenero(genero);
                    }
                    scanner.nextLine();
                    break;
                case 7:
                    genero = menuSelecionaGenero();

                    if(genero != null) {
                        plataforma.gerarRelatorio10MidiasMaisVisualizadasPorGenero(genero);
                    }
                    scanner.nextLine();
                    break;
                case 8:
                    plataforma.gerarRelatorioGeralMidias();
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("Opção inválida. Digite novamente.");
                    break;
            }
        }
    }

    public static void menuAvaliar(PlataformaStreaming plataforma, Midia midia){

        int nota;
        String comentario;
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;
        while (opcao != 1 && opcao != 2) {
            clearScreen();
            System.out.println();
            System.out.println("-------- AVALIAR -----------");
            System.out.println("1. Não avaliar");
            System.out.println("2. Dar nota");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
    
            switch (opcao) {
                case 1:
                    break;
                case 2:
                    System.out.print("Nota: ");
                    nota = scanner.nextInt();
                    scanner.nextLine();

                    if(nota < 1 || nota > 5) {
                        System.out.println("Nota inválida.");
                        spendTime(3000);
                        opcao = -1;
                        break;
                    }

                    Avaliacao avaliacao = new Avaliacao(plataforma.getClienteAtual().getUser(), midia.getId(), "", nota);
                    plataforma.cadastrarAvaliacao(avaliacao, midia);
                    break;
                default:
                    System.out.println("Essa não é uma opacao válida. Digite novamente");
                    spendTime(3000);
                    break;
            }
        }
    }

    public static void menuAvaliarEspecialista(PlataformaStreaming plataforma, Midia midia){

        int nota;
        String comentario;
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;
        while (opcao != 1 && opcao != 2) {
            clearScreen();
            System.out.println();
            System.out.println("-------- AVALIAR -----------");
            System.out.println("1. Não avaliar");
            System.out.println("2. Dar nota");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
    
            switch (opcao) {
                case 1:
                    break;
                case 2:
                    System.out.print("Nota: ");
                    nota = scanner.nextInt();
                    scanner.nextLine();

                    if(nota < 1 || nota > 5) {
                        System.out.println("Nota inválida.");
                        spendTime(3000);
                        opcao = -1;
                        break;
                    }

                    System.out.print("Comentario: ");
                    comentario = scanner.nextLine();

                    Avaliacao avaliacao = new Avaliacao(plataforma.getClienteAtual().getUser(), midia.getId(), comentario, nota);
                    plataforma.cadastrarAvaliacao(avaliacao, midia);
                    break;
                default:
                    System.out.println("Essa não é uma opacao válida. Digite novamente");
                    spendTime(3000);
                    break;
            }
        }
    }

    public static String menuProfissaoDoCliente() {
        String profissao = null;
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;
        while (opcao < 0 || opcao > 4) {
            clearScreen();
            System.out.println();                    
            System.out.println("Selecione uma profissao:");
            System.out.println("1. Nenhuma das alternativas");
            System.out.println("2. Jornalista");
            System.out.println("3. Diretor");
            System.out.println("4. Ator");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
    
            switch (opcao) {
                case 1:
                    break;
                case 2:
                    profissao = "Jornalista";
                    break;
                case 3:
                    profissao = "Diretor";
                    break;
                case 4:
                    profissao = "Ator";
                    break;
                default:
                    System.out.println("Essa não é uma opacao válida. Digite novamente");
                    spendTime(3000);
                    break;
            }
        }
        return profissao;
    }

    public static String menuSelecionaGenero() {
        String genero = null;
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;
        while (opcao < 1 || opcao > 9) {
            clearScreen();
            System.out.println("Slecione o genero: ");
            System.out.println("1. Ação");
            System.out.println("2. Anime");
            System.out.println("3. Aventura");
            System.out.println("4. Comédia");
            System.out.println("5. Documentário");
            System.out.println("6. Drama");
            System.out.println("7. Policial");
            System.out.println("8. Romance");
            System.out.println("9. Suspense");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
    
            switch (opcao) {
                case 1:
                    genero = "Ação";
                    break;
                case 2:
                    genero = "Anime";
                    break;
                case 3:
                    genero = "Aventura";
                    break;
                case 4:
                    genero = "Comédia";
                    break;
                case 5:
                    genero = "Documentário";
                    break;
                case 6:
                    genero = "Drama";
                    break;
                case 7:
                    genero = "Policial";
                    break;
                case 8:
                    genero = "Romance";
                    break;
                case 9:
                    genero = "Suspense";
                    break;
                default:
                    clearScreen();
                    System.out.println("Essa não é uma opacao válida. Digite novamente");
                    spendTime(3000);
                    break;
            }
        }
        return genero;
    }
}

