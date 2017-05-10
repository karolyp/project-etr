package hu.adatb.jetr.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.adatb.jetr.model.bean.Kurzus;
import hu.adatb.jetr.model.dao.CourseDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	private static CourseDAO kurzusDao;
	private Stage courseStage;

	public MainController() {
		try {
			kurzusDao = new CourseDAO();
			this.courseStage = new Stage();
			this.courseStage.setResizable(false);
		} catch (IOException e) {
			logger.error("Error reading SQL script.");
		}
	}

	@FXML
	private MenuItem listCourses;

	@FXML
	public void listCoursesClicked() throws IOException {
		logger.info("Course list requested.");
		FXMLLoader loader = new FXMLLoader(Thread.currentThread().getContextClassLoader().getResource("RegisterCourses.fxml"));
		Parent root = loader.load();
		this.courseStage.setScene(new Scene(root));
		this.courseStage.setTitle("Kurzus felvétele");
		this.courseStage.show();
	}

	public static List<Kurzus> getCourses() {
		try {
			return kurzusDao.getAvaliableCourses();
		} catch (SQLException e) {
			logger.error("Error executing SQL script.");
		}
		return null;
	}

}
