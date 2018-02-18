package stockapp.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StockExchange extends Market implements Serializable{
	transient private StringProperty nameStockExchange;
	transient private StringProperty countryStockExchange;
	transient private StringProperty currencyStockExchange;
	transient private StringProperty cityStockExchange;
	transient private StringProperty addressStockExchange;
	transient private double CEOSalary;
	transient private ObservableList<Index> listOfIndex=FXCollections.observableArrayList();
	transient private ObservableList<Company> listOfCompanyOnStockExchange=FXCollections.observableArrayList();
	
	
	public StockExchange() {
		this.nameStockExchange=new SimpleStringProperty("StockExchange");
		this.countryStockExchange=new SimpleStringProperty("Country");
		this.currencyStockExchange=new SimpleStringProperty("Currency");
		this.cityStockExchange=new SimpleStringProperty("City");
		this.addressStockExchange=new SimpleStringProperty("Street 43");
	}
	
	@Override
	public double getMarkup(double price) {
		double markup=0.03;
		CEOSalary+=price*markup;
		return price*markup;
	}
	
	public StringProperty getNameStockExchangeProperty() {
		return nameStockExchange;
	}
	public String getNameStockExchange() {
		return nameStockExchange.get();
	}
	
	public void setNameStockExchange(String nameStockExchange) {
		this.nameStockExchange.set(nameStockExchange);
	}
	public String getCountryStockExchange() {
		return countryStockExchange.get();
	}
	public void setCountryStockExchange(String countryStockExchange) {
		this.countryStockExchange.set(countryStockExchange);
	}
	
	public String getCurrencyStockExchange() {
		return currencyStockExchange.get();
	}
	public void setCurrencyStockExchange(String currencyStockExchange) {
		this.currencyStockExchange.set(currencyStockExchange);
	}
	public String getCityStockExchange() {
		return cityStockExchange.get();
	}
	public void setCityStockExchange(String cityStockExchange) {
		this.cityStockExchange.set(cityStockExchange);
	}
	public String getAddressStockExchange() {
		return addressStockExchange.get();
	}
	public void setAddressStockExchange(String addressStockExchange) {
		this.addressStockExchange.set(addressStockExchange);
	}
	public ObservableList<Index> getListOfIndex() {
		return listOfIndex;
	}
	public void setListOfIndex(ObservableList<Index> listOfIndex) {
		this.listOfIndex = listOfIndex;
	}
	
	

	public ObservableList<Company> getListOfCompanyOnStockExchange() {
		return listOfCompanyOnStockExchange;
	}


	public void setListOfCompanyOnStockExchange(ObservableList<Company> listOfComppanyOnStockExchange) {
		this.listOfCompanyOnStockExchange = listOfComppanyOnStockExchange;
	}


	private void writeObject(ObjectOutputStream oos) {
		try {
			oos.defaultWriteObject();
			oos.writeObject(nameStockExchange.get());
			oos.writeObject(countryStockExchange.get());
			oos.writeObject(currencyStockExchange.get());
			oos.writeObject(cityStockExchange.get());
			oos.writeObject(addressStockExchange.get());
			oos.writeObject(new ArrayList<Index>(listOfIndex));
			oos.writeObject(new ArrayList<Company>(listOfCompanyOnStockExchange));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readObject(ObjectInputStream ois) {
		try {
			ois.defaultReadObject();
			nameStockExchange=new SimpleStringProperty((String) ois.readObject());
			countryStockExchange=new SimpleStringProperty((String) ois.readObject());
			currencyStockExchange=new SimpleStringProperty((String) ois.readObject());
			cityStockExchange=new SimpleStringProperty((String) ois.readObject());
			addressStockExchange=new SimpleStringProperty((String) ois.readObject());
			List<Index> listindex = (List<Index>) ois.readObject();
			this.setListOfIndex(FXCollections.observableArrayList(listindex));
			List<Company> company=(List<Company>)ois.readObject();
			this.setListOfCompanyOnStockExchange(FXCollections.observableArrayList(company));
		} catch (ClassNotFoundException | IOException e) {}

	}

	
	
}
