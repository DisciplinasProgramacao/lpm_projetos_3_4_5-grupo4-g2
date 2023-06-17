package models;

import java.util.ArrayList;

public class ClienteEspecialista  extends Usuario {

    private ArrayList<Midia> paraVer = new ArrayList<Midia>();
    private ArrayList<Midia> assistidas = new ArrayList<Midia>();
    private ArrayList<Avaliacao> avaliadas = new ArrayList<Avaliacao>();
    
    public ClienteEspecialista(String nome, String user, String senha, int role) {
        super(nome, user, senha, role);
    }
}
