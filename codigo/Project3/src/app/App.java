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
import entities.Serie;

public class App {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Serie> listSeries = new ArrayList<>();
		List<Cliente> listClientes = new ArrayList<>(); 

		//System.out.println("Enter file path: ");
		String seriesSource = "C:\\Users\\Pedro\\Desktop\\Pro3\\lpm_projetos_3_4_5-grupo4-g2\\codigo\\Project3\\src\\teste.csv";

		File sourceFile = new File(seriesSource);
		String sourceFolderStr = sourceFile.getParent();
		
		boolean success = new File(sourceFolderStr + "\\out").mkdir();

		String targetFileStr = sourceFolderStr + "\\out\\ListaDeFilmes.csv";

		/**
		 * Cria um objeto BufferedReader para ler o arquivo de entrada CSV.
		 *
		 * @param sourceFileStr o caminho do arquivo de entrada CSV
		 * @return um novo objeto BufferedReader criado a partir do arquivo de entrada
		 */

		//Bloco responsavel por ler a lista de filmes a partir do testes.csv e preencher o arquivo summary com os dados necessarios sobre cada filme
		try (BufferedReader br = new BufferedReader(new FileReader(seriesSource))) {

			String itemCsv = br.readLine();
			while (itemCsv != null) {

				String[] fields = itemCsv.split(",");
				String nome = fields[0];
				String genero = fields[1];
				String idioma = fields[2];
				int quantidadeEpisodios = Integer.parseInt(fields[3]);
				listSeries.add(new Serie(nome, genero, idioma, quantidadeEpisodios));

				itemCsv = br.readLine();
			}

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))) {

				for (Serie serie : listSeries) {
					bw.write("LISTA DE FILMES");
					bw.newLine();
					bw.write(serie.getNome() + "," + serie.getIdioma() + "," + serie.getGenero());
					bw.newLine();
				}

				System.out.println(targetFileStr + " CREATED!");

			} catch (IOException e) {
				System.out.println("Error writing file: " + e.getMessage());
			}

		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}
		
		
		
		//Bloco responsavel por ler e armazenar os logins dos clientes
		String clientesSource = "C:\\Users\\Pedro\\Desktop\\Pro3\\lpm_projetos_3_4_5-grupo4-g2\\codigo\\Project3\\src\\ListaDeClientes.csv";
		File sourceCliente = new File(clientesSource);
		
		sourceFolderStr = sourceFile.getParent();
		
		success = new File(sourceFolderStr + "\\out").mkdir();

		targetFileStr = sourceFolderStr + "\\out\\ListaDeClientesFinal.csv";

		
		
		try (BufferedReader brCliente = new BufferedReader(new FileReader(sourceCliente))) {

			String itemCsv = brCliente.readLine();
			while (itemCsv != null) {

				String[] fields = itemCsv.split(",");
				String login = fields[0];
				listClientes.add(new Cliente(login));

				itemCsv = brCliente.readLine();
			}

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))) {

				for (Cliente cliente : listClientes) {
					bw.write("LISTA DE CLIENTES");
					bw.newLine();
					bw.write(cliente.getNomeDeUsuario());
					bw.newLine();
				}

				System.out.println(targetFileStr + " CREATED!");

			} catch (IOException e) {
				System.out.println("Error writing file: " + e.getMessage());
			}

		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}


		
		
		
		sc.close();
	}
}
