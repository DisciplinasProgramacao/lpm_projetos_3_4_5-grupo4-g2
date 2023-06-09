package tests;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import models.Serie;

public class SerieTest {
    private Serie midiaS = new Serie("1", "S1", "18/03/2019");
    
    @Test
    public void shouldSetSerieEpisodesAmount(){
        assertNotEquals(midiaS.getQuantidadeEpisodios(), null);
    }
}
