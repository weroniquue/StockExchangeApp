package stockapp.view;

import java.io.File;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import stockapp.Main;

public class RootLayoutController {
	private Main main;

	public void setMain(Main main) {
		this.main = main;
	}

	@FXML
	private void handleNew() {
		main.getCompanyData().clear();
		main.getCurrencyData().clear();
		main.getIndexData().clear();
		main.getStockExchangeData().clear();
		main.getInvestorData().clear();
		main.getInvestmentFundData().clear();
		
		main.setFilePath(null);
	}

	@FXML
	private void handleSave() {
		File file=main.getfilePath();
		if(file!=null) {
			main.saveDataToFile(file.getAbsolutePath());
		}else {
			handleSave();
		}
	}

	@FXML
	private void handleSaveAs() {
		FileChooser fileChooser = new FileChooser();

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Ser files (*.ser)", "*.ser");
		fileChooser.getExtensionFilters().add(extFilter);

		File file = fileChooser.showSaveDialog(main.getPrimaryStage());

		if (file != null) {
			if (!file.getPath().endsWith(".ser")) {
				file = new File(file.getPath() + ".ser");
			}
			main.saveDataToFile(file.getPath());
		}
	}
	
	@FXML
	private void handleOpen() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Ser files (*.ser)", "*.ser");
		
		File file = fileChooser.showOpenDialog(main.getPrimaryStage());
		
		if(file!=null) {
			main.loadDataFromFile(file);
		}
	}
}
