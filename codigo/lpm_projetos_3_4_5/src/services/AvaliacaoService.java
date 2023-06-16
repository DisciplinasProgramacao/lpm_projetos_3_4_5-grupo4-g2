package services;

import models.Avaliacao;
import utils.Utilidade;

public class AvaliacaoService {
    
    public void cadastrarAvaliacao(Avaliacao avaliacao) {
        String str = avaliacao.getIdCliente()+";"+
        avaliacao.getIdMidia()+";"+
        avaliacao.getComentario()+";"+
        avaliacao.getNota();
                    
        Utilidade.escrever(str, "/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_test/POO_Avaliacoes.csv");
    }
}
