package stockapp.view;


import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.stage.Stage;
import stockapp.Main;
import stockapp.model.StockExchange;

public class StockExchangeOverviewController {

	@FXML
	private TreeTableView<StockExchange> stockExchangeTable;
	@FXML
	private TreeTableColumn<StockExchange, String> stockExchangeColumn;

	private Main main;
	
	public StockExchangeOverviewController() {
		// TODO Auto-generated constructor stub
	}

	@FXML
	private void initialize() {
		
		
	}
	
	public void setMain(Main main) {
		this.main=main;
	}


}
