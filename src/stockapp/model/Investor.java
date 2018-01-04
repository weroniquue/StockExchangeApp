package stockapp.model;

import java.io.Serializable;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Investor implements Serializable {

	private StringProperty nameInvestor;
	private StringProperty secondNameInvestor;
	private StringProperty numberPESEL;
	private FloatProperty budget;
	
	public Investor() {
		this.nameInvestor=new SimpleStringProperty();
		this.secondNameInvestor=new SimpleStringProperty();
		this.numberPESEL=new SimpleStringProperty();
		this.budget=new SimpleFloatProperty();
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

	public Float getBudget() {
		return budget.get();
	}

	public void setBudget(Float budget) {
		this.budget.set(budget);
	}
	
}
