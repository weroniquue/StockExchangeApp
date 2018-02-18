package stockapp.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**The class is model of currency*/
public class Currency implements Serializable {

	/**@param whereIsUsed is the list where are kept country where the currency is used.
	 * */
	transient private StringProperty nameCurrency;
	transient private StringProperty codeCurrency;
	transient private ObservableList<String> whereIsUsed = FXCollections.observableArrayList();

	public Currency() {
		this.nameCurrency = new SimpleStringProperty("Euro");
		this.codeCurrency = new SimpleStringProperty("PLN");
		this.whereIsUsed.addAll("Francja");

	}

	public StringProperty getNameCurrencyProperty() {
		return nameCurrency;
	}

	public String getNameCurrency() {
		return nameCurrency.get();
	}

	public void setNameCurrency(String nameCurrency) {
		this.nameCurrency.set(nameCurrency);
	}

	public StringProperty getCodeCurrencyProperty() {
		return codeCurrency;
	}

	public String getCodeCurrency() {
		return codeCurrency.get();
	}

	public void setCodeCurrency(String codeCurrency) {
		this.codeCurrency.set(codeCurrency);
	}

	public ObservableList<String> getWhereIsUsed() {
		return whereIsUsed;
	}

	public void setWhereIsUsed(ObservableList<String> whereIsUsed) {
		this.whereIsUsed = whereIsUsed;
	}
	
	/**@see main*/
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

	/**@see main*/
	private void readObject(ObjectInputStream ois) {
		try {
			ois.defaultReadObject();
			nameCurrency = new SimpleStringProperty((String) ois.readObject());
			codeCurrency = new SimpleStringProperty((String) ois.readObject());
			
			List<String> listwhereisused = (List<String>) ois.readObject();
			this.setWhereIsUsed(FXCollections.observableArrayList(listwhereisused));
		} catch (ClassNotFoundException | IOException e) {
		}

	}

}
