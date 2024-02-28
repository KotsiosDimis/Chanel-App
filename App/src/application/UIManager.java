// UIManager.java
package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;


import Controllers_Windows.AdsWindowController;
import Controllers_Windows.MovieWindowController;
import Controllers_Windows.TvShowWindowsController;
import DatabaceTables.AdvertisementsTable;
import DatabaceTables.AdvertiserTable;
import DatabaceTables.EpisodeTable;
import DatabaceTables.MovieTable;
import DatabaceTables.SeasonTable;
import DatabaceTables.TVShowTable;


public class UIManager {

	private static final String FXML_BASE_PATH = Constants.FXML_BASE_PATH;
    private static final String MAIN_UI_FXML = Constants.MAIN_UI_FXML;

    private Image icon = new Image(getClass().getResourceAsStream("icon.png"));

    private final Map<String, String> windowTitles = new HashMap<>();
    
    public UIManager() {
	    windowTitles.put(EDIT_MOVIE_WINDOW, "Edit Movie");
	    windowTitles.put(ADD_MOVIE_WINDOW, "Add Movie");
	    windowTitles.put(ADD_ADVERTISER_WINDOW, "Add Advertiser");
	    windowTitles.put(ADD_ADS_WINDOW, "Add Advertisements");
	    windowTitles.put(ADD_TVSHOW_WINDOW, "Add TV Show");
	    windowTitles.put(ADD_SEASON_WINDOW, "Add Season");
	    windowTitles.put(ADD_EPISODE_WINDOW, "Add Episode");
	    windowTitles.put(EDIT_TVSHOW_WINDOW, "Edit TV Show");
	    windowTitles.put(EDIT_SEASON_WINDOW, "Edit Season");
	    windowTitles.put(EDIT_EPISODE_WINDOW, "Edit Episode");
	    windowTitles.put(EDIT_ADVERTISER_WINDOW, "Edit Advertiser");
	    windowTitles.put(EDIT_ADS_WINDOW, "Edit Advertisements");
	    
    }
	
	
	
	
	private String EDIT_MOVIE_WINDOW = Constants.EDIT_MOVIE_WINDOW;
	private String ADD_MOVIE_WINDOW = Constants.ADD_MOVIE_WINDOW;
	private String ADD_ADVERTISER_WINDOW = Constants.ADD_ADVERTISER_WINDOW;
	private String ADD_ADS_WINDOW = Constants.ADD_ADS_WINDOW;
	private String ADD_TVSHOW_WINDOW = Constants.ADD_TVSHOW_WINDOW;
	private String ADD_SEASON_WINDOW = Constants.ADD_SEASON_WINDOW;
	private String ADD_EPISODE_WINDOW = Constants.ADD_EPISODE_WINDOW;
	
	private String EDIT_TVSHOW_WINDOW = "EditTvShowWindow.fxml";
	private String EDIT_SEASON_WINDOW = "EditSeasonWindow.fxml";
	private String EDIT_EPISODE_WINDOW = "EditEpisodeWindow.fxml";
	private String EDIT_ADVERTISER_WINDOW = Constants.EDIT_ADVERTISER_WINDOW;
	private String EDIT_ADS_WINDOW = "EditAdsWindow.fxml";
    
	
	public void startUI(Stage primaryStage) throws SQLException {
		 try { 
	        FXMLLoader loader = new FXMLLoader(getClass().getResource(MAIN_UI_FXML));
	        Parent root = loader.load();

	        Stage stage = new Stage();
	        stage.getIcons().add(icon);
	        stage.setTitle("Movie Manager");
	        stage.setScene(new Scene(root));
	        stage.getScene().getStylesheets().clear();
	        stage.show();
	              
	     } catch (IOException e) {
	           e.printStackTrace();
	      }	
	} 
	
	private String getFXMLPath(String fileName) {
        return FXML_BASE_PATH + fileName;
    }
	
	
	 public Stage createWindow(String fxmlFileName, Stage parentStage, Consumer<Object> controllerInitializer) {
	        try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource(getFXMLPath(fxmlFileName)));
	            Parent root = loader.load();
	            controllerInitializer.accept(loader.getController());

	            Stage stage = new Stage();
	            stage.initOwner(parentStage);
	            stage.setScene(new Scene(root));
	            stage.initModality(Modality.WINDOW_MODAL);
	            stage.getIcons().add(icon);
	            stage.setTitle(windowTitles.getOrDefault(fxmlFileName, fxmlFileName));
	            stage.showAndWait();

	            return stage;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	
	
	
	 public Stage editMoviesWindow(Stage parentStage, MovieTable selectedMovie) {
	        return createWindow(Constants.EDIT_MOVIE_WINDOW, parentStage, controller -> {
	            MovieWindowController movieController = (MovieWindowController) controller;
	            movieController.setMovie(selectedMovie);
	        });
	    }
	
	 public Stage editTvShowsWindow(Stage parentStage, TVShowTable selectedTvShow) {
	        return createWindow(Constants.EDIT_TVSHOW_WINDOW, parentStage, controller -> {
	            TvShowWindowsController tvShowController = (TvShowWindowsController) controller;
	            tvShowController.setSelectedTvshow(selectedTvShow);
	        });
	    }
	
	public Stage editSeaonsWindow(Stage parentStage, TVShowTable selectedTvShow, SeasonTable selectedSeason) {
        return createWindow(Constants.EDIT_SEASON_WINDOW, parentStage, controller -> {
            TvShowWindowsController tvShowController = (TvShowWindowsController) controller;
            tvShowController.setSelectedTvshow(selectedTvShow);
            tvShowController.setSelectedSeason(selectedSeason);
        });
    }

	
	 public Stage editEpisodeWindow(Stage parentStage, TVShowTable selectedTvShow, SeasonTable selectedSeason, EpisodeTable selectedEpisode) {
	        return createWindow(Constants.EDIT_EPISODE_WINDOW, parentStage, controller -> {
	            TvShowWindowsController tvShowController = (TvShowWindowsController) controller;
	            tvShowController.setSelectedTvshow(selectedTvShow);
	            tvShowController.setSelectedSeason(selectedSeason);
	            tvShowController.setSelectedEpisode(selectedEpisode);
	        });
	    }
	
	public Stage editAdvertisersWindow(Stage parentStage, AdvertiserTable selectedAdvertiser) {
        return createWindow(Constants.EDIT_ADVERTISER_WINDOW, parentStage, controller -> {
            AdsWindowController adsController = (AdsWindowController) controller;
            adsController.setSelectedAdvertiser(selectedAdvertiser);
        });
    }

	
	
	
	public Stage addMoviesWindow(Stage parentStage) {
        return createWindow(Constants.ADD_MOVIE_WINDOW, parentStage, controller -> {
            // Additional logic if needed
        });
    }

	public Stage addAdvertisersWindow(Stage parentStage) {
        return createWindow(Constants.ADD_ADVERTISER_WINDOW, parentStage, controller -> {
            // Additional logic if needed
        });
    }
    
	public Stage addAdsWindow(Stage parentStage, AdvertiserTable selectedAdvertiser) {
        return createWindow(Constants.ADD_ADS_WINDOW, parentStage, controller -> {
            AdsWindowController adsController = (AdsWindowController) controller;
            adsController.setSelectedAdvertiser(selectedAdvertiser);
        });
    }
    
    public Stage editAdsWindow(Stage parentStage, AdvertiserTable selectedAdvertiser, AdvertisementsTable selectedAd) {
    	System.out.println("here");
        return createWindow(Constants.EDIT_ADS_WINDOW, parentStage, controller -> {
            AdsWindowController adsController = (AdsWindowController) controller;
            adsController.setSelectedAdvertiser(selectedAdvertiser);
            adsController.setSelectedAd(selectedAd);
        });
    }
		
	
    
    public Stage addTvShowWindow(Stage parentStage) {
        return createWindow(Constants.ADD_TVSHOW_WINDOW, parentStage, controller -> {
            // Additional logic if needed
        });
    }
    
    public Stage addSeasonWindow(Stage parentStage, TVShowTable selectedTvShow, SeasonTable selectedSeason) {
        return createWindow(Constants.ADD_SEASON_WINDOW, parentStage, controller -> {
            TvShowWindowsController tvShowController = (TvShowWindowsController) controller;
            tvShowController.setSelectedTvshow(selectedTvShow);
            tvShowController.setSelectedSeason(selectedSeason);
        });
    }
      
    public Stage addEpisodeWindow(Stage parentStage, TVShowTable selectedTvShow, SeasonTable selectedSeason, EpisodeTable selectedEpisode) {
        return createWindow(Constants.ADD_EPISODE_WINDOW, parentStage, controller -> {
            TvShowWindowsController tvShowController = (TvShowWindowsController) controller;
            tvShowController.setSelectedTvshow(selectedTvShow);
            tvShowController.setSelectedSeason(selectedSeason);
            tvShowController.setSelectedEpisode(selectedEpisode);
        });
    }


	
}



