package controllers;

import java.util.ArrayList;

import models.Orcamento;
import models.dao.OrcamentoDAO;

public class OrcamentoProcess {

	// atributos
	public static ArrayList<Orcamento> orcamentos = new ArrayList<>();

	private static OrcamentoDAO od = new OrcamentoDAO();

	public static void abrir() {
		orcamentos = od.ler();
	}

	public static void adicionar() {
		od.escrever(orcamentos);
	}

}
