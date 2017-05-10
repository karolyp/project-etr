package hu.adatb.jetr.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hu.adatb.jetr.controller.Controller;
import hu.adatb.jetr.controller.ScriptRunner;
import hu.adatb.jetr.model.bean.Kurzus;

public class CourseDAO {
	private Connection conn;

	public CourseDAO() throws IOException {
		this.conn = Controller.getConnection();
	}

	public List<Kurzus> getAvaliableCourses() throws SQLException {
		List<Kurzus> courses = new ArrayList<>();
		PreparedStatement ps = ScriptRunner.createPreparedStatement(this.conn, "avaliable_courses.sql");
		ps.setString(1, System.getProperty("eha"));
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			courses.add(new Kurzus(
					rs.getString(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getInt(6),
					rs.getInt(7)
					));
		}

		return courses;
	}

	public List<Kurzus> getRegisteredCourses(){
		List<Kurzus> courses = new ArrayList<>();
		
		
		return courses;
	}
	
}
