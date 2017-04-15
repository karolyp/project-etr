package hu.adatb.jetr.controller;

import hu.adatb.jetr.model.dao.StudentDAO;
import hu.adatb.jetr.view.View;

public class Controller {
	private static StudentDAO studentDao;

	public Controller() {
		studentDao = new StudentDAO();
		View.launch(View.class);
	}

	public static StudentDAO getStudentDao() {
		return studentDao;
	}

}
