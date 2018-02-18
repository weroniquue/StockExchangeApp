package stockapp.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddCountryController {
	@FXML
	private TextField nameOfCountry;
	private Stage stage;
	private CurrencyOverviewController controller;
	
	public AddCountryController() {}
	
	public void setStage(Stage stage) {
		this.stage=stage;
	}
	public void setCurrencyController(CurrencyOverviewController c) {
		this.controller=c;
	}	
	
	public void handleClose() {
		stage.close();
	}
	
	/**Add the country to list if user click OK
	 * @return null*/
	public void handleOK() {
		controller.getCurrencyTable().getSelectionModel().getSelectedItem().getWhereIsUsed().add(nameOfCountry.getText());
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add country");
		alert.setHeaderText("Add country is completed!");
		alert.showAndWait();
		
		stage.close();
		
	}
	
	
}
