package hu.adatb.jetr.view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class RegisteredCoursesView extends JFrame {
	private JScrollPane scrollPane;

	public RegisteredCoursesView() {
		this.setTitle("Felvett kurzusok");
		this.setSize(800, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout(0, 0));
		this.setVisible(true);
	}

	public void addScrollPane(Component c) {
		this.scrollPane = new JScrollPane(c);
		this.add(scrollPane, BorderLayout.NORTH);
	}

}
