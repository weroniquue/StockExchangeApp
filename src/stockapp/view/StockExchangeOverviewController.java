package stockapp.view;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;
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
	@FXML
	private LineChart<?,?> linechart;
	@FXML
	private CategoryAxis x;
	@FXML
	private NumberAxis y;
	

	private StringConverter<Index> converter;

	public StockExchangeOverviewController() {
		// TODO Auto-generated constructor stub
	}

	public void setMain(Main main) {
		this.main = main;
		stockExchangeTable.setItems(main.getStockExchangeData());
	}

	@FXML
	private void initialize() {
		stockExchangeColumn.setCellValueFactory(cellData -> cellData.getValue().getNameStockExchangeProperty());

		showDetailsAboutStockExchange(null);

		stockExchangeTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showDetailsAboutStockExchange(newValue));

		indexColumn.setCellValueFactory(cellData -> cellData.getValue().getNameIndexProperty());

		indexTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> drawGraph(newValue));

		listCompany.setCellFactory(new Callback<ListView<Company>, ListCell<Company>>() {

			@Override
			public ListCell<Company> call(ListView<Company> p) {

				ListCell<Company> cell = new ListCell<Company>() {

					@Override
					protected void updateItem(Company company, boolean bln) {
						super.updateItem(company, bln);
						if (company != null) {
							setText(company.getCompanyName());
						}
					}
				};
				return cell;
			}
		});
		
	}

	public void showDetailsAboutStockExchange(StockExchange stockExchange) {
		if (stockExchange != null) {
			indexTable.setItems(stockExchange.getListOfIndex());
			countryLabel.setText(stockExchange.getCountryStockExchange());
			currencyLabel.setText(stockExchange.getCurrencyStockExchange());
			addressLabel.setText(stockExchange.getAddressStockExchange());
			cityLabel.setText(stockExchange.getCityStockExchange());
		} else {
			countryLabel.setText("");
			currencyLabel.setText("");
			addressLabel.setText("");
			cityLabel.setText("");
		}

	}

	public void drawGraph(Index index) {
		if (index != null) {
			listCompany.setItems(index.getCompanyInIndex());
			
			
		}

	}

	public void handeDeleteIndex() {
		if (stockExchangeTable.getSelectionModel().getSelectedIndex() >= 0) {
			int selectedIndex = indexTable.getSelectionModel().getSelectedIndex();
			if (selectedIndex >= 0) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Delete");
				alert.setHeaderText("Are you sure?");
				alert.setContentText("Do you want to delete choosing index?");

				ButtonType buttonYes = new ButtonType("Yes");
				ButtonType buttonNo = new ButtonType("No");

				alert.getButtonTypes().setAll(buttonYes, buttonNo);

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == buttonYes) {
					indexTable.getItems().remove(selectedIndex);
				}
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.initOwner(main.getPrimaryStage());
				alert.setTitle("No selection");
				alert.setHeaderText("No index selected");
				alert.setContentText("Please select index in the second table");

				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("No selection");
			alert.setHeaderText("No stock exchange selected");
			alert.setContentText("Please select stock exchange in the first table");

			alert.showAndWait();
		}
	}

	public void handleDeleteStockExchange() {
		int selectedStockExchange = stockExchangeTable.getSelectionModel().getSelectedIndex();
		if (selectedStockExchange >= 0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Delete");
			alert.setHeaderText("Are you sure?");
			alert.setContentText("Do you want to delete choosing Stock Exchange?");

			ButtonType buttonYes = new ButtonType("Yes");
			ButtonType buttonNo = new ButtonType("No");

			alert.getButtonTypes().setAll(buttonYes, buttonNo);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonYes) {
				stockExchangeTable.getItems().remove(selectedStockExchange);
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("No selection");
			alert.setHeaderText("No stock exchange selected");
			alert.setContentText("Please select stock exchange in the first table");

			alert.showAndWait();
		}

	}

}
