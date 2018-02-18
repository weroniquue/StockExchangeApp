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
import stockapp.model.CommodityPaper;
import stockapp.model.InvestmentFund;
import stockapp.model.InvestmentFundPaper;
import stockapp.model.InvestorIndividual;
import stockapp.model.Stock;

public class InvestmentFundOverviewController {

	@FXML
	private TableView<InvestmentFund> investmentFundTable;
	@FXML
	private TableColumn<InvestmentFund, String> investmentFundNameColumn;
	
	
	@FXML
	private TableView<Stock> stockTable;
	@FXML
	private TableColumn<Stock, String> companyColumn;
	@FXML
	private TableColumn<Stock, Number> priceColumn;
	
	@FXML
	private TableView<CommodityPaper> commodityTable;
	@FXML
	private TableColumn<CommodityPaper, String> commodityColumn;
	@FXML
	private TableColumn<CommodityPaper, Number> amountCommodityColumn;
	@FXML
	private TableColumn<CommodityPaper, Number> priceCommodityColumn;
	
	@FXML
	private Label nameInvestmentFund;
	@FXML
	private Label nameManager;
	@FXML
	private Label seconNameManager;
	
	Main main;
	
	public InvestmentFundOverviewController() {
		// TODO Auto-generated constructor stub
	}
	
	@FXML
	private void initialize() {
		investmentFundNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameInvestmentFoundProperty());
		
		investmentFundTable.getSelectionModel().selectedItemProperty()
			.addListener((observable, oldValue, newValue) -> showInvestmentFundDetails(newValue));
		
		companyColumn.setCellValueFactory(cellData-> cellData.getValue().getCompany().getCompanyNameProperty());
		priceColumn.setCellValueFactory(cellData->cellData.getValue().getPricePropertty());
		
		commodityColumn.setCellValueFactory(cellData->cellData.getValue().getCommodity().getNameProperty());
		amountCommodityColumn.setCellValueFactory(cellData->cellData.getValue().getAmountProperty());
		priceCommodityColumn.setCellValueFactory(cellData->cellData.getValue().getPriceProperty());
		
	}
	
	public void setMain(Main main) {
		this.main=main;
		
		/**Add investment Fund to table.*/
		investmentFundTable.setItems(main.getInvestmentFundData());
	}
	
	public void showInvestmentFundDetails(InvestmentFund investmentFund) {
		if (investmentFund != null) {
			nameInvestmentFund.setText(investmentFund.getNameInvestmentFound());
			stockTable.setItems(investmentFund.getStock());	
			commodityTable.setItems(investmentFund.getCommodityPapers());
			nameManager.setText(investmentFund.getNameManager());
			seconNameManager.setText(investmentFund.getSecondNameManager());
		}
	}
	
	public void deleteInvestmentFund() {
		int selectedInvestmentFund = investmentFundTable.getSelectionModel().getSelectedIndex();

		if (selectedInvestmentFund >= 0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Delete");
			alert.setHeaderText("Are you sure?");
			alert.setContentText("Do you want to delete chosen investment fund?");

			ButtonType buttonYes = new ButtonType("Yes");
			ButtonType buttonNo = new ButtonType("No");

			alert.getButtonTypes().setAll(buttonYes, buttonNo);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonYes) {
				investmentFundTable.getItems().remove(selectedInvestmentFund);
				investmentFundTable.getSelectionModel().getSelectedItem().setStart(false);
				main.getInvestmentFundData().remove(investmentFundTable.getSelectionModel().getSelectedItem());
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("No selection");
			alert.setHeaderText("No investment fund selected");
			alert.setContentText("Please select investment in the table");

			alert.showAndWait();
		}
	}
	
}
