package hu.adatb.jetr.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.adatb.jetr.dao.StudentDao;
import hu.adatb.jetr.model.HallgatoBean;
import hu.adatb.jetr.services.FileReaderService;
import hu.adatb.jetr.view.CoursesView;
import hu.adatb.jetr.view.LoginWindow;
import hu.adatb.jetr.view.MainWindow;

public class MainWindowController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	private MainWindow mainWindow;
	private StudentDao studentDao;
	private HallgatoBean hallgato;
	private CoursesView registeredCourses;

	public MainWindowController(HallgatoBean hallgato) {
		this.mainWindow = new MainWindow(hallgato.getEha());
		this.studentDao = AppController.getStudentDao();
		this.hallgato = hallgato;
		this.setRegisteredCoursesListener();
		this.setAvaliableCoursesListener();
		this.setLogOutListener();
		this.setFelvettVizsgakListener();
		this.mainWindow.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				logger.info("Application closing.");
				System.exit(0);
			}
		});

	}

	private void setRegisteredCoursesListener() {
		this.mainWindow.getJMenuBar().getMenu(1).getItem(0).addActionListener(e -> {
			CourseViewController cvc = new CourseViewController(this.hallgato,
					this.studentDao.getRegisteredCourses(this.hallgato), false);
		});

	}

	private void setAvaliableCoursesListener() {
		this.mainWindow.getJMenuBar().getMenu(1).getItem(1).addActionListener(e -> {
			CourseViewController cvc = new CourseViewController(this.hallgato,
					this.studentDao.getAvaliableCourses(this.hallgato));
		});
	}
	private void setFelvettVizsgakListener() {
		this.mainWindow.getJMenuBar().getMenu(2).getItem(0).addActionListener(e -> {
			ExamsViewController evc = new ExamsViewController(this.studentDao.getFelvettVizsgak(this.hallgato));
		});
	}

	private void setLogOutListener() {
		this.mainWindow.getJMenuBar().getMenu(0).getItem(0).addActionListener(e -> {
			removeUserFromCache();
			logger.info("User {} logged out.", hallgato.getEha());
			this.hallgato = null;
			this.mainWindow.setVisible(false);

			SwingUtilities.invokeLater(() -> {
				new LoginWindow();
			});
		});
	}

	private void removeUserFromCache() {
		Properties props = FileReaderService.getProperties("metadata.tmp");
		props.setProperty("user", "");
		File f = new File(System.getProperty("user.dir") + "\\src\\resources\\metadata.tmp");
		try {
			props.store(new FileOutputStream(f), "metadata temporary file");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
