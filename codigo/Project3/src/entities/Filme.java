package entities;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Filme extends Midia{
    
    private int id;
    private String nome;
    private LocalDate dataLancamento;
    private int duracaoMinutos;
    
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
    
    public Filme(int id, String nome, LocalDate dataLancamento, int duracaoMinutos) {
        this.id = id;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.duracaoMinutos = duracaoMinutos;
    }
    
    // getters e setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public LocalDate getDataLancamento() {
        return dataLancamento;
    }
    
    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
    
    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }
    
    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

}
