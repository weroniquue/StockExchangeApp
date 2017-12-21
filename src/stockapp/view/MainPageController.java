package stockapp.view;

import stockapp.Main;

public class MainPageController {

	private Main mainApp;
	
	public MainPageController() {}
	public void setMainPage(Main main) {
		this.mainApp=main;
	}
	
	public void showDetails() {
		mainApp.showDetails();
	}
	
	public void add() {
		mainApp.addMenu();
	}
}
