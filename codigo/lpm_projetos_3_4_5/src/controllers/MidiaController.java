package controllers;

import java.util.List;

import models.Filme;
import models.Midia;
import models.Serie;
import services.MidiaService;

public class MidiaController {
    private MidiaService midiaService;
    
    public MidiaController(List<Filme> filmes, List<Serie> series) {
        this.midiaService = new MidiaService(filmes, series);
    }

    public List<Midia> preencherMidias() {
        return midiaService.listarMidias();
    }
}