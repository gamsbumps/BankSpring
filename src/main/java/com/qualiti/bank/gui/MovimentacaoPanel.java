package com.qualiti.bank.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qualiti.bank.exceptions.BancoException;
import com.qualiti.bank.facade.IFachada;

@Component
public class MovimentacaoPanel extends JPanel {
	private JTextField numeroTxt;
	private JTextField numeroDestinoTxt;
	private JTextField valorTxt;
	private JLabel lblNmeroDeOrigem;
	private JLabel lblTipo;
	private JLabel lblValor;
	private JComboBox<String> tipoCb;
	private JLabel lblNmeroDeDestino;
	private JButton btnConfirmar;
	
	@Autowired
	private IFachada fachada;

	/**
	 * Create the panel.
	 */
	public MovimentacaoPanel() {
		setLayout(null);

		lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(29, 37, 31, 14);
		add(lblTipo);
		
		numeroDestinoTxt = new JTextField();
		numeroDestinoTxt.setEnabled(false);
		numeroDestinoTxt.setBounds(133, 122, 97, 20);
		add(numeroDestinoTxt);
		numeroDestinoTxt.setColumns(10);

		lblNmeroDeOrigem = new JLabel("N\u00FAmero de origem:");
		lblNmeroDeOrigem.setBounds(29, 77, 91, 14);
		add(lblNmeroDeOrigem);

		lblNmeroDeDestino = new JLabel("N\u00FAmero de destino:");
		lblNmeroDeDestino.setEnabled(false);
		lblNmeroDeDestino.setBounds(29, 125, 94, 14);
		add(lblNmeroDeDestino);

		lblValor = new JLabel("Valor:");
		lblValor.setBounds(92, 180, 31, 14);
		add(lblValor);

		tipoCb = new JComboBox<>();
		tipoCb.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
				String tipo = (String) tipoCb.getSelectedItem();
				
				if(tipo.equals("Transfer�ncia")){
					lblNmeroDeDestino.setEnabled(true);
					numeroDestinoTxt.setText("");
					numeroDestinoTxt.setEnabled(true);
				}else{
					lblNmeroDeDestino.setEnabled(false);
					numeroDestinoTxt.setText("");
					numeroDestinoTxt.setEnabled(false);
					
				}
				
			}
		});

		tipoCb.addItem("");
		tipoCb.addItem("Credito");
		tipoCb.addItem("Debito");
		tipoCb.addItem("Transfer�ncia");

		tipoCb.setBounds(70, 37, 100, 20);
		add(tipoCb);

		numeroTxt = new JTextField();
		numeroTxt.setBounds(130, 74, 100, 20);
		add(numeroTxt);
		numeroTxt.setColumns(10);

		

		valorTxt = new JTextField();
		valorTxt.setBounds(133, 177, 97, 20);
		add(valorTxt);
		valorTxt.setColumns(10);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipo = (String) tipoCb.getSelectedItem();
				String numero = numeroTxt.getText();
				String valorTexto = valorTxt.getText();

				double valor = Double.parseDouble(valorTexto);

				if (tipo.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Informe o tipo de movimenta��o", "Movimenta��o conta",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				if (tipo.equals("Credito")) {

					try {
						fachada.creditar(numero, valor);
						JOptionPane.showMessageDialog(null, "Credito efetuado com sucesso", "Movimenta��o conta",
								JOptionPane.INFORMATION_MESSAGE);

						tipoCb.setSelectedItem("");
						numeroTxt.setText("");
						valorTxt.setText("");

					} catch (BancoException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Movimenta��o conta",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}

				}

				if (tipo.equals("Debito")) {
					try {
						fachada.debitar(numero, valor);
						JOptionPane.showMessageDialog(null, "Debito efetuado com sucesso", "Movimenta��o conta",
								JOptionPane.INFORMATION_MESSAGE);

						tipoCb.setSelectedItem("");
						numeroTxt.setText("");
						valorTxt.setText("");

					} catch (BancoException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Movimenta��o conta",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}

				if (tipo.equals("Transfer�ncia")) {
					
					
					try {
						
						String numeroDestino = numeroDestinoTxt.getText();
						
						fachada.
						transferir(numero, numeroDestino, valor);
						JOptionPane.showMessageDialog(null,
								"Transfer�ncia efetuada com sucesso",
								"Movimenta��o conta",
								JOptionPane.INFORMATION_MESSAGE);

						tipoCb.setSelectedItem("");
						numeroTxt.setText("");
						valorTxt.setText("");
						numeroDestinoTxt.setText("");
					} catch (BancoException e1) {
						JOptionPane.showMessageDialog(null,
								e1.getMessage(),
								"Movimenta��o conta",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			}

		});
		btnConfirmar.setBounds(246, 236, 89, 23);
		add(btnConfirmar);

	}
}
