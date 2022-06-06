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
	 * + GETTERS && SETTERS um método getter retorna seu valor, enquanto um método
	 * setter o define ou atualiza.
	 * 
	 * + HASH AND eQUALS HASH cria um código para a informação fazendo ser rapido e
	 * simple encrotra-la dps eQual: se na hora de pesquisar pelo numero hash tiver
	 * dois ou mais numeros iguais (num mesmo bucket) o eQuals tem a função de os
	 * analizar mais minuciosamente
	 * 
	 * +toSTRING(): STRING O método toString retorna uma representação string de um
	 * objeto. Na classe Object, este método está escrito de maneira a retornar o
	 * nome da classe da qual o objeto é instância,
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
		// "%.2f", é a formatação para duas casas decimais :)

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