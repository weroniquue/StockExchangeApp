package stockapp.view;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
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
	// Reference to the main application.
	private Main main;

	public CompanyOverviewController() {
	}

	@FXML
	private void initialize() {
		// Initialize table Company:
		companyColumn.setCellValueFactory(cellData -> cellData.getValue().getCompanyNameProperty());

		showCompanyDetails(null);

		companyTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showCompanyDetails(newValue));
	}

	public void setMainApp(Main mainApp) {
		this.main = mainApp;

		// Add observable list data to the table
		companyTable.setItems(mainApp.getCompanyData());
	}

	public void showCompanyDetails(Company company) {
		if (company != null) {
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

	public void deleteCompany() {
		int selectedIndex = companyTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Delete");
			alert.setHeaderText("Are you sure?");
			alert.setContentText("Do you want to delete choosing company?");

			ButtonType buttonYes = new ButtonType("Yes");
			ButtonType buttonNo = new ButtonType("No");

			alert.getButtonTypes().setAll(buttonYes, buttonNo);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonYes) {
				companyTable.getItems().remove(selectedIndex);
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
