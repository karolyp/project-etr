package hu.adatb.jetr.view.tablemodel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import hu.adatb.jetr.model.VizsgaBean;

public class AvaliableExamsTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -6700628872664383385L;
	private Object[][] vizsgak;
	private List<VizsgaBean> vizsgaList;

	private final String[] header = { "Dátum", "Kurzus", "Terem", "Jelentkezők", "Max jelentkezők", "Felvétel" };

	public void setVizsga(List<VizsgaBean> vizsgak) {
		this.vizsgak = new Object[vizsgak.size()][header.length];
		for (int i = 0; i < vizsgak.size(); i++) {
			this.vizsgak[i] = vizsgak.get(i).toArray(69);
		}
	}

	public AvaliableExamsTableModel(List<VizsgaBean> vizsgak) {
		this.vizsgaList = vizsgak;
		this.setVizsga(vizsgak);
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

	public VizsgaBean getValueAt(int row) {
		return this.vizsgaList.get(row);
	}

	public void refresh(List<VizsgaBean> vizsgak) {
		this.setVizsga(vizsgak);
		fireTableDataChanged();
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return (col == vizsgak[row].length - 1) ? true : false;
	}

}
