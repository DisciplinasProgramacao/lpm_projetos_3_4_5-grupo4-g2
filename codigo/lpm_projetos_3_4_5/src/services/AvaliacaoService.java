package services;

import models.Avaliacao;
import utils.Utilidade;

public class AvaliacaoService {
    private String finalPath = Utilidade.CSV_FOLDER_PATH+"POO_Avaliacoes.csv";

    public void cadastrarAvaliacao(Avaliacao avaliacao) {
        String str = avaliacao.getUser()+";"+
        avaliacao.getIdMidia()+";"+
        avaliacao.getComentario()+";"+
        avaliacao.getNota();
                    
        Utilidade.escrever(str, finalPath);
    }
}
