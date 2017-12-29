package stockapp.view;

import javafx.fxml.FXML;
import stockapp.Main;

public class TabController {
	private Main main;
	
	public TabController() {}
	public void setMain(Main main) {
		this.main=main;
	}
	@FXML public void initialize() {
		
		
	}
	public void backToMenu() {
		main.initRootLayout();
		main.showMainPage();
	}
	
	public void showCompany() {
		main.showCompany();
	}
	
	public void showStockExchange() {
		main.showStockExchange();
	}
	
	public void showCurrency() {
		main.showCurrency();
	}
}
