package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import models.Cliente;
import models.PlataformaStreaming;

public class PlataformaStreamingTest {

    private PlataformaStreaming plataforma;
    private Cliente clienteNormal;

    @Before
    public void init(){
        //instancia plataforma
        plataforma = PlataformaStreaming.getInstance("Streaming");

        //instancia clientes
        clienteNormal =  new Cliente("1", "a", "ab", "123");

        plataforma.addCliente(clienteNormal);
    }

    @Test
    public void shouldLogInAUser() {
        Cliente expectedCliente = new Cliente("1", "a", "ab", "123");

        Cliente result = plataforma.login("ab", "123");

        assertEquals(result.getIdCliente(), expectedCliente.getIdCliente());
        assertEquals(result.getNome(), expectedCliente.getNome());
        assertEquals(result.getUser(), expectedCliente.getUser());
        assertEquals(result.getSenha(), expectedCliente.getSenha());
        assertEquals(result.getParaVer(), expectedCliente.getParaVer());
        assertEquals(result.getAssistidas(), expectedCliente.getAssistidas());
        assertEquals(result.getAvaliadas(), expectedCliente.getAvaliadas());
    }

    @Test
    public void shouldFailedToLogInAUnknowUser(){
        Cliente expectedCliente = null;

        Cliente result = plataforma.login("ac", "123");

        assertEquals(result, expectedCliente);

        result = plataforma.login("ab", "000");

        assertEquals(result, expectedCliente);

        result = plataforma.login("ac", "000");

        assertEquals(result, expectedCliente);
    }

    @Test
    public void shouldLogOffCurrentUser() {
        plataforma.login("ab", "123");

        assertNotEquals(null, plataforma.getClienteAtual());
        plataforma.logoff();

        assertEquals(plataforma.getClienteAtual(), null);
    }

    @Test
    public void shouldFillMidiasWithMovies() {
        int result = 201;

        try {
            plataforma.preencherFilmes();
            assertEquals(plataforma.getMidias().size(), result);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void shouldFillMidiasWithSeries() {
        int result = 130;

        try {
            plataforma.preencherSeries();
            assertEquals(plataforma.getMidias().size(), result);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void shouldFillClientes(){
        int result = 51893;

        try {
            plataforma.preencherClientes();
            assertEquals(result, plataforma.getClientes().size());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // @Test
    // public void shouldFillMidiasCourtHearing() {
    //     try {
    //         plataforma.preencherSeries();
    //         plataforma.preencherAudiencia();
    //     } catch(Exception e) {
    //         System.out.println(e.getMessage());
    //     }
    // }
}
