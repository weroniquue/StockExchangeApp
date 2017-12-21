package stockapp.view;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import stockapp.Main;

public class AddController implements Initializable{
	
	ObservableList<String> chooseList=FXCollections.observableArrayList("Company","Index","Currency");
	
	private Main main;
	private Stage window;
	
	@FXML
	private ChoiceBox<String> add;

	
	public AddController() {
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		add.getItems().addAll(chooseList);
		
	}
	
	public String getChoice(ChoiceBox<String> choice) {
		String choose=choice.getValue();
		return choose;
	}
	
	public void setMain(Main main) {
		this.main=main;
	}
	
	public void setStage(Stage window) {
		this.window=window;
	}
	
	
	public void handleCancel() {
		window.close();
	}
	
	public void handleOk() {
		if(add.getValue()==null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("No selection");
			alert.setHeaderText("No selected");
			alert.setContentText("Please select");

			alert.showAndWait();
		}
		else {
				window.setOpacity(0);
				switch (add.getValue()) {
				case "Company":
					main.showCompanyAddStage();
					break;
				case "Index":
					System.out.println("Ble");
					break;
				default:
					break;
				
				}
			}
		window.close();
		
	}


	

	
}
