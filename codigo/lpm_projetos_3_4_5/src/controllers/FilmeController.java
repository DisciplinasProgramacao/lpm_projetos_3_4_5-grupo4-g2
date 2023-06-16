package controllers;

import java.util.List;

import models.Filme;
import services.FilmeService;

public class FilmeController {
    private FilmeService filmeService;

    public FilmeController() {
        this.filmeService = new FilmeService();
    }

    public void cadastrarFilmes(Filme filme) {
        filmeService.cadastrarFilme(filme);
    }

    public List<Filme> preencherFilmes() {
        return filmeService.preencherFilmes();
    }
}
