package stockapp.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import stockapp.Main;
import stockapp.model.Investor;

public class InvestorOverviewController {

	@FXML
	private TableView<Investor> investorTable;
	@FXML
	private TableColumn<Investor, String> investorNameColumn;
	@FXML
	private TableColumn<Investor, String> investorSecondNameColumn;
	
	
	private Main main;
	
	public InvestorOverviewController() {
		// TODO Auto-generated constructor stub
	}
	@FXML
	private void initialize() {
		investorNameColumn.setCellValueFactory(cellData-> cellData.getValue().getNameInvestorProperty());
	}
	
	
	
	public void setMain(Main main) {
		this.main=main;
		
		investorTable.setItems(main.getInvestorData());
	}
	
	
}
