package hu.adatb.jetr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.adatb.jetr.model.HallgatoBean;
import hu.adatb.jetr.model.VizsgaBean;

public class ExamDao {

	private Connection conn;
	private static final Logger logger = LoggerFactory.getLogger(ExamDao.class);

	public ExamDao() {
		this.conn = ConnectionFactory.getConnection();
	}

	public boolean unsubExam(HallgatoBean hallgato, VizsgaBean vizsga) {
		try (PreparedStatement ps = ScriptRunner.createPreparedStatement(this.conn, "unsub_exam.sql")) {
			ps.setDate(1, vizsga.getDatum());
			ps.setString(2, vizsga.getKurzuskod());
			ps.setString(3, hallgato.getEha());

			if (ps.executeUpdate() == 1 && updateCardinality(vizsga, -1) == 1) {
				logger.info("Successfully removed exam {}", vizsga.getKurzusNev());
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			// TODO logger
			e.printStackTrace();
			return false;
		}
	}

	public int updateCardinality(VizsgaBean vizsga, int incOrDec) {
		int success = 0;
		try (PreparedStatement ps = ScriptRunner.createPreparedStatement(this.conn, "update_cardinality.sql")) {
			ps.setInt(1, incOrDec);
			ps.setDate(2, vizsga.getDatum());
			ps.setString(3, vizsga.getKurzuskod());

			success = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO logger
			e.printStackTrace();
		}

		return success;
	}

	// V1.DATUM, C.CODE, C.NAME, CR.NAME, V1.JELENTKEZETT, V1.MAX_JELENTKEZO
	public List<VizsgaBean> getAvaliableExams(HallgatoBean hallgato) {
		List<VizsgaBean> vizsgak = new ArrayList<>();

		try (PreparedStatement ps = ScriptRunner.createPreparedStatement(this.conn, "avaliable_exams.sql")) {
			ps.setString(1, hallgato.getEha());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				vizsgak.add(new VizsgaBean(rs.getDate(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(5), rs.getInt(6)));
			}

		} catch (SQLException e) {
			// TODO logger
			e.printStackTrace();
		}

		return vizsgak;

	}

	/*
	 *
	 * Name Null? Type ------------- -------- ------------ VI_DATUM NOT NULL
	 * DATE VI_KURZUS_KOD NOT NULL VARCHAR2(50) JEGY NOT NULL NUMBER
	 * HALLGATO_EHA NOT NULL VARCHAR2(50)
	 */

	public boolean addNewExam(VizsgaBean vb, HallgatoBean hallgato) {
		boolean succ = false;
		try (PreparedStatement ps = ScriptRunner.createPreparedStatement(this.conn, "insert_exam.sql")) {
			ps.setDate(1, vb.getDatum());
			ps.setString(2, vb.getKurzuskod());
			ps.setInt(3, 0);
			ps.setString(4, hallgato.getEha());

			succ = (ps.executeUpdate() == 1) && (updateCardinality(vb, 1) == 1);

		} catch (SQLException e) {
			// TODO logger
			e.printStackTrace();
			return false;
		}

		return succ;
	}

}
