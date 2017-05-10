package hu.adatb.jetr.controller;

import java.sql.Connection;

import hu.adatb.jetr.model.dao.ConnectionFactory;
import hu.adatb.jetr.model.dao.StudentDAO;
import hu.adatb.jetr.view.View;

public class Controller {
	private static Connection conn;
	private static StudentDAO studentDao;

	public Controller() {
		conn = ConnectionFactory.getConnection(PropertiesFactory.getProperties("db.properties"));
		studentDao = new StudentDAO();

		View.launch(View.class);
	}

	public static StudentDAO getStudentDao() {
		return studentDao;
	}

	public static Connection getConnection() {
		return conn;
	}



}
