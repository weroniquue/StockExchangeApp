package stockapp.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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

/**The class is model of company*/
public class Index implements Serializable {
	transient private StringProperty nameIndex;
	transient private ObservableList<Company> companyInIndex = FXCollections.observableArrayList();
	transient private DoubleProperty resultsOfIndex;
	transient private StockExchange stockEchange;
	Random random=new Random();

	/**@param stockExchange This is the stock exchange where the index is created.*/
	public Index(StockExchange stockExchange) {
		nameIndex = new SimpleStringProperty("Index");
		resultsOfIndex = new SimpleDoubleProperty(20.12);
		this.stockEchange = stockExchange;
	}

	public StringProperty getNameIndexProperty() {
		return nameIndex;
	}

	public String getNameIndex() {
		return nameIndex.get();
	}

	public void setNameIndex(String nameIndex) {
		this.nameIndex.set(nameIndex);
	}

	public ObservableList<Company> getCompanyInIndex() {
		return companyInIndex;
	}

	public void setCompanyInIndex(ObservableList<Company> companyInIndex) {
		this.companyInIndex = companyInIndex;
	}
	
	/**
	 * It sum the current price of all company in the index.*/
	public void calculateResults() {
		double current=0;
		for (Company company : companyInIndex) {
			current += company.getCurrentPrice();
		}
		current+=current*random.nextDouble()+10;
		resultsOfIndex.set(current);
	}

	public double getResultsOfIndex() {
		calculateResults();
		return resultsOfIndex.get();
	}

	public void setResultsOfIndex(float resultsOfIndex) {
		this.resultsOfIndex.set(resultsOfIndex);
	}

	private void writeObject(ObjectOutputStream oos) {
		try {
			oos.defaultWriteObject();
			oos.writeObject(nameIndex.get());
			oos.writeObject(new ArrayList<Company>(companyInIndex));
			oos.writeObject(resultsOfIndex.get());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void readObject(ObjectInputStream ois) {
		try {
			ois.defaultReadObject();
			nameIndex = new SimpleStringProperty((String) ois.readObject());
			List<Company> listCompanyInIndex = (List<Company>) ois.readObject();
			this.setCompanyInIndex(FXCollections.observableArrayList(listCompanyInIndex));
			resultsOfIndex = new SimpleDoubleProperty((Double) ois.readObject());

		} catch (ClassNotFoundException | IOException e) {
		}

	}

}
