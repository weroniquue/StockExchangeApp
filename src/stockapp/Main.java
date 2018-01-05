package stockapp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

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
import stockapp.model.Currency;
import stockapp.model.Index;
import stockapp.model.InvestmentFund;
import stockapp.model.Investor;
import stockapp.model.StockExchange;
import stockapp.view.AddController;
import stockapp.view.CompanyOverviewController;
import stockapp.view.CurrencyOverviewController;
import stockapp.view.InvestorOverviewController;
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
	private ObservableList<Investor> investorData = FXCollections.observableArrayList();
	private ObservableList<InvestmentFund> investmentFoundData = FXCollections.observableArrayList();

	public Main() {
		
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

			RootLayoutController controller = loader.getController();
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

	public BorderPane getDetails() {
		return details;
	}

	public ObservableList<Commodity> getCommodityData() {
		return commodityData;
	}

	public void setCommodityData(ObservableList<Commodity> commodityData) {
		this.commodityData = commodityData;
	}

	public ObservableList<Investor> getInvestorData() {
		return investorData;
	}

	public void setInvestorData(ObservableList<Investor> investorData) {
		this.investorData = investorData;
	}

	public ObservableList<InvestmentFund> getInvestmentFundData() {
		return investmentFoundData;
	}

	public void setInvestmentFundData(ObservableList<InvestmentFund> investmentFundData) {
		this.investmentFoundData = investmentFundData;
	}

	public void saveDataToFile(String filePath) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filePath)));

			out.writeObject(new ArrayList<Company>(this.getCompanyData()));
			out.writeObject(new ArrayList<Currency>(this.getCurrencyData()));
			out.writeObject(new ArrayList<Index>(this.getIndexData()));
			out.writeObject(new ArrayList<StockExchange>(this.getStockExchangeData()));
			out.writeObject(new ArrayList<Commodity>(this.getCommodityData()));
			out.writeObject(new ArrayList<Investor>(this.getInvestorData()));
			out.writeObject(new ArrayList<InvestmentFund>(this.getInvestmentFundData()));
			out.close();

		} catch (FileNotFoundException e) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not save data");
			alert.setContentText("Could not save data to file:\n" + filePath);

			alert.showAndWait();
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not save data");
			alert.setContentText("Could not save data to file:\n" + filePath);

			alert.showAndWait();
		}
	}

	public void loadDataFromFile(File file) {
		try {
			ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file.getPath())));

			List<Company> list = (List<Company>) in.readObject();
			this.setCompanyData(FXCollections.observableArrayList(list));

			List<Currency> listCurrency = (List<Currency>) in.readObject();
			this.setCurrencyData(FXCollections.observableArrayList(listCurrency));

			List<Index> listIndex = (List<Index>) in.readObject();
			this.setIndexData(FXCollections.observableArrayList(listIndex));

			List<StockExchange> listStockExchange = (List<StockExchange>) in.readObject();
			this.setStockExchangeData(FXCollections.observableArrayList(listStockExchange));

			List<Commodity> listCommodity = (List<Commodity>) in.readObject();
			this.setCommodityData(FXCollections.observableArrayList(listCommodity));

			List<Investor> listInvestor = (List<Investor>) in.readObject();
			this.setInvestorData(FXCollections.observableArrayList(listInvestor));

			List<InvestmentFund> listInvestmentFund = (List<InvestmentFund>) in.readObject();
			this.setInvestmentFundData(FXCollections.observableArrayList(listInvestmentFund));

			setFilePath(file);

		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
	}

	public File getfilePath() {
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		String filePath = prefs.get("filePath", null);
		if (filePath != null) {
			return new File(filePath);
		} else {
			return null;
		}
	}

	public void setFilePath(File file) {
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		if (file != null) {
			prefs.put("filePath", file.getPath());
			primaryStage.setTitle("StockExchangeApp-" + file.getName());
		} else {
			prefs.remove("filePath");

			primaryStage.setTitle("StockExchngeApp");
		}
	}
}
