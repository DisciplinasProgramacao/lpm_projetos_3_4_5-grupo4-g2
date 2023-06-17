package services;

import java.util.ArrayList;
import java.util.List;

import models.Midia;

public class MidiaService {

    public List<Midia> preencherMidias(List<Midia> midias) {
        List<Midia> fullMidias = new ArrayList<Midia>();
        fullMidias.addAll(midias);

        return fullMidias;
    }
}
