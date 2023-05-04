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

public class App {

	public static void main(String[] args) throws ParseException {
		
		int opcao;

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Serie> listSeries = new ArrayList<>();
		List<Cliente> listClientes = new ArrayList<>(); 

		/**
		 * Cria um objeto BufferedReader para ler o arquivo de entrada CSV.
		 *
		 * @param sourceFileStr o caminho do arquivo de entrada CSV
		 * @return um novo objeto BufferedReader criado a partir do arquivo de entrada
		 */
		
		DataLoader d1 = new DataLoader();		
		DataLoader.caregarSeries2Pedro();
		DataLoader.carregarClientes();		
		DataLoader.caregarFilmes();
		DataLoader.caregarAudiencia();
		
		//testando o metodo de registrar audiencia
		DataLoader.listSeries.get(0).setAudiencia(50.0);
		DataLoader.listSeries.get(0).registrarAudiencia();
		DataLoader.listSeries.get(0).registrarAudiencia();
		DataLoader.listSeries.get(0).registrarAudiencia();
		DataLoader.listSeries.get(0).registrarAudiencia();		
		System.out.println("Audiencia da serie: " + DataLoader.listSeries.get(0).getAudiencia());
		
		//tetando os metodos em que os clientes adicionam series assistidas e para assitir
		DataLoader.listClientes.get(0).adicionarNaListaParaVer(new Serie(123,"Friends", "Comedia", "Ingles", 500));
		DataLoader.listClientes.get(0).adicionarNaListaParaVer(new Serie(456,"Breaking bad", "Drama", "Ingles", 60));
		DataLoader.listClientes.get(0).adicionarNaListaParaVer(new Serie(789,"Game of Thrones", "Ficção", "Ingles", 100));
		
		System.out.println(DataLoader.listClientes.get(0).getListaParaVer());		
		
		DataLoader.listClientes.get(0).adicionarNaListaAssistidas(new Serie(111,"Vikings", "Ficção", "Escandinavo", 99));
		DataLoader.listClientes.get(0).adicionarNaListaAssistidas(new Serie(222,"The walking dead", "Ficção", "Ingles", 120));
		DataLoader.listClientes.get(0).adicionarNaListaAssistidas(new Serie(333,"Grays Anatomy", "Drama", "Ingles", 999));		
		
		System.out.println(DataLoader.listClientes.get(0).getListaJaVistas());		
		
		//Menu para chamar os métodos
		   Scanner scanner = new Scanner(System.in);

	        do {
	            System.out.println("Escolha uma opção:");
	            System.out.println("1 - ");
	            System.out.println("2 - ");
	            System.out.println("3 - ");
	            System.out.println("0 - Sair");

	            opcao = scanner.nextInt();

	            switch(opcao) {
	                case 1:
	                    
	                    break;
	                case 2:
	                    
	                    break;
	                case 3:
	                    
	                    break;
	                case 0:
	                    System.out.println("Saindo...");
	                    break;
	                default:
	                    System.out.println("Opção inválida!");
	            }
	        } while (opcao != 0);

	        scanner.close();
	}
}

