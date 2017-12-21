package stockapp.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.text.DateFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import stockapp.Main;
import stockapp.model.Company;

public class CompanyAddController {
	@FXML
	private TextField companyNameTextField;
	@FXML
	//private TextField dateOfFirstValuationTextField;
	private DatePicker dateOfFirstValuationTextField;
	@FXML
	private TextField openiningPriceTextField;
	@FXML
	private TextField currentPriceTextField;
	@FXML
	private TextField minimumPriceTextField;
	@FXML
	private TextField numberOfStockTextField;
	@FXML
	private TextField profitsTextField;
	@FXML
	private TextField incomeTextField;
	@FXML
	private TextField equitalCapitalTextField;
	@FXML
	private TextField initialCapitalTextField;
	@FXML
	private TextField volumeTextField;
	@FXML
	private TextField assetsTurnoverTextField;
	
	
	private Main main;
	private Stage stage;
	
	public void setMain(Main main) {
		this.main=main;
	}
	
	public void setStage(Stage stage) {
		this.stage=stage;
	}
	
	public void handleOK() {
		//if(true) {
			Company tmp=new Company("a");
			tmp.setCompanyName(companyNameTextField.getText());
			
			dateOfFirstValuationTextField.setConverter(new StringConverter<LocalDate>() {
				String pattern= "dd.MM.yyyy";
				DateTimeFormatter dateFormatter=DateTimeFormatter.ofPattern(pattern);
				
				
				@Override
				public String toString(LocalDate date) {
					if(date!=null) {
						return dateFormatter.format(date);
					}else {
						return "";
					}
				}
				
				@Override
				public LocalDate fromString(String string) {
					 if (string != null && !string.isEmpty()) {
				         return LocalDate.parse(string, dateFormatter);
				     } else {
				         return null;
				     }
				}
			});
			
			System.out.println(dateOfFirstValuationTextField.getConverter());
			tmp.setDateOfFirstValuation(dateOfFirstValuationTextField.getValue().toString());
			tmp.setMinimumPrice(Float.parseFloat(minimumPriceTextField.getText()));
			
			main.getCompanyData().add(tmp);
			stage.close();
			
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("Completed");
			alert.setHeaderText("Company added");

			alert.showAndWait();
		}
	
	
	public void handleCancel() {
		stage.close();
	}

}
