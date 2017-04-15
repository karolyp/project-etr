package hu.adatb.jetr.controller;

import java.sql.SQLException;

import hu.adatb.jetr.model.bean.HallgatoBasic;
import hu.adatb.jetr.model.dao.StudentDAO;
import hu.adatb.jetr.view.View;

public class Controller {
	private static StudentDAO studentDao;

	public Controller() {
		studentDao = new StudentDAO();
		new View(this);
	}

	public static HallgatoBasic getHallgatoByUsernamePassword(String name, String password) throws SQLException {
		return studentDao.getHallgatoByUsernamePassword(name, password);
	}

}
