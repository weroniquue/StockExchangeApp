package stockapp;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import stockapp.model.Commodity;
import stockapp.model.Company;
import stockapp.model.CompanyListWrapper;
import stockapp.model.Currency;
import stockapp.model.Index;
import stockapp.model.StockExchange;
import stockapp.view.AddController;
import stockapp.view.CompanyOverviewController;
import stockapp.view.CurrencyOverviewController;
import stockapp.view.MainPageController;
import stockapp.view.RootLayoutController;
import stockapp.view.StockExchangeOverviewController;
import stockapp.view.TabController;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private BorderPane details;

	private ObservableList<Company> companyData = FXCollections.observableArrayList();
	private ObservableList<Currency> currencyData = FXCollections.observableArrayList();
	private ObservableList<Index> indexData = FXCollections.observableArrayList();
	private ObservableList<StockExchange> stockExchangeData = FXCollections.observableArrayList();
	private ObservableList<Commodity> commodityData = FXCollections.observableArrayList();

	public Main() {
		companyData.add(new Company("Firma sprzatajaca"));
		companyData.add(new Company("Firma ble"));
		companyData.add(new Company("Firma 1"));
		companyData.add(new Company("Firma ble1"));
		companyData.add(new Company("Firma sprzatajaca2"));

		currencyData.add(new Currency("Euro", "EUR"));
		currencyData.add(new Currency("Zloty", "PLN"));

		StockExchange tmp = new StockExchange();
		tmp.setNameStockExchange("Warszawska Gie³da papierów wartoœciowych");
		tmp.setAddressStockExchange("Nowomiejska 56");

		StockExchange tmp1 = new StockExchange();
		tmp1.setNameStockExchange("Londynska Gie³da papierów wartoœciowych");
		tmp1.setAddressStockExchange("Nowomiejska 56");

		StockExchange tmp2 = new StockExchange();
		tmp2.setNameStockExchange("Londynska Gie³da papierów wartoœciowych");
		tmp2.setAddressStockExchange("Nowomiejska 56");
		tmp2.setCityStockExchange("Warszawa");
		tmp2.setCurrencyStockExchange("PLN");
		
		Index WIG20=new Index();
		WIG20.setNameIndex("Wig20");
		WIG20.getCompanyInIndex().addAll(companyData);
		tmp2.getListOfIndex().add(WIG20);
		
		Index WIG10=new Index();
		WIG10.setNameIndex("Wig10");
		WIG10.getCompanyInIndex().addAll(companyData.get(0),companyData.get(2));
		tmp2.getListOfIndex().add(WIG10);

		stockExchangeData.add(tmp);
		stockExchangeData.add(tmp1);
		stockExchangeData.add(tmp2);
	
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
			
			RootLayoutController controller=loader.getController();
			controller.setMain(this);

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

	public void showStockExchange() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/StockExchangeOverview.fxml"));
			AnchorPane stockExchange= (AnchorPane) loader.load();
			details.setCenter(stockExchange);

			StockExchangeOverviewController controller=loader.getController();
			controller.setMain(this);
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showCurrency() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/CurrencyOverview.fxml"));
			AnchorPane index = (AnchorPane) loader.load();
			details.setCenter(index);

			CurrencyOverviewController controller = loader.getController();
			controller.setMain(this);

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
			addWindow.initModality(Modality.WINDOW_MODAL);
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

	public ObservableList<Company> getCompanyData() {
		return companyData;
	}

	public void setCompanyData(ObservableList<Company> companyData) {
		this.companyData = companyData;
	}

	public ObservableList<Currency> getCurrencyData() {
		return currencyData;
	}

	public void setCurrencyData(ObservableList<Currency> currencyData) {
		this.currencyData = currencyData;
	}

	public ObservableList<Index> getIndexData() {
		return indexData;
	}

	public void setIndexData(ObservableList<Index> indexData) {
		this.indexData = indexData;
	}

	public ObservableList<StockExchange> getStockExchangeData() {
		return stockExchangeData;
	}

	public void setStockExchangeData(ObservableList<StockExchange> stockExchangeData) {
		this.stockExchangeData = stockExchangeData;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public ObservableList<Commodity> getCommodityData() {
		return commodityData;
	}

	public void setCommodityData(ObservableList<Commodity> commodityData) {
		this.commodityData = commodityData;
	}

	public void setDataFilePath(File file) {
		 Preferences prefs = Preferences.userNodeForPackage(Main.class);
		    if (file != null) {
		        prefs.put("filePath", file.getPath());

		        // Update the stage title.
		        primaryStage.setTitle("StockExchangeApp - " + file.getName());
		    } else {
		        prefs.remove("filePath");

		        // Update the stage title.
		        primaryStage.setTitle("StockExchangeApp");
		    }
	}
	public File getDataFilePath() {
	    Preferences prefs = Preferences.userNodeForPackage(Main.class);
	    String filePath = prefs.get("filePath", null);
	    if (filePath != null) {
	        return new File(filePath);
	    } else {
	        return null;
	    }
	}
	
	
	public void saveDataToFile(File file) {
		try {
			JAXBContext context = JAXBContext
	                .newInstance(CompanyListWrapper.class);
	        Marshaller m = context.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        
	        
	        // Wrapping our person data.
	        CompanyListWrapper wrapper = new CompanyListWrapper();
	        wrapper.setCompany(companyData);

	        // Marshalling and saving XML to the file.
	        m.marshal(wrapper, file);

	        // Save the file path to the registry.
	        setDataFilePath(file);
	    } catch (Exception e) { // catches ANY exception
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Could not save data");
	        alert.setContentText("Could not save data to file:\n" + file.getPath());

	        alert.showAndWait();
	    }
	}
	
	public void loadDataFromFile(File file) {
	    try {
	        JAXBContext context = JAXBContext
	                .newInstance(CompanyListWrapper.class);
	        Unmarshaller um = context.createUnmarshaller();

	        // Reading XML from the file and unmarshalling.
	        CompanyListWrapper wrapper = (CompanyListWrapper) um.unmarshal(file);

	        companyData.clear();
	        companyData.addAll(wrapper.getCompany());

	        System.out.println();
	        // Save the file path to the registry.
	        setDataFilePath(file);

	    } catch (Exception e) { // catches ANY exception
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Could not load data");
	        alert.setContentText("Could not load data from file:\n" + file.getPath());

	        alert.showAndWait();
	    }
	}
	
	
}
