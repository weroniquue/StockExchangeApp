package stockapp.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Stock implements Serializable{
	transient private Company company;
	transient private InvestmentFund investmentFund;
	transient private InvestorIndividual investor;
	transient private DoubleProperty price;

	/**
	 * Constructor with param:
	 * @param company It is the company which create this stock
	 * @param investmentFund and @param investor are owner of the stock after bought.
	 * @param price It is the price of stock.*/
	public Stock(Company company, InvestmentFund investmentFund,
			InvestorIndividual investor, double price) {
		this.company=company;
		this.investmentFund=investmentFund;
		this.investor=investor;
		this.price=new SimpleDoubleProperty(price);
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public InvestmentFund getInvestmentFund() {
		return investmentFund;
	}

	public void setInvestmentFund(InvestmentFund investmentFund) {
		this.investmentFund = investmentFund;
	}

	public InvestorIndividual getInvestor() {
		return investor;
	}

	public void setInvestor(InvestorIndividual investor) {
		this.investor = investor;
	}

	public double getPrice() {
		return price.get();
	}

	public void setPrice(double price) {
		this.price.set(price);
	}
	
	public DoubleProperty getPricePropertty() {
		return price;
	}
	
		
	
	private void writeObject(ObjectOutputStream oos) {
		try {
			oos.defaultWriteObject();
			oos.writeObject(this.company);
			oos.writeObject(this.investmentFund);
			oos.writeObject(this.investor);
			oos.writeObject(this.price);
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

	private void readObject(ObjectInputStream ois) {
		try {
			ois.defaultReadObject();
			company=(Company) ois.readObject();
			investmentFund=(InvestmentFund) ois.readObject();
			investor=(InvestorIndividual)ois.readObject();
			price=new SimpleDoubleProperty((Double) ois.readObject()); 
			
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
		}
	}
	
	
}
