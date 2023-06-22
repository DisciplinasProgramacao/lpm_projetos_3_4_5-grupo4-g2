package models;

/**
 * A classe Avaliacao representa uma avaliação feita por um usuário para uma
 * determinada mídia.
 */
public class Avaliacao {
    private String user;
    private String idMidia;
    private String comentario;
    private int nota;

    /**
     * Cria uma nova instância da classe Avaliacao com as informações fornecidas.
     * 
     * @param user       O nome do usuário que fez a avaliação.
     * @param idMidia    O identificador da mídia avaliada.
     * @param comentario O comentário associado à avaliação.
     * @param nota       A nota atribuída à mídia na avaliação.
     */
    public Avaliacao(String user, String idMidia, String comentario, int nota) {
        this.user = user;
        this.idMidia = idMidia;
        this.comentario = comentario;
        this.nota = nota;
    }

    /**
     * Obtém o nome do usuário que fez a avaliação.
     * 
     * @return O nome do usuário.
     */
    public String getUser() {
        return user;
    }

    /**
     * Obtém o identificador da mídia avaliada.
     * 
     * @return O identificador da mídia.
     */
    public String getIdMidia() {
        return idMidia;
    }

    /**
     * Obtém o comentário associado à avaliação.
     * 
     * @return O comentário da avaliação.
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Obtém a nota atribuída à mídia na avaliação.
     * 
     * @return A nota da avaliação.
     */
    public int getNota() {
        return nota;
    }
}
