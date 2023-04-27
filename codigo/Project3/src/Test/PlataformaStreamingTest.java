package Test;

import entities.Cliente;
import entities.PlataformaStreaming;
import entities.Serie;

public class PlataformaStreamingTest {

    private PlataformaStreaming plataforma;

    @Before
    public void setUp() {
        plataforma = new PlataformaStreaming("Netflix");
    }

    @Test
    public void testLogin() {
        Cliente cliente1 = new Cliente("cliente1", "senha1");
        Cliente cliente2 = new Cliente("cliente2", "senha2");
        plataforma.adicionarCliente(cliente1);
        plataforma.adicionarCliente(cliente2);

        assertNull(plataforma.login("cliente1", "senha2"));
        assertEquals(cliente1, plataforma.login("cliente1", "senha1"));
        assertNull(plataforma.login("cliente3", "senha3"));
    }

    @Test
    public void testAdicionarSerie() {
        Serie serie1 = new Serie("Série 1", "Gênero 1", "Idioma 1", 10);
        Serie serie2 = new Serie("Série 2", "Gênero 2", "Idioma 2", 20);
        plataforma.adicionarSerie(serie1);
        plataforma.adicionarSerie(serie2);

        assertTrue(plataforma.filtrarPorGenero("Gênero 1").contains(serie1));
        assertTrue(plataforma.filtrarPorGenero("Gênero 2").contains(serie2));
        assertEquals(2, plataforma.filtrarPorGenero("Gênero 1").size());
        assertEquals(2, plataforma.filtrarPorGenero("Gênero 2").size());
    }

    @Test
    public void testAdicionarCliente() {
        Cliente cliente1 = new Cliente("cliente1", "senha1");
        Cliente cliente2 = new Cliente("cliente2", "senha2");
        plataforma.adicionarCliente(cliente1);
        plataforma.adicionarCliente(cliente2);

        assertTrue(PlataformaStreaming.filtrarPorIdioma("cliente1").contains(cliente1));
        assertTrue(plataforma.filtrarPorIdioma("cliente2").contains(cliente2));
        assertEquals(2, plataforma.filtrarPorIdioma("cliente1").size());
        assertEquals(2, plataforma.filtrarPorIdioma("cliente2").size());
    }

    @Test
    public void testFiltrarPorGenero() {
        Serie serie1 = new Serie("Série 1", "Gênero 1", "Idioma 1", 10);
        Serie serie2 = new Serie("Série 2", "Gênero 2", "Idioma 2", 20);
        plataforma.adicionarSerie(serie1);
        plataforma.adicionarSerie(serie2);

        assertTrue(plataforma.filtrarPorGenero("Gênero 1").contains(serie1));
        assertTrue(plataforma.filtrarPorGenero("Gênero 2").contains(serie2));
        assertEquals(1, plataforma.filtrarPorGenero("Gênero 1").size());
        assertEquals(1, plataforma.filtrarPorGenero("Gênero 2").size());
    }


}

