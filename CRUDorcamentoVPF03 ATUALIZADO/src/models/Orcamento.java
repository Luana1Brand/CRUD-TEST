package models;

public class Orcamento {

	// Atribuutos
	private int id;
	private String fornecedor;
	private String produto;
	private double preco;
	private boolean maisBarato;

	/*
	 * METODOS: + CONTRUTORES para contruir objetos
	 * 
	 * + GETTERS && SETTERS um m�todo getter retorna seu valor, enquanto um m�todo
	 * setter o define ou atualiza.
	 * 
	 * + HASH AND eQUALS HASH cria um c�digo para a informa��o fazendo ser rapido e
	 * simple encrotra-la dps eQual: se na hora de pesquisar pelo numero hash tiver
	 * dois ou mais numeros iguais (num mesmo bucket) o eQuals tem a fun��o de os
	 * analizar mais minuciosamente
	 * 
	 * +toSTRING(): STRING O m�todo toString retorna uma representa��o string de um
	 * objeto. Na classe Object, este m�todo est� escrito de maneira a retornar o
	 * nome da classe da qual o objeto � inst�ncia,
	 * 
	 * 
	 */

	// construtores
	public Orcamento(int id, String fornecedor, String produto, double preco, boolean maisBarato) {
		this.id = id;
		this.fornecedor = fornecedor;
		this.produto = produto;
		this.preco = preco;
		this.maisBarato = maisBarato;
	}

	// para a tabela ficar certa
	public Orcamento(String linha) {
		this.id = Integer.parseInt(linha.split(";")[0]);
		this.fornecedor = linha.split(";")[1];
		this.produto = linha.split(";")[2];
		this.preco = Double.parseDouble(linha.split(";")[3]);
		this.maisBarato = Boolean.parseBoolean(linha.split(";")[4]);
	}

	// getters and setters
	public String getId(String s) {
		return String.valueOf(id);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public double getPreco() {
		return preco;
	}

	public String getPreco(String s) {
		return String.format("%.2f", preco);
		// "%.2f", � a formata��o para duas casas decimais :)

	}

	public boolean isMaisBarato() {
		return maisBarato;
	}

	public void setMaisBarato(boolean maisBarato) {
		this.maisBarato = maisBarato;
	}

	public String comprar() {
		if (maisBarato) {
			return "Comprar";
		}
		return null;
	}

	// toString
	@Override
	public String toString() {
		return id + ";" + fornecedor + ";" + produto + ";" + preco + ";" + comprar();
	}

	public String toCSV() {
		return id + ";" + fornecedor + ";" + produto + ";" + preco + ";" + maisBarato +"\r\n";
	}

}