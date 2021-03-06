package stockapp.view;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import stockapp.Main;
import stockapp.model.Currency;

public class CurrencyOverviewController {

	private Main main;

	@FXML
	private TableView<Currency> currencyTable;
	@FXML
	private TableColumn<Currency, String> currencyColumn;
	@FXML
	private TableColumn<Currency, String> codeColumn;
	@FXML
	private TableView<String> whereIsUsedTable;
	@FXML
	private TableColumn<String, String> whereIsUsedColumn;
	@FXML
	private Label currencyNameLabel;
	@FXML
	private ListView<String> whereIsUsed;

	public CurrencyOverviewController() {
	};

	@FXML
	private void initialize() {
		currencyColumn.setCellValueFactory(cellData -> cellData.getValue().getNameCurrencyProperty());
		codeColumn.setCellValueFactory(cellData -> cellData.getValue().getCodeCurrencyProperty());

		currencyTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showCurrencyDetails(newValue));
	}

	public void setMain(Main main) {
		this.main = main;
		currencyTable.setItems(main.getCurrencyData());
	}

	/**Show details about currency. */
	public void showCurrencyDetails(Currency currency) {
		if (currency != null) {
			whereIsUsed.setItems(currency.getWhereIsUsed());
			currencyNameLabel.setText(currency.getNameCurrency());
		} else {
			currencyNameLabel.setText("");
		}
	}

	/**Create new stage which is responsible for add new country to list.*/
	public void showAddCountry() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(main.getClass().getResource("view/AddCountryCurrency.fxml"));
			Pane add = loader.load();

			Stage addWindow = new Stage();
			addWindow.setTitle("Add");
			addWindow.initOwner(main.getPrimaryStage());
			addWindow.sizeToScene();

			Scene scene = new Scene(add);
			addWindow.setScene(scene);

			AddCountryController controller = loader.getController();
			controller.setStage(addWindow);
			controller.setCurrencyController(this);

			addWindow.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**It adds the country to list.*/
	public void addCountry() {
		int choosen = currencyTable.getSelectionModel().getSelectedIndex();
		if (choosen >= 0) {
			showAddCountry();
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("No selection");
			alert.setHeaderText("No currency selected");
			alert.setContentText("Please select currency in the table on left side");

			alert.showAndWait();
		}

	}

	/**It removes coutry from list.*/
	public void removeCountry() {
		int choosen = currencyTable.getSelectionModel().getSelectedIndex();
		int choosenCountry = whereIsUsed.getSelectionModel().getSelectedIndex();
		if (choosen >= 0) {
			if (choosenCountry >= 0) {
				currencyTable.getSelectionModel().getSelectedItem().getWhereIsUsed().remove(choosenCountry);

			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.initOwner(main.getPrimaryStage());
				alert.setTitle("No selection");
				alert.setHeaderText("No country selected");
				alert.setContentText("Please select country in the list to delete");

				alert.showAndWait();
			}

		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("No selection");
			alert.setHeaderText("No currency selected");
			alert.setContentText("Please select currency in the table on left side");

			alert.showAndWait();
		}
	}

	public TableView<Currency> getCurrencyTable() {
		return currencyTable;
	}

	/**It allows to delete currency from app.*/
	public void deleteCurrency() {
		int selectedCurrency = currencyTable.getSelectionModel().getSelectedIndex();

		if (selectedCurrency >= 0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Delete");
			alert.setHeaderText("Are you sure?");
			alert.setContentText("Do you want to delete chosen currency?");

			ButtonType buttonYes = new ButtonType("Yes");
			ButtonType buttonNo = new ButtonType("No");

			alert.getButtonTypes().setAll(buttonYes, buttonNo);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonYes) {
				currencyTable.getItems().remove(selectedCurrency);
				main.getCurrencyData().remove(currencyTable.getSelectionModel().getSelectedItem());
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("No selection");
			alert.setHeaderText("No currency selected");
			alert.setContentText("Please select currency in the table");

			alert.showAndWait();
		}
	}

}
