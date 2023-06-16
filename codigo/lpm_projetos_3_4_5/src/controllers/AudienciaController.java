package controllers;

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
        audienciaService.removerMidiasFuturas(audiencia);
    }

    public void preencherAudiencia() {
        audienciaService.preencherAudiencia();
    }
}
