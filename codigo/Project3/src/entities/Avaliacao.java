package entities;

public class Avaliacao {
    private int nota;

    public Avaliacao(int nota) {
        this.nota = nota;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public void setComentario(String comentario) {
    }
}