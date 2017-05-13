package hu.adatb.jetr.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ExamsView extends JFrame {
	private static final long serialVersionUID = -3510423666956338923L;

	private JScrollPane scrollPane;

	public ExamsView(String title) {
		this.setTitle(title);
		this.setSize(1000, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout(0, 0));
		this.setVisible(true);
	}

	public void addScrollPane(JTable table) {
		table.repaint();
		this.scrollPane = new JScrollPane(table);
		this.add(scrollPane, BorderLayout.NORTH);
	}

	@Override
	public void setSize(int x, int y) {
		super.setSize(x, y);
		super.setLocationRelativeTo(null);
	}

}

