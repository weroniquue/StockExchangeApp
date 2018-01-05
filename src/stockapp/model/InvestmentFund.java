package stockapp.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class InvestmentFund extends Thread implements Serializable {

	transient private StringProperty nameInvestmentFound;
	transient private StringProperty nameManager;
	transient private StringProperty secondNameManager;
	transient private DoubleProperty budget;

	public InvestmentFund() {
		this.nameInvestmentFound = new SimpleStringProperty();
		this.nameManager = new SimpleStringProperty();
		this.budget = new SimpleDoubleProperty();
	}

	public StringProperty getNameInvestmentFoundProperty() {
		return nameInvestmentFound;
	}

	public String getNameInvestmentFound() {
		return nameInvestmentFound.get();
	}

	public void setNameInvestmentFound(String nameInvestmentFound) {
		this.nameInvestmentFound.set(nameInvestmentFound);
	}

	public String getNameManager() {
		return nameManager.get();
	}

	public void setNameManager(String nameManager) {
		this.nameManager.set(nameManager);
	}
	
	public String getSecondNameManager() {
		return secondNameManager.get();
	}

	public void setSecondNameManager(String nameManager) {
		this.secondNameManager.set(nameManager);
	}
	

	public Double getBudget() {
		return budget.get();
	}

	public void setBudget(Double budget) {
		this.budget.set(budget);
	}

	private void writeObject(ObjectOutputStream oos) {
		try {
			oos.defaultWriteObject();
			oos.writeObject(nameInvestmentFound.get());
			oos.writeObject(nameManager.get());
			oos.writeObject(secondNameManager.get());
			oos.writeObject(budget.get());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void readObject(ObjectInputStream ois) {
		try {
			ois.defaultReadObject();
			nameInvestmentFound = new SimpleStringProperty((String) ois.readObject());
			nameManager = new SimpleStringProperty((String) ois.readObject());
			secondNameManager= new SimpleStringProperty((String) ois.readObject());
			budget = new SimpleDoubleProperty((Double) ois.readObject());
		} catch (ClassNotFoundException | IOException e) {
		}

	}

}
