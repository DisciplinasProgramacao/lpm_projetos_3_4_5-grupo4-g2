package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import javax.swing.CellEditor;

import org.junit.Test;

import models.Avaliacao;
import models.Cliente;
import models.Filme;

public class ClienteTest {
    Cliente C1 = new Cliente("Raffa Moreira", "lilRaf","123");
    Filme F1 = new Filme("776", "Lil Raffa Mano", "24/07/2022", 110);
    Avaliacao avaliacao = new Avaliacao(C1.getUser(),F1.getId(),"Manda dms!",5);
    Cliente Cprofissao = new Cliente("abc", "abc", "123", "profissao");
    Cliente Cexpert = new Cliente("aaa", "aaa", "123");

    @Test 
    public void shouldAddCustomerAssessmentToAvaliadasList(){
        C1.getAvaliadas().add(avaliacao);
        assertNotEquals(C1.getAvaliadas().size(), 0);
    }

    @Test
    public void shouldVerifyIfUserIsAProfessional() {
        assertNotEquals(true,C1.ehProfissional());
        assertEquals(true, Cprofissao.ehProfissional());
    }

    @Test
    public void shouldVerifyIfUserIsAnExpert() {
        assertNotEquals(true, Cexpert.ehEspecialista());

        for(int i = 0; i < 5; i++) {
            Cexpert.getAssistidas().add(F1);
        }

        assertEquals(true, Cexpert.ehEspecialista());
    }
}
