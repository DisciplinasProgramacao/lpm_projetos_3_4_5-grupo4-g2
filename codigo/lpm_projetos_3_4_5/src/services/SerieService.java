package services;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import models.Serie;
import utils.Utilidade;

public class SerieService {

    private String finalPath = Utilidade.CSV_FOLDER_PATH+"POO_Series.csv";

    public void cadastrarSerie(Serie serie) {
        String str = serie.getId()+";"+
                    serie.getNome()+";"+
                    serie.getDataLancamento();
        
        Utilidade.escrever(str, finalPath);
    }

    public List<Serie> preencherSeries() {
        try {
            List<Serie> series = Files.lines(Paths.get(finalPath))
            .map(line -> line.split(";"))
            .map(col -> new Serie(col[0], col[1], col[2]))
            .collect(Collectors.toList());

            return series;
            
        } catch(Exception e) {
            e.getMessage();
        }
        return null;
    }
}
