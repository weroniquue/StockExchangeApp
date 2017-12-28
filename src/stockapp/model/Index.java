package stockapp.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Index {
	private StringProperty nameIndex;
	private ObservableList<Company> companyInIndex = FXCollections.observableArrayList();
	private FloatProperty resultsOfIndex;

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

}
