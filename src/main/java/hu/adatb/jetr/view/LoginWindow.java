package hu.adatb.jetr.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import hu.adatb.jetr.controller.LoginController;

public class LoginWindow extends JFrame {

	private JTextField eha;
	private JPasswordField jelszo;
	private JButton belep;
	private JLabel errorLabel;

	public LoginWindow() {
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setTitle("JETR");
		this.setResizable(false);

		this.setLayout(null);

		JLabel lblUdv = new JLabel("Üdvözöljük!");
		lblUdv.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUdv.setBounds(134, 32, 114, 29);

		this.add(lblUdv);

		this.add(this.getLoginPanel());

		this.errorLabel = new JLabel("");
		this.errorLabel.setForeground(Color.RED);
		this.errorLabel.setBounds(140, 307, 250, 20);
		this.add(errorLabel);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new LoginController(this);
	}

	public JPanel getLoginPanel() {
		JPanel panel = new JPanel();
		panel.setBounds(97, 133, 193, 163);
		panel.setLayout(null);

		JLabel lblEha = new JLabel("EHA:");
		lblEha.setBounds(27, 43, 71, 14);
		panel.add(lblEha);

		JLabel lblJelsz = new JLabel("Jelszó:");
		lblJelsz.setBounds(27, 92, 71, 14);
		panel.add(lblJelsz);

		this.eha = new JTextField();
		this.eha.setBounds(97, 40, 86, 20);
		panel.add(eha);
		this.eha.setColumns(10);

		this.jelszo = new JPasswordField();
		this.jelszo.setBounds(97, 89, 86, 20);
		panel.add(jelszo);

		this.belep = new JButton("Belépés");
		this.belep.setBounds(56, 129, 89, 23);
		panel.add(this.belep);

		return panel;
	}

	public JTextField getEhaField() {
		return eha;
	}

	public JPasswordField getJelszoField() {
		return jelszo;
	}

	public JButton getBelepButton() {
		return belep;
	}

	public JLabel getErrorLabel() {
		return this.errorLabel;
	}

}