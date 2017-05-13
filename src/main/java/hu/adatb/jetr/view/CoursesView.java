package hu.adatb.jetr.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CoursesView extends JFrame {
	private static final long serialVersionUID = -3510423666956338923L;

	private JScrollPane scrollPane;
	private JButton applyBtn;

	public CoursesView(String title) {
		this.setTitle(title);
		this.setSize(1000, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout(0, 0));
		this.setVisible(true);
		this.applyBtn = new JButton("Kiválasztottak felvétele");
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

	public void setApplyButton() {
		this.add(applyBtn, BorderLayout.SOUTH);
	}

	public void addApplyButtonListener(ActionListener al) {
		this.applyBtn.addActionListener(al);
	}

}
