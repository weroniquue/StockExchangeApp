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
import stockapp.model.InvestmentFundPaper;
import stockapp.model.InvestorIndividual;
import stockapp.model.Stock;


public class InvestorOverviewController {

	@FXML
	private TableView<InvestorIndividual> investorTable;
	@FXML
	private TableColumn<InvestorIndividual, String> investorNameColumn;
	@FXML
	private TableColumn<InvestorIndividual, String> investorSecondNameColumn;
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
	private TableView<InvestmentFundPaper> investmentFundTable;
	@FXML
	private TableColumn<InvestmentFundPaper, String> investmentFundNameColumn;
	@FXML
	private TableColumn<InvestmentFundPaper, Number> priceInvestmentFundColumn;
	
	@FXML
	private Label name;
	@FXML
	private Label pesel;
	@FXML
	private Label budget;

	private Main main;

	public InvestorOverviewController() {
		// TODO Auto-generated constructor stub
	}

	@FXML
	private void initialize() {
		investorNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameInvestorProperty());
		investorSecondNameColumn.setCellValueFactory(cellData -> cellData.getValue().getSecondNameInvestorProperty());

		investorTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showInvestorDetails(newValue));
		
		companyColumn.setCellValueFactory(cellData-> cellData.getValue().getCompany().getCompanyNameProperty());
		priceColumn.setCellValueFactory(cellData->cellData.getValue().getPricePropertty());
		
		commodityColumn.setCellValueFactory(cellData->cellData.getValue().getCommodity().getNameProperty());
		amountCommodityColumn.setCellValueFactory(cellData->cellData.getValue().getAmountProperty());
		priceCommodityColumn.setCellValueFactory(cellData->cellData.getValue().getPriceProperty());
		
		investmentFundNameColumn.setCellValueFactory(cellData->cellData.getValue().getInvestmentFund().getNameInvestmentFoundProperty());
		priceInvestmentFundColumn.setCellValueFactory(cellData->cellData.getValue().getBudget());
		
		

	}

	public void setMain(Main main) {
		this.main = main;

		/**Add data to table.*/
		investorTable.setItems(main.getInvestorData());
	}

	/**It show informations about selected investor.*/
	public void showInvestorDetails(InvestorIndividual investor) {
		if (investor != null) {
			name.setText(investor.getNameInvestor()+" "+investor.getSecondNameInvestor());
			stockTable.setItems(investor.getStock());	
			commodityTable.setItems(investor.getCommodityPapers());
			investmentFundTable.setItems(investor.getInvestmentFundsPapers());
			pesel.setText(investor.getNumberPESEL());
			budget.setText(String.valueOf(investor.getBudget()));
		}

	}
	/**It allows to delete investro from app.*/
	public void deleteInvestor() {
		int selectedInvestor = investorTable.getSelectionModel().getSelectedIndex();

		if (selectedInvestor >= 0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Delete");
			alert.setHeaderText("Are you sure?");
			alert.setContentText("Do you want to delete chosen investor?");

			ButtonType buttonYes = new ButtonType("Yes");
			ButtonType buttonNo = new ButtonType("No");

			alert.getButtonTypes().setAll(buttonYes, buttonNo);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonYes) {
				investorTable.getItems().remove(selectedInvestor);
				investorTable.getSelectionModel().getSelectedItem().setStart(false);
				main.getInvestorData().remove(investorTable.getSelectionModel().getSelectedItem());
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("No selection");
			alert.setHeaderText("No investor selected");
			alert.setContentText("Please select investor in the table");

			alert.showAndWait();
		}
	}
}