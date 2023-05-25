package Test.Testes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entities.Cliente;
import entities.Filme;
import entities.Midia;
import entities.Serie;

import java.util.ArrayList;
import java.util.List;

public class PlataformaTestes {

    private entities.PlataformaStreaming PlataformaStreaming;

	@BeforeEach
	void setUp() {
		PlataformaStreaming = new entities.PlataformaStreaming("Minha Plataforma");
	}

	@Test
	void testAdicionarMidia() {
		Filme filme = new Filme("1", "O Senhor dos Anéis", "2001-12-19", 5);
		PlataformaStreaming.adicionarMidia(filme);
		List<Midia> midias = PlataformaStreaming.getMidias();
		Assertions.assertEquals(1, midias.size());
		Assertions.assertTrue(midias.contains(filme));
	}

	@Test
	void testAdicionarCliente() {
		Cliente cliente = new Cliente("João", "joao123", "senha123");
		PlataformaStreaming.adicionarCliente(cliente);
		List<Cliente> clientes = PlataformaStreaming.getClienteAtual();
		Assertions.assertEquals(1, clientes.size());
		Assertions.assertTrue(clientes.contains(cliente));
	}

	@Test
	void testFiltrarPorQtdEpisodios() {
		Serie serie1 = new Serie("1", "Friends", "1994-09-22", 5);
		serie1.setQuantidadeEpisodios(236);
		Serie serie2 = new Serie("2", "Stranger Things", "2016-07-15", 5);
		serie2.setQuantidadeEpisodios(34);
		Serie serie3 = new Serie("3", "Game of Thrones", "2011-04-17", 5);
		serie3.setQuantidadeEpisodios(73);

		PlataformaStreaming.adicionarMidia(serie1);
		PlataformaStreaming.adicionarMidia(serie2);
		PlataformaStreaming.adicionarMidia(serie3);

		List<Midia> midiasFiltradas = PlataformaStreaming.filtrarPorQtdEpisodios(50);

		Assertions.assertEquals(2, midiasFiltradas.size());
		Assertions.assertTrue(midiasFiltradas.contains(serie1));
		Assertions.assertTrue(midiasFiltradas.contains(serie3));
	}

	@Test
	void testFiltrarPorGenero() {
		Filme filme1 = new Filme("1", "Interestelar", "2014-11-07", 4);
		filme1.setGenero("Ficção Científica");
		Filme filme2 = new Filme("2", "Pantera Negra", "2018-02-13", 4);
		filme2.setGenero("Ação");
		Filme filme3 = new Filme("3", "O Poderoso Chefão", "1972-03-15", 4);
		filme3.setGenero("Crime");

		PlataformaStreaming.adicionarMidia(filme1);
		PlataformaStreaming.adicionarMidia(filme2);
		PlataformaStreaming.adicionarMidia(filme3);

		List<Filme> filmesFiltrados = PlataformaStreaming.filtrarPorGenero("Ação");

		Assertions.assertEquals(1, filmesFiltrados.size());
		Assertions.assertTrue(filmesFiltrados.contains(filme2));
	}

	@Test
	void testFiltrarPorIdioma() {
		Filme filme1 = new Filme("1", "Interestelar", "2014-11-07", 4);
		filme1.setIdioma("Inglês");
		Filme filme2 = new Filme("2", "Pantera Negra", "2018-02", 4);
		filme2.setIdioma("Português");
		Filme filme3 = new Filme("3", "O Poderoso Chefão", "1972-03-15", 4);
		filme3.setIdioma("Italiano");

		PlataformaStreaming.adicionarMidia(filme1);
		PlataformaStreaming.adicionarMidia(filme2);
		PlataformaStreaming.adicionarMidia(filme3);

		List<Midia> midiasFiltradas = PlataformaStreaming.filtrarPorIdioma("Inglês");

		Assertions.assertEquals(1, midiasFiltradas.size());
		Assertions.assertTrue(midiasFiltradas.contains(filme1));
	}

	@Test
	void testRegistrarAudiencia() {
		Serie serie = new Serie("1", "Friends", "1994-09-22", 5);
		PlataformaStreaming.adicionarMidia(serie);

		PlataformaStreaming.registrarAudiencia(serie);

		Assertions.assertEquals(1, serie.getAudiencia());
	}

	@Test
	void testLogoff() {
		Cliente cliente = new Cliente("João", "joao123", "senha123");
		PlataformaStreaming.setClienteAtual(cliente);

		PlataformaStreaming.logoff();

		Assertions.assertNull(PlataformaStreaming.getClienteAtual());
	}

	@Test
	void testBuscarMidia() {
		Filme filme = new Filme("1", "Interestelar", "2014-11-07", 4);
		PlataformaStreaming.adicionarMidia(filme);

		Midia midiaEncontrada = PlataformaStreaming.buscarMidia("Interestelar");

		Assertions.assertNotNull(midiaEncontrada);
		Assertions.assertEquals(filme, midiaEncontrada);
	}

	@Test
	void testCarregarCSV() {
		PlataformaStreaming.carregarCSV("caminho/para/arquivo/filmes.csv", "caminho/para/arquivo/series.csv");

		List<Midia> midias = PlataformaStreaming.getMidias();

		Assertions.assertFalse(midias.isEmpty());
		Assertions.assertEquals(2, midias.size());
	}

	@Test
	void testCarregarEspectadoresCSV() {
		PlataformaStreaming.carregarEspectadoresCSV("caminho/para/arquivo/espectadores.csv");

		List<Cliente> clientes = PlataformaStreaming.getClientes();

		Assertions.assertFalse(clientes.isEmpty());
		Assertions.assertEquals(3, clientes.size());
	}

	@Test
	void testCarregarAudienciaCSV() {
		Cliente cliente = new Cliente("João", "joao123", "senha123");
		PlataformaStreaming.adicionarCliente(cliente);

		PlataformaStreaming.carregarAudienciaCSV("caminho/para/arquivo/audiencia.csv");

		Assertions.assertEquals(10, cliente.getAudienciaTotal);
	}

	@Test
	void testBuscarClientePorNome() {
		Cliente cliente = new Cliente("João", "joao123", "senha123");
		PlataformaStreaming.adicionarCliente(cliente);

		Cliente clienteEncontrado = PlataformaStreaming.buscarClientePorNome("joao123");

		Assertions.assertNotNull(clienteEncontrado);
		Assertions.assertEquals(cliente, clienteEncontrado);
	}
}