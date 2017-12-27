package stockapp.model;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

public class StockExchange extends Market {
	private StringProperty nameStockExchange;
	private StringProperty countryStockExchange;
	private StringProperty currencyStockExchange;
	private StringProperty cityStockExchange;
	private StringProperty addressStockExchange;
	private ObservableList<Index> listOfIndex=FXCollections.observableArrayList();
	private ObservableSet<Company> allCompanyOnThisExchange=FXCollections.observableSet();
	
	
	public StockExchange() {}
	
	
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
	public ObservableSet<Company> getAllCompanyOnThisExchange() {
		return allCompanyOnThisExchange;
	}
	public void setAllCompanyOnThisExchange(ObservableSet<Company> allCompanyOnThisExchange) {
		this.allCompanyOnThisExchange = allCompanyOnThisExchange;
	}
	
	
	
	
}
