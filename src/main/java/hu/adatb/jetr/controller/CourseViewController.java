package hu.adatb.jetr.controller;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
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
import hu.adatb.jetr.view.tablemodel.ButtonColumn;
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
			} else {
				JOptionPane.showMessageDialog(cv, "Valószínűleg a kurzus már szerepel a listában!",
						"Hiba kurzufelvétel során", JOptionPane.ERROR_MESSAGE);

			}
		});

	}

	public CourseViewController(HallgatoBean hallgato, List<KurzusBean> kurzusok, boolean b) {
		this.courseDao = AppController.getCourseDao();

		RegisteredCoursesTableModel tm = new RegisteredCoursesTableModel(kurzusok);
		JTable table = new JTable(tm);
		table.setPreferredScrollableViewportSize(new Dimension(800, 600));
		table.setFillsViewportHeight(true);

		Action infoSheetClicked = new AbstractAction() {
			private static final long serialVersionUID = 7441881659224153070L;

			@Override
			public void actionPerformed(ActionEvent e) {
				logger.info("Infosheet requested");
				// int modelRow = Integer.valueOf(e.getActionCommand());
				// TODO: infosheet megvalósítás
				// proto: new InfoSheet(kurzusok.get(modelRow).getKurzus());
			}

		};

		Action change = new AbstractAction() {
			private static final long serialVersionUID = -3625827743573202700L;

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] options = new String[] { "Igen", "Nem" };
				int response;
				try {
					response = JOptionPane.showOptionDialog(null, "Biztos?", "Lejelentkezés",
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);

					if (response == 0) {
						int row = Integer.valueOf(e.getActionCommand());
						deleteFromDb(hallgato, kurzusok.get(row).getKod());
						tm.removeRow(AppController.getStudentDao().getRegisteredCourses(hallgato));
					}

				} catch (HeadlessException e1) {
					logger.error(e1.getMessage());
				}
			}

		};

		new ButtonColumn(table, infoSheetClicked, table.getColumnCount() - 2);
		new ButtonColumn(table, change, table.getColumnCount() - 1);

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

	public void deleteFromDb(HallgatoBean hallgato, String kurzus) {
		if (this.courseDao.deleteCourse(hallgato.getEha(), kurzus)) {
			logger.info("Deleted from DB.");
		} else {
			logger.error("Could not delete course {} from DB!", kurzus);
		}
	}

}
