package stockapp.view;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerExpression;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;
import stockapp.Main;
import stockapp.model.Company;

public class CompanyOverviewController {
	@FXML
	private TableView<Company> companyTable;
	@FXML
	private TableColumn<Company, String> companyColumn;

	@FXML
	private Label companyNameLabel;
	@FXML
	private Label dateOfFirstValuationLabel;
	@FXML
	private Label openiningPriceLabel;
	@FXML
	private Label currentPriceLabel;
	@FXML
	private Label minimumPriceLabel;
	@FXML
	private Label numberOfStockLabel;
	@FXML
	private Label profitsLabel;
	@FXML
	private Label incomeLabel;
	@FXML
	private Label equitalCapitalLabel;
	@FXML
	private Label initialCapitalLabel;
	@FXML
	private Label volumeLabel;
	@FXML
	private Label assetsTurnoverLabel;
	@FXML
	private LineChart<?, ?> linechart;
	@FXML
	private CategoryAxis x;
	@FXML
	private NumberAxis y;

	XYChart.Series seria1 = new XYChart.Series();

	// Reference to the main application.
	private Main main;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

	public CompanyOverviewController() {
	}

	@FXML
	private void initialize() {
		/**
		 * Initialize table Company:
		 * */
		companyColumn.setCellValueFactory(cellData -> cellData.getValue().getCompanyNameProperty());

		showCompanyDetails(null);

		/**It observe changes in the table*/
		companyTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showCompanyDetails(newValue));

		linechart.setCreateSymbols(false);
		linechart.setAnimated(true);
		linechart.setLegendVisible(false);

	}

	public void setMainApp(Main mainApp) {
		this.main = mainApp;

		/** Add observable list data to the table*/
		companyTable.setItems(mainApp.getCompanyData());
	}

	/**It show informations about selected company in the table.*/
	public void showCompanyDetails(Company company) {
		if (company != null) {
			//Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), ev -> {
				/*openiningPriceLabel.setText(String.valueOf(company.getOpeniningPrice()));
				currentPriceLabel.setText(String.valueOf(company.getCurrentPrice()));
				minimumPriceLabel.setText(String.valueOf(company.getMinimumPrice()));*/
				//numberOfStockLabel.setText(String.valueOf(company.getNumberOfStock()));
				/*profitsLabel.setText(String.valueOf(company.getProfit()));
				incomeLabel.setText(String.valueOf(company.getProfit()));
				equitalCapitalLabel.setText(String.valueOf(company.getEquitalCapital()));
				initialCapitalLabel.setText(String.valueOf(company.getInitialCapital()));
				volumeLabel.setText(String.valueOf(company.getVolume()));
				assetsTurnoverLabel.setText(String.valueOf(company.getAssetsTurnover()));

			}));
			timeline.setCycleCount(Animation.INDEFINITE);
			timeline.play();*/
			
			
			companyNameLabel.setText(company.getCompanyName());
			dateOfFirstValuationLabel.setText(company.getDateOfFirstValuation());
			openiningPriceLabel.setText(String.valueOf(company.getOpeniningPrice()));
			currentPriceLabel.setText(String.valueOf(company.getCurrentPrice()));
			minimumPriceLabel.setText(String.valueOf(company.getMinimumPrice()));
			numberOfStockLabel.setText(String.valueOf(company.getNumberOfStock()));
			profitsLabel.setText(String.valueOf(company.getProfit()));
			incomeLabel.setText(String.valueOf(company.getProfit()));
			equitalCapitalLabel.setText(String.valueOf(company.getEquitalCapital()));
			initialCapitalLabel.setText(String.valueOf(company.getInitialCapital()));
			volumeLabel.setText(String.valueOf(company.getVolume()));
			assetsTurnoverLabel.setText(String.valueOf(company.getAssetsTurnover()));
			
			
			seria1.getData().clear();
			for(int i=0;i<company.getCurrentPriceList().size();i++) {
				seria1.getData().add(new XYChart.Data<>(company.timeList.get(i).format(dtf),company.getCurrentPriceList().get(i)));
			}
			linechart.getData().add(seria1);


		} else {
			companyNameLabel.setText("Company details");
			dateOfFirstValuationLabel.setText("");
			openiningPriceLabel.setText("");
			currentPriceLabel.setText("");
			minimumPriceLabel.setText("");
			numberOfStockLabel.setText("");
			profitsLabel.setText("");
			incomeLabel.setText("");
			equitalCapitalLabel.setText("");
			initialCapitalLabel.setText("");
			volumeLabel.setText("");
			assetsTurnoverLabel.setText("");
		}
	}

	/**It allows to delete company from app.*/
	public void deleteCompany() {
		int selectedIndex = companyTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Delete");
			alert.setHeaderText("Are you sure?");
			alert.setContentText("Do you want to delete chosen company?");

			ButtonType buttonYes = new ButtonType("Yes");
			ButtonType buttonNo = new ButtonType("No");

			alert.getButtonTypes().setAll(buttonYes, buttonNo);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonYes) {
				companyTable.getItems().get(selectedIndex).setStart(false);
				companyTable.getItems().remove(selectedIndex);
				main.getCompanyData().remove(companyTable.getSelectionModel().getSelectedItem());
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("No selection");
			alert.setHeaderText("No company selected");
			alert.setContentText("Please select company in the table");

			alert.showAndWait();
		}
	}
}
