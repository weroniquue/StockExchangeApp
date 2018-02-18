package stockapp.view;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import stockapp.Main;
import stockapp.model.Currency;

public class AddCurrencyController {
	private Main main;
	private Stage window;
	@FXML
	private TextField nameCurrency;
	@FXML
	private TextField codeCurrency;
	@FXML
	private CheckBox country1;
	@FXML
	private CheckBox country2;
	@FXML
	private CheckBox country3;
	@FXML
	private CheckBox country4;
	@FXML
	private CheckBox country5;
	@FXML
	private CheckBox country6;
	@FXML
	private CheckBox country7;
	@FXML
	private CheckBox country8;
	@FXML
	private CheckBox country9;
	@FXML
	private CheckBox country10;
	@FXML
	private CheckBox country11;
	@FXML
	private CheckBox country12;
	ArrayList<CheckBox> checkBoxs=new ArrayList<>();
	
	public AddCurrencyController() {
		// TODO Auto-generated constructor stub
	}
	
	public void setMain(Main main) {
		this.main=main;
	}
	public void setStage(Stage window) {
		this.window=window;
	}
	
	public void handleCancel() {
		this.window.close();
	}
	
	public void handleSave() {
		if(isValid()) {
			Currency tmp=new Currency();
			tmp.setNameCurrency(nameCurrency.getText());
			tmp.setCodeCurrency(codeCurrency.getText());
			tmp.getWhereIsUsed().clear();
			if(country1.isSelected()) {
				tmp.getWhereIsUsed().add(country1.getText());
			}
			if(country2.isSelected()) {
				tmp.getWhereIsUsed().add(country2.getText());
			}
			if(country3.isSelected()) {
				tmp.getWhereIsUsed().add(country3.getText());
			}
			if(country4.isSelected()) {
				tmp.getWhereIsUsed().add(country4.getText());
			}
			if(country5.isSelected()) {
				tmp.getWhereIsUsed().add(country5.getText());
			}
			if(country6.isSelected()) {
				tmp.getWhereIsUsed().add(country6.getText());
			}
			if(country6.isSelected()) {
				tmp.getWhereIsUsed().add(country6.getText());
			}
			if(country7.isSelected()) {
				tmp.getWhereIsUsed().add(country7.getText());
			}
			if(country8.isSelected()) {
				tmp.getWhereIsUsed().add(country8.getText());
			}
			if(country9.isSelected()) {
				tmp.getWhereIsUsed().add(country9.getText());
			}
			if(country10.isSelected()) {
				tmp.getWhereIsUsed().add(country10.getText());
			}
			if(country11.isSelected()) {
				tmp.getWhereIsUsed().add(country11.getText());
			}
			if(country12.isSelected()) {
				tmp.getWhereIsUsed().add(country12.getText());
			}
			main.getCurrencyData().add(tmp);
		}
		window.close();
	}
	
	public boolean isValid() {
		String error="";
		if(nameCurrency.getText()==null||nameCurrency.getText().length()==0) {
			error+="No valid currency name!\n";
		}
		if(codeCurrency.getText()==null||codeCurrency.getText().length()==0) {
			error+="No valid currency code!\n";
		}
		if(error.length()==0) {
			return true;
		}
		else {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(window);
			alert.setTitle("Invalid fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(error);

			alert.showAndWait();
			
			return false;
		}
	}
}
