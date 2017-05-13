package hu.adatb.jetr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.adatb.jetr.exception.UserNotFoundException;
import hu.adatb.jetr.model.HallgatoBean;
import hu.adatb.jetr.model.KurzusBean;
import hu.adatb.jetr.model.VizsgaBean;

public class StudentDao {
	private static final Logger logger = LoggerFactory.getLogger(StudentDao.class);
	private Connection conn;

	private int getRowCount(ResultSet rs) throws SQLException {
		int c = 0;
		while (rs.next()) {
			c++;
		}
		return c;
	}

	public StudentDao() {
		this.conn = ConnectionFactory.getConnection();
	}

	public HallgatoBean getHallgato(String eha, String jelszo) throws UserNotFoundException {
		String query = "SELECT EHA, JELSZO, EVFOLYAM FROM H668139.HALLGATO WHERE EHA=? AND JELSZO=?";
		try (PreparedStatement ps = this.conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_READ_ONLY);) {

			ps.setString(1, eha);
			ps.setString(2, jelszo);

			ResultSet rs = ps.executeQuery();

			if (getRowCount(rs) == 1) {
				rs.previous();
				return new HallgatoBean(rs.getString(1), rs.getString(2), rs.getInt(3));
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
		String query = "SELECT EHA, JELSZO, EVFOLYAM FROM H668139.HALLGATO WHERE EHA=?";
		try (PreparedStatement ps = this.conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_READ_ONLY);) {

			ps.setString(1, eha);

			ResultSet rs = ps.executeQuery();

			if (getRowCount(rs) == 1) {
				rs.previous();
				return new HallgatoBean(rs.getString(1), rs.getString(2), rs.getInt(3));
			} else {
				throw new SQLException();
			}

		} catch (SQLException e) {
			logger.error("Error during execution of query.", e);
			return null;
		}

	}

	/**
	 * A query visszaad: C.CODE, C.NAME, C.TYPE, TEACHER.NAME, CLASSROOM.NAME,
	 * C.SEMESTER, C.CREDIT
	 *
	 * @param hallgato
	 * @return
	 */

	public List<KurzusBean> getRegisteredCourses(HallgatoBean hallgato) {
		List<KurzusBean> kurzusok = new ArrayList<>();
		try (PreparedStatement ps = ScriptRunner.createPreparedStatement(this.conn, "registered_courses.sql")) {
			ps.setInt(1, hallgato.getFelev());
			ps.setString(2, hallgato.getEha());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				kurzusok.add(new KurzusBean(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getInt(6), rs.getInt(7)));
			}

		} catch (SQLException e) {
			logger.error("Error during execution of query.", e);
		}

		return kurzusok;
	}

	/*
	 * SELECT DISTINCT C.CODE, C.NAME, C.TYPE, TEACHER.NAME, CLASSROOM.NAME,
	 * C.SEMESTER, C.CREDIT FROM H668139.COURSE C
	 */
	public List<KurzusBean> getAvaliableCourses(HallgatoBean hallgato) {
		List<KurzusBean> kurzusok = new ArrayList<>();
		try (PreparedStatement ps = ScriptRunner.createPreparedStatement(this.conn, "avaliable_courses.sql")) {
			ps.setString(1, hallgato.getEha());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				kurzusok.add(new KurzusBean(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getInt(6), rs.getInt(7)));
			}

		} catch (SQLException e) {
			logger.error("Error during execution of query.", e);
		}

		return kurzusok;
	}
	
	public List<VizsgaBean> getFelvettVizsgak(HallgatoBean hallgato){
		List<VizsgaBean> vizsgak = new ArrayList<>();
		try (PreparedStatement ps = ScriptRunner.createPreparedStatement(this.conn, "felvett_vizsgak.sql")) {
			ps.setString(1, hallgato.getEha());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				vizsgak.add(new VizsgaBean(rs.getDate(1), rs.getString(2), rs.getInt(3)));
			}

		} catch (SQLException e) {
			logger.error("Error during execution of query.", e);
		}
		
		return vizsgak;
	}

}
