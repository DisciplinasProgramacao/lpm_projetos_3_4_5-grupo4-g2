package models;

import java.util.ArrayList;

public class Cliente implements ClienteEspecialista {
    private String idCliente;
    private String nome;
    private String user;
    private String senha;
    private ArrayList<Midia> paraVer = new ArrayList<Midia>();
    private ArrayList<Midia> assistidas = new ArrayList<Midia>();
    private ArrayList<Avaliacao> avaliadas = new ArrayList<Avaliacao>();

    // construtor e getters / setters
    public Cliente(String idCliente,
                   String nome, 
                   String user, 
                   String senha, 
                   ArrayList<Midia> paraVer,
                   ArrayList<Midia> assistidas,
                   ArrayList<Avaliacao> avaliadas) {

        this.idCliente = idCliente;
        this.nome = nome;
        this.user = user;
        this.senha = senha;
        this.paraVer = paraVer;
        this.assistidas = assistidas;
        this.avaliadas = avaliadas;
    }

    public Cliente(String idCliente,
            String nome, 
            String user, 
            String senha) {

        this.idCliente = idCliente;
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
    
    public String getIdCliente() {
        return idCliente;
    }

    // operações da classe
    public void addAssistidas(Midia midia){
        this.assistidas.add(midia);
    }

    public void addParaVer(Midia midia) {
        this.paraVer.add(midia);
    }

    public void assistirMidia(Midia midia) {
        this.assistidas.add(midia);
        for(Midia m : paraVer) {
            if(m.getId().equals(midia.getId())){
                this.paraVer.remove(midia);
            }
        }
    }

    public void avaliarMidia(Avaliacao avaliacao) {
        this.avaliadas.add(avaliacao);
    }

    // operações de especialista
}
