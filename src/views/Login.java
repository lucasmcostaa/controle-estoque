package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.DAO;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblStatus;
	private JLabel lblNewLabel;
	private JTextField txtLogin;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/favicon.png")));
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 340, 219);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAcessar = new JButton("Acessar");
		btnAcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btnAcessar.setBounds(193, 129, 89, 23);
		contentPane.add(btnAcessar);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/dboff.png")));
		lblStatus.setBounds(10, 118, 48, 48);
		contentPane.add(lblStatus);

		lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(29, 33, 46, 14);
		contentPane.add(lblNewLabel);

		txtLogin = new JTextField();
		txtLogin.setBounds(82, 30, 189, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setBounds(29, 79, 46, 14);
		contentPane.add(lblNewLabel_1);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(82, 76, 189, 20);
		contentPane.add(txtSenha);

		// tecla Enter associada ao botão
		getRootPane().setDefaultButton(btnAcessar);

	}// fim do construtor

	DAO dao = new DAO();

	private void status() {
		try {
			// abrir a conexão
			Connection con = dao.conectar();
			if (con == null) {
				// escolher a imagem dboff
				lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/dboff.png")));
			} else {
				// escolher a imagem dbon
				lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/dbon.png")));
			}
			// Não esquecer de fechar a conexão
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void logar() {
		// validação da senha (captura segura)
		String capturaSenha = new String(txtSenha.getPassword());
		// validação de campos obrigatórios
		if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o seu login");
			txtLogin.requestFocus();
		} else if (capturaSenha.length() == 0) {
			JOptionPane.showMessageDialog(null, "Digite a sua senha");
			txtSenha.requestFocus();
		} else {
			// lógica principal (pesquisar login e senha correspondente)
			String read = "select * from usuarios where login = ? and senha = md5(?)";
			try {
				// abrir a conexão
				Connection con = dao.conectar();
				// preparar a query
				PreparedStatement pst = con.prepareStatement(read);
				// setar os argumentos(?,? login e senha)
				pst.setString(1, txtLogin.getText());
				pst.setString(2, capturaSenha);
				// Executar a query e executar o login se existir login e senha correspondente
				// no banco
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					Main main = new Main();
					// a linha abaixo captura o perfil do usuário
					String perfil = rs.getString(5);
					// comportamento de login em função do perfil
					if (perfil.equals("admin")) {
						main.setVisible(true);
						// Alterar a label da tela principal (inserir nome do usuário no rodapé)
						// apoio ao entendimento da lógica
						// System.out.println(rs.getString(2));
						main.lblUsuario.setText(rs.getString(2));
						// Habilitar os botões
						main.btnRelatorios.setEnabled(true);
						main.btnUsuarios.setEnabled(true);
						// alterar a cor do rodapé
						main.panelUsuario.setBackground(Color.RED);
						// fechar o JFrame
						this.dispose();
					} else {
						main.setVisible(true);
						// Alterar a label da tela principal (inserir nome do usuário no rodapé)
						// apoio ao entendimento da lógica
						// System.out.println(rs.getString(2));
						main.lblUsuario.setText(rs.getString(2));
						// fechar o JFrame
						this.dispose();
					}				
				} else {
					JOptionPane.showMessageDialog(null, "Login e/ou senha inválido(s)");
				}
				// encerrar a conexão
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

}// fim do código
