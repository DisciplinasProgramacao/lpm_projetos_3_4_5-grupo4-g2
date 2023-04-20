package atvidade;

import org.junit.Test;
import static org.junit.Assert.*;

public class SerieTest {

	@Test
	public void testRegistrarAudiencia() {
		Serie serie = new Serie("Breaking Bad", "Drama", "Inglês", 62);
		assertEquals(0, serie.getAudiencia());

		serie.registrarAudiencia();
		assertEquals(1, serie.getAudiencia());

		serie.registrarAudiencia();
		serie.registrarAudiencia();
		assertEquals(3, serie.getAudiencia());
	}

	@Test
	public void testGetters() {
		Serie serie = new Serie("Friends", "Comédia", "Inglês", 236);
		assertEquals("Friends", serie.getNome());
		assertEquals("Comédia", serie.getGenero());
		assertEquals("Inglês", serie.getIdioma());
		assertEquals(236, serie.getQuantidadeEpisodios());
	}
}
