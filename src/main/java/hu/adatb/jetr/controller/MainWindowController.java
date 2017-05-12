package hu.adatb.jetr.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.adatb.jetr.dao.StudentDao;
import hu.adatb.jetr.model.HallgatoBean;
import hu.adatb.jetr.model.KurzusBean;
import hu.adatb.jetr.services.FileReaderService;
import hu.adatb.jetr.view.LoginWindow;
import hu.adatb.jetr.view.MainWindow;
import hu.adatb.jetr.view.RegisteredCoursesView;
import hu.adatb.jetr.view.tablemodel.RegisteredCoursesTableModel;

public class MainWindowController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	private MainWindow mainWindow;
	private StudentDao studentDao;
	private HallgatoBean hallgato;
	private RegisteredCoursesView registeredCourses;

	public MainWindowController(HallgatoBean hallgato) {
		this.mainWindow = new MainWindow(hallgato.getEha());
		this.studentDao = AppController.getStudentDao();
		this.hallgato = hallgato;
		this.setRegisteredCoursesListener();
		this.setLogOutListener();
	}

	private void setRegisteredCoursesListener() {
		this.mainWindow.getJMenuBar().getMenu(1).getItem(0).addActionListener(e -> {
			List<KurzusBean> kurzusok = this.studentDao.getRegisteredCourses(this.hallgato);

			TableModel tm = new RegisteredCoursesTableModel(kurzusok);
			JTable table = new JTable(tm);
			table.setPreferredScrollableViewportSize(new Dimension(800, 600));
			table.setFillsViewportHeight(true);

			RegisteredCoursesView rcv = new RegisteredCoursesView();
			rcv.addScrollPane(table);

		});
	}
	private void setLogOutListener(){
		this.mainWindow.getJMenuBar().getMenu(0).getItem(0).addActionListener(e -> {
			removeUserFromCache();
			logger.info("User {} logged out.", hallgato.getEha());
			this.hallgato = null;
			this.mainWindow.setVisible(false);
			
			SwingUtilities.invokeLater(() -> {
				new LoginWindow();
			});				
		});
	}
	private void removeUserFromCache(){
		Properties props = FileReaderService.getProperties("metadata.tmp");
		props.setProperty("user", "");
		File f = new File(System.getProperty("user.dir") + "\\src\\resources\\metadata.tmp");
		try {
			props.store(new FileOutputStream(f), "metadata temporary file");
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
	}
	
	
	

}
