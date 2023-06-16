package models;

public class Usuario {
    private String nome;
    private String user;
    private String senha;
    private int role;

    // CONSTRUTOR
    public Usuario(String nome, String user, String senha, int role) {
        this.nome = nome;
        this.user = user;
        this.senha = senha;
        this.role = role;
    }

    // GETTERS 
    public String getNome() {
        return nome;
    }
    public String getUser() {
        return user;
    }
    public String getSenha() {
        return senha;
    }
    public int getRole() {
        return role;
    }

    // SETTERS
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public void setRole(int role) {
        this.role = role;
    }
}
