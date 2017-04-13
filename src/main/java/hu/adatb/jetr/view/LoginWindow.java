package hu.adatb.jetr.view;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.adatb.jetr.controller.PropertiesFactory;
import hu.adatb.jetr.view.scene.LoginScene;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginWindow extends Application {

	private static final Logger logger = LoggerFactory.getLogger(LoginWindow.class);
	private static final String LABELS = "labels.properties";
	private Properties labelsProps;

	@Override
	public void init() {
		try {
			super.init();
			this.labelsProps = PropertiesFactory.getProperties(LABELS);
		} catch (Exception e) {
			logger.error("Unexpected error occured during initializing window, application stops.");
			stop();
		}
	}

	@Override
	public void stop() {
		logger.info("Application closed.");
	}

	@Override
	public void start(Stage primaryStage) {
		decorateStage(primaryStage);
		logger.info("Greeting window opened.");
	}

	private void decorateStage(Stage primaryStage) {
		primaryStage.setTitle(labelsProps.getProperty("login.title"));
		primaryStage.setResizable(false);
		primaryStage.getIcons()
				.add(new Image(Thread.currentThread().getContextClassLoader().getResourceAsStream("appicon.png")));

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		primaryStage.setScene(new LoginScene(grid, 300, 275, labelsProps));
		primaryStage.show();
	}

}
