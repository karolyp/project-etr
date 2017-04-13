package hu.adatb.jetr.view;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.adatb.jetr.controller.PropertiesFactory;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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

		Scene scene = new Scene(grid, 300, 275);
		primaryStage.setScene(scene);

		Text scenetitle = new Text(labelsProps.getProperty("welcome.message"));
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label(labelsProps.getProperty("eha") + ":");
		grid.add(userName, 0, 1);

		TextField userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		Label pw = new Label(labelsProps.getProperty("password") + ":");
		grid.add(pw, 0, 2);

		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 2);

		Button btn = new Button(labelsProps.getProperty("login.button"));
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 4);

		Text actiontarget = new Text();
		grid.add(actiontarget, 1, 6);

		btn.setOnAction(e -> {
			actiontarget.setFill(Color.FIREBRICK);
			actiontarget.setText("Belépés megnyomva.");
		});

		primaryStage.show();
	}

}
