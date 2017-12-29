package stockapp.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Currency {
	
	private StringProperty nameCurrency;
	private StringProperty codeCurrency;
	private ObservableList<String> whereIsUsed=FXCollections.observableArrayList();
	//private List<String> whereIsUsed= new ArrayList<>();
	
	public Currency() {
		this.nameCurrency=new SimpleStringProperty("Euro");
		this.nameCurrency=new SimpleStringProperty("EUR");
		this.whereIsUsed.addAll("Niemc","Franjca");
		
	}
	
	public Currency(String name, String code) {
		this.nameCurrency=new SimpleStringProperty(name);
		this.codeCurrency=new SimpleStringProperty(code);
		this.whereIsUsed.addAll("ble","sdfbwevfk");
	}
	
	public StringProperty getCurrencyNameProperty() {
		return nameCurrency;
	}

	public String getCurrencyName() {
		return nameCurrency.get();
	}

	public void setCurrencyName(String nameCurrency) {
		this.nameCurrency.set(nameCurrency);
	}
	
	public StringProperty getCodeCurrencyProperty() {
		return codeCurrency;
	}
	
	public String getCodeCurrency() {
		return codeCurrency.get();
	}
	
	public void setCodeCurrency(String code) {
		this.codeCurrency.set(code);
	}
	
	//public ObservableList<StringProperty> getWhereIsUsed(){
	//	return whereIsUsed;
	//}
	
	public ObservableList<String> getWhereIsUsed(){
		return whereIsUsed;
	}
	@Override
	public String toString() {
		return getCurrencyName();
	}
}
