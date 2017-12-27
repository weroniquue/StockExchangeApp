package stockapp.view;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import stockapp.Main;
import stockapp.model.Company;
import stockapp.model.StockExchange;

public class CreateIndexController {

	private Main main;
	private Stage stage;
	@FXML
	private ComboBox<StockExchange> stockExchangeBox;
	@FXML
	private ListView<Company> companyList;

	public CreateIndexController() {
	}

	@FXML
	public void initialize() {
		StringConverter<StockExchange> converter = new StringConverter<StockExchange>() {
			@Override
			public String toString(StockExchange item) {
				return item.getNameStockExchange();
			}

			@Override
			public StockExchange fromString(String string) {
				return null;
			}
		};
		stockExchangeBox.setConverter(converter);

		companyList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		companyList.setCellFactory(parm -> new ListCell<Company>() {
			@Override
			protected void updateItem(Company item, boolean empty) {
				super.updateItem(item, empty);

				if (empty || item == null || item.getCompanyName() == null) {
					setText("");
				} else {
					setText(item.getCompanyName());
				}
			}
		});
	}

	public void setMain(Main main) {
		this.main = main;
		stockExchangeBox.setItems(main.getStockExchangeData());
		companyList.setItems(main.getCompanyData());
		companyList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void handleClose() {
		stage.close();
	}
}