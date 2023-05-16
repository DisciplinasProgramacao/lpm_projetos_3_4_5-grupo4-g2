package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlataformaStreaming {
     private String nome;
    private List<Serie> series;
    private Map<String, Filme> filmes;
    private Cliente clienteAtual;

    public PlataformaStreaming(String nome) {
        this.nome = nome;
        this.series = new ArrayList<>();
        this.filmes = new HashMap<>();
        this.clienteAtual = null;
    }

    // getters and setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Serie> getSeries() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }

    public Map<String, Filme> getFilmes() {
        return filmes;
    }

    public void setFilmes(Map<String, Filme> filmes) {
        this.filmes = filmes;
    }

    public Cliente getClienteAtual() {
        return clienteAtual;
    }

    public void setClienteAtual(Cliente clienteAtual) {
        this.clienteAtual = clienteAtual;
    }
}