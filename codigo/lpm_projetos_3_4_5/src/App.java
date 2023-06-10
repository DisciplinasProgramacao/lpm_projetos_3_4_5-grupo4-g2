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

    // MENU DE ACESSO
    public static void menuDeAcesso(PlataformaStreaming plataforma) {
        Scanner scanner = new Scanner(System.in);
        String idCliente, nome, user, senha;
        int opcao = -1;
        while (opcao != 0) {
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

                    Cliente clienteAtual = plataforma.login(user, senha);

                    if(clienteAtual != null) {
                        if(clienteAtual.getAssistidas().size()>4) {
                            menuClienteEspecialista(clienteAtual, plataforma);
                        } else {
                            menuCliente(clienteAtual, plataforma);
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
    public static void menuCliente(Cliente cliente, PlataformaStreaming plataforma) {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;
        while (opcao != 0) {
            System.out.println();
            System.out.println("-------- MENU CLIENTE -----------");
            System.out.println("Olá, " + cliente.getNome());
            System.out.println("0. Delogar");
            System.out.println("1. Adicionar serie aos ver depois.");
            System.out.println("2. Adicionar filme aos ver depois.");
            System.out.println("3. Assistir algo");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
    
            switch (opcao) {
                case 0:
                    plataforma.logoff();
                    break;
                case 1:
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Opção inválida. Digite novamente.");
                    break;
            }
        }
    }

    public static void menuClienteEspecialista(Cliente clienteEspecialista, PlataformaStreaming plataforma) {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;
        while (opcao != 0) {
            System.out.println();
            System.out.println("-------- MENU DE ESPECIALISTA -----------");
            System.out.println("0. Sair");
            System.out.println("1. Cadastrar-se");
            System.out.println("2. Fazer login");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
    
            switch (opcao) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("Encerrando o programa. Até mais!");
                default:
                    System.out.println("Opção inválida. Digite novamente.");
                    break;
            }
        }
        scanner.close();
    }

    // MENU DE ASSISTIR
    public static void assistirMidia(Cliente cliente) {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;
        while (opcao != 0) {
            System.out.println();
            System.out.println("-------- O QUE DESEJAS ASSISTIR ? -----------");
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
        scanner.close();
    }
}
