package services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import models.Audiencia;
import utils.Utilidade;

public class AudienciaService {

    private String finalPath = Utilidade.CSV_FOLDER_PATH+"POO_Audiencias.csv";
    
    public void cadastrarAudiencia(Audiencia audiencia) {
        String str = audiencia.getUser() + ";" + audiencia.getParaVer() + ";" + audiencia.getMidiaID();

        Utilidade.escrever(str, finalPath);
    }

    public void removerAudiencia(Audiencia audiencia) {
        try {
            ArrayList<String> oldLines = new ArrayList<String>(Files.readAllLines(Path.of(finalPath)));
            ArrayList<String> newLines =  new ArrayList<String>();

            //oldLines.forEach((l) -> newLines.add(l));
            newLines.addAll(oldLines);

            for(String s : oldLines) {
                String[] aux = s.split(";");
                if(aux[2].equals(audiencia.getMidiaID()) && aux[1].equals("F")) {
                    newLines.remove(s);
                }
            }

            Files.write(Path.of(finalPath), newLines, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        }catch(Exception e) {
            e.getMessage();
        }
    }

    public List<Audiencia> preencherAudiencia() {
        try {
            List<Audiencia> audiencias = new ArrayList<>();

            Files.lines(Paths.get(finalPath))
            .map(lines -> lines.split(";"))
            .map(col -> new Audiencia(col[1], col[2], col[3]))
            .collect(Collectors.toList());
            return audiencias;

        } catch(Exception e) {
            e.getMessage();
        }
        return null;
    }
}
