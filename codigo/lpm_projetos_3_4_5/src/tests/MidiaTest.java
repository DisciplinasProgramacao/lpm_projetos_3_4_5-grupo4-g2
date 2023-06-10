package tests;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import models.Avaliacao;
import models.Cliente;
import models.Filme;
import models.Midia;
import models.Serie;

public class MidiaTest {

    private Serie midiaS = new Serie("1", "S1", "18/03/2019"); 
    private Filme midiaF = new Filme("2", "F1", "18/03/2019", 96);
    private Midia result = null;

    Cliente C1 = new Cliente("777", "Raffa Moreira", "lilRaf", "777");
    Cliente C2 = new Cliente("7771", "Raffa Moreira", "lilRaf", "777");
    Cliente C3 = new Cliente("7772", "Raffa Moreira", "lilRaf", "777");

    private Avaliacao A1 = new Avaliacao(C1.getIdCliente(), midiaF.getId(), "prr", 3);
    private Avaliacao A2 = new Avaliacao(C2.getIdCliente(), midiaF.getId(), "prr", 3);
    private Avaliacao A3 = new Avaliacao(C3.getIdCliente(), midiaF.getId(), "prr", 3);

    @Test
    public void shouldSetMidiaGender(){
        result = midiaF;
        assertNotEquals(result.getGenero(), null);

        result = midiaS;
        assertNotEquals(result.getGenero(), null);
    }

    @Test
    public void shouldSetMidiaLenguage() {
        result = midiaF;
        assertNotEquals(result.getIdioma(), null);

        result = midiaS;
        assertNotEquals(result.getIdioma(), null);
    }

    // @Test
    // public void shouldCalculateMedia(){
        
    // }
}
