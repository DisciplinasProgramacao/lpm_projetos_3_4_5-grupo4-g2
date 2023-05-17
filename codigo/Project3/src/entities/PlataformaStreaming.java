package entities;

import java.util.ArrayList;
import java.util.List;

public class PlataformaStreaming {
    private String nome;
    private List<Midia> midias;
    private List<Cliente> clientes;
    private Cliente clienteAtual;

    public PlataformaStreaming(String nome) {
        this.nome = nome;
        this.midias = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.clienteAtual = null;
    }


    // getters and setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Midia> getMidias() {
        return midias;
    }

    public void setMidias(List<Midia> midias) {
        this.midias = midias;
    }


    public Cliente getClienteAtual() {
        return clienteAtual;
    }

    public void setClienteAtual(Cliente clienteAtual) {
        this.clienteAtual = clienteAtual;
    }

  
    public Cliente login(String nomeUsuario, String senha) {
        for (Cliente cliente : clientes) {
            if (cliente.getNomeDoUsuario().equals(nomeUsuario) && cliente.getSenha().equals(senha)) {
                clienteAtual = cliente;
                return cliente;
            }
        }
        return null;
    }


    public void adicionarMidia(Midia midia) {
        midias.add(midia);
    }

    public void adicionarCliente(Cliente cliente1) {
        clientes.add(cliente1);
    }

    public List<Midia> filtrarPorQtdEpisodios(int quantEpisodios) {
        List<Midia> midiasFiltradas = new ArrayList<>();
        for (Midia midia : midias) {
            if (midia instanceof Serie) {
                Serie serie = (Serie) midia;
                if (serie.getQuantidadeEpisodios() >= quantEpisodios) {
                    midiasFiltradas.add(midia);
                }
            }
        }
        return midiasFiltradas;
    }

    public List<Midia> filtrarPorIdioma(String idioma) {
        List<Midia> midiasFiltradas = new ArrayList<>();
        for (Midia midia : midias) {
            if (midia.getIdioma().equals(idioma)) {
                midiasFiltradas.add(midia);
            }
        }
        return midiasFiltradas;
    }

    public void registrarAudiencia(Midia midia) {
        if (midia instanceof Serie) {
            midia.registrarAudiencia();
        }
    }

    public void logoff() {
        clienteAtual = null;
    }

    public Midia buscarMidia(String nomeMidia) {
        for (Midia midia : midias) {
            if (midia.getNome().equals(nomeMidia)) {
                return midia;
            }
        }
        return null;
    }
}