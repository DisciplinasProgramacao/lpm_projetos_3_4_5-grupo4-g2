package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import entities.Cliente;

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

	    @Test
	    public void testAdicionarNaLista() {
	        cliente.adicionarNaLista(serie1);
	        cliente.adicionarNaLista(serie2);
	        List<entities.Serie> listaParaVer = cliente.getListaParaVer();
	        assertEquals(2, listaParaVer.size());
	        assertTrue(listaParaVer.contains(serie1));
	        assertTrue(listaParaVer.contains(serie2));
	    }

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

	    @Test
		public void testFiltrarPorQtdEpisodios() {
			List<entities.Serie> seriesFiltradas = entities.Cliente.filtrarPorQtdEpisodios();
			assertEquals(2, seriesFiltradas.size());
			assertTrue(seriesFiltradas.contains(serie1));
			assertTrue(seriesFiltradas.contains(serie2));
		}

		@Test
		public void testRegistrarAudiencia() {
			assertEquals(2, serie1.getAudiencia());
			assertEquals(3, serie2.getAudiencia());
			assertEquals(1, serie3.getAudiencia());
		}

		@Test
		public void testCasoNaoQueiraAvaliar(){
			cliente.avaliar("N", 0);
			assertNull();
		}

		public void testCasoQueiraAvaliar(){
			assertEquals(2, cliente.avaliar("S", 2));
		}
}

