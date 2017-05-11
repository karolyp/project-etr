package hu.adatb.jetr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.adatb.jetr.exception.UserNotFoundException;
import hu.adatb.jetr.model.HallgatoBean;

public class StudentDao {
	private static final Logger logger = LoggerFactory.getLogger(StudentDao.class);

	private int getRowCount(ResultSet rs) throws SQLException {
		int c = 0;
		while (rs.next()) {
			c++;
		}
		return c;
	}

	public HallgatoBean getHallgato(String eha, String jelszo) throws UserNotFoundException {
		Connection conn = ConnectionFactory.getConnection();
		String query = "SELECT EHA, JELSZO FROM H668139.HALLGATO WHERE EHA=? AND JELSZO=?";
		try (PreparedStatement ps = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_READ_ONLY);) {

			ps.setString(1, eha);
			ps.setString(2, jelszo);

			ResultSet rs = ps.executeQuery();

			if (getRowCount(rs) == 1) {
				rs.previous();
				return new HallgatoBean(rs.getString(1), rs.getString(2));
			} else {
				throw new UserNotFoundException();
			}

		} catch (SQLException e) {
			logger.error("Error during execution of query.", e);
		}
		return null;
	}

	/**
	 * Trusted query because user had been logged in at least once, so we will
	 * store the login in a temporary file.
	 * 
	 * @return
	 */
	public HallgatoBean getHallgato(String eha) {
		Connection conn = ConnectionFactory.getConnection();
		String query = "SELECT EHA, JELSZO FROM H668139.HALLGATO WHERE EHA=?";
		try (PreparedStatement ps = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_READ_ONLY);) {

			ps.setString(1, eha);

			ResultSet rs = ps.executeQuery();

			if (getRowCount(rs) == 1) {
				rs.previous();
				return new HallgatoBean(rs.getString(1), rs.getString(2));
			} else {
				throw new SQLException();
			}

		} catch (SQLException e) {
			logger.error("Error during execution of query.", e);
		}
		return null;
	}
}
