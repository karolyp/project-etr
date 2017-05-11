package hu.adatb.jetr.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = -2271818901148023358L;
	private JMenuBar menuBar;

	public MainWindow(String eha) {
		this.setTitle(eha);
		this.setSize(800, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setJMenuBar();
		this.setLayout(new BorderLayout(0, 0));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setJMenuBar() {
		this.menuBar = new JMenuBar();
		JMenu menu1 = new JMenu("Menüpont1");
		menu1.add(new JMenuItem("Almenüpont1"));

		JMenu menu2 = new JMenu("Kurzusok");
		menu2.add(new JMenuItem("Felvett kurzusok"));
		menu2.add(new JMenuItem("Kurzusok felvétele"));

		this.menuBar.add(menu1);
		this.menuBar.add(menu2);
		setJMenuBar(this.menuBar);
	}

}
