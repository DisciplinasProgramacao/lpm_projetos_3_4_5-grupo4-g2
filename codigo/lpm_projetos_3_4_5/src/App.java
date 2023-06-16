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

        plataforma.preencherFilmes();
        plataforma.preencherSeries();
        plataforma.preencherClientes();
        plataforma.preencherAudiencia();
        plataforma.preencherAvaliacoes();

        Scanner scanner = new Scanner(System.in);
        String idCliente, nome, user, senha;
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

                    idCliente = String.valueOf(plataforma.getClientes().size() + 1);

                    Cliente cliente = new Cliente(idCliente, nome, user, senha);
                    
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
                            if(clienteAtual.ehEspecialista()) {
                                menuClienteEspecialista(plataforma);
                            } else {
                                menuCliente(plataforma);
                            }
                        } else {
                            System.out.println("Usuario ou senha invalidos!");
                        }
                    } catch(Exception e){
                        e.getMessage();
                        spendTime(12000);
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

        plataforma.preencherFilmes();
        plataforma.preencherSeries();
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
                    System.out.println("Insira o nome:");
                    nome = scanner.nextLine();

                    System.out.println("Filtro por nome:");
                    plataforma.filtraPorNome(nome);
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.println("Insira o idioma:");
                    idioma = scanner.nextLine();

                    System.out.println("Filtro por idioma:");
                    plataforma.filtrarPorIdioma(idioma);
                    scanner.nextLine();
                    break;
               case 4:
                    System.out.println("Insira o gênero:");
                    genero = scanner.nextLine();

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
                    

                    if(clienteAtual.getAvaliadas().size() > 0) {
                        for(Avaliacao a : plataforma.getClienteAtual().getAvaliadas()) {
                            if(a.getIdMidia().equals(midia.getId())) {
                                naoAvaliado = false;
                            }
                        }
                    }

                    if(naoAvaliado && midia != null) {
                        menuAvaliar(plataforma, midia);
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
        public static void menuClienteEspecialista(PlataformaStreaming plataforma) throws Exception{

            String dataLancamento = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            
            String nome, idioma, genero, id;
            int duracao;
            Midia midia;
            Cliente clienteAtual = plataforma.getClienteAtual();

            plataforma.preencherFilmes();
            plataforma.preencherSeries();
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
                System.out.println("8. Cadastrar filme");
                System.out.println("9. Cadastrar Serie");
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
                        System.out.println("Insira o nome:");
                        nome = scanner.nextLine();
    
                        System.out.println("Filtro por nome:");
                        plataforma.filtraPorNome(nome);
                        scanner.nextLine();
                        break;
                    case 3:
                        System.out.println("Insira o idioma:");
                        idioma = scanner.nextLine();
    
                        System.out.println("Filtro por idioma:");
                        plataforma.filtrarPorIdioma(idioma);
                        scanner.nextLine();
                        break;
                   case 4:
                        System.out.println("Insira o gênero:");
                        genero = scanner.nextLine();
    
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

                        if(clienteAtual.getAvaliadas().size() > 0) {
                            for(Avaliacao a : plataforma.getClienteAtual().getAvaliadas()) {
                                if(a.getIdMidia().equals(midia.getId())) {
                                    naoAvaliado = false;
                                }
                            }
                        }

                        if(naoAvaliado && midia != null) {
                            menuAvaliarEspecialista(plataforma, midia);
                        }
                        break;
                    case 8:
                        System.out.print("Nome do filme: ");
                        nome = scanner.nextLine();
                        System.out.print("duracao do filme: ");
                        duracao = scanner.nextInt();
                        
                        id = String.valueOf(plataforma.getMidias().size() + 1);

                        Filme filme = new Filme(id, nome, dataLancamento, duracao);
                        plataforma.cadastrarFilme(filme);
                        break;
                    case 9:
                        System.out.print("Nome da serie: ");
                        nome = scanner.nextLine();
                        
                        id = String.valueOf(plataforma.getMidias().size() + 1);
                        
                        Serie serie = new Serie(id, nome, dataLancamento);
                        plataforma.cadastrarSerie(serie);
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

    public static void menuAvaliar(PlataformaStreaming plataforma, Midia midia){

        int nota;
        String comentario;
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;
        while (opcao != 1 && opcao != 2) {
            clearScreen();
            System.out.println();
            System.out.println("-------- AVALIAR FILME -----------");
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

                    Avaliacao avaliacao = new Avaliacao(plataforma.getClienteAtual().getIdCliente(), midia.getId(), "", nota);
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
            System.out.println("-------- AVALIAR FILME -----------");
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

                    Avaliacao avaliacao = new Avaliacao(plataforma.getClienteAtual().getIdCliente(), midia.getId(), comentario, nota);
                    plataforma.cadastrarAvaliacao(avaliacao, midia);
                    break;
                default:
                    System.out.println("Essa não é uma opacao válida. Digite novamente");
                    spendTime(3000);
                    break;
            }
        }
    }
}
