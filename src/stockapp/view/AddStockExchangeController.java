package stockapp.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import stockapp.Main;
import stockapp.model.StockExchange;

public class AddStockExchangeController {

	private Main main;
	private Stage stage;

	@FXML
	private TextField nameField;
	@FXML
	private TextField currencyCodeField;
	@FXML
	private TextField addressField;
	@FXML
	private TextField cityField;

	public AddStockExchangeController() {
		// TODO Auto-generated constructor stub
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void handleClose() {
		stage.close();
	}

	public void handleSave() {
		if (isInputValid()) {

			StockExchange tmp = new StockExchange();

			tmp.setNameStockExchange(nameField.getText());
			tmp.setCurrencyStockExchange(currencyCodeField.getText());
			tmp.setAddressStockExchange(addressField.getText());
			tmp.setCityStockExchange(cityField.getText());

			main.getStockExchangeData().add(tmp);

			stage.close();

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("Completed");
			alert.setHeaderText("Stock exchange added");

			alert.showAndWait();
		}
	}

	public boolean isInputValid() {
		String errorMessage = "";

		if (nameField.getText() == null || nameField.getText().length() == 0) {
			errorMessage += "No valid stock exchange name!\n";
		}
		if (currencyCodeField.getText() == null || currencyCodeField.getText().length() == 0) {
			errorMessage += "No valid currency code!\n";
		}
		if (addressField.getText() == null || addressField.getText().length() == 0) {
			errorMessage += "No valid address!\n";
		}
		if (cityField.getText() == null || cityField.getText().length() == 0) {
			errorMessage += "No valid city!\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {

			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(stage);
			alert.setTitle("Invalid fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}

	}

}
