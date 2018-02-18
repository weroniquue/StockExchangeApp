package stockapp.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class CommodityPaper implements Serializable {

	transient private Commodity commodity;
	transient private InvestorIndividual investor;
	transient private InvestmentFund investmetnFund;
	transient private IntegerProperty amount;
	transient private DoubleProperty price;
	
	/**
	 * Class constructor specifying the commodity(@param commodity),owner of commodity paper: investor(@param invsetor) or investment fund(@param investmentFund), the amount of commodity(@param amount) 
	 * and price(@param price)
	 * 
	 *   */
	public CommodityPaper(Commodity commodity, InvestorIndividual investor,InvestmentFund investmentFund, int amount, double price) {
		this.commodity=commodity;
		this.investor=investor;
		this.investmetnFund=investmentFund;
		this.amount=new SimpleIntegerProperty(amount);
		this.price=new SimpleDoubleProperty(price);
	}

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public InvestorIndividual getInvestor() {
		return investor;
	}

	public void setInvestor(InvestorIndividual investor) {
		this.investor = investor;
	}

	public IntegerProperty getAmountProperty() {
		return amount;
	}
	
	public int getAmount() {
		return amount.get();
	}

	public void setAmount(int amount) {
		this.amount.set(amount);
	}

	public DoubleProperty getPriceProperty() {
		return price;
	}
	
	public double getPrice() {
		return price.get();
	}

	public void setPrice(DoubleProperty price) {
		this.price = price;
	}
	
	/**
	 * It allows to make serialization property
	 * */
	private void writeObject(ObjectOutputStream oos) {
		try {
			oos.defaultWriteObject();
			oos.writeObject(commodity);
			oos.writeObject(investor);
			oos.writeObject(investmetnFund);
			oos.writeObject(amount);
			oos.writeObject(price);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * It allows to make deserialization property
	 * */
	private void readObject(ObjectInputStream ois) {
		try {
			ois.defaultReadObject();
			commodity=(Commodity) ois.readObject();
			investor=(InvestorIndividual)ois.readObject();
			investmetnFund=(InvestmentFund)ois.readObject();
			amount=new SimpleIntegerProperty((Integer)ois.readObject());
			price=new SimpleDoubleProperty((Double)ois.readObject());
			
		} catch (ClassNotFoundException | IOException e) {}

	}
	
	
}
