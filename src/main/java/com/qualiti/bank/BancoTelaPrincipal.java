package com.qualiti.bank;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.qualiti.bank.gui.ClienteCadastrarPanel;
import com.qualiti.bank.gui.ContaCadastrarPanel;
import com.qualiti.bank.gui.MovimentacaoPanel;

@SpringBootApplication
public class BancoTelaPrincipal implements CommandLineRunner {
	
	private static ConfigurableApplicationContext context;

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		context = 
				new SpringApplicationBuilder(BancoTelaPrincipal.class)
				.headless(false).run(args);
	}

	/**
	 * Create the application.
	 */
	public BancoTelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);
		
		JMenuItem mntmCadastrar = new JMenuItem("Cadastrar");
		mntmCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//para aparecer na tela(frame) principal
				
				ClienteCadastrarPanel cadastrarPanel = new ClienteCadastrarPanel();
				frame.setContentPane(cadastrarPanel);;
				frame.revalidate();
			}
		});
		mnClientes.add(mntmCadastrar);
		
		JMenu mnContas = new JMenu("Contas");
		menuBar.add(mnContas);
		
		JMenuItem mntmCadastrar_1 = new JMenuItem("Cadastrar");
		mntmCadastrar_1.addActionListener(new ActionListener() {//para aparecer na tela(frame) principal
			public void actionPerformed(ActionEvent e) {
				ContaCadastrarPanel cadastrarpanel1 = new ContaCadastrarPanel();
				frame.setContentPane(cadastrarpanel1);
				frame.revalidate();
			}
		});
		mnContas.add(mntmCadastrar_1);
		
		JMenu mnTransaes = new JMenu("Transa\u00E7\u00F5es");
		menuBar.add(mnTransaes);
		
		JMenuItem mntmMovimentaes = new JMenuItem("Movimenta\u00E7\u00F5es");
		mntmMovimentaes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MovimentacaoPanel movimentacoespanel = new MovimentacaoPanel();
				frame.setContentPane(movimentacoespanel);
				frame.revalidate();
			}
		});
		mnTransaes.add(mntmMovimentaes);
		
		JMenuItem mntmExtrato = new JMenuItem("Extrato");
		mnTransaes.add(mntmExtrato);
		
		frame.setLocationRelativeTo(null);
	}

	@Override
	public void run(String... args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BancoTelaPrincipal window = context.getBean(BancoTelaPrincipal.class);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
