package controllers;

import services.FilmeService;

public class FilmeController {
    private FilmeService filmeService;

    public FilmeController() {
        this.filmeService = new FilmeService();
    }

    
}
