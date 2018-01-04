package stockapp.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Index implements Serializable{
	transient private StringProperty nameIndex;
	transient private ObservableList<Company> companyInIndex = FXCollections.observableArrayList();
	transient private FloatProperty resultsOfIndex;

	public Index() {
		nameIndex=new SimpleStringProperty();
		resultsOfIndex=new SimpleFloatProperty();
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

	public float getResultsOfIndex() {
		for (Company company : companyInIndex) {
			resultsOfIndex.set(resultsOfIndex.get()+company.getCurrentPrice());
		}
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
			nameIndex=new SimpleStringProperty((String) ois.readObject());
			List<Company> listCompanyInIndex=(List<Company>) ois.readObject();
			this.setCompanyInIndex(FXCollections.observableArrayList(listCompanyInIndex));
			resultsOfIndex=new SimpleFloatProperty((Float) ois.readObject());
	
		} catch (ClassNotFoundException | IOException e) {}

	}
	
}
