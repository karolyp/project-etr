package hu.adatb.jetr.view;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.SystemColor;

public class MainWindow extends JFrame {
	
		public JButton btnKurzusFelvetel;
		public JButton btnFelvettKurzusok;
		public JButton btnFelvettVizsgk;
		public JButton btnVizsgkFelvtele;
		public JButton btnOrarend;
		public JButton btnKijelentkezes;
		
	public MainWindow(String eha) {
		getContentPane().setLayout(null);
	
		btnKurzusFelvetel = new JButton("Kurzusok felvétele");
		btnKurzusFelvetel.setBounds(27, 70, 174, 43);
		getContentPane().add(btnKurzusFelvetel);
		
		btnFelvettKurzusok = new JButton("Felvett kurzusok");
		btnFelvettKurzusok.setBounds(27, 11, 174, 43);
		getContentPane().add(btnFelvettKurzusok);
		
		btnFelvettVizsgk = new JButton("Felvett vizsgák");
		btnFelvettVizsgk.setBounds(234, 11, 174, 43);
		getContentPane().add(btnFelvettVizsgk);
		
		btnVizsgkFelvtele = new JButton("Vizsgák felvétele");
		btnVizsgkFelvtele.setBounds(234, 70, 174, 43);
		getContentPane().add(btnVizsgkFelvtele);
		
		
		
		btnOrarend = new JButton("Órarend");
		btnOrarend.setBounds(127, 161, 174, 43);
		getContentPane().add(btnOrarend);
		
		btnKijelentkezes = new JButton("Kijelentkezés");
		btnKijelentkezes.setBackground(new Color(255, 99, 71));
		btnKijelentkezes.setForeground(Color.BLACK);
		btnKijelentkezes.setBounds(150, 215, 137, 33);
		getContentPane().add(btnKijelentkezes);
		this.setTitle(eha);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setSize(440, 320);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
