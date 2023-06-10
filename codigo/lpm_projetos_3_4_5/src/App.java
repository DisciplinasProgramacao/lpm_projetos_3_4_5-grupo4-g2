import java.util.Scanner;

import models.Cliente;
import models.Filme;
import models.PlataformaStreaming;
import models.Serie;

public class App {
    public static void main(String[] args) throws Exception {

        // Inicia o sistema
        PlataformaStreaming plataforma = PlataformaStreaming.getInstance("plataforma");

        // Carrega dados
        plataforma.preencherFilmes();
        plataforma.preencherSeries();
        plataforma.preencherClientes();
        plataforma.preencherAudiencia();
        plataforma.preencherAvaliacoes();
        plataforma.preencherAssistidas();

        // Fluxo de menus
        menuDeAcesso(plataforma);




        // Filme F1 = new Filme("776", "Lil Raffa Mano", "24/07/2022", 110);
        // Cliente C1 = new Cliente("777", "Raffa Moreira", "lilRaf", "777");
        // Serie S1 = new Serie("776", "Lil Raffa Mano", "24/07/2022");

        // plataforma.cadastrarSerie(S1);
        // plataforma.preencherFilmes();
        // System.out.println(plataforma.getMidias().size());

        // plataforma.preencherSeries();
        // System.out.println(plataforma.getMidias().size());

        // plataforma.preencherClientes();
        // System.out.println(plataforma.getClientes().size() + " idCliente: " + plataforma.getClientes().get(3).getIdCliente());

        // // plataforma.preencherAudiencia();
        // // plataforma.printAudPerMidia();
        // // System.out.println();
        // // plataforma.printAllClientes();

        // plataforma.filtraPorNome("Fools Of The East");

        // fluxo normal e especialista
        // if(clienteAtual != null) {
        //     if(clienteAtual.getAssistidas().size() > 4) {
        //         menuClienteEspecialista(clienteAtual);
        //     } else {
        //         menuCliente(clienteAtual);
        //     }
        // } 
    }

    // UTILIDADE TERMINAL
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // MENU DE ACESSO
    public static void menuDeAcesso(PlataformaStreaming plataforma) {
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

                    if(clienteAtual != null) {
                        if(clienteAtual.getAssistidas().size()>4) {
                            menuClienteEspecialista(plataforma);
                        } else {
                            menuCliente(plataforma);
                        }
                    } else {
                        System.out.println("Usuario ou senha invalidos!");
                    }
                    break;
                default:
                    System.out.println("Opção inválida. Digite novamente.");
                    break;
            }
        }
        scanner.close();
    }

    // MENUS DE CLIENTE
    public static void menuCliente(PlataformaStreaming plataforma) {
        String nome, idioma, genero;
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

                    try {
                        Thread.sleep(3000);
                    } catch(Exception e){
                        e.getMessage();
                    }

                    plataforma.logoff();
                    break;
                case 1:
                    System.out.println("Todos:");
                    plataforma.mostrarCatalogo();
                    break;
                case 2:
                    System.out.println("Insira o nome:");
                    nome = scanner.nextLine();

                    System.out.println("Filtro por nome:");
                    plataforma.filtraPorNome(nome);
                    break;
                case 3:
                    System.out.println("Insira o idioma:");
                    idioma = scanner.nextLine();

                    System.out.println("Filtro por idioma:");
                    plataforma.filtrarPorIdioma(idioma);
                    break;
               case 4:
                    System.out.println("Insira o gênero:");
                    genero = scanner.nextLine();

                    System.out.println("Filtro por gênero:");
                    plataforma.filtrarPorGenero(genero);
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

                    plataforma.assistirMidia(nome);
                    break;
                default:
                    System.out.println("Opção inválida. Digite novamente.");
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
                    break;
                case 2:
                    System.out.println("Essa é sua lista de Avaliações:");
                    plataforma.mostrarListaAvaliadas();
                    break;
                case 3:
                    System.out.println("Essa é sua lista de midias para ver mais tarde:");
                    plataforma.mostrarListaParaVer();
                    break;
                default:
                    System.out.println("Opção inválida. Digite novamente.");
                    break;
            }
        }
    }

    // MENUS DE CLIENTE ESPECIALISTA
    public static void menuClienteEspecialista(PlataformaStreaming plataforma) {
        String nome, idioma, genero;
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

                    try {
                        Thread.sleep(3000);
                    } catch(Exception e){
                        e.getMessage();
                    }

                    plataforma.logoff();
                    break;
                case 1:
                    System.out.println("Todos:");
                    plataforma.mostrarCatalogo();
                    break;
                case 2:
                    System.out.println("Insira o nome:");
                    nome = scanner.nextLine();

                    System.out.println("Filtro por nome:");
                    plataforma.filtraPorNome(nome);
                    break;
                case 3:
                    System.out.println("Insira o idioma:");
                    idioma = scanner.nextLine();

                    System.out.println("Filtro por idioma:");
                    plataforma.filtrarPorIdioma(idioma);
                    break;
               case 4:
                    System.out.println("Insira o gênero:");
                    genero = scanner.nextLine();

                    System.out.println("Filtro por gênero:");
                    plataforma.filtrarPorGenero(genero);
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

                    plataforma.assistirMidia(nome);
                    break;
                default:
                    System.out.println("Opção inválida. Digite novamente.");
                    break;
            }
        }
    }

    // MENU DE ASSISTIR
    public static void assistirMidia(PlataformaStreaming plataforma) {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;
        while (opcao != 0) {
            clearScreen();
            System.out.println();
            System.out.println("-------- ASSISTIR MAIS TARDE -----------");
            System.out.println("0. Voltar");
            System.out.println("1. Serie");
            System.out.println("2. Filme");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
    
            switch (opcao) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                default:
                    break;
            }
        }
    }
}
