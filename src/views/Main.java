package views;

import java.awt.EventQueue;

import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import views.Sobre;

import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.util.Date;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//os objetos abaixo ser�o manipulados pela classe Login
	JButton btnUsuarios;
	JButton btnRelatorios;
	JPanel panelUsuario;
	JLabel lblUsuario;
	private JButton btnFornecedor;
	private JLabel lblData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				Date data = new Date();
				DateFormat formatador = DateFormat.getDateInstance(DateFormat.LONG);
				lblData.setText(formatador.format(data));
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/img/favicon.png")));
		setTitle("Controle de estoque");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 562, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnUsuarios = new JButton("");
		btnUsuarios.setEnabled(false);
		btnUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuarios.setContentAreaFilled(false);
		btnUsuarios.setBorderPainted(false);
		btnUsuarios.setToolTipText("Usuarios");
		btnUsuarios.setIcon(new ImageIcon(Main.class.getResource("/img/users.png")));
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuarios usuarios = new Usuarios();
				usuarios.setVisible(true);
			}
		});
		btnUsuarios.setBounds(21, 11, 117, 114);
		contentPane.add(btnUsuarios);
		
		btnFornecedor = new JButton("");
		btnFornecedor.setContentAreaFilled(false);
		btnFornecedor.setBorderPainted(false);
		btnFornecedor.setVerifyInputWhenFocusTarget(false);
		btnFornecedor.setToolTipText("Fornecedores");
		btnFornecedor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFornecedor.setIcon(new ImageIcon(Main.class.getResource("/img/supliers.png")));
		btnFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setVisible(true);
			}
		});
		btnFornecedor.setBounds(202, 22, 117, 114);
		contentPane.add(btnFornecedor);
		
		JButton btnProdutos = new JButton("");
		btnProdutos.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Produtos produtos = new Produtos();
				produtos.setVisible(true);
				
			}
		});
		btnProdutos.setIcon(new ImageIcon(Main.class.getResource("/img/products.png")));
		btnProdutos.setToolTipText("Produtos");
		btnProdutos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnProdutos.setContentAreaFilled(false);
		btnProdutos.setBorderPainted(false);
		btnProdutos.setBounds(402, 11, 117, 125);
		contentPane.add(btnProdutos);
		
		JButton btnClientes = new JButton("");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clientes clientes = new Clientes();
				clientes.setVisible(true);
			
			}
		});
		btnClientes.setIcon(new ImageIcon(Main.class.getResource("/img/clientes.png")));
		btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClientes.setContentAreaFilled(false);
		btnClientes.setBorderPainted(false);
		btnClientes.setSelected(true);
		btnClientes.setToolTipText("Clientes");
		btnClientes.setBounds(21, 143, 128, 125);
		contentPane.add(btnClientes);
		
		btnRelatorios = new JButton("");
		btnRelatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Relatorios relatorios = new Relatorios();
				relatorios.setVisible(true);
			}
		});
		btnRelatorios.setIcon(new ImageIcon(Main.class.getResource("/img/report.png")));
		btnRelatorios.setBorderPainted(false);
		btnRelatorios.setContentAreaFilled(false);
		btnRelatorios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelatorios.setSelected(true);
		btnRelatorios.setToolTipText("Relat\u00F3rios");
		btnRelatorios.setEnabled(false);
		btnRelatorios.setBounds(202, 154, 117, 114);
		contentPane.add(btnRelatorios);
		
		JButton btnSobre = new JButton("");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});
		btnSobre.setIcon(new ImageIcon(Main.class.getResource("/img/3017957_examination_fill_inquiry_interrogation_investigation_icon.png")));
		btnSobre.setContentAreaFilled(false);
		btnSobre.setBorderPainted(false);
		btnSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSobre.setToolTipText("Sobre");
		btnSobre.setBounds(420, 171, 89, 84);
		contentPane.add(btnSobre);
		
		panelUsuario = new JPanel();
		panelUsuario.setBackground(SystemColor.textHighlight);
		panelUsuario.setBounds(0, 279, 546, 51);
		contentPane.add(panelUsuario);
		panelUsuario.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usu\u00E1rio:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 26, 55, 14);
		panelUsuario.add(lblNewLabel);
		
		lblUsuario = new JLabel("");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsuario.setBounds(68, 26, 190, 14);
		panelUsuario.add(lblUsuario);
		
		lblData = new JLabel("");
		lblData.setBounds(343, 11, 164, 30);
		panelUsuario.add(lblData);
	}//fim do construtor
}//fim do c�digo
