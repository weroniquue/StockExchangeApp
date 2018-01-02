package stockapp.view;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.util.StringConverter;
import stockapp.Main;
import stockapp.model.Company;
import stockapp.model.Index;
import stockapp.model.StockExchange;

public class StockExchangeOverviewController {
	
	private Main main;

	@FXML
	private TableView<StockExchange> stockExchangeTable;
	@FXML
	private TableColumn<StockExchange, String> stockExchangeColumn;
	@FXML
	private TableView<Index> indexTable;
	@FXML
	private TableColumn<Index, String> indexColumn;
	@FXML
	private ListView<Company> listCompany;
	@FXML
	private Label countryLabel;
	@FXML
	private Label currencyLabel;
	@FXML
	private Label addressLabel;
	@FXML
	private Label cityLabel;
	
	private StringConverter<Index> converter;

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
		
		showDetailsAboutStockExchange(null);

		
		stockExchangeTable.getSelectionModel().selectedItemProperty().
			addListener((observable,oldValue,newValue)-> showDetailsAboutStockExchange(newValue));
		
		indexColumn.setCellValueFactory(cellData->cellData.getValue().getNameIndexProperty());
		
		indexTable.getSelectionModel().selectedItemProperty().
			addListener((observable,oldValue,newValue)-> drawGraph(newValue));
	
	
	}
	
	public void showDetailsAboutStockExchange(StockExchange stockExchange) {
		if(stockExchange!=null) {
			
			indexTable.setItems(stockExchange.getListOfIndex());
			countryLabel.setText(stockExchange.getCountryStockExchange());
			currencyLabel.setText(stockExchange.getCurrencyStockExchange());
			addressLabel.setText(stockExchange.getAddressStockExchange());
			cityLabel.setText(stockExchange.getCityStockExchange());
		}else {
			countryLabel.setText("");
			currencyLabel.setText("");
			addressLabel.setText("");
			cityLabel.setText("");
		}
		
	}
	
	public void drawGraph(Index index) {
		
	}
	
	

}
