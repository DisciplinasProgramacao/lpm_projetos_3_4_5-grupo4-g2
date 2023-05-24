package entities;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Criar a plataforma de streaming
        PlataformaStreaming plataforma = new PlataformaStreaming("MinhaPlataforma");

        // Carregar dados das mídias
        plataforma.carregarCSV(
                "C:/Users/Assemp/lpm_projetos_3_4_5-grupo4-g2/codigo/project3/src/entities/POO_Filmes.csv",
                "C:/Users/Assemp/lpm_projetos_3_4_5-grupo4-g2/codigo/project3/src/entities/POO_Series.csv");

        // Carregar dados dos espectadores
        plataforma.carregarEspectadoresCSV(
                "C:/Users/Assemp/lpm_projetos_3_4_5-grupo4-g2/codigo/project3/src/entities/POO_Espectadores.csv");

        // Criar alguns clientes
        Cliente cliente1 = new Cliente("Jose", "Cliente1", "senha1");
        Cliente cliente2 = new Cliente("Andressa", "Cliente2", "senha2");

        // Adicionar os clientes à plataforma
        plataforma.adicionarCliente(cliente1);
        plataforma.adicionarCliente(cliente2);

        
        Cliente clienteLogado = realizarLogin(plataforma);

        if (clienteLogado != null) {
            exibirMenu(plataforma, clienteLogado);
        } else {
            System.out.println("Login falhou. Encerrando o programa.");
        }
    }

    public static Cliente realizarLogin(PlataformaStreaming plataforma) {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;
        while (opcao != 3) {
            System.out.println();
            System.out.println("-------- MENU DE ACESSO -----------");
            System.out.println("1. Cadastrar-se");
            System.out.println("2. Fazer login");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
    
            switch (opcao) {
                case 1:
                    System.out.print("Digite seu nome completo: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Digite o nome de usuário desejado: ");
                    String novoNomeUsuario = scanner.nextLine();
                    System.out.print("Digite a senha desejada: ");
                    String novaSenha = scanner.nextLine();
                    Cliente novoCliente = new Cliente(novoNome, novoNomeUsuario, novaSenha);
                    plataforma.adicionarCliente(novoCliente);
                    System.out.println();
                    System.out.println("Cadastro realizado com sucesso! Faça login para continuar.");
                    System.out.println();
                    break;
                case 2:
                    System.out.print("Digite seu nome de usuário: ");
                    String nomeUsuario = scanner.nextLine();
                    System.out.print("Digite sua senha: ");
                    String senha = scanner.nextLine();
                    Cliente clienteLogado = plataforma.login(nomeUsuario, senha);
                    if (clienteLogado != null) {
                        System.out.println("Login realizado com sucesso!");
                        System.out.println();
                        System.out.println("Seja Bem Vindo " + clienteLogado.getNome());
                        return clienteLogado;
                    } else {
                        System.out.println("Nome de usuário ou senha incorretos.");
                        System.out.println();
                    }
                    break;
                case 3:
                    System.out.println("Encerrando o programa. Até mais!");
                    return null;
                default:
                    System.out.println("Opção inválida. Digite novamente.");
                    break;
            }
        }
        scanner.close();
        return null;
    }

    public static void exibirMenu(PlataformaStreaming plataforma, Cliente clienteLogado) {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;
        while (opcao != 7) {
            System.out.println();
            System.out.println("-------- MENU -----------");
            System.out.println("1. Mostrar catálogo de mídias");
            System.out.println("2. Adicionar mídia à lista para assistir");
            System.out.println("3. Retirar mídia da lista para assistir");
            System.out.println("4. Filtrar mídias");
            System.out.println("5. Avaliar Midia");
            System.out.println("6. Fazer logoff");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
    
            switch (opcao) {
                case 1:
                    System.out.println();
                    System.out.println("-------- CATÁLOGO DE MÍDIAS ---------------");
                    plataforma.mostrarMidias();
                    break;
                case 2:
                    System.out.println();
                    System.out.print("Digite o nome da mídia que deseja adicionar à lista: ");
                    String nomeMidia = scanner.nextLine();
                    Midia midia = plataforma.buscarMidia(nomeMidia);
                    if (midia != null) {
                        clienteLogado.adicionarNaListaParaVer(midia);
                        System.out.println("Mídia adicionada à lista para assistir!");
                    } else {
                        System.out.println("Mídia não encontrada.");
                    }
                    break;
                case 3:
                    System.out.println();
                    clienteLogado.mostrarLista();
                    System.out.print("Digite o nome da mídia que deseja retirar da lista: ");
                    nomeMidia = scanner.nextLine();
                    midia = plataforma.buscarMidia(nomeMidia);
                    if (midia != null) {
                        clienteLogado.retirarNaListaParaVer(midia);
                        System.out.println("Mídia retirada da lista para assistir!");
                    } else {
                        System.out.println("Mídia não encontrada.");
                    }
                    break;
                case 4:
                    exibirMenuFiltrarMidias(clienteLogado);
                    break;
                case 5:
                    System.out.println();
                    System.out.print("Digite o nome da mídia que deseja avaliar: ");
                    nomeMidia = scanner.nextLine();
                    midia = plataforma.buscarMidia(nomeMidia);
                    if (midia != null) {
                        System.out.print("Qual é a sua avaliação de 0 a 5: ");
                        int aval = scanner.nextInt();
                        clienteLogado.avaliar(plataforma.buscarMidia(nomeMidia), aval);
                        plataforma.buscarMidia(nomeMidia).registrarAudiencia();
    
                        System.out.println();
                        System.out.println("------------------ MÉDIA --------------------");
                        System.out.println("A média de avaliação da mídia " + nomeMidia + " é de: "
                                + plataforma.buscarMidia(nomeMidia).calcularMediaAvaliacoes());
                        System.out.println();
                    } else {
                        System.out.println("Mídia não encontrada.");
                    }
                    break;
                case 6:
                    System.out.println("Fazendo logoff...");
                    plataforma.logoff();
                    clienteLogado = realizarLogin(plataforma);
                    if (clienteLogado != null) {
                        System.out.println("Login realizado com sucesso!");
                    } else {
                        System.out.println("Login falhou. Encerrando o programa.");
                        opcao = 7;
                    }
                    break;
                case 7:
                    System.out.println("Encerrando o programa. Até mais!");
                    plataforma.logoff();
                    break;
                default:
                    System.out.println("Opção inválida. Digite novamente.");
                    break;
            }
        }
        scanner.close();
    }
    

    public static void exibirMenuFiltrarMidias(Cliente clienteLogado) {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;
        while (opcao != 4) {
            System.out.println();
            System.out.println("-------- FILTRAR MÍDIAS -----------");
            System.out.println("1. Filtrar por gênero");
            System.out.println("2. Filtrar por idioma");
            System.out.println("3. Filtrar por quantidade de episódios");
            System.out.println("4. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.println();
                    System.out.print("Digite o gênero: ");
                    String genero = scanner.nextLine();
                    List<Midia> midiasFiltradas = clienteLogado.filtrarPorGenero(genero);
                    System.out.println("Mídias filtradas por gênero: " + genero);
                    for (Midia m : midiasFiltradas) {
                        System.out.println(m.getNome());
                    }
                    break;
                case 2:
                    System.out.println();
                    System.out.print("Digite o idioma: ");
                    String idioma = scanner.nextLine();
                    List<Midia> midiasFiltradasIdioma = clienteLogado.filtrarPorIdioma(idioma);
                    System.out.println("Mídias filtradas por idioma: " + idioma);
                    for (Midia m : midiasFiltradasIdioma) {
                        System.out.println(m.getNome());
                    }
                    break;
                case 3:
                    System.out.println();
                    System.out.print("Digite a quantidade mínima de episódios: ");
                    int quantidadeEpisodios = scanner.nextInt();
                    List<Midia> midiasFiltradasEpisodios = clienteLogado.filtrarPorQtdEpisodios(quantidadeEpisodios);
                    System.out.println("Mídias filtradas por quantidade de episódios (mínimo " + quantidadeEpisodios
                            + " episódios):");
                    for (Midia m : midiasFiltradasEpisodios) {
                        if (m instanceof Serie) {
                            Serie serie = (Serie) m;
                            System.out.println(serie.getNome());
                        }
                    }
                    break;
                case 4:
                    System.out.println("Voltando ao menu principal.");
                    break;
                default:
                    System.out.println("Opção inválida. Digite novamente.");
                    break;
            }
        }
    }
}
