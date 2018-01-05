package stockapp.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import stockapp.Main;

public class TabController {
	private Main main;

	public TabController() {
	}

	public void setMain(Main main) {
		this.main = main;
	}

	@FXML
	public void initialize() {

	}

	public void backToMenu() {
		main.initRootLayout();
		main.showMainPage();
	}

	public void showCompany() {
		main.showCompany();
	}

	public void showStockExchange() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(main.getClass().getResource("view/StockExchangeOverview.fxml"));
			AnchorPane stockExchange = (AnchorPane) loader.load();
			main.getDetails().setCenter(stockExchange);

			StockExchangeOverviewController controller = loader.getController();
			controller.setMain(main);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showCurrency() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(main.getClass().getResource("view/CurrencyOverview.fxml"));
			AnchorPane index = (AnchorPane) loader.load();
			main.getDetails().setCenter(index);

			CurrencyOverviewController controller = loader.getController();
			controller.setMain(main);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showInvestor() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(main.getClass().getResource("view/InvestorOverview.fxml"));
			AnchorPane investor = (AnchorPane) loader.load();
			main.getDetails().setCenter(investor);

			//InvestorOverviewController controller = loader.getController();
			//controller.setMain(main);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
