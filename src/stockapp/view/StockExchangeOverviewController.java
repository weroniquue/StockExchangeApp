package stockapp.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import stockapp.Main;
import stockapp.model.StockExchange;

public class StockExchangeOverviewController {
	
	private Main main;

	@FXML
	private TableView<StockExchange> stockExchangeTable;
	@FXML
	private TableColumn<StockExchange, String> stockExchangeColumn;

	public StockExchangeOverviewController() {
		// TODO Auto-generated constructor stub
	}

	public void setMain(Main main) {
		this.main=main;
		stockExchangeTable.setItems(main.getStockExchangeData());
	}
	
	@FXML
	private void initialize() {
		stockExchangeColumn.setCellValueFactory(cellData->cellData.getValue().getNameStockExchangeProperty());
		
		//stockExchangeTable.getSelectionModel().selectedItemProperty().
		//addListener((observable,oldValue,newValue)-> showIndexInStockExchange(newValue));
	}
	
	

}
