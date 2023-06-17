package services;

import java.util.ArrayList;
import java.util.List;

import models.Filme;
import models.Midia;
import models.Serie;

public class MidiaService {
    private List<Midia> midias;

    public MidiaService(List<Filme> filmes, List<Serie> series) {
        this.midias.addAll(filmes);
        this.midias.addAll(series);
    }

    public List<Midia> listarMidias() {
        return midias;
    }

}
