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

    private String relativePath = Utilidade.CSV_FOLDER_PATH+"POO_Audiencias.csv";
    
    public void cadastrarAudiencia(Audiencia audiencia) {
        String str = audiencia.getUser() + ";" + audiencia.getParaVer() + ";" + audiencia.getMidiaID();

        Utilidade.escrever(str, relativePath);
    }

    public void removerMidiasFuturas(Audiencia audiencia) {
        try {
            ArrayList<String> oldLines = new ArrayList<String>(Files.readAllLines(Path.of(relativePath)));
            ArrayList<String> newLines =  new ArrayList<String>();

            oldLines.forEach((l) -> newLines.add(l));

            for(String s : oldLines) {
                String[] aux = s.split(";");
                if(aux[2].equals(audiencia.getMidiaID()) && aux[1].equals("F")) {
                    newLines.remove(s);
                }
            }

            Files.write(Path.of(relativePath), newLines, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        }catch(Exception e) {
            e.getMessage();
        }
    }

    public List<Audiencia> preencherAudiencia() {
        try {
            List<Audiencia> audiencias = new ArrayList<>();

            Files.lines(Paths.get(relativePath))
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
