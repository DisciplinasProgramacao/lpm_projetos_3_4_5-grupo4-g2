import java.util.Scanner;

import models.Cliente;
import models.PlataformaStreaming;

public class App {
    public static void main(String[] args) throws Exception {
        PlataformaStreaming plataforma = PlataformaStreaming.getInstance("plataforma");

        plataforma.preencheFilmes();
        System.out.println(plataforma.getMidias().size());

        plataforma.preencherSeries();
        System.out.println(plataforma.getMidias().size());

        plataforma.preencherClientes();
        System.out.println(plataforma.getClientes().size() + " idCliente: " + plataforma.getClientes().get(0).getIdCliente());

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
    public static Cliente menuDeAcesso(PlataformaStreaming plataforma) {
        Scanner scanner = new Scanner(System.in);
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
                case 1:
                    break;
                case 2:
                    return plataforma.login("bb", "123");
                case 3:
                    System.out.println("Encerrando o programa. Até mais!");
                default:
                    System.out.println("Opção inválida. Digite novamente.");
                    break;
            }
        }
        scanner.close();
        return null;
    }

    // MENUS DE CLIENTE
    public static void menuCliente(Cliente cliente) {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;
        while (opcao != 0) {
            System.out.println();
            System.out.println("-------- MENU -----------");
            System.out.println("0. Voltar");
            System.out.println("1. Adicionar serie aos ver depois.");
            System.out.println("2. Adicionar filme aos ver depois.");
            System.out.println("3. Assistir algo");
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

    public static void menuClienteEspecialista(Cliente clienteEspecialista) {
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
