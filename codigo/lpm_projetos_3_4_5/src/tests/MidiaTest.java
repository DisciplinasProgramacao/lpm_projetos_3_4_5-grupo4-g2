package tests;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import models.Filme;
import models.Midia;
import models.Serie;

public class MidiaTest {

    private Serie midiaS = new Serie("1", "S1", "18/03/2019"); 
    private Filme midiaF = new Filme("2", "F1", "18/03/2019", 96);
    private Midia result = null;

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
}
