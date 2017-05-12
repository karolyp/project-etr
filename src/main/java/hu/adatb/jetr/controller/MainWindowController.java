package hu.adatb.jetr.controller;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.adatb.jetr.dao.ConnectionFactory;
import hu.adatb.jetr.dao.StudentDao;
import hu.adatb.jetr.model.HallgatoBean;
import hu.adatb.jetr.model.KurzusBean;
import hu.adatb.jetr.services.FileReaderService;
import hu.adatb.jetr.view.CoursesView;
import hu.adatb.jetr.view.LoginWindow;
import hu.adatb.jetr.view.MainWindow;
import hu.adatb.jetr.view.tablemodel.AvaliableCoursesTableModel;
import hu.adatb.jetr.view.tablemodel.RegisteredCoursesTableModel;

public class MainWindowController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	private MainWindow mainWindow;
	private StudentDao studentDao;
	private HallgatoBean hallgato;
	private CoursesView registeredCourses;

	public MainWindowController(HallgatoBean hallgato) {
		this.mainWindow = new MainWindow(hallgato.getEha());
		this.studentDao = AppController.getStudentDao();
		this.hallgato = hallgato;
		this.setRegisteredCoursesListener();
		this.setAvaliableCoursesListener();
		this.setLogOutListener();
		this.mainWindow.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				logger.info("Application closing.");
				System.exit(0);
			}
		});

	}

	private void setRegisteredCoursesListener() {
		this.mainWindow.getJMenuBar().getMenu(1).getItem(0).addActionListener(e -> {
			List<KurzusBean> kurzusok = this.studentDao.getRegisteredCourses(this.hallgato);

			TableModel tm = new RegisteredCoursesTableModel(kurzusok);
			JTable table = new JTable(tm);
			table.setPreferredScrollableViewportSize(new Dimension(800, 600));
			table.setFillsViewportHeight(true);

			table.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					int row = table.rowAtPoint(e.getPoint());
					int col = table.columnAtPoint(e.getPoint());
					if (e.getClickCount() == 2 && col == table.getColumnCount() - 1 && row >= 0) {
						logger.info("Infosheet requested for course {}", table.getValueAt(row, 0));
						// TODO: infosheet gui implementálása
						// proto: new InfosheetWindow(String kurzuskod)
					}
				}

			});

			CoursesView rcv = new CoursesView("Felvett kurzusok");
			resizeColumnWidth(table);
			rcv.addScrollPane(table);

		});

	}

	private void setAvaliableCoursesListener() {
		this.mainWindow.getJMenuBar().getMenu(1).getItem(1).addActionListener(e -> {
			List<KurzusBean> kurzusok = this.studentDao.getAvaliableCourses(this.hallgato);

			TableModel tm = new AvaliableCoursesTableModel(kurzusok);
			JTable table = new JTable(tm);
			table.setPreferredScrollableViewportSize(new Dimension(1280, 500));
			table.setFillsViewportHeight(true);
			CoursesView cv = new CoursesView("Felvehető kurzusok");
			cv.setSize(1280, 600);
			resizeColumnWidth(table);
			cv.setApplyButton();
			cv.addScrollPane(table);
			ConnectionFactory.closeConnection();
		});
	}

	public void resizeColumnWidth(JTable table) {
		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = 25;
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width + 1, width);
			}
			table.getColumnModel().getColumn(column).setPreferredWidth(width);
		}
	}

	private void setLogOutListener() {
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

	private void removeUserFromCache() {
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
