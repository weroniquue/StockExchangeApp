package stockapp.view;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import stockapp.Main;
import stockapp.model.Commodity;

public class CommodityOverviewController {
	@FXML
	private Label commodityName;
	@FXML
	private Label measurementUnit;
	@FXML
	private Label currency;
	@FXML
	private Label currentPrice;
	@FXML
	private Label minimalPrice;
	@FXML
	private Label maximalPrice;
	@FXML
	TableView<Commodity> commodityTable;
	@FXML
	TableColumn<Commodity, String> commodityColumn;
	@FXML
	private LineChart<?, ?> linechart;
	@FXML
	private CategoryAxis x;
	@FXML
	private NumberAxis y;

	private XYChart.Series seria1 = new XYChart.Series();
	
	private Main main;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	public CommodityOverviewController() {
		// TODO Auto-generated constructor stub
	}
	
	@FXML
	public void initialize() {
		
		commodityColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());


		commodityTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showCommodityDetails(newValue));

		
		linechart.setCreateSymbols(false);
		linechart.setAnimated(true);
		linechart.setLegendVisible(false);
		
	}
	
	/**Allows to have access to main.*/
	public void setMain(Main main) {
		this.main=main;
		
		/**Add commodity to Table.*/
		commodityTable.setItems(main.getCommodityData());
	}
	
	/**It display in the scene current information about commodity and draw line graph in time.*/
	public void showCommodityDetails(Commodity commodity) {
		if(commodity!=null) {
			commodityName.setText(commodity.getName());
			measurementUnit.setText(commodity.getMeasurementUnit());
			currentPrice.setText(String.valueOf(commodity.getCurrentPrice()));
			minimalPrice.setText(String.valueOf(commodity.getMinimalPrice()));
			maximalPrice.setText(String.valueOf(commodity.getMaximalPrice()));
			
			seria1.getData().clear();
			for(int i=0;i<commodity.getCurrentPriceList().size();i++) {
				seria1.getData().add(new XYChart.Data<>(commodity.getTimeList().get(i).format(dtf),commodity.getCurrentPriceList().get(i)));
			}
			linechart.getData().add(seria1);

			
		}
	}
	/**It allows to delete commodity from app.*/
	public void deleteCommodity() {
		int selectedIndex = commodityTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Delete");
			alert.setHeaderText("Are you sure?");
			alert.setContentText("Do you want to delete chosen commodity?");

			ButtonType buttonYes = new ButtonType("Yes");
			ButtonType buttonNo = new ButtonType("No");

			alert.getButtonTypes().setAll(buttonYes, buttonNo);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonYes) {
				commodityTable.getItems().remove(selectedIndex);
				main.getCommodityData().remove(commodityTable.getSelectionModel().getSelectedItem());
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("No selection");
			alert.setHeaderText("No commodity selected");
			alert.setContentText("Please select commodity in the table");

			alert.showAndWait();
		}
	}
	
}
