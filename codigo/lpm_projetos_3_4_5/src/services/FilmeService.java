package services;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import models.Filme;
import utils.Utilidade;

public class FilmeService {

    private String finalPath = Utilidade.CSV_FOLDER_PATH+"POO_Filmes.csv";
    public void cadastrarFilme(Filme filme) {
        String str = filme.getId()+";"+
                    filme.getNome()+";"+
                    filme.getDataLancamento()+";"+
                    filme.getDuracao();

        Utilidade.escrever(str, finalPath);
    }

    public List<Filme> preencherFilmes() {
        try{
            List<Filme> filmes = Files.lines(Paths.get(finalPath))
                .skip(1)
                .map(line -> line.split(";"))
                .map(col -> new Filme(col[0], col[1], col[2], Integer.parseInt(col[3])))
                .collect(Collectors.toList());

                return filmes;
        }catch(Exception e) {
            e.getMessage();
        }
        return null;
    }
}
