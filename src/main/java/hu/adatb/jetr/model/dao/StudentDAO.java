package hu.adatb.jetr.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import hu.adatb.jetr.controller.PropertiesFactory;

public class StudentDAO {
	private Properties dbProps = PropertiesFactory.getProperties("db.properties");
	private Connection conn;

	public StudentDAO() throws SQLException, ClassNotFoundException {
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

		String host = dbProps.getProperty("host");
		String port = dbProps.getProperty("port");
		String sid = dbProps.getProperty("sid");
		String username = dbProps.getProperty("username");
		String password = dbProps.getProperty("password");

		conn = DriverManager.getConnection("jdbc:oracle:thin:@" + host + ":" + port + ":" + sid, username, password);
	}

}
