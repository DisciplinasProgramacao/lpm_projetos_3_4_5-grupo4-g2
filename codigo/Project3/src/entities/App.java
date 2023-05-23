package entities;

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