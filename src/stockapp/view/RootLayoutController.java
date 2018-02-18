package stockapp.view;

import java.io.File;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import stockapp.Main;
import stockapp.model.Company;

public class RootLayoutController {
	private Main main;

	public void setMain(Main main) {
		this.main = main;
	}

	/**
	 * This clear all data in app.
	 * */
	@FXML
	private void handleNew() {
		for (Company  company: main.getCompanyData()) {
			company.setStart(false);
		}
		main.getCompanyData().clear();
		main.getCurrencyData().clear();
		main.getCommodityData().clear();
		main.getIndexData().clear();
		main.getStockExchangeData().clear();
		main.getInvestorData().clear();
		main.getInvestmentFundData().clear();
		
		
		main.setFilePath(null);
	}

	
	/**Sava data in file.*/
	@FXML
	private void handleSave() {
		File file=main.getfilePath();
		if(file!=null) {
			main.saveDataToFile(file.getAbsolutePath());
		}else {
			handleSaveAs();
		}
	}

	/**User can choose the file, where the program can save data.*/
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
	
	/**It allows to open save data in file.*/
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
