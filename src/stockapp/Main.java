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
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import stockapp.model.Commodity;
import stockapp.model.Company;
import stockapp.model.Currency;
import stockapp.model.Index;
import stockapp.model.InvestmentFund;
import stockapp.model.InvestorIndividual;
import stockapp.model.StockExchange;
import stockapp.view.AddController;
import stockapp.view.CompanyOverviewController;
import stockapp.view.MainPageController;
import stockapp.view.RootLayoutController;
import stockapp.view.TabController;

public class Main extends Application{

	private Stage primaryStage;
	private BorderPane rootLayout;
	private BorderPane details;

	static public LocalTime localTime;
	Random ranodm = new Random();

	//List of all available data on app.
	transient private ObservableList<Company> companyData = FXCollections.observableArrayList();
	transient private ObservableList<Currency> currencyData = FXCollections.observableArrayList();
	transient private ObservableList<Index> indexData = FXCollections.observableArrayList();
	transient private ObservableList<StockExchange> stockExchangeData = FXCollections.observableArrayList();
	transient private ObservableList<Commodity> commodityData = FXCollections.observableArrayList();
	transient private ObservableList<InvestorIndividual> investorData = FXCollections.observableArrayList();
	transient private ObservableList<InvestmentFund> investmentFoundData = FXCollections.observableArrayList();

	/**
	 * @param task is responsible for start thread of investor according amount of assets .
	 */
	Timer myTimer = new Timer();
	TimerTask task = new TimerTask() {

		@Override
		public void run() {
			int activeValue = 0;
			int activeInvestor = 0;
			localTime = localTime.now();
			activeValue += companyData.size();
			activeValue += commodityData.size();
			activeValue += currencyData.size();

			if (investmentFoundData.size() == 1) {
				InvestmentFund randomInvestmentFund = investmentFoundData
						.get(ranodm.nextInt(investmentFoundData.size()));
				if (!randomInvestmentFund.isStart()) {
					randomInvestmentFund.setStart(true);
					randomInvestmentFund.start();
					activeInvestor++;
				}
			}
			if (investorData.size() == 1) {
				investorData.get(0).setStart(true);
				investorData.get(0).start();
				activeInvestor++;
			}
			if (activeValue > 0 && activeValue % 8 == 0) {
				InvestorIndividual randomInvestor = investorData.get(ranodm.nextInt(investorData.size()));
				if (!randomInvestor.isStart()) {
					randomInvestor.setStart(true);
					randomInvestor.start();
					activeInvestor++;
				}
			}
			if (activeInvestor != 0 && (activeValue / activeInvestor) > 7) {
				InvestorIndividual randomInvestor = investorData.get(ranodm.nextInt(investorData.size()));
				if (!randomInvestor.isStart()) {
					randomInvestor.setStart(false);
				}
			}
			activeValue = 0;
			activeInvestor = 0;
		}

	};

	public Main() {

	}

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("StockExangeApp");

		initRootLayout();
		showMainPage();

		/**
		 * When the close button is pressed all thread are stopped.
		 */
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				for (Company company : companyData) {
					company.setStart(false);
				}
				for (InvestorIndividual investor : investorData) {
					investor.setStart(false);
				}
				for (InvestmentFund investmentFund : investmentFoundData) {
					investmentFund.setStart(false);
				}
				
				myTimer.cancel();

			}
		});
		myTimer.scheduleAtFixedRate(task, 0, 1000 * 5);

	}

	public static void main(String[] args) {
		launch(args);
	}

	/**Initializes the root layout.
	 * 
	 */
	public void initRootLayout() {
		try {
			/**
			 * @param load Load root layout from fxml file.
			 * 
			 */
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/RootLayout.fxml"));
			rootLayout = loader.load();

			/**Show the scene containing the root layout.
			 * 
			 */
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

			/**
			 * Give the controller access to main app.
			 */
			RootLayoutController controller = loader.getController();
			controller.setMain(this);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 *Shows the main page inside the root layout. 
	 * */
	public void showMainPage() {
		try {
			/**
			 * @param loader Load main page fxml.
			 */
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/MainPage.fxml"));
			AnchorPane MainPage = (AnchorPane) loader.load();

			/**Set main page overview into the center of root layout.
			 * 
			 */
			rootLayout.setCenter(MainPage);

			/**
			 * Give the controller access to the main app.
			 */
			MainPageController controller = loader.getController();
			controller.setMainPage(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Show the detail overview with tab to control switch between scene.
	 * */
	public void showDetails() {
		try {

			/**
			 * Load fxml file.
			 */
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/RootDetails.fxml"));
			details = (BorderPane) loader.load();
			Scene scene = new Scene(details);
			primaryStage.setScene(scene);

			showCompany();

			/**
			 * Give the controller access to the main app.
			 */
			TabController controller = loader.getController();
			controller.setMain(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Show details of company. 
	 * */
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

	/**
	 * Create new stage to add data to app.
	 * */
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

			//Give the controller acces to main app and add stage.
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

	public ObservableList<InvestorIndividual> getInvestorData() {
		return investorData;
	}

	public void setInvestorData(ObservableList<InvestorIndividual> investorData) {
		this.investorData = investorData;
	}

	public ObservableList<InvestmentFund> getInvestmentFundData() {
		return investmentFoundData;
	}

	public void setInvestmentFundData(ObservableList<InvestmentFund> investmentFundData) {
		this.investmentFoundData = investmentFundData;
	}

	/**
	 * It allows to write all object in file.
	 * */
	public void saveDataToFile(String filePath) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filePath)));

			out.writeObject(new ArrayList<Company>(this.getCompanyData()));
			out.writeObject(new ArrayList<Currency>(this.getCurrencyData()));
			out.writeObject(new ArrayList<Index>(this.getIndexData()));
			out.writeObject(new ArrayList<StockExchange>(this.getStockExchangeData()));
			out.writeObject(new ArrayList<Commodity>(this.getCommodityData()));
			out.writeObject(new ArrayList<InvestorIndividual>(this.getInvestorData()));
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

	/**
	 * It allows to load data from file and resume simulation
	 * */
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

			List<InvestorIndividual> listInvestor = (List<InvestorIndividual>) in.readObject();
			this.setInvestorData(FXCollections.observableArrayList(listInvestor));

			List<InvestmentFund> listInvestmentFund = (List<InvestmentFund>) in.readObject();
			this.setInvestmentFundData(FXCollections.observableArrayList(listInvestmentFund));

			in.close();
			
			setFilePath(file);

			
			for (Company company : companyData) { 
				company.start(); 
			} 

		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
	}

	
	/**
	 * Returns the person file preference, i.e. the file that was last opened.
	 * If no such preference can be found, null is returned.
	 * @return
	 */
	public File getfilePath() {
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		String filePath = prefs.get("filePath", null);
		if (filePath != null) {
			return new File(filePath);
		} else {
			return null;
		}
	}
/**
 * Sets the file path of the currently loaded file. The path is persisted in
 * the OS specific registry.
 * 
 * @param file the file or null to remove the path
 */
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
