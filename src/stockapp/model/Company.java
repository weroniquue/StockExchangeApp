package stockapp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import stockapp.Main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The class is model of company.
 * */

public class Company extends Thread implements Serializable {
	
	/**
	 * @param listOfStock It is the list with actual stock */
	transient private StringProperty companyName;// nazwa spolki
	transient private StringProperty dateOfFirstValuation;// data pierwszej wyceny
	transient private DoubleProperty openiningPrice;// kurs otwarcia
	transient private SimpleDoubleProperty currentPrice;// obecny kurs
	transient private SimpleDoubleProperty minimumPrice;// minimalna kurs
	transient private SimpleIntegerProperty numberOfStock;// liczba akcji
	transient private SimpleIntegerProperty numberOfSellStock;// liczba aktualnie akcji z jednej emisji
	transient private SimpleDoubleProperty profit;// zysk
	transient private SimpleDoubleProperty income;// przychod
	transient private SimpleDoubleProperty equitalCapital;// kapital wlasny
	transient private SimpleDoubleProperty initialCapital;// kapital zak³adowy
	transient private SimpleIntegerProperty volume;// wolumen
	transient private SimpleDoubleProperty assetsTurnover;// obrot
	private double lastCurrentPrice=0;
	transient private ObservableList<Stock> listOfStocks = FXCollections.observableArrayList();
	transient private ObservableList<Double> currenPriceList=FXCollections.observableArrayList();
	transient public ObservableList<LocalTime> timeList=FXCollections.observableArrayList();
	
	/**
	 * @param start It allows to start company thread.*/
	boolean start = true;

	private RandomDate randomDate = new RandomDate();
	Random random = new Random();

	/**
	 * This function round double value to 2 decimal places.
	 * @return double value with 2 decimal places.
	 * */
	public static double roundToDouble(double d) {
		BigDecimal bd = new BigDecimal(d);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}

	/**Construstor.
	 * @param name name of company.
	 * */
	public Company(String name) {
		this.companyName = new SimpleStringProperty(name);
		try {
			this.dateOfFirstValuation = new SimpleStringProperty(randomDate.GetRandomDate());
		} catch (ParseException e) {
			this.dateOfFirstValuation = new SimpleStringProperty("01.01.2017");
		}

		this.openiningPrice = new SimpleDoubleProperty(roundToDouble(1 + random.nextFloat() * (20 - 1)));
		this.currentPrice = new SimpleDoubleProperty(
				roundToDouble(1 + random.nextFloat() * (20 - 1) + openiningPrice.get() / 2));
		this.minimumPrice = new SimpleDoubleProperty(1);
		this.numberOfStock = new SimpleIntegerProperty(random.nextInt(400));
		this.numberOfSellStock = new SimpleIntegerProperty(0);
		this.profit = new SimpleDoubleProperty(roundToDouble(random.nextFloat() * (40000 - 100) + 100));
		this.income = new SimpleDoubleProperty(roundToDouble(random.nextFloat() * (20000 - 50) + 100));
		this.equitalCapital = new SimpleDoubleProperty(100000);
		this.initialCapital = new SimpleDoubleProperty(50000);
		this.volume = new SimpleIntegerProperty(random.nextInt(35));
		this.assetsTurnover = new SimpleDoubleProperty(currentPrice.get() * volume.get()); 

	}

	public Company() {
		this(null);
	}

	/**
	 * It compares two object. Check if the name are the same.*/
	@Override
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		if(obj==null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
            
        Company other = (Company) obj;
        if (companyName.get() == null) {
            if (other.companyName.get() != null)
                return false;
        } else if (!companyName.get().equals(other.companyName.get()))
            return false;
        return true;
	}
	
	public String getCompanyName() {
		return companyName.get();
	}

	public StringProperty getCompanyNameProperty() {
		return companyName;
	}

	public void setCompanyNameProperty(StringProperty name) {
		companyName = name;
	}

	public void setCompanyName(String companyName) {
		this.companyName.set(companyName);
	}

	public String getDateOfFirstValuation() {
		return dateOfFirstValuation.get();
	}

	public void setDateOfFirstValuation(String dateOfFirstValuation) {
		this.dateOfFirstValuation.set(dateOfFirstValuation);
	}

	public double getOpeniningPrice() {
		return openiningPrice.get();
	}

	public void setOpeniningPrice(double openiningPrice) {
		this.openiningPrice.set(openiningPrice);
	}

	public double getCurrentPrice() {
		return currentPrice.get();
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice.set(currentPrice);
	}

	public double getMinimumPrice() {
		return minimumPrice.get();
	}

	public void setMinimumPrice(double minimumPrice) {
		this.minimumPrice.set(minimumPrice);
	}

	public int getNumberOfStock() {
		return numberOfStock.get();
	}

	public void setNumberOfStock(int numberOfStock) {
		this.numberOfStock.set(numberOfStock);
	}
	public IntegerProperty getNumberOfStockProperty() {
		return numberOfStock;
	}

	public int getNumberOfSellStock() {
		return numberOfSellStock.get();
	}

	public void setNumberOfSellStock(int nuberOfSellStock) {
		this.numberOfSellStock.set(nuberOfSellStock);
	}

	public double getProfit() {
		return profit.get();
	}

	public void setProfit(double profit) {
		this.profit.set(profit);
	}

	public double getIncome() {
		return income.get();
	}

	public void setIncome(double income) {
		this.income.set(income);
	}

	public double getEquitalCapital() {
		return equitalCapital.get();
	}

	public void setEquitalCapital(double equitalCapital) {
		this.equitalCapital.set(equitalCapital);
	}

	public double getInitialCapital() {
		return initialCapital.get();
	}

	public void setInitialCapital(double initialCapital) {
		this.initialCapital.set(initialCapital);
	}

	public int getVolume() {
		return volume.get();
	}

	public void setVolume(int volume) {
		this.volume.set(volume);
	}

	public double getAssetsTurnover() {
		return assetsTurnover.get();
	}

	public void setAssetsTurnover(double assetsTurnover) {
		this.assetsTurnover.set(assetsTurnover);
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public boolean getStart() {
		return start;
	}
	

	public ObservableList<Stock> getListOfStocks() {
		return listOfStocks;
	}

	public void setListOfStocks(ObservableList<Stock> listOfStocks) {
		this.listOfStocks = listOfStocks;
	}
	
	public void setLastCurrentPrice(double price) {
		this.lastCurrentPrice=price;
	}
	public double getLastCurrentPrice() {
		return this.lastCurrentPrice;
	}
	public ObservableList<Double> getCurrentPriceList(){
		return currenPriceList;
	}
	
	public void setCurrenPriceList(ObservableList<Double> currenPriceList) {
		this.currenPriceList = currenPriceList;
	}

	public ObservableList<LocalTime> getTimeList() {
		return timeList;
	}

	public void setTimeList(ObservableList<LocalTime> timeList) {
		this.timeList = timeList;
	}

	/**@see Main*/
	private void writeObject(ObjectOutputStream oos) {
		try {
			oos.defaultWriteObject();
			oos.writeObject(companyName.get());
			oos.writeObject(dateOfFirstValuation.get());
			oos.writeObject(openiningPrice.get());
			oos.writeObject(currentPrice.get());
			oos.writeObject(minimumPrice.get());
			oos.writeObject(numberOfStock.get());
			oos.writeObject(numberOfSellStock.get());
			oos.writeObject(profit.get());
			oos.writeObject(income.get());
			oos.writeObject(equitalCapital.get());
			oos.writeObject(initialCapital.get());
			oos.writeObject(volume.get());
			oos.writeObject(assetsTurnover.get());
			oos.writeObject(new ArrayList<Stock>(this.getListOfStocks()));
			oos.writeObject(new ArrayList<Double>(this.getCurrentPriceList()));
			oos.writeObject(new ArrayList<LocalTime>(this.getTimeList()));
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

	/**@see main*/
	private void readObject(ObjectInputStream ois) {
		try {
			ois.defaultReadObject();
			companyName = new SimpleStringProperty((String) ois.readObject());
			dateOfFirstValuation = new SimpleStringProperty((String) ois.readObject());
			openiningPrice = new SimpleDoubleProperty((Double) ois.readObject());
			currentPrice = new SimpleDoubleProperty((Double) ois.readObject());
			minimumPrice = new SimpleDoubleProperty((Double) ois.readObject());
			numberOfStock = new SimpleIntegerProperty((int) ois.readObject());
			numberOfSellStock=new SimpleIntegerProperty((int) ois.readObject());
			profit = new SimpleDoubleProperty((Double) ois.readObject());
			income = new SimpleDoubleProperty((Double) ois.readObject());
			equitalCapital = new SimpleDoubleProperty((Double) ois.readObject());
			initialCapital = new SimpleDoubleProperty((Double) ois.readObject());
			volume = new SimpleIntegerProperty((int) ois.readObject());
			assetsTurnover = new SimpleDoubleProperty((Double) ois.readObject());
			
			List<Stock> stocklist=(List<Stock>) ois.readObject();
			this.setListOfStocks(FXCollections.observableArrayList(stocklist));
			
			List<Double> price=(List<Double>)ois.readObject();
			this.setCurrenPriceList(FXCollections.observableArrayList(price));
			
			List<LocalTime> time=(List<LocalTime>) ois.readObject();
			this.setTimeList(FXCollections.observableArrayList());
			
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
		}
	}

	/**
	 * It create new Stock of company and add to @param listOfStock.
	 * */
	public void issueOfShares() {
		int number = random.nextInt(50);
		synchronized (new Object()) {
			for (int i = 0; i < number; i++) {
				listOfStocks.add(new Stock(this, null, null, currentPrice.get()));
			}
			this.numberOfStock.set(this.numberOfStock.get() + number);
			this.numberOfSellStock.set(numberOfSellStock.get() + number);
			System.out.println(
					"Ilosc wyemitowanych akcji: " + companyName.get() + " " + number + " " + numberOfStock.get());
			System.out.println("Laczna ilosc aktualnie sprzedawanych akcji spo³ki: " + numberOfSellStock.get());
			System.out.println("Laczna ilosc akcji spolki" + numberOfStock.get());
			System.out.println();
		}
	}
	
	
	/**
	 * It in random time create income and profits of company. */
	synchronized public void createIncomeAndProfits() {
			double randomIncome =Company.roundToDouble(random.nextDouble()*4000);
			this.income.set(this.income.get()+randomIncome);
			double randomProfit=Company.roundToDouble(random.nextDouble()*1500);
			this.profit.set(this.profit.get()+randomProfit);
	}

	@Override
	public void run() {
		while (this.getStart()) {
			issueOfShares();
			createIncomeAndProfits();
			try {
				Thread.sleep(random.nextInt(20000) + 100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
