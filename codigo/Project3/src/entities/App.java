package entities;


public class App {
    public static void main(String[] args) {
        // Criar a plataforma de streaming
        PlataformaStreaming plataforma = new PlataformaStreaming("MinhaPlataforma");

        // Criar algumas mídias (filmes e séries)
        Midia filme1 = new Filme("1", "Filme 1", "2022-01-01", 0, "Ação", 120, "Portugues");
        Midia filme2 = new Filme("2", "Filme 2", "2022-02-02", 0, "Comédia", 90, "Espanhol");
        Midia serie1 = new Serie("1", "Série 1", "2022-01-01", 0, "Drama", 10, "Ingles");
        Midia serie2 = new Serie("2", "Série 2", "2022-02-02", 0, "Comédia", 20, "Portugues");

        // Adicionar as mídias à plataforma
        plataforma.adicionarMidia(filme1);
        plataforma.adicionarMidia(filme2);
        plataforma.adicionarMidia(serie1);
        plataforma.adicionarMidia(serie2);

        // Criar alguns clientes
        Cliente cliente1 = new Cliente("Cliente1", "senha1");
        Cliente cliente2 = new Cliente("Cliente2", "senha2");

        // Adicionar os clientes à plataforma
        plataforma.adicionarCliente(cliente1);
        plataforma.adicionarCliente(cliente2);

        // Realizar o login de um cliente
        Cliente clienteLogado = plataforma.login("Cliente1", "senha1");

        if (clienteLogado != null) {
            // Exemplo de avaliação
            clienteLogado.avaliar(filme1, 4);

            // Registrar audiência para uma mídia
            filme1.registrarAudiencia();


            // Realizar logoff
            plataforma.logoff();
        } else {
            System.out.println("Login inválido. Verifique seu nome de usuário e senha.");
        }
    }
}