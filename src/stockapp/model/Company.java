package stockapp.model;

import java.util.Random;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.text.ParseException;

public class Company {
	private StringProperty companyName;// nazwa spolki
	private StringProperty dateOfFirstValuation;// data pierwszej wyceny
	private FloatProperty openiningPrice;// kurs otwarcia
	private SimpleFloatProperty currentPrice;// obecny kurs
	private SimpleFloatProperty minimumPrice;// minimalna kurs
	private SimpleIntegerProperty numberOfStock;// liczba akcji
	private SimpleFloatProperty profit;// zysk
	private SimpleFloatProperty income;// przychod
	private SimpleFloatProperty equitalCapital;// kapital wlasny
	private SimpleFloatProperty initialCapital;// kapital poczatkowy
	private SimpleIntegerProperty volume;// wolumen
	private SimpleFloatProperty assetsTurnover;// obrot
	private RandomDate randomDate = new RandomDate();
	Random random = new Random();

	public Company(String name) {
		this.companyName = new SimpleStringProperty(name);
		try {
			this.dateOfFirstValuation = new SimpleStringProperty(randomDate.GetRandomDate());
		} catch (ParseException e) {
			this.dateOfFirstValuation = new SimpleStringProperty("01.01.2017");
		}

		this.openiningPrice = new SimpleFloatProperty(1 + random.nextFloat() * (20 - 1));
		this.currentPrice = new SimpleFloatProperty(1 + random.nextFloat() * (20 - 1) + openiningPrice.get() / 2);
		this.minimumPrice = new SimpleFloatProperty(1);
		this.numberOfStock = new SimpleIntegerProperty(random.nextInt(400));
		this.profit = new SimpleFloatProperty(random.nextFloat() * (40000 - 100) + 100);
		this.income = new SimpleFloatProperty(random.nextFloat() * (40000 - 100) + 100);
		this.equitalCapital = new SimpleFloatProperty(100000);
		this.initialCapital = new SimpleFloatProperty(50000);
		this.volume = new SimpleIntegerProperty(random.nextInt(35));
		this.assetsTurnover = new SimpleFloatProperty(currentPrice.get() * volume.get()); // Czy to się tak liczy?

	}

	public Company() {
		this(null);
	}

	public String getCompanyName() {
		return companyName.get();
	}

	public StringProperty getCompanyNameProperty() {
		return companyName;
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

	public float getOpeniningPrice() {
		return openiningPrice.get();
	}

	public void setOpeniningPrice(float openiningPrice) {
		this.openiningPrice.set(openiningPrice);
	}

	public float getCurrentPrice() {
		return currentPrice.get();
	}

	public void setCurrentPrice(float currentPrice) {
		this.currentPrice.set(currentPrice);
	}

	public float getMinimumPrice() {
		return minimumPrice.get();
	}

	public void setMinimumPrice(float minimumPrice) {
		this.minimumPrice.set(minimumPrice);
	}

	public int getNumberOfStock() {
		return numberOfStock.get();
	}

	public void setNumberOfStock(int numberOfStock) {
		this.numberOfStock.set(numberOfStock);
	}

	public float getProfit() {
		return profit.get();
	}

	public void setProfit(float profit) {
		this.profit.set(profit);
	}

	public float getIncome() {
		return income.get();
	}

	public void setIncome(float income) {
		this.income.set(income);
	}

	public float getEquitalCapital() {
		return equitalCapital.get();
	}

	public void setEquitalCapital(float equitalCapital) {
		this.equitalCapital.set(equitalCapital);
	}

	public float getInitialCapital() {
		return initialCapital.get();
	}

	public void setInitialCapital(float initialCapital) {
		this.initialCapital.set(initialCapital);
	}

	public int getVolume() {
		return volume.get();
	}

	public void setVolume(int volume) {
		this.volume.set(volume);
	}

	public float getAssetsTurnover() {
		return assetsTurnover.get();
	}

	public void setAssetsTurnover(float assetsTurnover) {
		this.assetsTurnover.set(assetsTurnover);
	}

	public String getRandomDate() {
		return randomDate.toString();
	}
}
