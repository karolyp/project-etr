package hu.adatb.jetr.view;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import hu.adatb.jetr.controller.LoginController;
import hu.adatb.jetr.services.FileReaderService;

public class LoginWindow extends JFrame {

	private static final long serialVersionUID = 5979677222202070395L;

	private JTextField eha;
	private JPasswordField jelszo;
	private JButton belep;
	private JLabel errorLabel;
	private JLabel lblCimer;

	public LoginWindow() {
		this.setSize(951, 491);
		this.setLocationRelativeTo(null);
		this.setTitle("JETR");
		this.setResizable(false);

		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.WHITE);

		JLabel lblUdv = new JLabel("Üdvözöljük!");
		lblUdv.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUdv.setBounds(40, 34, 114, 29);

		getContentPane().add(lblUdv);

		getContentPane().add(this.getLoginPanel());

		this.errorLabel = new JLabel("");
		this.errorLabel.setForeground(Color.RED);
		this.errorLabel.setBounds(100, 307, 250, 20);
		getContentPane().add(errorLabel);

		lblCimer = new JLabel("");
		try {
			lblCimer.setIcon(new ImageIcon(ImageIO.read(FileReaderService.getInputStream("szte_cimer.png"))));
		} catch (IOException e) {
			e.printStackTrace(); // logger
		}
		lblCimer.setBounds(0, 0, 946, 296);
		getContentPane().add(lblCimer);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new LoginController(this);
	}

	public JPanel getLoginPanel() {
		JPanel panel = new JPanel();
		panel.setBounds(275, 299, 423, 163);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);

		JLabel lblEha = new JLabel("EHA:");
		lblEha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEha.setBounds(51, 11, 99, 35);
		panel.add(lblEha);

		JLabel lblJelsz = new JLabel("Jelszó:");
		lblJelsz.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblJelsz.setBounds(51, 57, 99, 38);
		panel.add(lblJelsz);

		this.eha = new JTextField();
		this.eha.setBounds(160, 11, 197, 35);
		panel.add(eha);
		this.eha.setColumns(10);

		this.jelszo = new JPasswordField();
		this.jelszo.setBounds(160, 63, 197, 35);
		panel.add(jelszo);

		this.belep = new JButton("Belépés");
		this.belep.setBounds(160, 109, 197, 28);
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