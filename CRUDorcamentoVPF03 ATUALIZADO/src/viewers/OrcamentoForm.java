package viewers;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;

import controllers.OrcamentoProcess;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;

import controllers.OrcamentoProcess;
import models.Orcamento;

public class OrcamentoFORM extends JFrame implements ActionListener {

	private static final long serialVersionUID = 3L;

	private JPanel panel;

	private JLabel Lid, Lfornecedor, Lproduto, Lpreco;

	private JTextField Tid, Tfornecedor, Tproduto, Tpreco;

	private JButton cadastrar, buscar, atualizar, excluir;

	private JTable tableOrca;
	private DefaultTableModel tableDefault;
	private JScrollPane rolagem;

	private final Locale BRASIL = new Locale("pt", "BR");
	private DecimalFormat df = new DecimalFormat("#.00");

	OrcamentoFORM() {
		setTitle("Orçamentos");
		// setTitle.setBackground(new Color(213, 233, 200));
		setBounds(350, 200, 600, 340);
		panel = new JPanel();
		panel.setBackground(new Color(255, 240, 255));
		setContentPane(panel);
		setLayout(null);

		// id
		Lid = new JLabel("Id:");
		Lid.setBounds(10, 10, 120, 30);
		panel.add(Lid);

		Tid = new JTextField("Digite o ID do orçamento");
		Tid.setBounds(90, 15, 300, 25);
		panel.add(Tid);

		// fornecedor
		Lfornecedor = new JLabel("fornecedor");
		Lfornecedor.setBounds(10, 35, 120, 30);
		panel.add(Lfornecedor);

		Tfornecedor = new JTextField("Digite o nome do fornecedor");
		Tfornecedor.setBounds(90, 35, 300, 25);
		panel.add(Tfornecedor);

		// produto
		Lproduto = new JLabel("produto");
		Lproduto.setBounds(10, 55, 120, 30);
		panel.add(Lproduto);

		Tproduto = new JTextField("Digite o nome do produto");
		Tproduto.setBounds(90, 60, 300, 25);
		panel.add(Tproduto);

		// preco
		Lpreco = new JLabel("preco");
		Lpreco.setBounds(10, 85, 120, 30);
		panel.add(Lpreco);

		Tpreco = new JTextField("Digite preco");
		Tpreco.setBounds(90, 85, 300, 25);
		panel.add(Tpreco);

		// BOTÕES
		cadastrar = new JButton("Cadastrar");
		cadastrar.setBounds(450, 15, 100, 25);
		panel.add(cadastrar);

		buscar = new JButton("Buscar");
		buscar.setBounds(450, 40, 100, 25);
		panel.add(buscar);

		atualizar = new JButton("Atualizar");
		atualizar.setBounds(450, 65, 100, 25);
		panel.add(atualizar);

		excluir = new JButton("Excluir ");
		excluir.setBounds(450, 90, 100, 25);
		panel.add(excluir);

		cadastrar.addActionListener(this);
		buscar.addActionListener(this);
		atualizar.addActionListener(this);
		excluir.addActionListener(this);
		
		
		
		tabela();
		
	}

	
	
	/*
	 * private JTable tableOrca;
	private DefaultTableModel tableDefault;
	private JScrollPane rolagem;
	 */
	private void tabela() {
		tableOrca = new JTable();
		tableDefault = new DefaultTableModel();
		tableDefault.addColumn("ID");
		tableDefault.addColumn("Fornecedor");
		tableDefault.addColumn(" Produto");
		tableDefault.addColumn("Preco");
		tableDefault.addColumn("Comprar");
		if (OrcamentoProcess.orcamentos.size() != 0) {
			preencherTabela();
		}
		tableOrca = new JTable(tableDefault);
		tableOrca.setEnabled(false);
		rolagem = new JScrollPane(tableOrca);
		rolagem.setBounds(10, 130, 565, 160);
		rolagem.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		panel.add(rolagem);
	}

	private void preencherTabela() {
		int totLinhas = tableDefault.getRowCount();
		if (tableDefault.getRowCount() > 0) {
			for (int i = 0; i < totLinhas; i++) {
				tableDefault.removeRow(0);
			}
		}
		for (Orcamento orca : OrcamentoProcess.orcamentos) {
			tableDefault.addRow(new String[] { orca.getId("s"), orca.getFornecedor(), orca.getProduto(),
					orca.getPreco("s"), orca.comprar() });
		}
	}

	// Tid, Tfornecedor, Tproduto, Tpreco
	private void limparCampos() {
		Tid.setText(null);
		Tfornecedor.setText(null);
		Tproduto.setText(null);
		Tpreco.setText(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cadastrar) {
			adicionar();
		}
		if (e.getSource() == buscar) {
			buscar();
		}
		if (e.getSource() == atualizar) {
			atualizar();
		}
		if (e.getSource() == excluir) {
			excluir();
		}
	}

	
	
	
	
	private boolean comparaPreco() {
		double preco = 0;
		boolean comprar = true;
		try {
			preco = Double.parseDouble(df.parse(Tpreco.getText()).toString());
		}catch(ParseException e) {
			System.out.println(e);
		}
		for(int i = 0; i < OrcamentoProcess.orcamentos.size(); i++) {
			if(OrcamentoProcess.orcamentos.get(i).getProduto().contains(Tproduto.getText())) {
				if(OrcamentoProcess.orcamentos.get(i).getPreco() > preco) {
					OrcamentoProcess.orcamentos.get(i).setMaisBarato(false);
					comprar = true;
				}else if(OrcamentoProcess.orcamentos.get(i).getPreco() < preco){
					OrcamentoProcess.orcamentos.get(i).setMaisBarato(true);
					comprar = false;
				}
			}
			
		}
		return comprar;
	}
	
	private void adicionar() {
		if (Tid.getText().length() != 0 && Tfornecedor.getText().length() != 0 && Tproduto.getText().length() != 0
				&& Tpreco.getText().length() != 0) {
			df.setCurrency(Currency.getInstance(BRASIL));
			double preco = 0;
			boolean comprar = true;
			try {
				preco = Double.parseDouble(df.parse(Tpreco.getText()).toString());
			} catch (ParseException e) {
				System.out.println(e);
			}
			for (int i = 0; i < OrcamentoProcess.orcamentos.size(); i++) {
				if (OrcamentoProcess.orcamentos.get(i).getProduto().contains(Tproduto.getText())) {
					if (OrcamentoProcess.orcamentos.get(i).getPreco() > preco) {
						OrcamentoProcess.orcamentos.get(i).setMaisBarato(false);
						comprar = true;
					} else {
						comprar = false;
					}
				}

			}
			OrcamentoProcess.orcamentos.add(new Orcamento(Integer.parseInt(Tid.getText()), Tfornecedor.getText(),
					Tproduto.getText(), preco, comprar));

			preencherTabela();
			limparCampos();
			OrcamentoProcess.adicionar();

		}
	}

	
	
	
	
	
	
	private void buscar() {
		String entrada = JOptionPane.showInputDialog(this, "Digite o ID");
		if(entrada != null) {
			int id = (Integer.parseInt(entrada));
			for(int i = 0; i < OrcamentoProcess.orcamentos.size(); i++) {
				if(OrcamentoProcess.orcamentos.get(i).getId() == id){
					Tid.setText(OrcamentoProcess.orcamentos.get(i).getId("s"));
					Tfornecedor.setText(OrcamentoProcess.orcamentos.get(i).getFornecedor());
					Tproduto.setText(OrcamentoProcess.orcamentos.get(i).getProduto());
					Tpreco.setText(OrcamentoProcess.orcamentos.get(i).getPreco("s"));
			
					
					
					
					//private JButton cadastrar, buscar, atualizar, excluir;
					cadastrar.setEnabled(false);
					atualizar.setEnabled(true);
					excluir.setEnabled(true);
					OrcamentoProcess.adicionar();
				}
			}
		}
	}
	
	
	
	private void excluir() {
		int id = (Integer.parseInt(Tid.getText()));
		for(int i = 0; i < OrcamentoProcess.orcamentos.size(); i++) {
			if(OrcamentoProcess.orcamentos.get(i).getId() == id){
		OrcamentoProcess.orcamentos.remove(i);
			}
		}
		
		for(int i = 0; i < OrcamentoProcess.orcamentos.size(); i++) {
			for(int j = 0; j < OrcamentoProcess.orcamentos.size(); j++) {
				if(OrcamentoProcess.orcamentos.get(i).getProduto().contains(Tproduto.getText())) {
					if(OrcamentoProcess.orcamentos.get(i).getPreco() < OrcamentoProcess.orcamentos.get(j).getPreco()) {
						OrcamentoProcess.orcamentos.get(i).setMaisBarato(true);
						OrcamentoProcess.orcamentos.get(j).setMaisBarato(false);
					}
				}
				
			}
		}
		
		preencherTabela();
		limparCampos();
		
		cadastrar.setEnabled(true);
		atualizar.setEnabled(false);
		excluir.setEnabled(false);
		OrcamentoProcess.adicionar();
	

	}

	private void atualizar() {
		
		int id = Integer.parseInt(Tid.getText()) -1;
		
		double preco = 0;
		boolean comprar = comparaPreco();
		
		try {
			preco = Double.parseDouble(df.parse(Tpreco.getText()).toString());
		}catch(ParseException e) {
			System.out.println(e);
		}
		OrcamentoProcess.orcamentos.set(id, new Orcamento(Integer.parseInt(Tid.getText()), Tfornecedor.getText(), Tproduto.getText(),  preco, comparaPreco()));
		
		cadastrar.setEnabled(true);
		atualizar.setEnabled(false);
		excluir.setEnabled(false);
		
		preencherTabela();
		limparCampos();
		OrcamentoProcess.adicionar();
	}


	
	

	public static void main(String[] args) {
		OrcamentoProcess.abrir();
		new OrcamentoFORM().setVisible(true);
	}

}
