package hu.adatb.jetr.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CoursesView extends JFrame {
	private JScrollPane scrollPane;

	public CoursesView(String title) {
		this.setTitle(title);
		this.setSize(800, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout(0, 0));
		this.setVisible(true);
	}

	public void addScrollPane(JTable table) {
		this.scrollPane = new JScrollPane(table);
		this.add(scrollPane, BorderLayout.NORTH);
	}

	@Override
	public void setSize(int x, int y) {
		super.setSize(x, y);
		super.setLocationRelativeTo(null);
	}

	public void setApplyButton() {
		JButton applyBtn = new JButton("Kiválasztottak felvétele");
		this.add(applyBtn, BorderLayout.SOUTH);
	}

}
