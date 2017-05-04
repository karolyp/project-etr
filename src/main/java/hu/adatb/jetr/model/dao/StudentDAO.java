package hu.adatb.jetr.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.adatb.jetr.controller.Controller;

public class StudentDAO {
	private static final Logger logger = LoggerFactory.getLogger(StudentDAO.class);
	private Connection conn;

	public StudentDAO() {
		this.conn = Controller.getConnection();
	}

	public boolean isExist(String username, String password) {
		String sql = "SELECT * FROM H668139.HALLGATO WHERE EHA=? AND JELSZO=?";

		try (PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_READ_ONLY)) {
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (getRowCount(rs) == 1) {
				return true;
			}

		} catch (SQLException e) {
			logger.error("Error occured when tried to log you in.");
		}
		return false;
	}

	private int getRowCount(ResultSet rs) throws SQLException {
		int n = 0;
		while (rs.next()) {
			n++;
		}
		return n;
	}

	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			logger.error("Error occured during closing DB connection.");
		}
	}

}
