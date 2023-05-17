package entities;

import java.util.List;

public class App {
    public static void main(String[] args) {
        // Criar a plataforma de streaming
        PlataformaStreaming plataforma = new PlataformaStreaming("MinhaPlataforma");

        // Criar algumas mídias (filmes e séries)
        Midia filme1 = new Filme(1, "Velozes e Furiosos", "2022-01-01", 0, "Ação", 120, "Portugues");
        Midia filme2 = new Filme(2, "Gente Grande", "2022-02-02", 0, "Comédia", 90, "Espanhol");
        Midia serie1 = new Serie(1, "Estação 19", "2022-01-01", 0, "Drama", 10, "Inglês");
        Midia serie2 = new Serie(2, "The Big Bang Theory", "2022-02-02", 0, "Comédia", 20, "Portugues");

        // Adicionar as mídias à plataforma
        plataforma.adicionarMidia(filme1);
        plataforma.adicionarMidia(filme2);
        plataforma.adicionarMidia(serie1);
        plataforma.adicionarMidia(serie2);

        // Carregar dados do arquivo CSV
        plataforma.carregarCSV("C:/Users/Assemp/lpm_projetos_3_4_5-grupo4-g2/codigo/project3/src/entities/dados.csv", plataforma);

        // Criar alguns clientes
        Cliente cliente1 = new Cliente("Cliente1", "senha1");
        Cliente cliente2 = new Cliente("Cliente2", "senha2");

        // Adicionar os clientes à plataforma
        plataforma.adicionarCliente(cliente1);
        plataforma.adicionarCliente(cliente2);

        // Realizar o login de um cliente
        Cliente clienteLogado = plataforma.login("Cliente1", "senha1");
        System.out.println();
        System.out.println("-------- CATALOGO DE MIDIAS --------------- ");
        plataforma.mostrarFilmes();

        Midia midia = plataforma.buscarMidia("Estação 19");
        System.out.println();
            clienteLogado.adicionarNaListaParaVer(midia);
            midia = plataforma.buscarMidia("Gente Grande");
            clienteLogado.adicionarNaListaParaVer(midia);
            midia = plataforma.buscarMidia("The Big Bang Theory");
            clienteLogado.adicionarNaListaParaVer(midia);
            midia = plataforma.buscarMidia("Velozes e Furiosos");
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
            List<Midia> midiasFiltradas = clienteLogado.filtrarPorGenero("Drama");
            System.out.print("Midias filtradas por gênero:");
            for (Midia m : midiasFiltradas) {
                System.out.print(" " + m.getNome() + " ");
            }
            
            System.out.println();

            // Exemplo de filtrar midias por idioma
            List<Midia> midiasFiltradasIdioma = clienteLogado.filtrarPorIdioma("Espanhol");
            System.out.print("Midias filtradas por idioma:");
            for (Midia m : midiasFiltradasIdioma) {
                System.out.print(" " + m.getNome() + " ");
            }
            
            System.out.println();

           // Exemplo de filtrar midias por quantidade de episódios
           List<Midia> midiasFiltradasEpisodios = clienteLogado.filtrarPorQtdEpisodios(20);
           System.out.print("Mídias filtradas por quantidade de episódios:");
           for (Midia m : midiasFiltradasEpisodios) {
               if (m instanceof Serie) {
                   Serie serie = (Serie) m;
                   if (serie.getQuantidadeEpisodios() >= 10) {
                       System.out.print(" " + serie.getNome() + " ");
                   }
               }
           }

        if (clienteLogado != null) {
            // Exemplo de avaliação
            clienteLogado.avaliar(filme1, 4);

            // Registrar audiência para uma mídia
            filme1.registrarAudiencia();
            
            System.out.println();
            System.out.println();
            System.out.println("------------------ MÉDIA --------------------");
            System.out.println("A média de avaliação da midia " + filme1 + " é de: " + filme1.calcularMediaAvaliacao());


            // Realizar logoff
            plataforma.logoff();
        } else {
            System.out.println("Login inválido. Verifique seu nome de usuário e senha.");
        }
    }
}