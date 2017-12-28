package stockapp.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//Surowiec jako towar!
public class Commodity {
	private StringProperty name;
	private StringProperty measurementUnit;
	private Currency currency;
	private FloatProperty currentPrice;
	private FloatProperty minimalPrice;
	private FloatProperty maximalPrice;
	
	public Commodity() {
		this.name=new SimpleStringProperty();
		this.measurementUnit=new SimpleStringProperty();
		this.currentPrice=new SimpleFloatProperty();
		this.minimalPrice=new SimpleFloatProperty();
		this.maximalPrice=new SimpleFloatProperty();
	}

	public StringProperty getNameProperty() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
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

	public Float getCurrentPrice() {
		return currentPrice.get();
	}

	public void setCurrentPrice(Float currentPrice) {
		this.currentPrice.set(currentPrice);
	}

	public Float getMinimalPrice() {
		return minimalPrice.get();
	}

	public void setMinimalPrice(Float minimalPrice) {
		this.minimalPrice.set(minimalPrice);
	}

	public Float getMaximalPrice() {
		return maximalPrice.get();
	}

	public void setMaximalPrice(Float maximalPrice) {
		this.maximalPrice.set(maximalPrice);
	}
	
	

}
