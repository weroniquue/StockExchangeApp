package stockapp.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Currency implements Serializable {

	transient private StringProperty nameCurrency;
	transient private StringProperty codeCurrency;
	transient private ObservableList<String> whereIsUsed = FXCollections.observableArrayList();

	public Currency() {
		this.nameCurrency = new SimpleStringProperty("Euro");
		this.nameCurrency = new SimpleStringProperty("EUR");
		// this.whereIsUsed.addAll("Niemc","Franjca");

	}

	public Currency(String name, String code) {
		this.nameCurrency = new SimpleStringProperty(name);
		this.codeCurrency = new SimpleStringProperty(code);
		this.whereIsUsed.addAll("Francja");
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

	public ObservableList<String> getWhereIsUsed() {
		return whereIsUsed;
	}

	public void setWhereIsUsed(ObservableList<String> list) {
		this.whereIsUsed = list;
	}

	@Override
	public String toString() {
		return getCurrencyName();
	}

	private void writeObject(ObjectOutputStream oos) {
		try {
			oos.defaultWriteObject();
			oos.writeObject(nameCurrency.get());
			oos.writeObject(codeCurrency.get());
			oos.writeObject(new ArrayList<String>(whereIsUsed));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void readObject(ObjectInputStream ois) {
		try {
			ois.defaultReadObject();
			nameCurrency = new SimpleStringProperty((String) ois.readObject());
			codeCurrency = new SimpleStringProperty((String) ois.readObject());
			List<String> listwhereisused = (List<String>) ois.readObject();
			this.setWhereIsUsed(FXCollections.observableArrayList(listwhereisused));
		} catch (ClassNotFoundException | IOException e) {}

	}

}
