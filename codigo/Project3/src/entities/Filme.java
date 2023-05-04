package entities;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Filme {

	private int id;
	private String nome;
	private LocalDate dataLancamento;
	private int duracaoMinutos;

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * Construtor da classe Filme que recebe o id, o nome, a data de lançamento e a
	 * duração do filme e os atribui ao objeto instanciado.
	 * 
	 * @param id, o id do filme.
	 * @param nome, o nome do filme.
	 * @param dataLancamento, a data de lançamento do filme.
	 * @param duracaoMinutos, a duração do filme em minutos.
	 */
	public Filme(int id, String nome, LocalDate dataLancamento, int duracaoMinutos) {
		this.id = id;
		this.nome = nome;
		this.dataLancamento = dataLancamento;
		this.duracaoMinutos = duracaoMinutos;
	}

	// Abaixo estão os getters e setters necessários
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
