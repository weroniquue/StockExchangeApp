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
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The class is model of commodity.*/

//Surowiec jako towar!
public class Commodity implements Serializable{
	/**
	 * @param name The name of commodity.
	 * @param measurementUnit The measurement unit of commodity.
	 * @param currency The currency , which we have to pay for commodity.
	 * @param currentPrice The current price of commodity,
	 * @param minimalPrice The minimal price of commodity.
	 * @param maximalPrice The maximal price of commodity.
	 * @param timeList It is the list where is keep time of bought commodity. It's necessary to draw line graph.
	 * @param currentPriceList It's the list where is keep price of bought commodity.
	 */
	transient private StringProperty name;
	transient private StringProperty measurementUnit;
	transient private Currency currency;
	transient private SimpleDoubleProperty currentPrice;
	transient private SimpleDoubleProperty minimalPrice;
	transient private DoubleProperty maximalPrice;
	transient public ObservableList<LocalTime> timeList=FXCollections.observableArrayList();
	transient public ObservableList<Double> currentPriceList=FXCollections.observableArrayList();
	
	Random ranodm=new Random();
	
	public Commodity() {
		this.name=new SimpleStringProperty("A");
		this.measurementUnit=new SimpleStringProperty("g");
		this.currentPrice=new SimpleDoubleProperty(ranodm.nextDouble()*100);
		this.minimalPrice=new SimpleDoubleProperty(ranodm.nextDouble()*100);
		this.maximalPrice=new SimpleDoubleProperty(ranodm.nextDouble()*100);
	}

	public StringProperty getNameProperty() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
	}
	public String getName() {
		return name.get();
	}

	public String getMeasurementUnit() {
		return measurementUnit.get();
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit.set(measurementUnit);
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Double getCurrentPrice() {
		return currentPrice.get();
	}

	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice.set(currentPrice);
	}

	public Double getMinimalPrice() {
		return minimalPrice.get();
	}

	public void setMinimalPrice(Double minimalPrice) {
		this.minimalPrice.set(minimalPrice);
	}

	public Double getMaximalPrice() {
		return maximalPrice.get();
	}

	public void setMaximalPrice(Double maximalPrice) {
		this.maximalPrice.set(maximalPrice);
	}
	
	public ObservableList<LocalTime> getTimeList(){
		return timeList;
	}
	
	public void setTimeList(ObservableList<LocalTime> timeList){
		this.timeList=timeList;
	}
	public ObservableList<Double> getCurrentPriceList(){
		return currentPriceList;
	}
	
	public DoubleProperty getCurrentPriceProperty() {
		return currentPrice;
	}
	

	public void setCurrentPriceList(ObservableList<Double> currentPriceList) {
		this.currentPriceList = currentPriceList;
	}

	/**
	 * It allows to make serialization property
	 * */
	private void writeObject(ObjectOutputStream oos) {
		try {
			oos.defaultWriteObject();
			oos.writeObject(name.get());
			oos.writeObject(measurementUnit.get());
			oos.writeObject(currency);
			oos.writeObject(currentPrice.get());
			oos.writeObject(minimalPrice.get());
			oos.writeObject(maximalPrice.get());
			oos.writeObject(new ArrayList<LocalTime>(this.timeList));
			oos.writeObject(new ArrayList<Double>(this.currentPriceList));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * It allows to make deserialization property
	 * */
	private void readObject(ObjectInputStream ois) {
		try {
			ois.defaultReadObject();
			name = new SimpleStringProperty((String) ois.readObject());
			measurementUnit = new SimpleStringProperty((String) ois.readObject());
			currency=(Currency) ois.readObject();
			currentPrice=new SimpleDoubleProperty((Double) ois.readObject());
			minimalPrice=new SimpleDoubleProperty((Double) ois.readObject());
			maximalPrice=new SimpleDoubleProperty((Double) ois.readObject());
			
			List<LocalTime> timeList=(List<LocalTime>) ois.readObject();
			this.setTimeList(FXCollections.observableArrayList(timeList));
			
			List<Double> currentPrice=(List<Double>) ois.readObject();
			this.setCurrentPriceList(FXCollections.observableArrayList(currentPrice));
			
			
		} catch (ClassNotFoundException | IOException e) {}

	}
	

}
