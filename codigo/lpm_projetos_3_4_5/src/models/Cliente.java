package models;

import java.util.ArrayList;

public class Cliente {
    private String nome;
    private String user;
    private String senha;
    private ArrayList<Midia> paraVer = new ArrayList<Midia>();
    private ArrayList<Midia> assistidas = new ArrayList<Midia>();
    private ArrayList<Avaliacao> avaliadas = new ArrayList<Avaliacao>();
    private String profissao;

    // construtor e getters / setters
    public Cliente(String nome, 
                   String user, 
                   String senha, 
                   ArrayList<Midia> paraVer,
                   ArrayList<Midia> assistidas,
                   ArrayList<Avaliacao> avaliadas,
                   String profissao) {

        this.nome = nome;
        this.user = user;
        this.senha = senha;
        this.paraVer = paraVer;
        this.assistidas = assistidas;
        this.avaliadas = avaliadas;
        this.profissao = profissao;
    }

    public Cliente(String nome, 
            String user, 
            String senha, 
            String profissao) {

        this.nome = nome;
        this.user = user;
        this.senha = senha;
        this.profissao = profissao;
    }

    public Cliente(String nome, 
            String user, 
            String senha) {

        this.nome = nome;
        this.user = user;
        this.senha = senha;
    }


    public String getNome() {
        return nome;
    }

    public String getUser() {
        return user;
    }

    public String getSenha() {
        return senha;
    }

    public ArrayList<Midia> getAssistidas() {
        return assistidas;
    }

    public ArrayList<Avaliacao> getAvaliadas() {
        return avaliadas;
    }

    public ArrayList<Midia> getParaVer() {
        return paraVer;
    }

    public String getProfissao() {
        return profissao;
    }

    // operações da classe
    public boolean ehEspecialista() {
        return this.getAssistidas().size() > 4;
    }

    public boolean ehProfissional() {
        return this.profissao != null;
    }
}
