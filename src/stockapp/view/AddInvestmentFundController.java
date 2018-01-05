package stockapp.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import stockapp.Main;
import stockapp.model.InvestmentFund;

public class AddInvestmentFundController {

	@FXML
	private TextField nameInvestmentFund;
	@FXML
	private TextField nameManager;
	@FXML
	private TextField secondNameManager;
	
	private Main main;
	private Stage stage;
	
	public void setStage(Stage stage) {
		this.stage=stage;
	}
	
	public void setMain(Main main) {
		this.main=main;
	}
	
	public void handleCancel() {
		stage.close();
	}
	
	public void handleSave() {
		if(isValid()) {
			InvestmentFund tmp= new InvestmentFund();
			tmp.setNameInvestmentFound(nameInvestmentFund.getText());
			tmp.setNameManager(nameManager.getText());
			tmp.setSecondNameManager(secondNameManager.getText());
			
			main.getInvestmentFundData().add(tmp);
		}
	}
	
	public boolean isValid() {
		String error="";
		if(nameInvestmentFund.getText()==null||nameInvestmentFund.getText().length()==0) {
			error+="No valid Investment Fund name!\n";
		}
		if(nameManager.getText()==null||nameManager.getText().length()==0) {
			error+="No valid Manager name!\n";
		}
		if(secondNameManager.getText()==null||secondNameManager.getText().length()==0) {
			error+="No valid Manager second name!\n";
		}
		
		if(error.length()==0) {
			return true;
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(stage);
			alert.setTitle("Invalid fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(error);

			alert.showAndWait();

			return false;
		}
	}
}
