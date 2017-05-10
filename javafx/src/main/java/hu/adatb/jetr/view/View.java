package hu.adatb.jetr.view;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.adatb.jetr.controller.PropertiesFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class View extends Application {
	private final static Logger logger = LoggerFactory.getLogger(View.class);
	private static Stage primaryStage;

	@Override
	public void start(Stage stage) throws Exception {
		primaryStage = stage;
		Properties loginProps = PropertiesFactory.getProperties("login.properties");

		FXMLLoader loader = new FXMLLoader(Thread.currentThread().getContextClassLoader().getResource("Login.fxml"));
		loginProps.stringPropertyNames().forEach(key -> loader.getNamespace().put(key, loginProps.getProperty(key)));
		Parent root = loader.load();

		logger.info("Login frame objects created.");
		primaryStage.setResizable(false);
		primaryStage.getIcons()
				.add(new Image(Thread.currentThread().getContextClassLoader().getResourceAsStream("appicon.png")));
		primaryStage.setTitle(loginProps.getProperty("login.title"));
		primaryStage.setScene(new Scene(root, 300, 275));
		primaryStage.show();
	}

	public static void closeLoginScene() {
		primaryStage.hide();
	}

}
