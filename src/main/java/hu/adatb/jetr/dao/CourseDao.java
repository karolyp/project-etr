package hu.adatb.jetr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.adatb.jetr.exception.PrereqNotCompletedException;
import hu.adatb.jetr.model.FelvettKurzusBean;
import hu.adatb.jetr.model.HallgatoBean;

public class CourseDao {

	private Connection conn;
	private static final Logger logger = LoggerFactory.getLogger(CourseDao.class);

	public CourseDao() {
		this.conn = ConnectionFactory.getConnection();
	}

	public int getRowCount(ResultSet rs) throws SQLException {
		int c = 0;
		while (rs.next()) {
			c++;
		}
		return c;
	}

	public boolean isPrereqAccomplished(HallgatoBean hallgato, String targy) throws PrereqNotCompletedException {
		try (PreparedStatement ps = ScriptRunner.createPreparedStatement(this.conn, "check_prereq.sql")) {
			ps.setString(1, hallgato.getEha());
			ps.setString(2, targy);

			ResultSet rs = ps.executeQuery();

			if (getRowCount(rs) != 0) {
				rs.beforeFirst();
				boolean b = false;

				while (rs.next()) {
					if (rs.getInt(2) >= 2) {
						b = true;
					} else {
						throw new PrereqNotCompletedException(targy, rs.getString(1));
					}
				}

				return b;

			} else {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<FelvettKurzusBean> getFelvehetoKurzusok(HallgatoBean hallgato, List<String> selected) {
		List<FelvettKurzusBean> felveheto = new ArrayList<>();

		for (String s : selected) {
			try {
				if (isPrereqAccomplished(hallgato, s)) {
					felveheto.add(new FelvettKurzusBean(hallgato.getEha(), s, 0));
				}
			} catch (PrereqNotCompletedException e) {
				logger.error(e.getMessage());
			}

		}

		return felveheto;

	}

	public boolean insertCourses(List<FelvettKurzusBean> felvett) {
		boolean succ = false;

		for (FelvettKurzusBean fkb : felvett) {
			if (insertFelvettBean(fkb)) {
				succ = true;
				logger.info("Succesful registration. Course code: {}", fkb.getKurzuskod());
			} else {
				succ = false;
				logger.error("Error during course registration.");
			}
		}

		return succ;
	}

	public boolean insertFelvettBean(FelvettKurzusBean fkb) {
		try (PreparedStatement ps = ScriptRunner.createPreparedStatement(this.conn, "insert_reg_course.sql")) {
			ps.setString(1, fkb.getHallgato());
			ps.setString(2, fkb.getKurzuskod());
			ps.setInt(3, fkb.getErdemjegy());

			return ps.executeUpdate() == 1;

		} catch (SQLException e) {
			// TODO logger
			e.printStackTrace();
			return false;
		}
	}

}
