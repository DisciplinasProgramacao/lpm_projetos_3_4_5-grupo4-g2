package Test.Testes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import entities.Filme;


public class FilmeTestes {
	@Test
	void testGetDuracaoFilme() {
		Filme filme = new Filme("1", "O Senhor dos Anéis", "2001-12-19", 5);
		filme.setDuracao(178);
		int duracao = filme.getDuracaoFilme();
		Assertions.assertEquals(178, duracao);
	}

	@Test
	void testToString() {
		Filme filme = new Filme("2", "Interestelar", "2014-11-07", 4);
		filme.setGenero("Ficção Científica");
		filme.setDuracao(169);
		String expected = "Filme: Interestelar, Gênero: Ficção Científica, Duração: 169 minutos";
		String result = filme.toString();
		Assertions.assertEquals(expected, result);
	}
}

