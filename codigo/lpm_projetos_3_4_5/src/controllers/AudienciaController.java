package controllers;

import java.util.List;

import models.Audiencia;
import services.AudienciaService;

public class AudienciaController {
    private AudienciaService audienciaService;

    public AudienciaController() {
        this.audienciaService = new AudienciaService();
    }

    public void cadastrarAudiencia(Audiencia audiencia) {
        audienciaService.cadastrarAudiencia(audiencia);
    }

    public void removerMidiasFuturas(Audiencia audiencia) {
        audienciaService.removerAudiencia(audiencia);
    }

    public List<Audiencia> preencherAudiencia() {
        return audienciaService.preencherAudiencia();
    }
}
