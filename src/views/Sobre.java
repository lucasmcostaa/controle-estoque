package views;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import javax.swing.JTextField;

public class Sobre extends JDialog {

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
					Sobre dialog = new Sobre();
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
	public Sobre() {
		setTitle("Sobre");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/pngegg.png")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Lucas Marcelino Costa dos Santos");
		lblNewLabel_1.setBounds(10, 112, 246, 14);
		getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setIcon(new ImageIcon(Sobre.class.getResource("/img/pngegg.png")));
		btnNewButton.setBounds(296, 11, 128, 98);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1_1 = new JLabel("Bem vindo ao meu portifolio de projetos.");
		lblNewLabel_1_1.setBounds(10, 53, 246, 14);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Projetos aprendidos na UC10 com o professor ");
		lblNewLabel_1_2.setBounds(10, 137, 295, 14);
		getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Jose de Assis no Senac Tatua\u00E9.");
		lblNewLabel_1_3.setBounds(10, 162, 246, 14);
		getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Licen\u00E7a MIT");
		lblNewLabel_1_1_1.setBounds(332, 112, 69, 14);
		getContentPane().add(lblNewLabel_1_1_1);

	}
}

