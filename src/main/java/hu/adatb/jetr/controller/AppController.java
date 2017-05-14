package hu.adatb.jetr.controller;

import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.adatb.jetr.dao.ConnectionFactory;
import hu.adatb.jetr.dao.CourseDao;
import hu.adatb.jetr.dao.ExamDao;
import hu.adatb.jetr.dao.StudentDao;
import hu.adatb.jetr.services.FileReaderService;
import hu.adatb.jetr.view.LoginWindow;

public class AppController {
	private static final Logger logger = LoggerFactory.getLogger(AppController.class);

	private static StudentDao studentDao;
	private static CourseDao courseDao;
	private static ExamDao examDao;

	public AppController() {
		ConnectionFactory.createConnection();
		this.addShutdownHook();

		studentDao = new StudentDao();
		courseDao = new CourseDao();
		examDao = new ExamDao();

		String eha = FileReaderService.getProperties("metadata.tmp").getProperty("user");
		if (!eha.isEmpty()) {
			logger.info("Found user in cache: {}", eha);
			SwingUtilities.invokeLater(() -> {
				new MainWindowController(studentDao.getHallgato(eha));
			});
		} else {
			SwingUtilities.invokeLater(() -> {
				new LoginWindow();

			});
		}

	}

	public static StudentDao getStudentDao() {
		return studentDao;
	}

	public static CourseDao getCourseDao() {
		return courseDao;
	}

	public static ExamDao getExamDao() {
		return examDao;
	}

	public void addShutdownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				ConnectionFactory.closeConnection();
			}
		});
	}

}
