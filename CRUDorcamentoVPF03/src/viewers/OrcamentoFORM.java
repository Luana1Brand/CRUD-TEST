package viewers;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controllers.OrcamentoProcess;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import controllers.OrcamentoProcess;
import models.Orcamento;


public class OrcamentoFORM  extends JFrame implements ActionListener{
	
	
	private static final long serialVersionUID = 3L;
	
	private JPanel panel;
	
	
	private JLabel Lid, Lfornecedor, Lproduto, Lpreco;
	
	private JTextField Tid, Tfornecedor, Tproduto, Tpreco;
	
	
	private JButton adicionar, alterar, excluir, buscar;
	
	
	private JScrollPane rolagem;
	
	private JTextArea texto;
	
	
	
	
	
	 OrcamentoFORM() {
		setTitle("Orçamentos");
		//setTitle.setBackground(new Color(213, 233, 200));
		setBounds(350, 200, 600, 480);
		panel = new JPanel();
		panel.setBackground(new Color(255,240,255));
		setContentPane(panel);
		setLayout(null);

	
		// id
		Lid = new JLabel("Id:");
		Lid.setBounds(10, 10, 120, 30);
		panel.add(Lid);
		
		
		Tid = new JTextField("Digite o seu ID");
		Tid.setBounds(50, 15, 300, 25);
		panel.add(Tid);

		
		
	}
	
	public static void main(String[] args) {
		OrcamentoProcess.abrir();
		new OrcamentoFORM().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
