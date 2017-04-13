package hu.adatb.jetr.controller;

import hu.adatb.jetr.model.dao.StudentDAO;
import hu.adatb.jetr.view.View;

public class Controller {
	private StudentDAO studentDao;

	public Controller() {
		this.studentDao = new StudentDAO();
		new View(this);
	}

	public StudentDAO getStudentDao() {
		return this.studentDao;
	}

	public static boolean isStudentExist(String name, String password) {

		return true;
	}

}
