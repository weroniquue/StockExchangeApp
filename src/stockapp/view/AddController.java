package stockapp.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import stockapp.Main;

public class AddController implements Initializable {

	ObservableList<String> chooseList = FXCollections.observableArrayList("Commodity","Company", "Currency", "Index",
			"Stock Exchange");

	private Main main;
	private Stage window;

	@FXML
	private ChoiceBox<String> add;

	public AddController() {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		add.getItems().addAll(chooseList);
	}

	public String getChoice(ChoiceBox<String> choice) {
		String choose = choice.getValue();
		return choose;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public void setStage(Stage window) {
		this.window = window;
	}

	public void handleCancel() {
		window.close();
	}

	public void showCompanyAddStage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(main.getClass().getResource("view/AddCompanyOverview.fxml"));
			AnchorPane addCompany = loader.load();

			Stage addCompanyStage = new Stage();
			addCompanyStage.setTitle("Add Company");
			addCompanyStage.initModality(Modality.WINDOW_MODAL);
			addCompanyStage.initOwner(main.getPrimaryStage());

			Scene scene = new Scene(addCompany);
			addCompanyStage.setScene(scene);

			CompanyAddController controller = loader.getController();
			controller.setMain(main);
			controller.setStage(addCompanyStage);

			addCompanyStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showStockExchangeAddStage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(main.getClass().getResource("view/CreateStockExchange.fxml"));
			AnchorPane addStockExchange = loader.load();

			Stage addStockExchangeStage = new Stage();
			addStockExchangeStage.setTitle("Add Company");
			addStockExchangeStage.initModality(Modality.WINDOW_MODAL);
			addStockExchangeStage.initOwner(main.getPrimaryStage());

			Scene scene = new Scene(addStockExchange);
			addStockExchangeStage.setScene(scene);

			AddStockExchangeController controller=loader.getController();
			controller.setMain(main);
			controller.setStage(addStockExchangeStage);

			addStockExchangeStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showCreateIndexStage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(main.getClass().getResource("view/CreateIndex.fxml"));
			AnchorPane addIndex = loader.load();

			Stage addIndexStage = new Stage();
			addIndexStage.setTitle("Add Company");
			addIndexStage.initModality(Modality.WINDOW_MODAL);
			addIndexStage.initOwner(main.getPrimaryStage());

			Scene scene = new Scene(addIndex);
			addIndexStage.setScene(scene);

			CreateIndexController controller=loader.getController();
			controller.setMain(main);
			controller.setStage(addIndexStage);

			addIndexStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showCreateCommadityStage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(main.getClass().getResource("view/CreateCommodity.fxml"));
			AnchorPane addCommodity = loader.load();

			Stage addCommodityStage = new Stage();
			addCommodityStage.setTitle("Add Commodity");
			addCommodityStage.initModality(Modality.WINDOW_MODAL);
			addCommodityStage.initOwner(main.getPrimaryStage());

			Scene scene = new Scene(addCommodity);
			addCommodityStage.setScene(scene);

			//controller=loader.getController();
			//controller.setMain(main);
			//controller.setStage(addCommadityStage);

			addCommodityStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void handleOk() {
		if (add.getValue() == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("No selection");
			alert.setHeaderText("No selected");
			alert.setContentText("Please select");

			alert.showAndWait();
		} else {
			
			window.setOpacity(0);

			switch (add.getValue()) {
			case "Commodity":
				showCreateCommadityStage();
				break;
			case "Company":
				showCompanyAddStage();
				break;
			case "Index":
				showCreateIndexStage();
				break;
			case "Stock Exchange":
				showStockExchangeAddStage();
				break;
			default:
				window.close();
				break;

			}
		}
		window.close();

	}

}
