package hu.adatb.jetr.controller;

import java.awt.Component;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import hu.adatb.jetr.model.HallgatoBean;
import hu.adatb.jetr.model.KurzusBean;
import hu.adatb.jetr.model.VizsgaBean;
import hu.adatb.jetr.view.CoursesView;
import hu.adatb.jetr.view.TimetableView;
import hu.adatb.jetr.view.tablemodel.ExamsTableModel;
import hu.adatb.jetr.view.tablemodel.TimetableTableModel;

public class TimetableViewController {
	
	public TimetableViewController(HallgatoBean hallgato, List<KurzusBean> kurzusok) {
		TimetableTableModel tm = new TimetableTableModel(kurzusok);
		JTable table = new JTable(tm);
		table.setPreferredScrollableViewportSize(new Dimension(800, 600));
		table.setFillsViewportHeight(true);

		TimetableView ttv = new TimetableView("Órarend");
		resizeColumnWidth(table);
		ttv.addScrollPane(table);
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

}
