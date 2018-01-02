package stockapp.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import stockapp.model.Company;

@XmlRootElement(name="companies")
public class CompanyListWrapper {
	private List<Company> company;
	
	@XmlElements(@XmlElement(name="company"))
	public List<Company> getCompany(){
		return company;
	}
	
	public void setCompany(List<Company> company) {
		this.company=company;
	}
	
}