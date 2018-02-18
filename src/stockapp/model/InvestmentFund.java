package stockapp.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import stockapp.Main;

public class InvestmentFund extends Thread implements Serializable {

	transient private StringProperty nameInvestmentFound;
	transient private StringProperty nameManager;
	transient private StringProperty secondNameManager;
	transient private DoubleProperty budget;

	transient private ObservableList<Stock> stock = FXCollections.observableArrayList();
	transient private ObservableList<CommodityPaper> commodityPapers = FXCollections.observableArrayList();
	Random random = new Random();
	Main main;
	Random rand = new Random();
	/**
	 * @param start
	 *            is responsible for star the investment fund thread.
	 */
	private boolean start=false;

	public InvestmentFund() {
		this.nameInvestmentFound = new SimpleStringProperty("Investment");
		this.nameManager = new SimpleStringProperty("name");
		this.secondNameManager = new SimpleStringProperty("");
		this.budget = new SimpleDoubleProperty(random.nextInt(200) + 20);
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public StringProperty getNameInvestmentFoundProperty() {
		return nameInvestmentFound;
	}

	public String getNameInvestmentFound() {
		return nameInvestmentFound.get();
	}

	public void setNameInvestmentFound(String nameInvestmentFound) {
		this.nameInvestmentFound.set(nameInvestmentFound);
	}

	public String getNameManager() {
		return nameManager.get();
	}

	public void setNameManager(String nameManager) {
		this.nameManager.set(nameManager);
	}

	public String getSecondNameManager() {
		return secondNameManager.get();
	}

	public void setSecondNameManager(String secondName) {
		this.secondNameManager.set(secondName);
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public void setNameManager(StringProperty nameManager) {
		this.nameManager = nameManager;
	}

	public void setSecondNameManager(StringProperty secondNameManager) {
		this.secondNameManager = secondNameManager;
	}

	public void setBudget(DoubleProperty budget) {
		this.budget = budget;
	}

	public Double getBudget() {
		return budget.get();
	}

	public void setBudget(Double budget) {
		this.budget.set(budget);
	}

	public ObservableList<Stock> getStock() {
		return stock;
	}

	public void setStock(ObservableList<Stock> stock) {
		this.stock = stock;
	}

	public ObservableList<CommodityPaper> getCommodityPapers() {
		return commodityPapers;
	}

	public void setCommodityPapers(ObservableList<CommodityPaper> commodityPapers) {
		this.commodityPapers = commodityPapers;
	}

	private void writeObject(ObjectOutputStream oos) {
		try {
			oos.defaultWriteObject();
			oos.writeObject(nameInvestmentFound.get());
			oos.writeObject(nameManager.get());
			oos.writeObject(secondNameManager.get());
			oos.writeObject(budget.get());
			oos.writeObject(new ArrayList<Stock>(this.getStock()));
			oos.writeObject(new ArrayList<CommodityPaper>(this.getCommodityPapers()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void readObject(ObjectInputStream ois) {
		try {
			ois.defaultReadObject();
			nameInvestmentFound = new SimpleStringProperty((String) ois.readObject());
			nameManager = new SimpleStringProperty((String) ois.readObject());
			secondNameManager = new SimpleStringProperty((String) ois.readObject());
			budget = new SimpleDoubleProperty((Double) ois.readObject());
			List<Stock>liststock=(List<Stock>)ois.readObject();
			this.setStock(FXCollections.observableArrayList(liststock));
			
			List<CommodityPaper> listcommodity=(List<CommodityPaper>) ois.readObject();
			this.setCommodityPapers(FXCollections.observableArrayList(listcommodity));
		} catch (ClassNotFoundException | IOException e) {
		}

	}

	public void sellStock() {
		synchronized (new Object()) {
			if (this.stock.size() > 0) {
				int randomNumber = rand.nextInt(this.stock.size());
				while (randomNumber >= 0) {
					int randomStockIndex = rand.nextInt(this.stock.size());
					this.budget.add(this.stock.get(randomStockIndex).getPrice());
					this.stock.get(randomStockIndex).getCompany()
							.setNumberOfStock(this.stock.get(randomStockIndex).getCompany().getNumberOfStock() - 1);
					this.stock.get(randomStockIndex).getCompany()
							.setEquitalCapital(this.stock.get(randomStockIndex).getCompany().getEquitalCapital()
									- this.stock.get(randomStockIndex).getPrice());
					this.stock.remove(randomStockIndex);
					randomNumber--;
				}
			}
		}
	}

	/** @see investor individual */
	public void buyStock() {
		if (main.getStockExchangeData().size() > 0) {
			synchronized (new Object()) {
				StockExchange randomStockExchange = main.getStockExchangeData()
						.get(rand.nextInt(main.getStockExchangeData().size()));
				if (randomStockExchange.getListOfCompanyOnStockExchange().size() > 0) {
					Company randomCompany = randomStockExchange.getListOfCompanyOnStockExchange()
							.get(rand.nextInt(randomStockExchange.getListOfCompanyOnStockExchange().size()));
					int randomNumberOfStock;
					if (randomCompany.getNumberOfSellStock() > 0) {
						randomNumberOfStock = rand.nextInt(randomCompany.getNumberOfSellStock());
					} else {
						randomNumberOfStock = 0;
					}

					double randomPrice;
					double factorPrice = (rand.nextInt(12) + 1 - 5) / 100.0;
					if (factorPrice != 0) {
						randomPrice = Company.roundToDouble((factorPrice + 1) * randomCompany.getCurrentPrice());
					} else {
						randomPrice = randomCompany.getCurrentPrice();
					}
					int bought = 0;
					for (Stock stock : randomCompany.getListOfStocks()) {
						if (stock.getInvestor() == null && stock.getInvestmentFund() == null
								&& randomStockExchange.getMarkup(stock.getPrice()) <= budget.get()
								&& bought <= randomNumberOfStock) {
							budget.set(budget.get() - randomStockExchange.getMarkup(stock.getPrice()));
							stock.getCompany()
									.setInitialCapital(stock.getCompany().getInitialCapital() + stock.getPrice());
							stock.setInvestmentFund(this);
							this.stock.add(stock);
							bought++;
						}
					}
					randomCompany.setLastCurrentPrice(randomCompany.getCurrentPrice());
					randomCompany.setCurrentPrice(randomPrice);
					randomCompany.getCurrentPriceList().add(randomCompany.getCurrentPrice());
					randomCompany.timeList.add(LocalTime.now());
					randomCompany.getListOfStocks().remove(0, bought);
					randomCompany.setNumberOfSellStock(randomCompany.getNumberOfSellStock() - bought);


				}

			}
		}
		try {
			this.wait(1000*3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void buyCommodity() {
		if (main.getCommodityData().size() > 0) {
			synchronized (new Object()) {
				Commodity randomCommodity = main.getCommodityData().get(rand.nextInt(main.getCommodityData().size()));
				int bound = (int) (this.budget.get() / randomCommodity.getCurrentPrice());
				if (bound > 0) {
					int randomAmount = rand.nextInt(bound);
					if (randomCommodity.getCurrentPrice() * randomAmount <= this.budget.get()) {

						commodityPapers.add(new CommodityPaper(randomCommodity, null, this, randomAmount,
								randomCommodity.getCurrentPrice()));

						randomCommodity.setCurrentPrice(randomCommodity.getMinimalPrice() + Company
								.roundToDouble((randomCommodity.getMaximalPrice() - randomCommodity.getMinimalPrice())
										* rand.nextDouble()));
						randomCommodity.getTimeList().add(LocalTime.now());
						randomCommodity.getCurrentPriceList().add(randomCommodity.getCurrentPrice());
						System.out
								.println("Kupiono: " + randomAmount + "Po cenie " + randomCommodity.getCurrentPrice());
					}
				}
			}
		}
	}

	public void sellCommodity() {
		synchronized (new Object()) {
			if (commodityPapers.size() > 0) {
				int randomNumber = rand.nextInt(this.commodityPapers.size());
				while (randomNumber >= 0) {
					int randomIndex = rand.nextInt(this.commodityPapers.size());
					this.budget.add(this.commodityPapers.get(randomIndex).getPrice()
							* this.commodityPapers.get(randomIndex).getAmount());
					this.commodityPapers.remove(randomIndex);
					randomNumber--;
				}
			}

		}
	}

	@Override
	public void run() {
		while (start) {
			buyStock();
			sellStock();
			buyCommodity();
			sellCommodity();
			try {
				Thread.sleep(rand.nextInt(15000) + 5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
