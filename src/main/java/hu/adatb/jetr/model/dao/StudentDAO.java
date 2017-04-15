package hu.adatb.jetr.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.adatb.jetr.controller.PropertiesFactory;
import hu.adatb.jetr.model.bean.HallgatoBasic;

public class StudentDAO {
	private static final Logger logger = LoggerFactory.getLogger(StudentDAO.class);
	private Properties dbProps = PropertiesFactory.getProperties("db.properties");
	private Connection conn;

	public StudentDAO() {
		String host = dbProps.getProperty("host");
		String port = dbProps.getProperty("port");
		String sid = dbProps.getProperty("sid");
		String username = dbProps.getProperty("username");
		String password = dbProps.getProperty("password");
		String connectionStr = "jdbc:oracle:thin:@" + host + ":" + port + ":" + sid;
		try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		} catch (SQLException e) {
			logger.error("Cannot register OJDBC driver.");
			System.exit(1);
		}

		try {
			conn = DriverManager.getConnection(connectionStr, username, password);
			if (conn != null) {
				logger.info("Successfully connected to {}:{}.", host, port);
			}

		} catch (SQLException e) {
			logger.error("Cannot connect to DB with the given parameters:\n {} \n Application stops.",
					PropertiesFactory.getProperties("db.properties").toString());
			System.exit(1);
		}

	}

	private int getRowCount(ResultSet rs) throws SQLException {
		int n = 0;
		while (rs.next()) {
			n++;
		}
		return n;
	}

	public HallgatoBasic getHallgatoByUsernamePassword(String username, String password) throws SQLException {
		String sql = "SELECT * FROM H668139.HALLGATO WHERE EHA=? AND JELSZO=?";
		PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ps.setString(1, username);
		ps.setString(2, password);

		ResultSet rs = ps.executeQuery();

		if (getRowCount(rs) == 1) {
			rs.beforeFirst();
			rs.next();
			return new HallgatoBasic(rs.getString("EHA"), rs.getString("VEZETEKNEV"), rs.getString("UTONEV"));
		}

		return null;
	}

}
