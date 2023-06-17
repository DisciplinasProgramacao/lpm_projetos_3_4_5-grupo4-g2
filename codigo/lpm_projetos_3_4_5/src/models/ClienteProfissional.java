package models;

import java.util.ArrayList;

public class ClienteProfissional extends Usuario {

    private ArrayList<Midia> paraVer = new ArrayList<Midia>();
    private ArrayList<Midia> assistidas = new ArrayList<Midia>();
    private ArrayList<Avaliacao> avaliadas = new ArrayList<Avaliacao>();

    public ClienteProfissional(String nome, String user, String senha, int role) {
        super(nome, user, senha, role);
        //TODO Auto-generated constructor stub
    }
    
}
