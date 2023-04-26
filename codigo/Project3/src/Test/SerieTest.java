package Test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import entities.Serie;

class serieTeste {
	@Test
	public void testRegistrarAudiencia() {
		Serie s = new Serie("The Office", "Comédia", "Inglês", 201);
		assertEquals(0, s.getAudiencia());
		s.registrarAudiencia();
		assertEquals(1, s.getAudiencia());
	}

	@Test
	public void testGetNome() {
		Serie s = new Serie("Breaking Bad", "Drama", "Inglês", 62);
		assertEquals("Breaking Bad", s.getNome());
	}

	@Test
	public void testGetGenero() {
		Serie s = new Serie("Game of Thrones", "Ação", "Inglês", 73);
		assertEquals("Ação", s.getGenero());
	}

	@Test
	public void testGetIdioma() {
		Serie s = new Serie("Friends", "Comédia", "Inglês", 236);
		assertEquals("Inglês", s.getIdioma());
	}

	@Test
	public void testGetQuantidadeEpisodios() {
		Serie s = new Serie("Lost", "Drama", "Inglês", 121);
		assertEquals(121, s.getQuantidadeEpisodios());
	}

	@Test
	public void testGetAudiencia() {
		Serie s = new Serie("Stranger Things", "Ficção científica", "Inglês", 25);
		assertEquals(0, s.getAudiencia());
	}

}
