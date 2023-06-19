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
        clienteNormal =  new Cliente("a", "ab", "123");

        plataforma.getClientes().add(clienteNormal);
    }

    @Test
    public void shouldLogInAUser() {
        Cliente expectedCliente = new Cliente("1", "a", "ab", "123");

        plataforma.login("ab", "123");

        assertNotEquals(plataforma.getClienteAtual(), expectedCliente);
    }

    @Test
    public void shouldFailedToLogInAUnknowUser(){
        Cliente expectedCliente = null;

        plataforma.login("ac", "123");

        assertEquals(plataforma.getClienteAtual(), expectedCliente);

        plataforma.login("ab", "000");

        assertEquals(plataforma.getClienteAtual(), expectedCliente);

        plataforma.login("ac", "000");

        assertEquals(plataforma.getClienteAtual(), expectedCliente);
    }

    @Test
    public void shouldLogOffCurrentUser() {
        plataforma.login("ab", "123");

        assertNotEquals(null, plataforma.getClienteAtual());
        plataforma.logoff();

        assertEquals(null, plataforma.getClienteAtual());
    }

    @Test
    public void shouldFillMidias() {
        int result = 48;

        try {
            plataforma.preencherMidias();
            assertEquals(plataforma.getMidias().size(), result);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void shouldFillClientes(){
        int result = 29;

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
