package stockapp.view;

import stockapp.Main;

/**This class is responsible for three main button on the main page. */
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
