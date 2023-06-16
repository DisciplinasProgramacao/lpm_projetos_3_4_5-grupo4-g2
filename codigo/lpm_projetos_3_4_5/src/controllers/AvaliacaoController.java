package controllers;

import models.Avaliacao;
import services.AvaliacaoService;

public class AvaliacaoController {
    private AvaliacaoService avaliacaoService;
    
    public AvaliacaoController() {
        this.avaliacaoService = new AvaliacaoService();
    }

    public void cadastrarAvaliacao(Avaliacao avaliacao) {
        avaliacaoService.cadastrarAvaliacao(avaliacao);
    }
}
