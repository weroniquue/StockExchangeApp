package stockapp.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


//Surowiec jako towar!
public class Commodity implements Serializable{
	transient private StringProperty name;
	transient private StringProperty measurementUnit;
	transient private Currency currency;
	transient private SimpleDoubleProperty currentPrice;
	transient private SimpleDoubleProperty minimalPrice;
	transient private DoubleProperty maximalPrice;
	
	public Commodity() {
		this.name=new SimpleStringProperty();
		this.measurementUnit=new SimpleStringProperty();
		this.currentPrice=new SimpleDoubleProperty();
		this.minimalPrice=new SimpleDoubleProperty();
		this.maximalPrice=new SimpleDoubleProperty();
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
	
	
	private void writeObject(ObjectOutputStream oos) {
		try {
			oos.defaultWriteObject();
			oos.writeObject(name.get());
			oos.writeObject(measurementUnit.get());
			oos.writeObject(currency);
			oos.writeObject(currentPrice.get());
			oos.writeObject(minimalPrice.get());
			oos.writeObject(maximalPrice.get());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void readObject(ObjectInputStream ois) {
		try {
			ois.defaultReadObject();
			name = new SimpleStringProperty((String) ois.readObject());
			measurementUnit = new SimpleStringProperty((String) ois.readObject());
			currency=(Currency) ois.readObject();
			currentPrice=new SimpleDoubleProperty((Double) ois.readObject());
			minimalPrice=new SimpleDoubleProperty((Double) ois.readObject());
			maximalPrice=new SimpleDoubleProperty((Double) ois.readObject());
			
		} catch (ClassNotFoundException | IOException e) {}

	}
	

}
