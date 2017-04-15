package hu.adatb.jetr.view.scene;

import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.adatb.jetr.controller.Controller;
import hu.adatb.jetr.model.bean.HallgatoBasic;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class LoginScene extends Scene {

	private static final Logger logger = LoggerFactory.getLogger(LoginScene.class);

	private Text actiontarget;

	public LoginScene(GridPane grid, int i, int j, Properties labelsProps) {
		super(grid, i, j);

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

		actiontarget = new Text();
		grid.add(actiontarget, 1, 6);

		btn.setOnAction(e -> {
			String username = userTextField.getText();
			String password = pwBox.getText();
			if (username.isEmpty() || password.isEmpty()) {
				writeActionResult(Color.FIREBRICK, labelsProps.getProperty("empty.fields"));
				pwBox.setText("");
			} else {
				try {
					HallgatoBasic hb = Controller.getHallgatoByUsernamePassword(username, password);

					if (hb != null) {
						logger.info("Logged in as {}", hb.getEha());
						writeActionResult(Color.GREEN, labelsProps.getProperty("login.successful"));
					} else {
						logger.error("Wrong username/password, user not found.");
						writeActionResult(Color.FIREBRICK, labelsProps.getProperty("login.wrong"));
						pwBox.setText("");
					}

				} catch (SQLException e1) {
					logger.error("Unknown error occured when tried to log you in.", e1);
				}
			}

		});
	}

	private void writeActionResult(Paint color, String message) {
		this.actiontarget.setFill(color);
		this.actiontarget.setText(message);
	}

}
