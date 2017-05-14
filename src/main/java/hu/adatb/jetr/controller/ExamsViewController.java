package hu.adatb.jetr.controller;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import hu.adatb.jetr.model.HallgatoBean;
import hu.adatb.jetr.model.VizsgaBean;
import hu.adatb.jetr.view.CoursesView;
import hu.adatb.jetr.view.tablemodel.AvaliableExamsTableModel;
import hu.adatb.jetr.view.tablemodel.ButtonColumn;
import hu.adatb.jetr.view.tablemodel.ExamsTableModel;

public class ExamsViewController {
	// felvett vizsgakhoz
	public ExamsViewController(HallgatoBean hallgato, List<VizsgaBean> vizsgak) {
		ExamsTableModel tm = new ExamsTableModel(vizsgak);
		JTable table = new JTable(tm);
		table.setPreferredScrollableViewportSize(new Dimension(800, 600));
		table.setFillsViewportHeight(true);

		CoursesView rcv = new CoursesView("Felvett vizsgak");
		resizeColumnWidth(table);
		rcv.addScrollPane(table);

		Action remove = new AbstractAction() {

			private static final long serialVersionUID = -6595864376556810824L;

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = Integer.parseInt(e.getActionCommand());
				String[] options = new String[] { "Igen", "Nem" };
				int response = JOptionPane.showOptionDialog(null, "Biztos?", "Lejelentkezés",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);

				if (response == 0) {
					if (AppController.getExamDao().unsubExam(hallgato, tm.getValueAt(row))) {
						tm.refresh(AppController.getStudentDao().getFelvettVizsgak(hallgato));
					}
				}

			}
		};

		new ButtonColumn(table, remove, table.getColumnCount() - 1);
	}

	// overload miatt a megfelelőt fogja hívni
	public ExamsViewController(HallgatoBean hallgato, List<VizsgaBean> vizsgak, boolean b) {
		AvaliableExamsTableModel tm = new AvaliableExamsTableModel(vizsgak);
		JTable table = new JTable(tm);
		table.setPreferredScrollableViewportSize(new Dimension(800, 600));
		table.setFillsViewportHeight(true);

		CoursesView rcv = new CoursesView("Felvehető vizsgak");
		resizeColumnWidth(table);
		rcv.addScrollPane(table);

		Action add = new AbstractAction() {

			private static final long serialVersionUID = 2472965723925215141L;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (addNewExam(tm.getValueAt(Integer.parseInt(e.getActionCommand())), hallgato)) {
					JOptionPane.showMessageDialog(null, "Sikeres kurzusfelvétel!", "Yeah",
							JOptionPane.INFORMATION_MESSAGE);
					tm.refresh(AppController.getExamDao().getAvaliableExams(hallgato));
				}
			}

		};

		new ButtonColumn(table, add, table.getColumnCount() - 1);

	}

	public void resizeColumnWidth(JTable table) {
		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = 25;
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width + 1, width);
			}
			table.getColumnModel().getColumn(column).setPreferredWidth(width);
		}
	}

	public boolean addNewExam(VizsgaBean vb, HallgatoBean hallgato) {
		if (AppController.getExamDao().addNewExam(vb, hallgato)) {
			return true;
		}

		return false;
	}

}
