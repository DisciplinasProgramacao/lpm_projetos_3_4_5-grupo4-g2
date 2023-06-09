import java.util.Scanner;

import models.Cliente;
import models.Filme;
import models.PlataformaStreaming;
import models.Serie;

public class App {
    public static void main(String[] args) throws Exception {
        //instancia plataforma
        PlataformaStreaming plataforma = PlataformaStreaming.getInstance("Streaming");

        //instancia filmes
        Filme filme1 = new Filme("1","Heroes And Captains", "21/04/2022", 83);
        Filme filme2 = new Filme("2","Heroes And Captains", "21/04/2022", 83);
        Filme filme3 = new Filme("3","Heroes And Captains", "21/04/2022", 83);

        //instancia series
        Serie serie1 = new Serie("1","Clear","14/09/2017");
        Serie serie2 = new Serie("2","Clear","14/09/2017");

        //instancia clientes
        Cliente clienteNormal =  new Cliente("1", "a", "ab", "123", null, null, null);
        Cliente clienteEspecialista = new Cliente("2", "b", "bb", "123");

        clienteEspecialista.addFilmeAssistidas(filme1);
        clienteEspecialista.addFilmeAssistidas(filme2);
        clienteEspecialista.addFilmeAssistidas(filme3);
        
        clienteEspecialista.addSerieAssistidas(serie2);
        clienteEspecialista.addSerieAssistidas(serie1);

        plataforma.addCliente(clienteNormal);
        plataforma.addCliente(clienteEspecialista);

        Cliente clienteAtual = menuDeAcesso(plataforma);

        if(clienteAtual != null) {
            if(clienteAtual.getAssistidas().size() > 4) {
                menuClienteEspecialista(clienteAtual);
            } else {
                menuCliente(clienteAtual);
            }
        } 
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
