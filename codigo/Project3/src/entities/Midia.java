package entities;


//Essa classe será a superclasse da classe Filme e Serie
public class Midia {
    private String id;
    private String nome;
    private String dataLancamento;
    private int audiencia;
    private String genero;

    public Midia(String id, String nome, String dataLancamento, int audiencia, String genero) {
        this.id = id;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.audiencia = audiencia;
        this.genero = genero;
    }

    // getters e setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public int getAudiencia() {
        return audiencia;
    }

    public void setAudiencia(int audiencia) {
        this.audiencia = audiencia;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double calcularMediaAvaliacao() {
        // lógica para calcular a média de avaliação da mídia
        return 0.0;
    }
}
