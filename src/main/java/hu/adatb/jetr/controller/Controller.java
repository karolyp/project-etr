package hu.adatb.jetr.controller;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.adatb.jetr.model.dao.StudentDAO;
import hu.adatb.jetr.view.View;

public class Controller {
	private StudentDAO studentDAO;
	private static final Logger logger = LoggerFactory.getLogger(Controller.class);

	public void startDesktop() {
		try {
			studentDAO = new StudentDAO();
			new View(this);
		} catch (ClassNotFoundException e) {
			logger.error("Cannot find OJDBC driver, application stops.");
			System.exit(1);
		} catch (SQLException e) {
			logger.error("Cannot connect to DB with the given parameters:\n {} \n Application stops.",
					PropertiesFactory.getProperties("db.properties").toString());
			System.exit(1);
		}
	}
}
