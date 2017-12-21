package stockapp;

import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import stockapp.model.Company;
import stockapp.view.AddController;
import stockapp.view.CompanyAddController;
import stockapp.view.CompanyOverviewController;
import stockapp.view.MainPageController;
import stockapp.view.TabController;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private BorderPane details;
	
	
	private ObservableList<Company> companyData = FXCollections.observableArrayList();
	// private ObservableList<TreeItem<Index>>
	// indexData=FXCollections.observableArrayList();

	public Main() {
		companyData.add(new Company("Firma sprzatajaca"));
		companyData.add(new Company("Firma ble"));
		companyData.add(new Company("Firma 1"));
		companyData.add(new Company("Firma ble1"));
		companyData.add(new Company("Firma sprzatajaca2"));
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("StockExangeApp");

		initRootLayout();
		showMainPage();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/RootLayout.fxml"));
			rootLayout = loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showMainPage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/MainPage.fxml"));
			AnchorPane MainPage = (AnchorPane) loader.load();

			rootLayout.setCenter(MainPage);
			
			MainPageController controller = loader.getController();
			controller.setMainPage(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showDetails() {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/RootDetails.fxml"));
			details = (BorderPane) loader.load();
			Scene scene = new Scene(details);
			primaryStage.setScene(scene);

			showCompany();

			TabController controller = loader.getController();
			controller.setMain(this);

			// Parent pane=FXMLLoader.load(getClass().getResource("view/RootDetails.fxml"));
			// primaryStage.getScene().setRoot(pane);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showCompany() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/CompanyOverview.fxml"));
			AnchorPane company = (AnchorPane) loader.load();
			details.setCenter(company);

			CompanyOverviewController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showIndex() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/IndexOverview.fxml"));
			AnchorPane index = (AnchorPane) loader.load();
			details.setCenter(index);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addMenu() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/ChooseWhatAdd.fxml"));
			Pane add = loader.load();

			Stage addWindow = new Stage();
			addWindow.setTitle("Add");
			addWindow.initOwner(primaryStage);
			addWindow.sizeToScene();

			Scene scene = new Scene(add);
			addWindow.setScene(scene);
		
			AddController controller = loader.getController();
			controller.setMain(this);
			controller.setStage(addWindow);

			addWindow.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void showCompanyAddStage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/AddCompanyOverview.fxml"));
			AnchorPane addCompany=loader.load();
			
			Stage addCompanyStage=new Stage();
			addCompanyStage.setTitle("Add Company");
			addCompanyStage.initOwner(primaryStage);
			
			Scene scene=new Scene(addCompany);
			addCompanyStage.setScene(scene);
			
			CompanyAddController controller=loader.getController();
			controller.setMain(this);
			controller.setStage(addCompanyStage);
			
			addCompanyStage.showAndWait();
				
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<Company> getCompanyData() {
		return companyData;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}
}
