package entities;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataLoader {
	
	public static List<Serie> listSeries = new ArrayList<>();
	public static List<Cliente> listClientes = new ArrayList<>(); 

    /**
     * Carrega a lista de séries a partir de um arquivo.
     * @param fileName o nome do arquivo contendo as séries.
     * @return uma lista de séries.
     */
    public static List<Serie> carregarSeries(String fileName) {
        List<Serie> series = new ArrayList<>();

        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(";");

                String nome = fields[0];
                String genero = fields[1];
                String idioma = fields[2];
                int quantidadeEpisodios = Integer.parseInt(fields[3]);

                Serie serie = new Serie(nome, genero, idioma, quantidadeEpisodios);
                series.add(serie);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + fileName);
        }

        return series;
    }

	static String seriesSource = "C:\\Users\\Pedro\\Desktop\\Pro3\\lpm_projetos_3_4_5-grupo4-g2\\codigo\\Project3\\src\\teste.csv";

	static File sourceFile = new File(seriesSource);
	static String sourceFolderStr = sourceFile.getParent();		
	static boolean success = new File(sourceFolderStr + "\\out").mkdir();
	static String targetFileStr = sourceFolderStr + "\\out\\ListaDeFilmes.csv";
    
    public static void caregarSeries2Pedro() {
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
					bw.write("LISTA DE SERIES");
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
		
    	
    }
    
    
    public static void carregarClientes() {
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
    }
    
    
    /**
     * Carrega a lista de clientes a partir de um arquivo.
     * @param fileName o nome do arquivo contendo os clientes.
     * @return uma lista de clientes.
     */
    public static List<Cliente> carregarClientes(String fileName) {
        List<Cliente> clientes = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine(); // ler a primeira linha
    
            while (line != null) {
                String[] campos = line.split(";");
    
                // extrair os dados do cliente a partir dos campos
                int id = Integer.parseInt(campos[0]);
                String nome = campos[1];
                String email = campos[2];
                String telefone = campos[3];
    
                // criar o objeto Cliente e adicioná-lo à lista
                Cliente cliente = new Cliente(nome, email);
                clientes.add(cliente);
    
                line = reader.readLine(); // ler a próxima linha
            }
    
            reader.close(); // fechar o arquivo
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    
        return clientes;
    }

    /**
     * Registra a audiência de uma série a partir de um arquivo.
     * @param fileName o nome do arquivo contendo as informações de
     */

     public static void carregarAudiencia(String fileName, List<Serie> series) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(";");
                String nome = tokens[0];
                double audiencia = Double.parseDouble(tokens[1]);
                for (Serie serie : series) {
                    if (serie.getNome().equals(nome)) {
                        serie.setAudiencia(audiencia);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + fileName);
        } catch (NumberFormatException e) {
            System.out.println("Erro ao ler a audiência do arquivo: " + e.getMessage());
        }
    }
    
}          
