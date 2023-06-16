package models;

enum Role {
    ADMIN,
    COMUM,
    ESPECIALISTA,
    PROFISSIONAL
}

public class Usuario {
    private String id;
    private String nome;
    private String user;
    private String senha;
    private Role role;

    // GETTERS 
    public String getId() {
        return id;
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
    public Role getRole() {
        return role;
    }

    // SETTERS
    public void setId(String id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public void setRole(Role role) {
        this.role = role;
    }
}
