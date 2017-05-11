package hu.adatb.jetr.controller;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.adatb.jetr.dao.StudentDao;
import hu.adatb.jetr.exception.UserNotFoundException;
import hu.adatb.jetr.model.HallgatoBean;
import hu.adatb.jetr.services.FileReaderService;
import hu.adatb.jetr.view.LoginWindow;

public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	private LoginWindow loginWindow;
	private StudentDao studentDao;
	private HallgatoBean hallgato;

	public LoginController(LoginWindow loginWindow) {
		this.loginWindow = loginWindow;
		this.studentDao = AppController.getStudentDao();
		this.loginWindow.getBelepButton().addActionListener(getBelepListener());

	}

	public ActionListener getBelepListener() {
		ActionListener al = e -> {
			String eha = this.loginWindow.getEhaField().getText();
			String password = String.valueOf(this.loginWindow.getJelszoField().getPassword());

			if (!eha.isEmpty() && !password.isEmpty()) {
				try {
					this.hallgato = this.studentDao.getHallgato(eha, password);
					this.loginWindow.getBelepButton().setEnabled(false);

					float[] color = Color.RGBtoHSB(49, 165, 0, null);

					this.loginWindow.getErrorLabel().setForeground(Color.getHSBColor(color[0], color[1], color[2]));
					this.loginWindow.getErrorLabel().setText("Sikeres belépés!");
					logger.info("User {} logged in.", eha);
					setUserCached(eha);

					new MainWindowController(this.hallgato);

				} catch (UserNotFoundException unfe) {
					logger.error("User not found in the database.");
					this.loginWindow.getErrorLabel().setText("Felhasználó nem található!");
				}
			} else {
				logger.error("Username or password could not be empty!");
				this.loginWindow.getErrorLabel().setText("EHA / jelszó mező üres!");
			}

		};

		return al;

	}

	public void setUserCached(String eha) {
		Properties props = FileReaderService.getProperties("metadata.tmp");
		props.setProperty("user", eha);
		File f = new File(System.getProperty("user.dir") + "\\src\\resources\\metadata.tmp");
		try {
			props.store(new FileOutputStream(f), "metadata temporary file");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
