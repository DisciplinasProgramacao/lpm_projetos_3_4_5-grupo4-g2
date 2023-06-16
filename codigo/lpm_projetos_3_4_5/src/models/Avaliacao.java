package models;

public class Avaliacao {
    private String user;
    private String idMidia;
    private String comentario;
    private int nota;

    // custrutor e getters / setters
    public Avaliacao(String user, String idMidia, String comentario, int nota) {
        this.user = user;
        this.idMidia = idMidia;
        this.comentario = comentario;
        this.nota = nota;
    }

    public String getUser() {
        return user;
    }

    public String getIdMidia() {
        return idMidia;
    }

    public String getComentario() {
        return comentario;
    }
    
    public int getNota() {
        return nota;
    }
}
