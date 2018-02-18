package stockapp.model;

import java.io.Serializable;

import javafx.beans.property.DoubleProperty;


abstract public class Market implements Serializable {
	private DoubleProperty markup;//mar¿a
	
	public abstract double getMarkup(double price);
}
