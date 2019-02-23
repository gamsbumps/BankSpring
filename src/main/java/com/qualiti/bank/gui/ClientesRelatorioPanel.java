package com.qualiti.bank.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.qualiti.bank.exceptions.BancoException;
import com.qualiti.bank.facade.IFachada;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Component
@Scope("prototype")
public class ClientesRelatorioPanel extends JPanel {
	
	@Autowired
	private IFachada fachada;
	private JButton btnGerarRelatorio;
	private JTextArea gerarRelatorioTxt;

	/**
	 * Create the panel.
	 */
	public ClientesRelatorioPanel() {
		setLayout(null);
		
		btnGerarRelatorio = new JButton("Gerar Relatorio");
		btnGerarRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String relatorio;
				try {
					relatorio = fachada.nomesClientesOrdemAlfabetica();
					gerarRelatorioTxt.setText(relatorio);
				} catch (BancoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnGerarRelatorio.setBounds(43, 33, 112, 23);
		add(btnGerarRelatorio);
		
		gerarRelatorioTxt = new JTextArea();
		gerarRelatorioTxt.setBounds(43, 87, 342, 189);
		add(gerarRelatorioTxt);

	}
}
