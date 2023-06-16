package controllers;

import java.util.List;

import models.Midia;
import services.MidiaService;

public class MidiaController {
    private MidiaService midiaService;
    
    public MidiaController() {
        this.midiaService = new MidiaService();
    }

    public List<Midia> preencherMidias() {
        return midiaService.listarMidias();
    }

    
}