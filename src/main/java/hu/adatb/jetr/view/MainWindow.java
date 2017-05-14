package hu.adatb.jetr.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = -2271818901148023358L;
	private JMenuBar menuBar;

	public MainWindow(String eha) {
		setBackground(Color.WHITE);
		this.setTitle(eha);
		this.setSize(800, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setJMenuBar();
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().setBackground(Color.WHITE);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setJMenuBar() {
		this.menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		JMenu menu1 = new JMenu("Menü");
		JMenuItem menuItem = new JMenuItem("Kijelentkezés");
		menuItem.addActionListener(arg0 -> {

		});
		menu1.add(menuItem);

		JMenu menu2 = new JMenu("Kurzusok");
		menu2.add(new JMenuItem("Felvett kurzusok"));
		menu2.add(new JMenuItem("Kurzusok felvétele"));

		this.menuBar.add(menu1);
		this.menuBar.add(menu2);
		setJMenuBar(this.menuBar);

		JMenu mnVizsgk = new JMenu("Vizsgák");
		menuBar.add(mnVizsgk);

		JMenuItem mntmFelvettVizsgk = new JMenuItem("Felvett vizsgák");
		mnVizsgk.add(mntmFelvettVizsgk);

		JMenuItem mntmAvaliableExams = new JMenuItem("Vizsgák felvétele");
		mnVizsgk.add(mntmAvaliableExams);
	}

}
