package services;

import java.util.ArrayList;
import java.util.List;

import models.Midia;

public class MidiaService {
    private List<Midia> midias;

    public MidiaService() {
        this.midias = new ArrayList<>();
    }

    public List<Midia> listarMidias() {
        return midias;
    }
}
