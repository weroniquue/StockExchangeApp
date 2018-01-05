package stockapp.view;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Duration;
import stockapp.Main;
import stockapp.model.Investor;

public class InvestorOverviewController {

	@FXML
	private TableView<Investor> investorTable;
	@FXML
	private TableColumn<Investor, String> investorNameColumn;
	@FXML
	private TableColumn<Investor, String> investorSecondNameColumn;
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
		
		
		
	}

	public void setMain(Main main) {
		this.main = main;

		investorTable.setItems(main.getInvestorData());
	}

	public void showInvestorDetails(Investor investor) {

		if (investor != null) {
			pesel.setText(investor.getNumberPESEL());
			budget.setText(String.valueOf(investor.getBudget()));
			Timeline timeline=new Timeline(new KeyFrame(Duration.seconds(1), ev-> {
				budget.setText(String.valueOf(investor.getBudget()));
			}));
			timeline.setCycleCount(Animation.INDEFINITE);
			timeline.play();
			
		}

}
}