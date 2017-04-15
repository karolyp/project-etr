package hu.adatb.jetr.controller;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	private Properties loginProps;

	public LoginController() {
		this.loginProps = PropertiesFactory.getProperties("login.properties");
	}

	@FXML
	private TextField ehaTxtFld;
	@FXML
	private PasswordField pwFld;
	@FXML
	private Text actionTarget;

	private void setActionTargetText(Paint color, String text) {
		actionTarget.setFill(color);
		actionTarget.setText(text);
	}

	@FXML
	public void doLogin() {
		String eha;
		String password;

		eha = ehaTxtFld.getText();
		password = pwFld.getText();

		if (!eha.isEmpty() && !password.isEmpty()) {
			if (Controller.getStudentDao().isExist(eha, password)) {
				logger.info("Logged in as {}", eha);
				setActionTargetText(Color.GREEN, this.loginProps.getProperty("login.successful"));
			} else {
				logger.error("Wrong EHA/password, user not found.");
				setActionTargetText(Color.FIREBRICK, this.loginProps.getProperty("login.wrong"));
			}
		} else {
			logger.error("EHA and password have to be filled.");
			setActionTargetText(Color.FIREBRICK, this.loginProps.getProperty("empty.fields"));
		}
	}

}
