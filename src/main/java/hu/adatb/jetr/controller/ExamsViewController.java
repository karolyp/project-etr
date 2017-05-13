package hu.adatb.jetr.controller;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import hu.adatb.jetr.model.HallgatoBean;
import hu.adatb.jetr.model.KurzusBean;
import hu.adatb.jetr.model.VizsgaBean;
import hu.adatb.jetr.view.CoursesView;
import hu.adatb.jetr.view.ExamsView;
import hu.adatb.jetr.view.tablemodel.AvaliableCoursesTableModel;
import hu.adatb.jetr.view.tablemodel.ExamsTableModel;
import hu.adatb.jetr.view.tablemodel.RegisteredCoursesTableModel;

public class ExamsViewController {
	
	
	//felvett vizsgakhoz
	public ExamsViewController(List<VizsgaBean> vizsgak) { 
		TableModel tm = new ExamsTableModel(vizsgak);
		JTable table = new JTable(tm);
		table.setPreferredScrollableViewportSize(new Dimension(800, 600));
		table.setFillsViewportHeight(true);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			int row = table.rowAtPoint(e.getPoint());
			int col = table.columnAtPoint(e.getPoint());
			}
		});
		
		CoursesView rcv = new CoursesView("Felvett vizsgak");
		resizeColumnWidth(table);
		rcv.addScrollPane(table);
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
