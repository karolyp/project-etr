package hu.adatb.jetr.controller;

import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.adatb.jetr.dao.ConnectionFactory;
import hu.adatb.jetr.dao.StudentDao;
import hu.adatb.jetr.services.FileReaderService;
import hu.adatb.jetr.view.LoginWindow;

public class AppController {
	private static final Logger logger = LoggerFactory.getLogger(AppController.class);

	private static StudentDao studentDao;

	public AppController() {
		ConnectionFactory.createConnection();
		studentDao = new StudentDao();

		String eha = FileReaderService.getProperties("metadata.tmp").getProperty("user");
		if (!eha.isEmpty()) {
			logger.info("Found user in cache: {}", eha);
			// nem kell bejelentkezés
		} else {
			SwingUtilities.invokeLater(() -> {
				new LoginWindow();

			});
		}

	}

	public static StudentDao getStudentDao() {
		return studentDao;
	}

}
