package services;

import models.Usuario;
import utils.Utilidade;

public class UsuarioService {

    public void cadastrarUsuario(Usuario usuario) {
        String str = usuario.getNome()+";"+
                    usuario.getUser()+";"+
                    usuario.getSenha();

        Utilidade.escrever(str, "/home/ribas/PUCMINAS/Lab_PM/lpm_projetos_3_4_5-grupo4-g2/codigo/lpm_projetos_3_4_5/src/csv_files_test/POO_Espectadores.csv");
    }
}
