package hu.adatb.jetr.controller;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.adatb.jetr.dao.CourseDao;
import hu.adatb.jetr.dao.StudentDao;
import hu.adatb.jetr.model.FelvettKurzusBean;
import hu.adatb.jetr.model.HallgatoBean;
import hu.adatb.jetr.model.KurzusBean;
import hu.adatb.jetr.view.CoursesView;
import hu.adatb.jetr.view.tablemodel.AvaliableCoursesTableModel;
import hu.adatb.jetr.view.tablemodel.RegisteredCoursesTableModel;

public class CourseViewController {

	private final Logger logger = LoggerFactory.getLogger(CourseViewController.class);

	private List<String> selectedCourses;
	private CourseDao courseDao;
	private StudentDao studentDao;

	public CourseViewController(HallgatoBean hallgato, List<KurzusBean> kurzusok) { // felvehető
																					// //
		// kurzusokhoz
		this.selectedCourses = new ArrayList<>();
		this.courseDao = AppController.getCourseDao();
		this.studentDao = AppController.getStudentDao();

		TableModel tm = new AvaliableCoursesTableModel(kurzusok);
		JTable table = new JTable(tm);
		table.setPreferredScrollableViewportSize(new Dimension(1280, 500));
		table.setFillsViewportHeight(true);
		CoursesView cv = new CoursesView("Felvehető kurzusok");
		cv.setSize(1280, 600);
		resizeColumnWidth(table);
		cv.setApplyButton();
		cv.addScrollPane(table);

		table.getModel().addTableModelListener(e -> {
			int row = e.getFirstRow();
			int column = e.getColumn();
			TableModel model = (TableModel) e.getSource();
			String courseCode = (String) model.getValueAt(row, 0);
			Boolean selected = (Boolean) model.getValueAt(row, column);

			if (!selected && this.selectedCourses.contains(courseCode)) {
				this.selectedCourses.remove(courseCode);
			} else {
				this.selectedCourses.add(courseCode);

			}
		});

		cv.addApplyButtonListener(e -> {
			List<FelvettKurzusBean> felveheto = this.courseDao.getFelvehetoKurzusok(hallgato, selectedCourses);

			if (this.courseDao.insertCourses(felveheto)) {
				JOptionPane.showMessageDialog(cv, "Sikeres felvett kurzusok: " + felveheto.size(), "Sikeres felvétel!",
						JOptionPane.INFORMATION_MESSAGE);
				cv.setVisible(false);
				new CourseViewController(hallgato, this.studentDao.getAvaliableCourses(hallgato));

			} else {
				JOptionPane.showMessageDialog(cv, "Valószínűleg a kurzus már szerepel a listában!",
						"Hiba kurzufelvétel során", JOptionPane.ERROR_MESSAGE);

			}
		});

	}

	public CourseViewController(List<KurzusBean> kurzusok, boolean b) { // felvett
																		// kurzusokhoz
		TableModel tm = new RegisteredCoursesTableModel(kurzusok);
		JTable table = new JTable(tm);
		table.setPreferredScrollableViewportSize(new Dimension(800, 600));
		table.setFillsViewportHeight(true);

		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				int col = table.columnAtPoint(e.getPoint());
				if (e.getClickCount() == 2 && col == table.getColumnCount() - 1 && row >= 0) {
					logger.info("Infosheet requested for course {}", table.getValueAt(row, 0));
					// TODO: infosheet gui implementálása
					// proto: new InfosheetWindow(String kurzuskod)
				}
			}

		});

		CoursesView rcv = new CoursesView("Felvett kurzusok");
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
