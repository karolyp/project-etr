package hu.adatb.jetr.view.tablemodel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import hu.adatb.jetr.model.KurzusBean;

public class RegisteredCoursesTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -6700628872664383385L;
	private List<KurzusBean> kurzusok;

	public RegisteredCoursesTableModel(List<KurzusBean> kurzusok) {

	}

	@Override
	public int getColumnCount() {
		return 0;
	}

	@Override
	public int getRowCount() {
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return null;
	}

}
