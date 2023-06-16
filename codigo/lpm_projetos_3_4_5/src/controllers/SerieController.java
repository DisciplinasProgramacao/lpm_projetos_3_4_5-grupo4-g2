package controllers;

import java.util.List;

import models.Serie;
import services.SerieService;

public class SerieController {
    private SerieService serieService;

    public SerieController() {
        this.serieService = new SerieService();
    }

    public void cadastrarSeries(Serie serie) {
        serieService.cadastrarSerie(serie);
    }

    public List<Serie> preencherSeries() {
        return serieService.preencherSeries();
    }
}
