package hu.adatb.jetr.view.tablemodel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import hu.adatb.jetr.model.KurzusBean;
import hu.adatb.jetr.model.VizsgaBean;

public class ExamsTableModel extends AbstractTableModel{
	
	private static final long serialVersionUID = -6700628872664383385L;
	private Object[][] vizsgak;
	
	private final String[] header = { "Dátum", "Kurzuskód", "Jegy"};
	
	public ExamsTableModel(List<VizsgaBean> vizsgak) {
		this.vizsgak = new Object[vizsgak.size()][header.length];
		for (int i = 0; i < vizsgak.size(); i++) {
			this.vizsgak[i] = vizsgak.get(i).toArray();
		}

	}
	
	@Override
	public int getColumnCount() {
		return header.length;
	}

	@Override
	public int getRowCount() {
		return this.vizsgak.length;
	}

	@Override
	public String getColumnName(int col) {
		return this.header[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		return this.vizsgak[row][col];
	}

}
