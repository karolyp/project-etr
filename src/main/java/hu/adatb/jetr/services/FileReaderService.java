package hu.adatb.jetr.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileReaderService {
	public static InputStream getInputStream(String file) {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);

		return is;
	}

	public static Properties getProperties(String file) {
		Properties props = new Properties();
		try {
			props.load(getInputStream(file));
		} catch (IOException e) {
			// TODO logger
			e.printStackTrace();
		}
		return props;
	}

}
