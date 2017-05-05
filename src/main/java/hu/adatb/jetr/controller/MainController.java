package hu.adatb.jetr.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.adatb.jetr.model.bean.Kurzus;
import hu.adatb.jetr.model.dao.CourseDAO;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	private CourseDAO kurzusDao;

	public MainController() {
		try {
			this.kurzusDao = new CourseDAO();
		} catch (IOException e) {
			logger.error("Error reading SQL script.");
		}
	}

	@FXML
	private MenuItem listCourses;

	@FXML
	public void listCoursesClicked() {
		logger.info("Course list requested.");
		try {

			for (Kurzus k : this.kurzusDao.getAvaliableCourses()) { // TODO: GUI
				System.out.println(k.toString());
			}

		} catch (SQLException e) {
			logger.error("Error executing SQL script.", e);
		}

	}

}
