package stockapp.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import stockapp.Main;
import stockapp.model.Company;

public class CompanyAddController {
	@FXML
	private TextField companyNameTextField;
	@FXML
	private DatePicker dateOfFirstValuationTextField;
	@FXML
	private TextField openiningPriceTextField;
	@FXML
	private TextField currentPriceTextField;
	@FXML
	private TextField minimumPriceTextField;
	@FXML
	private TextField numberOfStockTextField;
	@FXML
	private TextField profitsTextField;
	@FXML
	private TextField incomeTextField;
	@FXML
	private TextField equitalCapitalTextField;
	@FXML
	private TextField initialCapitalTextField;
	@FXML
	private TextField volumeTextField;
	@FXML
	private TextField assetsTurnoverTextField;

	private String dateString;

	private Main main;
	private Stage stage;

	public void setMain(Main main) {
		this.main = main;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public String convertDate(LocalDate date) {
		dateString = String.valueOf(date.getDayOfMonth()) + "." + String.valueOf(date.getDayOfMonth()) + "."
				+ String.valueOf(date.getYear());
		return dateString;
	}

	public void handleOK() {
		if (isInputValid()) {
			Company tmp = new Company("a");
			tmp.setCompanyName(companyNameTextField.getText());

			dateOfFirstValuationTextField.setConverter(new StringConverter<LocalDate>() {
				String pattern = "dd.MM.yyyy";
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

				@Override
				public String toString(LocalDate date) {
					if (date != null) {
						return dateFormatter.format(date);
					} else {
						return "";
					}
				}

				@Override
				public LocalDate fromString(String string) {
					if (string != null && !string.isEmpty()) {
						return LocalDate.parse(string, dateFormatter);
					} else {
						return null;
					}
				}
			});

			tmp.setDateOfFirstValuation(convertDate(dateOfFirstValuationTextField.getValue()));
			tmp.setOpeniningPrice(Double.parseDouble(openiningPriceTextField.getText()));
			tmp.setCurrentPrice(Double.parseDouble(currentPriceTextField.getText()));
			tmp.setMinimumPrice(Double.parseDouble(minimumPriceTextField.getText()));
			tmp.setNumberOfStock(Integer.parseInt(numberOfStockTextField.getText()));
			tmp.setProfit(Double.parseDouble(profitsTextField.getText()));
			tmp.setIncome(Double.parseDouble(incomeTextField.getText()));
			tmp.setEquitalCapital(Double.parseDouble(equitalCapitalTextField.getText()));
			tmp.setInitialCapital(Double.parseDouble(initialCapitalTextField.getText()));
			tmp.setVolume(Integer.parseInt(volumeTextField.getText()));
			tmp.setAssetsTurnover(Double.parseDouble(assetsTurnoverTextField.getText()));

			tmp.start();
			main.getCompanyData().add(tmp);
			stage.close();

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("Completed");
			alert.setHeaderText("Company added");

			alert.showAndWait();
		}
	}

	public boolean isInputValid() {
		String errorMessage = "";

		if (companyNameTextField.getText() == null || companyNameTextField.getText().length() == 0) {
			errorMessage += "No valid company name!\n";
		}

		if (dateOfFirstValuationTextField.getValue() == null) {
			errorMessage += "No valid date of first valuation!\n";
		}

		if (openiningPriceTextField.getText() == null || openiningPriceTextField.getText().length() == 0) {
			errorMessage += "No valid value of opening price!\n";
		} else {
			try {
				Double.parseDouble(openiningPriceTextField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid opening price (must be number with dot)!\n";
			}
		}

		if (currentPriceTextField.getText() == null || currentPriceTextField.getText().length() == 0) {
			errorMessage += "No valid current price!\n";
		} else {
			try {
				Double.parseDouble(currentPriceTextField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid current price (must be number with dot)!\n";
			}
		}

		if (minimumPriceTextField.getText() == null || minimumPriceTextField.getText().length() == 0) {
			errorMessage += "No valid value of minimum price!\n";
		} else {
			try {
				Double.parseDouble(minimumPriceTextField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid minimum price (must be number with dot)!\n";
			}
		}

		if (numberOfStockTextField.getText() == null || numberOfStockTextField.getText().length() == 0) {
			errorMessage += "No valid value of number of stock!\n";
		} else {
			try {
				Integer.parseInt(numberOfStockTextField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid number of stock (must be number)!\n";
			}
		}

		if (profitsTextField.getText() == null || profitsTextField.getText().length() == 0) {
			errorMessage += "No valid value of profits!\n";
		} else {
			try {
				Double.parseDouble(profitsTextField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid profits (must be number)!\n";
			}
		}

		if (incomeTextField.getText() == null || incomeTextField.getText().length() == 0) {
			errorMessage += "No valid value of income!\n";
		} else {
			try {
				Double.parseDouble(incomeTextField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid income (must be number)!\n";
			}
		}

		if (equitalCapitalTextField.getText() == null || equitalCapitalTextField.getText().length() == 0) {
			errorMessage += "No valid value of equaital capital!\n";
		} else {
			try {
				Double.parseDouble(equitalCapitalTextField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid equital capital (must be number)!\n";
			}
		}

		if (initialCapitalTextField.getText() == null || initialCapitalTextField.getText().length() == 0) {
			errorMessage += "No valid value of initial capital!\n";
		} else {
			try {
				Double.parseDouble(initialCapitalTextField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid initial capital (must be number)!\n";
			}
		}

		if (volumeTextField.getText() == null || volumeTextField.getText().length() == 0) {
			errorMessage += "No valid value of volume!\n";
		} else {
			try {
				Integer.parseInt(volumeTextField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid volume (must be integer)!\n";
			}
		}

		if (assetsTurnoverTextField.getText() == null || assetsTurnoverTextField.getText().length() == 0) {
			errorMessage += "No valid value of assets turnover!\n";
		} else {
			try {
				Double.parseDouble(assetsTurnoverTextField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid assets turnover (must be number)!\n";
			}
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

	public void handleCancel() {
		stage.close();
	}

}
