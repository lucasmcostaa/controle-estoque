package views;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JDialog;

import models.DAO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import java.awt.Cursor;

public class Usuarios extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuarios dialog = new Usuarios();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Usuarios() {
		setModal(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {

			}
		});
		setResizable(false);
		setTitle("Usu\u00E1rios");
		setBounds(100, 100, 534, 304);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(350, 27, 26, 14);
		getContentPane().add(lblNewLabel);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(386, 24, 79, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		JButton btnBuscar = new JButton("");
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setBorderPainted(false);
		btnBuscar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/search.png")));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarUsuario();
			}
		});
		btnBuscar.setBounds(216, 24, 70, 23);
		getContentPane().add(btnBuscar);

		JLabel lblNewLabel_1 = new JLabel("Usu\u00E1rio");
		lblNewLabel_1.setBounds(31, 75, 46, 14);
		getContentPane().add(lblNewLabel_1);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(87, 72, 378, 20);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setBounds(31, 27, 46, 14);
		getContentPane().add(lblNewLabel_2);

		txtLogin = new JTextField();
		txtLogin.setBounds(88, 24, 118, 20);
		getContentPane().add(txtLogin);
		txtLogin.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Senha");
		lblNewLabel_3.setBounds(31, 123, 46, 14);
		getContentPane().add(lblNewLabel_3);

		JButton btnAdicionar = new JButton("");
		btnAdicionar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/create.png")));
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarUsuario();
			}
		});
		btnAdicionar.setBounds(261, 185, 64, 64);
		getContentPane().add(btnAdicionar);

		JButton btnEditar = new JButton("");
		btnEditar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/update.png")));
		btnEditar.setContentAreaFilled(false);
		btnEditar.setBorderPainted(false);
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setToolTipText("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Verificar se o checkbox está selecionado
				//Para verificar se não está selecionado use NOT (!)
				if (chckSenha.isSelected()) {
					alterarUsuarioSenha();
				} else {
					alterarUsuario();
				}
			}
		});
		btnEditar.setBounds(335, 185, 64, 64);
		getContentPane().add(btnEditar);

		JButton btnExcluir = new JButton("");
		btnExcluir.setBorderPainted(false);
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setDefaultCapable(false);
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setIcon(new ImageIcon(Usuarios.class.getResource("/img/delete.png")));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirUsuario();
			}
		});
		btnExcluir.setBounds(409, 185, 64, 64);
		getContentPane().add(btnExcluir);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(87, 120, 263, 20);
		getContentPane().add(txtPassword);

		JLabel lblNewLabel_4 = new JLabel("Perfil");
		lblNewLabel_4.setBounds(31, 170, 38, 14);
		getContentPane().add(lblNewLabel_4);

		cboPerfil = new JComboBox();
		cboPerfil.setModel(new DefaultComboBoxModel(new String[] { "", "admin", "user" }));
		cboPerfil.setBounds(87, 166, 89, 22);
		getContentPane().add(cboPerfil);
		
		chckSenha = new JCheckBox("Alterar senha");
		chckSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//fazer o check na caixa Jcheckbox
				txtPassword.setEditable(true);
				txtPassword.setText(null);
				txtPassword.requestFocus();
				txtPassword.setBackground(Color.ORANGE);
			}
		});
		chckSenha.setVisible(false);
		chckSenha.setBounds(378, 119, 120, 23);
		getContentPane().add(chckSenha);

	}// fim do construtor

	DAO dao = new DAO();

	/**
	 * Pesquisa do usuario por id
	 */
	private void pesquisarUsuario() {
		// System.out.println("Teste do botão pesquisar usuário");
		String read = "select * from usuarios where login = ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setString(1, txtLogin.getText());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				txtUsuario.setText(rs.getString(2));
				txtId.setText(rs.getString(1));
				txtPassword.setText(rs.getString(4));
				cboPerfil.setSelectedItem(rs.getString(5));
				//exibir a caixa checkbox
				chckSenha.setVisible(true);
				//desativar a edição da senha
				txtPassword.setEditable(false);
			} else {
				JOptionPane.showMessageDialog(null, "Usuário inexistente");
				limparCampos();
				txtLogin.requestFocus();
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}// fim do método pesquisarUsuario()

	private void adicionarUsuario() {
		// validação
		// System.out.println("teste do botão adicionar");
		String create = "insert into usuarios (usuario,login,senha,perfil) values (?,?,md5(?),?)";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, txtUsuario.getText());
			pst.setString(2, txtLogin.getText());
			//captura segura de senha
			String capturaSenha = new String(txtPassword.getPassword());
			pst.setString(3, capturaSenha);
			pst.setString(4, cboPerfil.getSelectedItem().toString());
			//execução e confirmação da query
			int confirma = pst.executeUpdate();
			//apoio ao entendimento da lógica
			//System.out.println(confirma);
			if (confirma == 1) {
				JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso");
			} else {
				JOptionPane.showMessageDialog(null, "ERRO - Usuário não adicionado");
			}
			limparCampos();
			con.close();
			
		} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
			JOptionPane.showMessageDialog(null, "Usuário não adicionado - Login existente");
			txtLogin.setText(null);
			txtLogin.requestFocus();
		} catch (Exception e2) {
			System.out.println(e2);
		} 
	}
	
	private void alterarUsuario() {
		//System.out.println("teste do botão editar");
		//validação de campos
		String update = "update usuarios set usuario = ?, login = ?, perfil = ? where id = ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, txtUsuario.getText());
			pst.setString(2, txtLogin.getText());
			pst.setString(3, cboPerfil.getSelectedItem().toString());
			pst.setString(4, txtId.getText());
			//execução e confirmação
			int confirma = pst.executeUpdate();
			if (confirma == 1) {
				JOptionPane.showMessageDialog(null, "Dados do usuário alterados com sucesso");
			} else {
				JOptionPane.showMessageDialog(null, "ERRO - Dados do usuário não foram alterados");
			}
			limparCampos();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	private void alterarUsuarioSenha() {
		//System.out.println("teste do botão editar");
		//validação de campos
		String update = "update usuarios set usuario = ?, login = ?, senha = md5(?), perfil = ? where id = ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, txtUsuario.getText());
			pst.setString(2, txtLogin.getText());
			//captura segura de senha
			String capturaSenha = new String(txtPassword.getPassword());
			pst.setString(3, capturaSenha);
			pst.setString(4, cboPerfil.getSelectedItem().toString());
			pst.setString(5, txtId.getText());
			//execução e confirmação
			int confirma = pst.executeUpdate();
			if (confirma == 1) {
				JOptionPane.showMessageDialog(null, "Dados do usuário alterados com sucesso");
			} else {
				JOptionPane.showMessageDialog(null, "ERRO - Dados do usuário não foram alterados");
			}
			limparCampos();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	private void excluirUsuario() {
		//System.out.println("teste do botão excluir");
		//validação (confirmação de exclusão)
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste usuário ?", "ATENÇÃO!", JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			//lógica principal
			String delete = "delete from usuarios where id = ?";
			try {
				Connection con = dao.conectar();				
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				//executar e confirmar a exclusão
				int excluido = pst.executeUpdate();
				if (excluido == 1) {
					limparCampos();
					JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso");
				} else {
					JOptionPane.showMessageDialog(null, "Erro na exclusão do usuário");
				}
				con.close();
				
			} catch (Exception e) {
				System.out.println(e);
			}			
		}
	}
	
	private void limparCampos() {
		txtId.setText(null);
		txtUsuario.setText(null);
		txtLogin.setText(null);
		txtPassword.setText(null);
		txtPassword.setBackground(Color.WHITE);
		txtPassword.setEditable(true);
		cboPerfil.setSelectedItem("");
		chckSenha.setSelected(false); //desmarcar a caixa check
		chckSenha.setVisible(false);
		
	}

	private JTextField txtId;
	private JTextField txtUsuario;
	private JTextField txtLogin;
	private JPasswordField txtPassword;
	private JComboBox cboPerfil;
	private JCheckBox chckSenha;
}// fim do código
