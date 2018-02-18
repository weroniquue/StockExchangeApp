package stockapp.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import stockapp.Main;

/***It's the mode of investment fund paperr.
 */
public class InvestmentFundPaper implements Serializable{
	
	transient private InvestorIndividual investor;
	transient private InvestmentFund investmentFund;
	transient private DoubleProperty budget;
	
	/**
	 * @param investor It is the owner who bought the paper.
	 * @param budget It is the budget to investment fund from owner.
	 * @param investmentFund It is the owner of paper.
	 * */
	public InvestmentFundPaper(InvestorIndividual investor, double budget,InvestmentFund investmentFund) {
		this.investor=investor;
		this.budget=new SimpleDoubleProperty(budget);
		this.investmentFund=investmentFund;
	}

	public InvestorIndividual getInvestor() {
		return investor;
	}

	public void setInvestor(InvestorIndividual investor) {
		this.investor = investor;
	}

	public DoubleProperty getBudget() {
		return budget;
	}

	public void setBudget(DoubleProperty budget) {
		this.budget = budget;
	}

	public InvestmentFund getInvestmentFund() {
		return investmentFund;
	}

	public void setInvestmentFund(InvestmentFund investmentFund) {
		this.investmentFund = investmentFund;
	}
	
	/**@see Main*/
	private void writeObject(ObjectOutputStream oos) {
		try {
			oos.defaultWriteObject();
			oos.writeObject(investor);
			oos.writeObject(investmentFund);
			oos.writeObject(budget);
		
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

	/**@see main*/
	private void readObject(ObjectInputStream ois) {
		try {
			ois.defaultReadObject();
			investor=(InvestorIndividual)ois.readObject();
			investmentFund=(InvestmentFund)ois.readObject();
			budget= new SimpleDoubleProperty((Double)ois.readObject());
		
			
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
		}
	}
	
}
