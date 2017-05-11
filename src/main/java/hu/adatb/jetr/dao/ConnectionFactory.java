package hu.adatb.jetr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.adatb.jetr.services.FileReaderService;

public class ConnectionFactory {
	private static final Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);
	private static Connection conn;

	public static void createConnection() {
		Properties dbProps = FileReaderService.getProperties("db.properties");
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
					FileReaderService.getProperties("db.properties").toString());
			System.exit(1);
		}
	}

	public static Connection getConnection() {
		return conn;
	}

	public static void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			logger.error("Could not close connection with DB.", e);
		}
	}

}
