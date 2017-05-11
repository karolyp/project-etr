package hu.adatb.jetr;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TesztCucc {

	private JFrame frmJetr;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				TesztCucc window = new TesztCucc();
				window.frmJetr.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public TesztCucc() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJetr = new JFrame();
		frmJetr.setTitle("JETR");
		frmJetr.setBounds(100, 100, 400, 400);
		frmJetr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJetr.getContentPane().setLayout(null);

		JLabel lblUdv = new JLabel("Üdvözöljük!");
		lblUdv.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUdv.setBounds(134, 32, 114, 29);
		frmJetr.getContentPane().add(lblUdv);

		JPanel panel = new JPanel();
		panel.setBounds(97, 133, 193, 163);
		frmJetr.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblEha = new JLabel("EHA:");
		lblEha.setBounds(27, 43, 71, 14);
		panel.add(lblEha);

		JLabel lblJelsz = new JLabel("Jelszó:");
		lblJelsz.setBounds(27, 92, 71, 14);
		panel.add(lblJelsz);

		textField = new JTextField();
		textField.setBounds(97, 40, 86, 20);
		panel.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(97, 89, 86, 20);
		panel.add(passwordField);

		JButton btnBelps = new JButton("Belépés");
		btnBelps.setBounds(56, 129, 89, 23);
		panel.add(btnBelps);

		JLabel errorLabel = new JLabel("New label");
		errorLabel.setBounds(169, 307, 46, 14);
		frmJetr.getContentPane().add(errorLabel);
	}
}
