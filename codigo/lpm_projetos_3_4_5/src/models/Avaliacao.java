package models;

public class Avaliacao {
    private String idCliente;
    private String idMidia;
    private String comentario;
    private int nota;

    // custrutor e getters / setters
    public Avaliacao(String idCliente, String idMidia, String comentario, int nota) {
        this.idCliente = idCliente;
        this.idMidia = idMidia;
        this.comentario = comentario;
        this.nota = nota;
    }

    public int getNota() {
        return nota;
    }
}
