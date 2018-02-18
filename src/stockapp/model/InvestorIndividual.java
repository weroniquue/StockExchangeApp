package stockapp.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import stockapp.Main;

public class InvestorIndividual extends Thread implements Serializable {

	transient private StringProperty nameInvestor;
	transient private StringProperty secondNameInvestor;
	transient private StringProperty numberPESEL;
	transient private DoubleProperty budget;
	transient private ObservableList<Stock> stock = FXCollections.observableArrayList();
	transient private ObservableList<CommodityPaper> commodityPapers = FXCollections.observableArrayList();
	transient private ObservableList<InvestmentFundPaper> investmentFundsPapers=FXCollections.observableArrayList();
	
	private boolean start=false;

	Main main;

	Random rand = new Random();

	public InvestorIndividual() {
		this.nameInvestor = new SimpleStringProperty("Name Investor");
		this.secondNameInvestor = new SimpleStringProperty("Second Name");
		this.numberPESEL = new SimpleStringProperty("00000000000");
		this.budget = new SimpleDoubleProperty(rand.nextInt(600) + 600);
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public StringProperty getNameInvestorProperty() {
		return nameInvestor;
	}

	public String getNameInvestor() {
		return nameInvestor.get();
	}

	public void setNameInvestor(String nameInvestor) {
		this.nameInvestor.set(nameInvestor);
	}

	public String getSecondNameInvestor() {
		return secondNameInvestor.get();
	}

	public StringProperty getSecondNameInvestorProperty() {
		return secondNameInvestor;
	}

	public void setNameInvestorProperty(StringProperty nameInvestor) {
		this.nameInvestor=nameInvestor;
	}
	public void setSecondNameInvestor(String secondNameInvestor) {
		this.secondNameInvestor.set(secondNameInvestor);
	}

	public String getNumberPESEL() {
		return numberPESEL.get();
	}

	public void setNumberPESEL(String numberPESEL) {
		this.numberPESEL.set(numberPESEL);
	}

	public Double getBudget() {
		return budget.get();
	}

	public DoubleProperty getBudgetProperty() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget.set(budget);
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
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
	

	public ObservableList<InvestmentFundPaper> getInvestmentFundsPapers() {
		return investmentFundsPapers;
	}

	public void setInvestmentFundsPapers(ObservableList<InvestmentFundPaper> investmentFundsPapers) {
		this.investmentFundsPapers = investmentFundsPapers;
	}

	private void writeObject(ObjectOutputStream oos) {
		try {
			oos.writeObject(nameInvestor.get());
			oos.writeObject(secondNameInvestor.get());
			oos.writeObject(numberPESEL.get());
			oos.writeObject(budget.get());
			oos.writeObject(new ArrayList<Stock>(stock));
			oos.writeObject(new ArrayList<CommodityPaper>(commodityPapers));
			oos.writeObject(new ArrayList<InvestmentFundPaper>(investmentFundsPapers));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void readObject(ObjectInputStream ois) {
		try {
			this.nameInvestor = new SimpleStringProperty((String) ois.readObject());
			this.secondNameInvestor = new SimpleStringProperty((String) ois.readObject());
			this.numberPESEL = new SimpleStringProperty((String) ois.readObject());
			this.budget = new SimpleDoubleProperty((Double) ois.readObject());
			List<Stock>stock=(List<Stock>)ois.readObject();
			this.setStock(FXCollections.observableArrayList(stock));
			List<CommodityPaper> commodity=(List<CommodityPaper>)ois.readObject();
			this.setCommodityPapers(FXCollections.observableArrayList(commodity));
			List<InvestmentFundPaper> innvestment=(List<InvestmentFundPaper>)ois.readObject();
			this.setInvestmentFundsPapers(FXCollections.observableArrayList(innvestment));
			
		} catch (ClassNotFoundException | IOException e) {
		}
	}

	public void increaseBudget() {
		Random random = new Random();
		double ranodmBudget = Company.roundToDouble((random.nextDouble() * (2000-100))+100);

		synchronized (this) {
			budget.set(budget.get() + ranodmBudget);
		}
		System.out.println("Nowy buzet: " + budget.get());
	}

	public void buyDirectlyStock() {
		if (main.getStockExchangeData().size() > 0) {
			synchronized (new Object()) {
				StockExchange randomStockExchange = main.getStockExchangeData()
						.get(rand.nextInt(main.getStockExchangeData().size()));
				if (randomStockExchange.getListOfCompanyOnStockExchange().size() > 0) {

					for (Company company : randomStockExchange.getListOfCompanyOnStockExchange()) {
						System.out.println(company.getCompanyName());
					}
					Company randomCompany = randomStockExchange.getListOfCompanyOnStockExchange()
							.get(rand.nextInt(randomStockExchange.getListOfCompanyOnStockExchange().size()));
					System.out.println("Na poczatku do sprzedania ma : " + randomCompany.getNumberOfSellStock());
					int randomNumberOfStock;
					if (randomCompany.getNumberOfSellStock() > 0) {
						randomNumberOfStock = rand.nextInt(randomCompany.getNumberOfSellStock());
					} else {
						randomNumberOfStock = 0;
					}

					double randomPrice;
					double factorPrice = (rand.nextInt(12) + 1 - 5) / 100.0;
					System.out.println(factorPrice);
					System.out.println();
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
							stock.setInvestor(this);
							this.stock.add(stock);
							bought++;
						}
					}
				
					randomCompany.setLastCurrentPrice(randomCompany.getCurrentPrice());
					randomCompany.setCurrentPrice(randomPrice);
					randomCompany.getCurrentPriceList().add(randomCompany.getCurrentPrice());
					randomCompany.getTimeList().add(LocalTime.now());
					randomCompany.getListOfStocks().remove(0, bought);
					randomCompany.setNumberOfSellStock(randomCompany.getNumberOfSellStock() - bought);

					/*System.out.println("Kupiono: " + bought + " akcji po cenie: " + randomCompany.getCurrentPrice());
					System.out.println(
							"Obecny stan:" + randomCompany.getCompanyName() + " " + randomCompany.getNumberOfSellStock()
									+ "ilosc w liscie:" + randomCompany.getListOfStocks().size());
					System.out.println("Spolka kapital: " + randomCompany.getInitialCapital());
					System.out.println("Budzet funduszu " + this.budget + " Kupione przez " + this.nameInvestor.get()
							+ " " + this.secondNameInvestor.get());
					System.out.println("Aktualna cena akcji: " + randomCompany.getCurrentPrice());
					System.out.println();
					System.out.println();*/
				}

			}
		}
	}

	public void sellStock() {
		synchronized (new Object()) {
			if (this.stock.size() > 0) {
				int randomNumber = rand.nextInt(this.stock.size());
				for (int i = 0; i < randomNumber; i++) {
					this.budget.add(this.stock.get(i).getPrice());
					this.stock.get(i).getCompany()
							.setNumberOfStock(this.stock.get(i).getCompany().getNumberOfStock() - 1);
					this.stock.get(i).getCompany().setEquitalCapital(
							this.stock.get(i).getCompany().getEquitalCapital() - this.stock.get(i).getPrice());
				}
				this.stock.remove(0, randomNumber);
			}
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

						commodityPapers.add(new CommodityPaper(randomCommodity, this, null, randomAmount,
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
				for (int i = 0; i < randomNumber; i++) {
					this.budget.add(this.commodityPapers.get(i).getPrice() * this.commodityPapers.get(i).getAmount());
				}
				this.commodityPapers.remove(0, randomNumber);
			}

		}
	}

	public void buyInvestmentFundPaper() {
		if(main.getInvestmentFundData().size()>0) {
			double ranodmBudget=Company.roundToDouble(rand.nextDouble()*(500-300)+300);
			InvestmentFund randomInvestmentFund=main.getInvestmentFundData().get(rand.nextInt(main.getInvestmentFundData().size()));
			synchronized (new Object()) {
				if(this.budget.get()>=ranodmBudget) {
					investmentFundsPapers.add(new InvestmentFundPaper(this, ranodmBudget,randomInvestmentFund));
					randomInvestmentFund.setBudget(randomInvestmentFund.getBudget()+ranodmBudget);
					this.budget.set(this.budget.get()-ranodmBudget);
					System.out.println("Kupiono: "+ranodmBudget);
				}
			}
		}
	}

	@Override
	public void run() {
		while (start) {
			increaseBudget();
			buyDirectlyStock();
			sellStock();
			buyCommodity();
			sellCommodity();
			buyInvestmentFundPaper();
			try {
				Thread.sleep(rand.nextInt(10000) + 5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
