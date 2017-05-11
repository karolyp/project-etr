package hu.adatb.jetr.controller;

import hu.adatb.jetr.model.HallgatoBean;

public class MainWindowController {
	public MainWindowController(HallgatoBean hallgato) {
		new MainWindow(hallgato.getEha());
	}

}
