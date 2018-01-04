package stockapp.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class InvestmentFund extends Thread{

	private StringProperty nameInvestmentFound;
	private StringProperty nameManager;
	private FloatProperty budget;
	
	public InvestmentFund() {
		this.nameInvestmentFound=new SimpleStringProperty();
		this.nameManager=new SimpleStringProperty();
		this.budget=new SimpleFloatProperty();
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

	public Float getBudget() {
		return budget.get();
	}

	public void setBudget(Float budget) {
		this.budget.set(budget);
	}
	
	
	
	
	
	
}
