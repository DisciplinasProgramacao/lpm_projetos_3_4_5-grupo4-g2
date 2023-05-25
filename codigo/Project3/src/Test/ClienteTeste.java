package Test.Testes;
import entities.Cliente;
import entities.Midia;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ClienteTeste{
    private Cliente cliente;

	@BeforeEach
	void setUp() {
		cliente = new Cliente("John Doe", "johndoe", "password");
	}

	@Test
	void atualizarStatusEspecialista_NoMediaWatched_ShouldNotBecomeEspecialista() {
		cliente.atualizarStatusEspecialista();
		Assertions.assertFalse(cliente.especialista);
	}

	@Test
	void atualizarStatusEspecialista_FiveMediaWatched_ShouldBecomeEspecialista() {
		// Adicionar 5 mídias assistidas
		for (int i = 0; i < 5; i++) {
			Midia midia = new Midia();
			midia.setDataAssistida(LocalDate.now().minusMonths(1));
			cliente.retirarNaListaAssistidas(midia);
		}

		cliente.atualizarStatusEspecialista();
		Assertions.assertTrue(cliente.especialista());
	}

	@Test
	void adicionarNaListaParaVer_ShouldAddMidiaToListaParaVer() {
		Midia midia = new Midia();
		cliente.adicionarNaListaParaVer(midia);

		Assertions.assertTrue(cliente.getListaParaVer().contains(midia));
	}

	@Test
	void retirarNaListaParaVer_ShouldRemoveMidiaFromListaParaVer() {
		Midia midia = new Midia();
		cliente.adicionarNaListaParaVer(midia);

		cliente.retirarNaListaParaVer(midia);

		Assertions.assertFalse(cliente.getListaParaVer().contains(midia));
	}

	@Test
	void retirarNaListaAssistidas_ShouldRemoveMidiaFromListaAssistidas() {
		Midia midia = new Midia();
		cliente.retirarNaListaAssistidas(midia);

		Assertions.assertFalse(cliente.getListaAssistidas().contains(midia));
	}

	@Test
	void mostrarLista_ShouldPrintAllMidiasInListaParaVer() {
		List<Midia> midias = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Midia midia = new Midia();
			midias.add(midia);
			cliente.adicionarNaListaParaVer(midia);
		}

		Assertions.assertDoesNotThrow(() -> cliente.mostrarLista());
	}

	@Test
	void filtrarPorGenero_ShouldReturnMidiasWithMatchingGenero() {
		List<Midia> midias = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Midia midia = new Midia();
			midia.setGenero("Ação");
			midias.add(midia);
		}
		cliente.setCatalogo(midias);

		List<Midia> midiasFiltradas = cliente.filtrarPorGenero("Ação");

		Assertions.assertEquals(5, midiasFiltradas.size());
	}

	@Test
	    void filtrarPorIdioma_ShouldReturnMidiasWithMatchingIdioma() {
	        List<Midia> midias = new ArrayList<>();
	        for (int i = 0; i < 5; i++) {
	            Midia midia = new Midia();
	            midia.setIdioma("Inglês");
	            midias.add(midia);
	        }
}