package entities;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Criar a plataforma de streaming
        PlataformaStreaming plataforma = new PlataformaStreaming("MinhaPlataforma");

        // Carregar dados das mídias
        plataforma.carregarCSV("C:/Users/Assemp/lpm_projetos_3_4_5-grupo4-g2/codigo/project3/src/entities/POO_Filmes.csv", "C:/Users/Assemp/lpm_projetos_3_4_5-grupo4-g2/codigo/project3/src/entities/POO_Series.csv");

        // Carregar dados dos espectadores
        plataforma.carregarEspectadoresCSV("C:/Users/Assemp/lpm_projetos_3_4_5-grupo4-g2/codigo/project3/src/entities/POO_Espectadores.csv");

        // Criar alguns clientes
        Cliente cliente1 = new Cliente("Jose","Cliente1", "senha1");
        Cliente cliente2 = new Cliente("Andressa","Cliente2", "senha2");

        // Adicionar os clientes à plataforma
        plataforma.adicionarCliente(cliente1);
        plataforma.adicionarCliente(cliente2);

        // Realizar o login de um cliente
        Cliente clienteLogado = realizarLogin(plataforma);

        if (clienteLogado != null) {
            exibirMenu(plataforma, clienteLogado);
        } else {
            System.out.println("Login falhou. Encerrando o programa.");
        }
    }

    public static Cliente realizarLogin(PlataformaStreaming plataforma) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite seu nome de usuário: ");
        String nomeUsuario = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();
        Cliente clienteLogado = plataforma.login(nomeUsuario, senha);
        if (clienteLogado != null) {
            System.out.println("Login realizado com sucesso!");
        }
        return clienteLogado;
    }

    public static void exibirMenu(PlataformaStreaming plataforma, Cliente clienteLogado) {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;
        while (opcao != 5) {
            System.out.println();
            System.out.println("-------- MENU -----------");
            System.out.println("1. Mostrar catálogo de mídias");
            System.out.println("2. Adicionar mídia à lista para assistir");
            System.out.println("3. Retirar mídia da lista para assistir");
            System.out.println("4. Filtrar mídias");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println();
                    System.out.println("-------- CATÁLOGO DE MÍDIAS ---------------");
                    plataforma.mostrarFilmes();
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
                    System.out.println("Encerrando o programa. Até mais!");
                    plataforma.logoff();
                    break;
                default:
                    System.out.println("Opção inválida. Digite novamente.");
                    break;
            }
        }
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
            scanner.nextLine(); // Limpar o buffer do scanner

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
                    System.out.println("Mídias filtradas por quantidade de episódios (mínimo " + quantidadeEpisodios + " episódios):");
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


/**package entities;

import java.util.List;

public class App {
    public static void main(String[] args) {
        // Criar a plataforma de streaming
        PlataformaStreaming plataforma = new PlataformaStreaming("MinhaPlataforma");

        // Carregar dados das midias
       plataforma.carregarCSV("C:/Users/Assemp/lpm_projetos_3_4_5-grupo4-g2/codigo/project3/src/entities/POO_Filmes.csv", "C:/Users/Assemp/lpm_projetos_3_4_5-grupo4-g2/codigo/project3/src/entities/POO_Series.csv");

        // Carregar dados dos espectadores
        plataforma.carregarEspectadoresCSV("C:/Users/Assemp/lpm_projetos_3_4_5-grupo4-g2/codigo/project3/src/entities/POO_Espectadores.csv");

        // Carregar dados da audiencia
        //plataforma.carregarAudienciaCSV("C:/Users/Assemp/lpm_projetos_3_4_5-grupo4-g2/codigo/project3/src/entities/POO_Audiencia.csv");

        

        // Criar alguns clientes
        Cliente cliente1 = new Cliente("Jose","Cliente1", "senha1");
        Cliente cliente2 = new Cliente("Andressa","Cliente2", "senha2");

        // Adicionar os clientes à plataforma
        plataforma.adicionarCliente(cliente1);
        plataforma.adicionarCliente(cliente2);

        // Realizar o login de um cliente
        Cliente clienteLogado = plataforma.login("Cliente1", "senha1");
        System.out.println();
        System.out.println("-------- CATALOGO DE MIDIAS --------------- ");
        plataforma.mostrarFilmes();

        

        Midia midia = plataforma.buscarMidia("Light");
        System.out.println();
            clienteLogado.adicionarNaListaParaVer(midia);
            midia = plataforma.buscarMidia("Shoreman");
            clienteLogado.adicionarNaListaParaVer(midia);
            midia = plataforma.buscarMidia("Robotman");
            clienteLogado.adicionarNaListaParaVer(midia);
            midia = plataforma.buscarMidia("Red County");
            clienteLogado.adicionarNaListaParaVer(midia);
            System.out.println("------------------ LISTA PARA VER --------------------");
            clienteLogado.mostrarLista();
            
            System.out.println();
            // Exemplo de retirar midia da lista para assistir
            clienteLogado.retirarNaListaParaVer(midia);
            System.out.println("------------------ LISTA PARA VER --------------------");
            clienteLogado.mostrarLista();

            
            System.out.println();

            
            
            System.out.println("------------------ FILTROS --------------------");
            // Exemplo de filtrar midias por gênero
            List<Midia> midiasFiltradas = clienteLogado.filtrarPorGenero("Terror");
            System.out.println("Midias filtradas por gênero: Terror");
            for (Midia m : midiasFiltradas) {
                System.out.println(" " + m.getNome() + " ");
            }
            
            System.out.println();

            // Exemplo de filtrar midias por idioma
            List<Midia> midiasFiltradasIdioma = clienteLogado.filtrarPorIdioma("Inglês");
            System.out.println("Midias filtradas por idioma: Inglês");
            for (Midia m : midiasFiltradasIdioma) {
                System.out.println(" " + m.getNome() + " ");
            }
            
            System.out.println();

           // Exemplo de filtrar midias por quantidade de episódios
           List<Midia> midiasFiltradasEpisodios = clienteLogado.filtrarPorQtdEpisodios(20);
           System.out.println("Mídias filtradas por quantidade de episódios:");
           for (Midia m : midiasFiltradasEpisodios) {
               if (m instanceof Serie) {
                   Serie serie = (Serie) m;
                   if (serie.getQuantidadeEpisodios() >= 10) {
                       System.out.println(" " + serie.getNome() + " ");
                   }
               }
           }

        if (clienteLogado != null) {
            // Exemplo de avaliação
            clienteLogado.avaliar(plataforma.buscarMidia("Master Minds"), 4);

            // Registrar audiência para uma mídia
            plataforma.buscarMidia("Master Minds").registrarAudiencia();
            
            System.out.println();
            System.out.println();
            System.out.println("------------------ MÉDIA --------------------");
            System.out.println("A média de avaliação da midia " + plataforma.buscarMidia("Master Minds") + " é de: " + plataforma.buscarMidia("Master Minds").calcularMediaAvaliacoes());


        }
        // Realizar logoff
        plataforma.logoff();

        // Realizar o login de um cliente
        clienteLogado = plataforma.login("Cliente2", "senha2");
        clienteLogado.avaliar(plataforma.buscarMidia("Master Minds"), 1);

            // Registrar audiência para uma mídia
            plataforma.buscarMidia("Master Minds").registrarAudiencia();
            
            System.out.println();
            System.out.println();
            System.out.println("------------------ MÉDIA --------------------");
            System.out.println("A média de avaliação da midia " + plataforma.buscarMidia("Master Minds") + " é de: " + plataforma.buscarMidia("Master Minds").calcularMediaAvaliacoes());

    }
    
}
*/