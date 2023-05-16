package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Cliente;
import entities.DataLoader;
import entities.Serie;
import entities.Filme;
import entities.Midia;
import entities.PlataformaStreaming;

public class App { public static void main(String[] args) {
	PlataformaStreaming plataforma = new PlataformaStreaming("MinhaPlataforma");

        Serie serie1 = new Serie("1", "Série 1", "2021-01-01", 100000, "Drama", 10);
        Serie serie2 = new Serie("2", "Série 2", "2022-01-01", 150000, "Comédia", 8);
        Filme filme1 = new Filme("1", "Filme 1", "2020-01-01", 200000, "Ação", 120);
        Filme filme2 = new Filme("2", "Filme 2", "2019-01-01", 180000, "Romance", 90);
	

        plataforma.getSeries().add(serie1);
        plataforma.getSeries().add(serie2);
        plataforma.getFilmes().put(filme1.getId(), filme1);
        plataforma.getFilmes().put(filme2.getId(), filme2);

        Cliente cliente = new Cliente("João", "joao123", "senha123");

		Serie serie3 = new Serie("3", "Série 3", "2021-01-01", 100000, "Drama", 10);
		Filme filme3 = new Filme("3", "Filme 3", "2020-01-01", 200000, "Ação", 120);

		cliente.cadastrarMidia(serie3);
		cliente.cadastrarMidia(filme3);

        cliente.getListaParaVer().add(serie1);
        cliente.getListaParaVer().add(filme1);

        plataforma.setClienteAtual(cliente);

        System.out.println("Nome do cliente atual: " + plataforma.getClienteAtual().getNomeDoUsuario());
        System.out.println("Quantidade de séries na plataforma: " + plataforma.getSeries().size());
        System.out.println("Quantidade de filmes na plataforma: " + plataforma.getFilmes().size());
    System.out.println("Média de avaliação da série 1: " + serie1.calcularMediaAvaliacao());

    System.out.println("Lista de mídias para ver do cliente:");
    for (Midia midia : cliente.getListaParaVer()) {
        System.out.println(midia.getNome());
    }
}

}