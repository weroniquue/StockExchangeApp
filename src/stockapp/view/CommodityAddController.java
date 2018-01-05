package stockapp.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import stockapp.Main;
import stockapp.model.Commodity;
import stockapp.model.Currency;
public class CommodityAddController {
	@FXML
	private TextField nameField;
	@FXML
	private TextField measurementsUnitField;
	@FXML
	private ChoiceBox<Currency> currencyChoiceBox;
	@FXML
	private TextField currentPriceField;
	@FXML
	private TextField minimalPriceField;
	@FXML
	private TextField maximalPriceField;

	Main main;
	Stage stage;

	public CommodityAddController() {
		// TODO Auto-generated constructor stub
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		StringConverter<Currency> converter = new StringConverter<Currency>() {
			@Override
			public String toString(Currency item) {
				return item.getNameCurrency();
			}

			@Override
			public Currency fromString(String string) {
				return null;
			}
		};
		currencyChoiceBox.setConverter(converter);
	}

	public void setMain(Main main) {
		this.main = main;
		currencyChoiceBox.getItems().addAll(main.getCurrencyData());
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void handleClose() {
		stage.close();
	}

	public void handleSave() {
		if (isValid()) {
			Commodity tmp=new Commodity();
			tmp.setName(nameField.getText());
			tmp.setMeasurementUnit(measurementsUnitField.getText());
			tmp.setCurrency(currencyChoiceBox.getValue());
			tmp.setCurrentPrice(Double.parseDouble(currentPriceField.getText()));
			tmp.setMinimalPrice(Double.parseDouble(minimalPriceField.getText()));
			tmp.setMaximalPrice(Double.parseDouble(maximalPriceField.getText()));
			
			main.getCommodityData().add(tmp);
	
			stage.close();
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("Completed");
			alert.setHeaderText("Commodity added");

			alert.showAndWait();
		}
	}

	public boolean isValid() {
		String error = "";

		if (nameField.getText() == null || nameField.getText().length() == 0) {
			error += "No valid commodity name!\n";
		}
		if (measurementsUnitField.getText() == null || measurementsUnitField.getText().length() == 0) {
			error += "No valid mesurements unit! \n";
		}

		if (currencyChoiceBox.getValue() == null) {
			error += "No valid currency!\n";
		}
		if (currentPriceField.getText() == null || currentPriceField.getText().length() == 0) {
			error += "No valid value of current price!\n";
		} else {
			try {
				Float.parseFloat(currentPriceField.getText());
			} catch (NumberFormatException e) {
				error += "No valid current price (must be float)!\n";
			}
		}
		if (minimalPriceField.getText() == null || minimalPriceField.getText().length() == 0) {
			error += "No valid value of minimal price!\n";
		} else {
			try {
				Float.parseFloat(minimalPriceField.getText());
			} catch (NumberFormatException e) {
				error += "No valid minimal price (must be float)!\n";
			}
		}
		
		if (maximalPriceField.getText() == null || maximalPriceField.getText().length() == 0) {
			error += "No valid value of maximal price!\n";
		} else {
			try {
				Float.parseFloat(maximalPriceField.getText());
			} catch (NumberFormatException e) {
				error += "No valid maximal price (must be float)!\n";
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
