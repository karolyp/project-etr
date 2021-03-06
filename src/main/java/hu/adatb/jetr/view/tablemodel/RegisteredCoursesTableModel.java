package hu.adatb.jetr.view.tablemodel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import hu.adatb.jetr.model.KurzusBean;

public class RegisteredCoursesTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -6700628872664383385L;
	private Object[][] kurzusok;
	private final String[] header = { "Kurzuskód", "Kurzus neve", "Típus", "Oktató", "Terem", "Félév", "Kredit",
			"Infosheet", "Lejelentkezés" };

	public void setData(List<KurzusBean> kurzusok) {
		this.kurzusok = new Object[kurzusok.size()][header.length];
		for (int i = 0; i < kurzusok.size(); i++) {
			this.kurzusok[i] = kurzusok.get(i).toArray();
		}
	}

	public RegisteredCoursesTableModel(List<KurzusBean> kurzusok) {
		this.setData(kurzusok);
	}

	@Override
	public int getColumnCount() {
		return header.length;
	}

	@Override
	public int getRowCount() {
		return this.kurzusok.length;
	}

	@Override
	public String getColumnName(int col) {
		return this.header[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		return this.kurzusok[row][col];
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return (col >= this.kurzusok[row].length - 2) ? true : false;

	}

	public void removeRow(List<KurzusBean> kurzusok) {
		this.setData(kurzusok);
		fireTableDataChanged();
	}

}
