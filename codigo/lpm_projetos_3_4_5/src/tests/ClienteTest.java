package tests;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import models.Avaliacao;
import models.Cliente;
import models.Filme;

public class ClienteTest {
    Cliente C1 = new Cliente("777", "Raffa Moreira", "lilRaf", "777");
    Filme F1 = new Filme("776", "Lil Raffa Mano", "24/07/2022", 110);
    Avaliacao avaliacao = new Avaliacao(C1.getUser(),F1.getId(),"Manda dms!",5);

    @Test
    public void shouldAddCustomerAssessmentToAvaliadasList(){
        C1.getAvaliadas().add(avaliacao);
        assertNotEquals(C1.getAvaliadas().size(), 0);
    }
}
