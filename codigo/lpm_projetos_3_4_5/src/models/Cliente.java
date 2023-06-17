package models;

import java.util.ArrayList;

public class Cliente {
    private String nome;
    private String user;
    private String senha;
    private ArrayList<Midia> paraVer = new ArrayList<Midia>();
    private ArrayList<Midia> assistidas = new ArrayList<Midia>();
    private ArrayList<Avaliacao> avaliadas = new ArrayList<Avaliacao>();
    private String profissão;

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
        this.profissão = profissao;
    }

    public Cliente(String nome, 
            String user, 
            String senha, 
            String profissao) {

        this.nome = nome;
        this.user = user;
        this.senha = senha;
        this.profissão = profissao;
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

    public String getProfissão() {
        return profissão;
    }

    // operações da classe
    public void assistirMidia(Midia midia) {
        for(Midia m : paraVer) {
            if(m.getId().equals(midia.getId())){
                this.paraVer.remove(midia);
            }
        }
    }

    public boolean ehEspecialista() {
        return this.getAssistidas().size() > 4;
    }

    public boolean ehProfissional() {
        return this.profissão != null;
    }

    // operações de especialista
}
