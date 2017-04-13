package hu.adatb.jetr.controller;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesFactory {

	private static final Logger logger = LoggerFactory.getLogger(PropertiesFactory.class);

	public static Properties getProperties(String propsFile) {
		Properties props = new Properties();
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propsFile));

			if (props.isEmpty()) {
				throw new IOException();
			}

			logger.info("{} file loaded.", propsFile);

			return props;

		} catch (IOException e) {
			logger.error("Property file \"{}\" cannot be loaded, application stops.",
					propsFile.replace(".properties", ""));
			System.exit(1);
		}

		return null;

	}

}
