package hu.adatb.jetr.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import hu.adatb.jetr.services.FileReaderService;

public class ScriptRunner {
	public static PreparedStatement createPreparedStatement(Connection conn, String path) {
		String query = "";
		String line;
		BufferedReader br = new BufferedReader(new InputStreamReader(FileReaderService.getInputStream(path)));
		try {
			while ((line = br.readLine()) != null) {
				query += line;
			}
		} catch (IOException e1) {
			e1.printStackTrace(); // TODO
		}

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ps;
	}
}
