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

    /**
     * Constrói um objeto Cliente com as informações fornecidas.
     * 
     * @param nome       o nome do cliente
     * @param user       o usuário do cliente
     * @param senha      a senha do cliente
     * @param paraVer    a lista de mídias para assistir do cliente
     * @param assistidas a lista de mídias já assistidas pelo cliente
     * @param avaliadas  a lista de avaliações realizadas pelo cliente
     * @param profissao  a profissão do cliente
     */
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

    /**
     * Constrói um objeto Cliente com as informações fornecidas.
     * 
     * @param nome      o nome do cliente
     * @param user      o usuário do cliente
     * @param senha     a senha do cliente
     * @param profissao a profissão do cliente
     */
    public Cliente(String nome,
            String user,
            String senha,
            String profissao) {

        this.nome = nome;
        this.user = user;
        this.senha = senha;
        this.profissao = profissao;
    }

    /**
     * Constrói um objeto Cliente com as informações fornecidas.
     * 
     * @param nome  o nome do cliente
     * @param user  o usuário do cliente
     * @param senha a senha do cliente
     */
    public Cliente(String nome,
            String user,
            String senha) {

        this.nome = nome;
        this.user = user;
        this.senha = senha;
    }

    /**
     * Retorna o nome do cliente.
     * 
     * @return o nome do cliente
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o usuário do cliente.
     * 
     * @return o usuário do cliente
     */
    public String getUser() {
        return user;
    }

    /**
     * Retorna a senha do cliente.
     * 
     * @return a senha do cliente
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Retorna a lista de mídias já assistidas pelo cliente.
     * 
     * @return a lista de mídias assistidas pelo cliente
     */
    public ArrayList<Midia> getAssistidas() {
        return assistidas;
    }

    /**
     * Retorna a lista de avaliações realizadas pelo cliente.
     * 
     * @return a lista de avaliações realizadas pelo cliente
     */
    public ArrayList<Avaliacao> getAvaliadas() {
        return avaliadas;
    }

    /**
     * Retorna a lista de mídias para assistir do cliente.
     * 
     * @return a lista de mídias para assistir do cliente
     */
    public ArrayList<Midia> getParaVer() {
        return paraVer;
    }

    /**
     * Retorna a profissão do cliente.
     * 
     * @return a profissão do cliente
     */
    public String getProfissao() {
        return profissao;
    }

    /**
     * Verifica se o cliente é um especialista.
     * 
     * @return true se o cliente já assistiu mais de 4 mídias, caso contrário, false
     */
    public boolean ehEspecialista() {
        return this.getAssistidas().size() > 4;
    }

    /**
     * Verifica se o cliente possui uma profissão definida.
     * 
     * @return true se o cliente possui uma profissão, caso contrário, false
     */
    public boolean ehProfissional() {
        return this.profissao != null;
    }
}