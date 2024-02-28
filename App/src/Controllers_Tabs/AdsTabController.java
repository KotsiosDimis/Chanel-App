package Controllers_Tabs;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import DatabaceTables.AdvertisementsTable;
import DatabaceTables.AdvertiserTable;
import FetchTables.FetchAdsTables;
import Procudures.AdsProcuders;
import application.Constants;
import application.UIManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AdsTabController {
	
	private ObservableList<AdvertisementsTable> Advertisementstable = FXCollections.observableArrayList();
	private ObservableList<AdvertiserTable> Advertisertable = FXCollections.observableArrayList();
	
	private AdvertiserTable selectedAdvertiser;
	private AdvertisementsTable selectedAdvertisement;
	
	@FXML
	private Button AddAdsButtons, EditAdsButtons , RemoveAdsButtons;
	   
	@FXML
	private Button AddAdvertiserButtons, EditAdvertiserButtons, RemoveAdvertiserButtons;

	@FXML
	private ButtonBar advertiserButtonsBar1,  advertiserButtonsBar;

		    
	/////////////////tables views////////////   
	@FXML
	private TableView<AdvertiserTable> advertiserTable;
	
	@FXML
	private TableView<AdvertisementsTable> adsTable;
	
	
	
	
	/////////////////columns////////////
	@FXML private TableColumn<AdvertiserTable, String> companyNameColumnAdvertiser;
	@FXML private TableColumn<AdvertiserTable, String> ContactPersonColumnAdvertiser;
	@FXML private TableColumn<AdvertiserTable, String> phoneColumnAdvertiser;
	@FXML private TableColumn<AdvertiserTable, String> emailColumnAdvertiser;
	
	@FXML private TableColumn<AdvertisementsTable, String> adNameColumnAds;	
	@FXML private TableColumn<AdvertisementsTable, Date> startDateColumnAds;
	@FXML private TableColumn<AdvertisementsTable, Double> durationColumnAds;
	@FXML private TableColumn<AdvertisementsTable, Integer> adIdColumn;
	@FXML private Tab AdsTab;
	
	private FXMLLoader AdvertiserLoader = new FXMLLoader(getClass().getResource("/rec/EditAdvertiserWindow.fxml"));
	
	 @FXML
	 public void initialize() throws SQLException {
	        // Clear the ObservableList before fetching data
	    	Advertisertable.clear();
	    	Advertisertable.addAll(FetchAdsTables.fetchAdvertisers());

	    	companyNameColumnAdvertiser.setCellValueFactory(new PropertyValueFactory<>("companyName"));
	    	ContactPersonColumnAdvertiser.setCellValueFactory(new PropertyValueFactory<>("ContactPerson"));		    
	    	phoneColumnAdvertiser.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));		    
	    	emailColumnAdvertiser.setCellValueFactory(new PropertyValueFactory<>("email"));
       
	    	advertiserTable.setItems(Advertisertable);
	    	advertiserTable.requestFocus();
	    	advertiserTable.getSelectionModel().selectFirst();
	    	
	    	handleSelectedRowAdvertiser();
	    	
	    }
	 
	 
    @FXML
    private void handleSelectedRowAdvertiser() {
        // Get the selected TVShow
    	AdvertiserTable selectedAdvertiser = advertiserTable.getSelectionModel().getSelectedItem();

		if (selectedAdvertiser != null) {
			handleSelectedRowAdvertisement(selectedAdvertiser);
		}
    }
    
	private void handleSelectedRowAdvertisement(AdvertiserTable selectedAdvertiser) {
        
            // Extract the title from the selected TVShow
            int selectedTitle = selectedAdvertiser.getAdvertiserId();
            
            Advertisementstable.clear();
            Advertisementstable.addAll(FetchAdsTables.fetchAdvertisements(selectedTitle));
            
    	    adNameColumnAds.setCellValueFactory(new PropertyValueFactory<>("adName"));
    	    startDateColumnAds.setCellValueFactory(new PropertyValueFactory<>("startDate"));
    	    durationColumnAds.setCellValueFactory(new PropertyValueFactory<>("durationSeconds"));
    	    
  
    	    // Check if adIdColumn is not already added
    	    adIdColumn.setCellValueFactory(new PropertyValueFactory<>("adId"));
    	    adIdColumn.setVisible(false); // Make the column invisible
            
	        // Set data to the TableView
            adsTable.setItems(Advertisementstable);
			adsTable.requestFocus();
			adsTable.getSelectionModel().selectFirst();
            
        
	}
        
    
   
    
    @FXML
    private void handleAddButtonAdvertisers() {
        // Open the add movie window
		UIManager uiManager = new UIManager();
		Stage currentStage = (Stage) AddAdvertiserButtons.getScene().getWindow();
		Stage childStage = uiManager.addAdvertisersWindow(currentStage);
		Update();
    }
    
    @FXML void handleEditButtonAdvertisers() {
    	AdvertiserTable selectedAdvertiser = advertiserTable.getSelectionModel().getSelectedItem();
    	
    	if (selectedAdvertiser != null) {
    		Advertisertable.clear();
			Advertisertable.addAll(FetchAdsTables.fetchAdvertisers());	
    		try {	
				Parent root = AdvertiserLoader.load();
			} catch (IOException e) {
		 	    System.err.println("Error loading Advertiser data");
				e.printStackTrace();
			}
    		UIManager uiManager = new UIManager();
			Stage currentStage = (Stage) EditAdvertiserButtons.getScene().getWindow();
			Stage childStage = uiManager.editAdvertisersWindow(currentStage, selectedAdvertiser);
			Update();
		}
 
    }
    
    @FXML
	private void handleEditButtonAds() {
		AdvertisementsTable selectedAdvertisement = adsTable.getSelectionModel().getSelectedItem();
		AdvertiserTable selectedAdvertiser = advertiserTable.getSelectionModel().getSelectedItem();
		if (selectedAdvertiser != null) {
			Advertisementstable.clear();
			Advertisementstable.addAll(FetchAdsTables.fetchAdvertisements(selectedAdvertiser.getAdvertiserId()));
			try {
				Parent root = AdvertiserLoader.load();
			} catch (IOException e) {
				System.err.println("Error loading Advertisements data");
				e.printStackTrace();
			}
			UIManager uiManager = new UIManager();
			Stage currentStage = (Stage) EditAdsButtons.getScene().getWindow();
			Stage childStage = uiManager.editAdsWindow(currentStage,selectedAdvertiser,selectedAdvertisement);
			Update();
		}
	}
    
    @FXML void handleRemoveButtonAdvertisers() {
		AdvertiserTable selectedAdvertiser = advertiserTable.getSelectionModel().getSelectedItem();
		
		if (selectedAdvertiser != null) {
			int AdvertiserId = selectedAdvertiser.getAdvertiserId();
			AdsProcuders.removeAdvertiser(AdvertiserId);
		}Update();
	    
    }
    
    @FXML
    private void handleAddButtonAds() {
    	AdvertiserTable selectedAdvertiser = advertiserTable.getSelectionModel().getSelectedItem();
        // Open the add movie window
		UIManager uiManager = new UIManager();
		Stage currentStage = (Stage) AddAdsButtons.getScene().getWindow();
		Stage childStage = uiManager.addAdsWindow(currentStage, selectedAdvertiser);
		Update();
    }
    
    
    
    @FXML void handleRemoveButtonAds() {
	    AdvertisementsTable selectedAdvertisement = adsTable.getSelectionModel().getSelectedItem();
	    
	    if (selectedAdvertisement != null) {
		    int AdId = selectedAdvertisement.getAdId();
		    AdsProcuders.removeAd(AdId);
	    }Update();
    }
    public void Update() {
	    try {
			initialize();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
 
    
   
    
    
 
	  

}
