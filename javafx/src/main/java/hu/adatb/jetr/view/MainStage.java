package hu.adatb.jetr.view;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.adatb.jetr.controller.PropertiesFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainStage extends Stage {
	private static final Logger logger = LoggerFactory.getLogger(MainStage.class);

	private Properties loginProps;
	private Properties mainProps;

	public MainStage(String eha) {
		loginProps = PropertiesFactory.getProperties("login.properties");
		mainProps = PropertiesFactory.getProperties("main.properties");

		logger.info("Opening main window.");
		this.setResizable(false);
		this.getIcons()
		.add(new Image(Thread.currentThread().getContextClassLoader().getResourceAsStream("appicon.png")));
		this.setTitle(loginProps.getProperty("login.title") + " - " + eha);

		try {
			FXMLLoader loader = new FXMLLoader(Thread.currentThread().getContextClassLoader().getResource("Main.fxml"));
			mainProps.stringPropertyNames().forEach(key -> loader.getNamespace().put(key, mainProps.getProperty(key)));
			Parent root = loader.load();

			this.setScene(new Scene(root));

		} catch (IOException e) {
			e.printStackTrace();
		}

		this.show();
	}


}
