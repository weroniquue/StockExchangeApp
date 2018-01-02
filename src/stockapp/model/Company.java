package stockapp.model;

import java.util.Random;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.math.BigDecimal;
import java.text.ParseException;


@XmlRootElement(name="company")
public class Company extends Thread{
	private StringProperty companyName;// nazwa spolki
	private StringProperty dateOfFirstValuation;// data pierwszej wyceny
	private FloatProperty openiningPrice;// kurs otwarcia
	private SimpleFloatProperty currentPrice;// obecny kurs
	private SimpleFloatProperty minimumPrice;// minimalna kurs
	private SimpleIntegerProperty numberOfStock;// liczba akcji
	private SimpleFloatProperty profit;// zysk
	private SimpleFloatProperty income;// przychod
	private SimpleFloatProperty equitalCapital;// kapital wlasny
	private SimpleFloatProperty initialCapital;// kapital zak³adowy
	private SimpleIntegerProperty volume;// wolumen
	private SimpleFloatProperty assetsTurnover;// obrot
	//private ObservableList<Stock> listOfShares=FXCollections.observableArrayList();
	
	private RandomDate randomDate = new RandomDate();
	Random random = new Random();

	public static float roundToDouble(float d) {
		BigDecimal bd=new BigDecimal(Float.toString(d));
		bd=bd.setScale(2,BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}
	
	public Company(String name) {
		this.companyName = new SimpleStringProperty(name);
		try {
			this.dateOfFirstValuation = new SimpleStringProperty(randomDate.GetRandomDate());
		} catch (ParseException e) {
			this.dateOfFirstValuation = new SimpleStringProperty("01.01.2017");
		}

		this.openiningPrice = new SimpleFloatProperty(roundToDouble(1 + random.nextFloat() * (20 - 1)));
		this.currentPrice = new SimpleFloatProperty(roundToDouble(1 + random.nextFloat() * (20 - 1) + openiningPrice.get() / 2));
		this.minimumPrice = new SimpleFloatProperty(1);
		this.numberOfStock = new SimpleIntegerProperty(random.nextInt(400));
		this.profit = new SimpleFloatProperty(roundToDouble(random.nextFloat() * (40000 - 100) + 100));
		this.income = new SimpleFloatProperty(roundToDouble(random.nextFloat() * (40000 - 100) + 100));
		this.equitalCapital = new SimpleFloatProperty(100000);
		this.initialCapital = new SimpleFloatProperty(50000);
		this.volume = new SimpleIntegerProperty(random.nextInt(35));
		this.assetsTurnover = new SimpleFloatProperty(currentPrice.get() * volume.get()); // Czy to siÄ™ tak liczy?

	}

	public Company() {
		this(null);
	}

	@XmlElement(name="companyName")
	public String getCompanyName() {
		return companyName.get();
	}

	public StringProperty getCompanyNameProperty() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName.set(companyName);
	}
	
	@XmlElement(name="dateOfFirstValuation")
	public String getDateOfFirstValuation() {
		return dateOfFirstValuation.get();
	}

	public void setDateOfFirstValuation(String dateOfFirstValuation) {
		this.dateOfFirstValuation.set(dateOfFirstValuation);
	}

	@XmlElement(name="openingPrice")
	public float getOpeniningPrice() {
		return openiningPrice.get();
	}

	public void setOpeniningPrice(float openiningPrice) {
		this.openiningPrice.set(openiningPrice);
	}
	
	@XmlElement(name="currentPrice")
	public float getCurrentPrice() {
		return currentPrice.get();
	}

	public void setCurrentPrice(float currentPrice) {
		this.currentPrice.set(currentPrice);
	}

	@XmlElement(name="minimumPrice")
	public float getMinimumPrice() {
		return minimumPrice.get();
	}

	public void setMinimumPrice(float minimumPrice) {
		this.minimumPrice.set(minimumPrice);
	}

	@XmlElement(name="numberOfStock")
	public int getNumberOfStock() {
		return numberOfStock.get();
	}

	public void setNumberOfStock(int numberOfStock) {
		this.numberOfStock.set(numberOfStock);
	}

	@XmlElement(name="profit")
	public float getProfit() {
		return profit.get();
	}

	public void setProfit(float profit) {
		this.profit.set(profit);
	}

	@XmlElement(name="income")
	public float getIncome() {
		return income.get();
	}

	public void setIncome(float income) {
		this.income.set(income);
	}

	@XmlElement(name="equitalCapital")
	public float getEquitalCapital() {
		return equitalCapital.get();
	}

	public void setEquitalCapital(float equitalCapital) {
		this.equitalCapital.set(equitalCapital);
	}

	@XmlElement(name="initialCapital")
	public float getInitialCapital() {
		return initialCapital.get();
	}

	public void setInitialCapital(float initialCapital) {
		this.initialCapital.set(initialCapital);
	}

	@XmlElement(name="volume")
	public int getVolume() {
		return volume.get();
	}

	public void setVolume(int volume) {
		this.volume.set(volume);
	}

	@XmlElement(name="assetsTurnover")
	public float getAssetsTurnover() {
		return assetsTurnover.get();
	}

	public void setAssetsTurnover(float assetsTurnover) {
		this.assetsTurnover.set(assetsTurnover);
	}


	
	/*public void emitujAkcje() {
		int ilosc= random.nextInt(20);
		int cena=5;
		this.numberOfStock.set(this.numberOfStock.get()+ilosc);
		for(int i=0;i<ilosc;i++) {
			listaAkcji.add(new Akcja(this.getName(), null, cena));
		}
		System.out.println("Ilosc akcji spolki: "+this.companyName.get()+numberOfStock.get());
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(random.nextInt(10000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			emitujAkcje();
		}
	}*/
	
}
