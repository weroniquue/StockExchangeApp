package stockapp.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import stockapp.Main;
import stockapp.model.Company;
import stockapp.model.Index;
import stockapp.model.StockExchange;

public class CreateIndexController {

	private Main main;
	private Stage stage;
	@FXML
	private ComboBox<StockExchange> stockExchangeBox;
	@FXML
	private ListView<Company> companyList;
	@FXML
	private TextField nameField;

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
		companyList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	public void setMain(Main main) {
		this.main = main;
		stockExchangeBox.setItems(main.getStockExchangeData());
		companyList.setItems(main.getCompanyData());

	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void handleClose() {
		stage.close();
	}

	public void handleSave() {
		if (isValid()) {
			Index tmp=new Index();
			tmp.setNameIndex(nameField.getText());
			tmp.getCompanyInIndex().addAll(companyList.getSelectionModel().getSelectedItems());
			stockExchangeBox.getSelectionModel().getSelectedItem().getListOfIndex().add(tmp);
			
			stage.close();
			
			Alert alert=new Alert(AlertType.INFORMATION);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("Create Index");
			alert.setHeaderText("Create index");
			alert.setContentText("The index is created properly");

			alert.showAndWait();

		}
		
	}

	private boolean isValid() {
		String error="";
		if(nameField==null||nameField.getText().length()==0) {
			error+="No valid index name!\n";
		}
		if(stockExchangeBox.getSelectionModel().getSelectedIndex()<0) {
			error+="No valid Stock exchange! \n";
		}
		if(companyList.getSelectionModel().isEmpty()) {
			error+="No selected items on list!\n";
		}
		if(error.length()==0) {
			return true;
		}else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("No valid index");
			alert.setHeaderText("Error");
			alert.setContentText(error);

			alert.showAndWait();
			return false;
		}
	}
}
