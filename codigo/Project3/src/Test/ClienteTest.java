package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class clienteTeste {

	private entities.Cliente cliente;
	private entities.Serie serie1;
	private entities.Serie serie2;
	private entities.Serie serie3;

	@BeforeAll
	public void setUp() throws Exception {
		cliente = new entities.Cliente();
		serie1 = new entities.Serie("Série 1", "Gênero 1", "Idioma 1", 5);
		serie2 = new entities.Serie("Série 2", "Gênero 2", "Idioma 2", 10);
		serie3 = new entities.Serie("Série 3", "Gênero 1", "Idioma 1", 15);
	}

	/**
	 * 
	 * Teste unitário para adicionar séries à lista do cliente. Verifica se as
	 * séries são adicionadas corretamente e se a lista tem o tamanho esperado.
	 */
	@Test
	public void testAdicionarNaLista() {
		cliente.adicionarNaLista(serie1);
		cliente.adicionarNaLista(serie2);
		List<entities.Serie> listaParaVer = cliente.getListaParaVer();
		assertEquals(2, listaParaVer.size());
		assertTrue(listaParaVer.contains(serie1));
		assertTrue(listaParaVer.contains(serie2));
	}

	/**
	 * 
	 * Teste unitário para remover uma série da lista do cliente. Verifica se a
	 * série é removida corretamente e se a lista tem o tamanho esperado.
	 */
	@Test
	public void testRetirarDaLista() {
		cliente.adicionarNaLista(serie1);
		cliente.adicionarNaLista(serie2);
		cliente.retirarDaLista("Série 1");
		List<entities.Serie> listaParaVer = cliente.getListaParaVer();
		assertEquals(1, listaParaVer.size());
		assertFalse(listaParaVer.contains(serie1));
		assertTrue(listaParaVer.contains(serie2));
	}

	/**
	 * 
	 * Teste unitário para filtrar séries por gênero. Verifica se as séries são
	 * filtradas corretamente e se o resultado tem o tamanho esperado.
	 */
	@Test
	public void testFiltrarPorGenero() {
		cliente.getListaJaVistas().add(serie1);
		cliente.getListaJaVistas().add(serie2);
		cliente.getListaJaVistas().add(serie3);
		List<entities.Serie> seriesFiltradas = cliente.filtrarPorGenero("Gênero 1");
		assertEquals(2, seriesFiltradas.size());
		assertTrue(seriesFiltradas.contains(serie1));
		assertTrue(seriesFiltradas.contains(serie3));
		assertFalse(seriesFiltradas.contains(serie2));
	}

	/**
	 * 
	 * Teste unitário para filtrar séries por idioma. Verifica se as séries são
	 * filtradas corretamente e se o resultado tem o tamanho esperado.
	 */
	@Test
	public void testFiltrarPorIdioma() {
		cliente.getListaJaVistas().add(serie1);
		cliente.getListaJaVistas().add(serie2);
		cliente.getListaJaVistas().add(serie3);
		List<entities.Serie> seriesFiltradas = cliente.filtrarPorIdioma("Idioma 1");
		assertEquals(2, seriesFiltradas.size());
		assertTrue(seriesFiltradas.contains(serie1));
		assertTrue(seriesFiltradas.contains(serie3));
		assertFalse(seriesFiltradas.contains(serie2));
	}

	/**
	 * 
	 * Teste unitário para filtrar séries por quantidade de episódios. Verifica se
	 * as séries são filtradas corretamente e se o resultado tem o tamanho esperado.
	 */
	@Test
	public void testFiltrarPorQtdEpisodios() {
		List<entities.Serie> seriesFiltradas = entities.Cliente.filtrarPorQtdEpisodios();
		assertEquals(2, seriesFiltradas.size());
		assertTrue(seriesFiltradas.contains(serie1));
		assertTrue(seriesFiltradas.contains(serie2));
	}

	/**
	 * 
	 * Teste unitário para verificar se a audiência das séries está sendo registrada
	 * corretamente. Verifica se a audiência da primeira série é igual a 2, da
	 * segunda é igual a 3 e da terceira é igual a 1.
	 */
	@Test
	public void testRegistrarAudiencia() {
		assertEquals(2, serie1.getAudiencia());
		assertEquals(3, serie2.getAudiencia());
		assertEquals(1, serie3.getAudiencia());
	}

	/**
	 * 
	 * Teste unitário para verificar se o método de avaliar a mídia retorna null
	 * quando o usuário não quer avaliar. Verifica se o método de avaliar retorna
	 * null após receber uma resposta "N" do usuário.
	 */
	@Test
	public void testCasoNaoQueiraAvaliar() {
		cliente.avaliar("N", 0);
		assertNull();
	}

	/**
	 * Teste unitário para verificar se o método de avaliar a mídia retorna a avaliação do usuário quando ele quer avaliar.
	 * Verifica se o método de avaliar retorna a avaliação passada como argumento após receber uma resposta "S" do usuário.
	 */
	@Test
		public void testCasoQueiraAvaliar(){
			assertEquals(2, cliente.avaliar("S", 2));
		}
}
