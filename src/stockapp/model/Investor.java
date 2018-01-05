package stockapp.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class Investor extends Thread implements Serializable {

	transient private StringProperty nameInvestor;
	transient private StringProperty secondNameInvestor;
	transient private StringProperty numberPESEL;
	transient private DoubleProperty budget;
	//dopisz do zapisu do pliku 
	//private ObservableMap<, int>

	public Investor() {
		this.nameInvestor = new SimpleStringProperty();
		this.secondNameInvestor = new SimpleStringProperty();
		this.numberPESEL = new SimpleStringProperty();
		this.budget = new SimpleDoubleProperty();
	}

	public StringProperty getNameInvestorProperty() {
		return nameInvestor;
	}

	public String getNameInvestor() {
		return nameInvestor.get();
	}

	public void setNameInvestor(String nameInvestor) {
		this.nameInvestor.set(nameInvestor);
	}

	public String getSecondNameInvestor() {
		return secondNameInvestor.get();
	}

	public void setSecondNameInvestor(String secondNameInvestor) {
		this.secondNameInvestor.set(secondNameInvestor);
	}

	public String getNumberPESEL() {
		return numberPESEL.get();
	}

	public void setNumberPESEL(String numberPESEL) {
		this.numberPESEL.set(numberPESEL);
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
			oos.writeObject(nameInvestor.get());
			oos.writeObject(secondNameInvestor.get());
			oos.writeObject(numberPESEL.get());
			oos.writeObject(budget.get());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void readObject(ObjectInputStream ois) {
		try {
			ois.defaultReadObject();
			nameInvestor = new SimpleStringProperty((String) ois.readObject());
			secondNameInvestor = new SimpleStringProperty((String) ois.readObject());
			numberPESEL = new SimpleStringProperty((String) ois.readObject());
			budget = new SimpleDoubleProperty((Double) ois.readObject());
		} catch (ClassNotFoundException | IOException e) {
		}
	}

	public void increaseBudget() {
		Random random=new Random();
		double ranodmBudget= Math.round(random.nextFloat()*500*100)/100.0;
		synchronized (this) {
			budget.set(budget.get()+ranodmBudget);
		}
	}
	
}
