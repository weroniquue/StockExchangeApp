package stockapp.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import stockapp.Main;
import stockapp.model.Investor;

public class AddInvestorController {

	private Main main;
	private Stage stage;
	
	@FXML
	private TextField nameField;
	@FXML
	private TextField secondNameField;
	@FXML
	private TextField PESEL;
	@FXML
	private TextField budget;
	
	
	public AddInvestorController() {
		// TODO Auto-generated constructor stub
	}
	
	public void setMain(Main main) {
		this.main=main;
	}
	
	public void setStage(Stage stage) {
		this.stage=stage;
	}
	
	public void handleClose() {
		stage.close();
	}
	
	public void handleSave() {
		if(isValid()) {
			Investor tmp=new Investor();
			
			tmp.setNameInvestor(nameField.getText());
			tmp.setSecondNameInvestor(secondNameField.getText());
			tmp.setNumberPESEL(PESEL.getText());
			tmp.setBudget(Double.parseDouble(budget.getText()));
			
			tmp.start();
			main.getInvestorData().add(tmp);
			
			stage.close();

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("Completed");
			alert.setHeaderText("Investor added");

			alert.showAndWait();
		}
	}
	
	public boolean isValid() {
		String error="";
		
		if(nameField.getText()==null ||nameField.getText().length()==0) {
			error+="No valid name! \n";
		}
		if(secondNameField.getText()==null||secondNameField.getText().length()==0) {
			error+="No valid second name!\n";
		}
		if(PESEL.getText()==null||PESEL.getText().length()==0) {
			error+="No valid PESEL!\n";
		}
		if(PESEL.getText().length()!=11||!PESEL.getText().matches("\\d+")) {
			error+="PESEL must consist of 11 digits!\n";
		}
		if(budget.getText()==null||budget.getText().length()==0) {
			error+="No valid budget";
		}else {
			try {
				Float.parseFloat(budget.getText());
			} catch (NumberFormatException e) {
				error += "No valid budget (must be number)!\n";
			}
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
