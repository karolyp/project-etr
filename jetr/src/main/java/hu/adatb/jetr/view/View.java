package hu.adatb.jetr.view;

import hu.adatb.jetr.controller.Controller;

public class View {
	private Controller controller;
	
	public View(Controller controller){
		this.controller = controller;
		LoginWindow.launch(LoginWindow.class);
	}
}
