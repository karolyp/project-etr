package hu.adatb.jetr.model.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hu.adatb.jetr.controller.Controller;
import hu.adatb.jetr.model.bean.Kurzus;

public class CourseDAO {
	private Connection conn;
	private String query;

	private String readQuery(String path) throws IOException {
		String query = "";
		String line;
		BufferedReader br = new BufferedReader(
				new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(path)));

		while ((line = br.readLine()) != null) {
			query += line;
		}
		return query;
	}

	public CourseDAO() throws IOException {
		this.conn = Controller.getConnection();
		this.query = readQuery("courses.sql");

	}

	/*
	 *
	 * SELECT C.CODE, C.NAME, C.TYPE, TEACHER.NAME, CLASSROOM.NAME, C.SEMESTER,
	 * C.CREDIT FROM H668139.COURSE C INNER JOIN TEACHER ON
	 * C.TEACHER_ID=TEACHER.ID INNER JOIN CLASSROOM ON C.CLASSROOM_ID =
	 * CLASSROOM.ID WHERE SEMESTER=3;
	 */

	public List<Kurzus> getCourses() {
		List<Kurzus> courses = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(this.query, ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_READ_ONLY)) {
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

		} catch (SQLException e) {
			System.err.println("Shit happens");
			e.printStackTrace();
		}

		return courses;
	}

}
