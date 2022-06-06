package models.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import models.Orcamento;

public class OrcamentoDAO {

	/*
	 * Data Access Object - DAO buscar dados no banco de dados e tranformar os dador
	 * em objetos
	 */

	/*
	 * - BufferdReader - BufferedWritter -arquivo String
	 */

	private BufferedReader br;
	private BufferedWriter bw;
	private String DATA = "./DATA/orçamento.csv";

	/*
	 * +ler() +escrever()
	 */

	public ArrayList<Orcamento> ler() {
		ArrayList<Orcamento> linhas = new ArrayList<>();
		Orcamento orca;
		try {
			br = new BufferedReader(new FileReader(DATA));
			String linha = br.readLine();

			while (linha != null) {
				orca = new Orcamento(linha);
				linhas.add(orca);
				linha = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return linhas;
	}

	public void escrever(ArrayList<Orcamento> linhas) {
		try {
			bw = new BufferedWriter(new FileWriter(DATA));
			for (Orcamento orca : linhas) {
				bw.write(orca.toCSV());
			}
			bw.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
