package stockapp.view;

import java.io.File;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import stockapp.Main;

public class RootLayoutController {
	private Main main;
	
	public void setMain(Main main) {
		this.main=main;
	}
	
	@FXML
	private void handleNew() {
		main.getCompanyData().clear();
	}
	
	@FXML
	private void handleSave() {
		handleSaveAs();
	}
	
	@FXML
	private void handleSaveAs() {
		FileChooser fileChooser=new FileChooser();
		
		FileChooser.ExtensionFilter extFilter=new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);
		
		File file =fileChooser.showSaveDialog(main.getPrimaryStage());
		
		if(file !=null) {
			 if (!file.getPath().endsWith(".xml")) {
	                file = new File(file.getPath() + ".xml");
	                
	            }
	         //file=new File(file.getPath());
		main.saveDataToFile(file);
	   
		}
	}
}
