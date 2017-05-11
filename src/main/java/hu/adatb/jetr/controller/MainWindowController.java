package hu.adatb.jetr.controller;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import hu.adatb.jetr.dao.StudentDao;
import hu.adatb.jetr.model.HallgatoBean;
import hu.adatb.jetr.model.KurzusBean;
import hu.adatb.jetr.view.MainWindow;
import hu.adatb.jetr.view.RegisteredCoursesView;
import hu.adatb.jetr.view.tablemodel.RegisteredCoursesTableModel;

public class MainWindowController {

	private MainWindow mainWindow;
	private StudentDao studentDao;
	private HallgatoBean hallgato;
	private RegisteredCoursesView registeredCourses;

	public MainWindowController(HallgatoBean hallgato) {
		this.mainWindow = new MainWindow(hallgato.getEha());
		this.studentDao = AppController.getStudentDao();
		this.hallgato = hallgato;
		this.setRegisteredCoursesListener();
	}

	private void setRegisteredCoursesListener() {
		this.mainWindow.getJMenuBar().getMenu(1).getItem(0).addActionListener(e -> {
			List<KurzusBean> kurzusok = this.studentDao.getRegisteredCourses(this.hallgato);

			TableModel tm = new RegisteredCoursesTableModel(kurzusok);
			JTable table = new JTable(tm);
			table.setPreferredScrollableViewportSize(new Dimension(800, 600));
			table.setFillsViewportHeight(true);

			RegisteredCoursesView rcv = new RegisteredCoursesView();
			rcv.addScrollPane(table);

		});
	}

}
