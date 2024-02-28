package Controllers_Tabs;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import Controllers_Windows.TvShowWindowsController;
import DatabaceTables.EpisodeTable;
import DatabaceTables.SeasonTable;
import DatabaceTables.TVShowTable;
import FetchTables.FetchTvShowsTables;
import Procudures.TvShowsProcuders;
import application.UIManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TvShowsTabController {

    private final ObservableList<TVShowTable> tvShowstable = FXCollections.observableArrayList();
    private final ObservableList<SeasonTable> seasonstable = FXCollections.observableArrayList();
    private final ObservableList<EpisodeTable> episodetable = FXCollections.observableArrayList();

    private TVShowTable selectedTvShow;
    private TvShowWindowsController tvShowWindowsController;

    private FXMLLoader editTvShowLoader = new FXMLLoader(getClass().getResource("/rec/EditTvShowWindow.fxml"));
    private FXMLLoader addSeasonLoader = new FXMLLoader(getClass().getResource("/rec/AddSeasonWindow.fxml"));

    @FXML
    private Button AddEpisodeButton, AddSeasonsButton, AddTvShowsButton, EditEpisodeButton,
            EditSeasonsButton, EditTvShowsButton, RemoveEpisodeButton, RemoveSeasonsButton, RemoveTvShowsButton;
    @FXML
    private Button[] allButtons;
    @FXML
    private Tab TvShowsTab;
    @FXML
    private TableView<TVShowTable> tvShowTable;
    @FXML
    private TableColumn<TVShowTable, String> titleColumnTVShow, genreColumnTVShow, releaseDateColumnTVShow;
    @FXML
    private TableView<SeasonTable> seasonsTable;
    @FXML
    private TableColumn<SeasonTable, String> seasonColumnSeasons, airDateColumnSeasons;
    @FXML
    private TableColumn<SeasonTable, Number> episodeAmountColumnSeasons;
    @FXML
    private TableView<EpisodeTable> episodesTable;
    @FXML
    private TableColumn<EpisodeTable, Integer> episodeNumberColumnEpisode;
    @FXML
    private TableColumn<EpisodeTable, String> titleColumnEpisode;
    @FXML
    private TableColumn<EpisodeTable, Number> durationColumnEpisode;
    @FXML
    private TableColumn<EpisodeTable, Date> airDateColumnEpisode;

    @FXML
    public void initialize() throws SQLException {
        initTvShowsTable();
        initSeasonsTable();
        initEpisodesTable();
    }

    private void initTvShowsTable() throws SQLException {
    	
    	
    	tvShowstable.clear();
        tvShowstable.addAll(FetchTvShowsTables.fetchTVShows());
    	
        titleColumnTVShow.setCellValueFactory(new PropertyValueFactory<>("title"));
        genreColumnTVShow.setCellValueFactory(new PropertyValueFactory<>("genre"));
        releaseDateColumnTVShow.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
        
        tvShowTable.setItems(tvShowstable);

        // Select the first item in the table if available
        tvShowTable.requestFocus();
        tvShowTable.getSelectionModel().selectFirst();

        // Get the selected TV show
        TVShowTable selectedTVShow = tvShowTable.getSelectionModel().getSelectedItem();

        // Invoke the handleSelectedRowTVShow method with the selected TV show
        handleSelectedRowTVShow(selectedTVShow);
    }

    private void initSeasonsTable() {
      
        seasonsTable.requestFocus();
        seasonsTable.getSelectionModel().selectFirst();
    }

    private void initEpisodesTable() {
     
    }

    // Handle selected row methods...
    private void handleSelectedRowEpisode(EpisodeTable selectedEpisode) {
        // Handle episode selection if needed
    }
    @FXML
    private void handleSelectedRowTVShow() {
        TVShowTable selectedTVShow = tvShowTable.getSelectionModel().getSelectedItem();
        if (selectedTVShow != null) {
        	handleSelectedRowTVShow(selectedTVShow);
        	seasonsTable.getSelectionModel().selectFirst();
        	handleSelectedRowSeason();
        }  
    }
    
	private void handleSelectedRowTVShow(TVShowTable selectedTVShow) {	
	       if (selectedTVShow != null) {
	            String selectedTitle = selectedTVShow.getTitle();
	            seasonstable.clear();
	            episodetable.clear();
	            seasonstable.addAll(FetchTvShowsTables.fetchSeasons(selectedTitle));
	            setSeasonsTableData();
	            seasonColumnSeasons.setCellValueFactory(new PropertyValueFactory<>("title"));
                airDateColumnSeasons.setCellValueFactory(new PropertyValueFactory<>("airdate"));
                episodeAmountColumnSeasons.setCellValueFactory(new PropertyValueFactory<>("totalEpisodes"));

                // Set data to the seasons TableView
                seasonsTable.setItems(seasonstable);
	       }
	}
    
    @FXML
    private void handleSelectedRowSeason() {
        SeasonTable selectedSeason = seasonsTable.getSelectionModel().getSelectedItem();
        if (selectedSeason != null) {
            handleSelectedRowSeason(selectedSeason); 
            // Select the first episode by default
            episodesTable.getSelectionModel().selectFirst();    
            // Manually trigger the handleSelectedRowEpisode method for the default selection
           // handleSelectedRowEpisode();
        }
    }
    
    private void handleSelectedRowSeason(SeasonTable selectedSeason) {
        if (selectedSeason != null) {
        	String selectedSeasonTitle = selectedSeason.getTitle();
            episodetable.clear();
            episodetable.addAll(FetchTvShowsTables.fetchEpisodes(selectedSeasonTitle));
            setEpisodesTableData();
            episodetable.clear();
            episodetable.addAll(FetchTvShowsTables.fetchEpisodes(selectedSeasonTitle));

            episodeNumberColumnEpisode.setCellValueFactory(new PropertyValueFactory<>("episodeNumber"));
            titleColumnEpisode.setCellValueFactory(new PropertyValueFactory<>("title"));
            durationColumnEpisode.setCellValueFactory(new PropertyValueFactory<>("duration"));
            airDateColumnEpisode.setCellValueFactory(new PropertyValueFactory<>("airDate"));

            episodesTable.setItems(episodetable);
        } else {
            episodetable.clear();
            EditEpisodeButton.setDisable(true);
            RemoveEpisodeButton.setDisable(true);
        }
    }

    private void setSeasonsTableData() {
        // Set data to the seasons TableView...
    }

    private void setEpisodesTableData() {
        // Set data to the episodes TableView...
    }

    @FXML
    private void handleRemoveTvShowsButton() {
        
        TVShowTable selectedTVShow = tvShowTable.getSelectionModel().getSelectedItem();
        if (selectedTVShow != null) {
            String title = selectedTVShow.getTitle();
            TvShowsProcuders.removeTvShow(title);
            update();
        }
        
    }

    @FXML
    private void handleRemoveSeasonsButton() {
        
        SeasonTable selectedSeason = seasonsTable.getSelectionModel().getSelectedItem();
        if (selectedSeason != null) {
            TvShowsProcuders.removeSeason(selectedSeason.getTitle());
            update();
        }
        
    }

    @FXML
    private void handleRemoveEpisodeButton() {
        EpisodeTable selectedEpisode = episodesTable.getSelectionModel().getSelectedItem();
        if (selectedEpisode != null) {
            TvShowsProcuders.removeEpisode(selectedEpisode.getSeasonId(), selectedEpisode.getEpisodeNumber());
            update();
        }
    }

    @FXML
    private void handleAddButtonTvShows() throws SQLException {
        UIManager uiManager = new UIManager();
        Stage currentStage = (Stage) AddTvShowsButton.getScene().getWindow();
        Stage childStage = uiManager.addTvShowWindow(currentStage);
        update();
    }

    @FXML
    private void handleEditButtonTvShows() {
        TVShowTable selectedTvShow = tvShowTable.getSelectionModel().getSelectedItem();
        if (selectedTvShow != null) {
            try {
                editTvShowLoader = new FXMLLoader(getClass().getResource("/rec/EditTvShowWindow.fxml"));
                tvShowstable.clear();
                tvShowstable.addAll(FetchTvShowsTables.fetchTVShows());

                Parent root = editTvShowLoader.load();

                UIManager uiManager = new UIManager();
                Stage currentStage = (Stage) EditTvShowsButton.getScene().getWindow();
                Stage childStage = uiManager.editTvShowsWindow(currentStage, selectedTvShow);
                update();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        update();
    }

    @FXML
    private void handleAddButtonSeasons() throws IOException {
        TVShowTable selectedTVShow = tvShowTable.getSelectionModel().getSelectedItem();
        SeasonTable selectedSeason = seasonsTable.getSelectionModel().getSelectedItem();
        if (selectedTVShow != null) {
            addSeasonLoader = new FXMLLoader(getClass().getResource("/rec/AddSeasonWindow.fxml"));
            seasonstable.clear();
            seasonstable.addAll(FetchTvShowsTables.fetchSeasons(selectedTVShow.getTitle()));

            Parent root = addSeasonLoader.load();

            UIManager uiManager = new UIManager();
            Stage currentStage = (Stage) AddSeasonsButton.getScene().getWindow();
            Stage childStage = uiManager.addSeasonWindow(currentStage, selectedTVShow, selectedSeason);
            update();
        } else {
            showNoTvShowSelectedAlert();
        }
    }

    @FXML
    private void handleEditButtonSeasons() throws IOException {
        TVShowTable selectedTVShow = tvShowTable.getSelectionModel().getSelectedItem();
        SeasonTable selectedSeason = seasonsTable.getSelectionModel().getSelectedItem();
        if (selectedSeason != null) {
            FXMLLoader SeasonLoader = new FXMLLoader(getClass().getResource("/rec/EditSeasonWindow.fxml"));
            seasonstable.clear();
            seasonstable.addAll(FetchTvShowsTables.fetchSeasons(selectedTVShow.getTitle()));

            Parent root = SeasonLoader.load();

            TvShowWindowsController TvShowWindowController = SeasonLoader.getController();
            TvShowWindowController.setSelectedSeason(selectedSeason);

            UIManager uiManager = new UIManager();
            Stage currentStage = (Stage) EditSeasonsButton.getScene().getWindow();
            Stage childStage = uiManager.editSeaonsWindow(currentStage, selectedTVShow, selectedSeason);

        } else {
            showNoSeasonSelectedAlert();
        }
        update();
    }

    @FXML
    private void handleAddButtonEpisodes() {
        TVShowTable selectedTVShow = tvShowTable.getSelectionModel().getSelectedItem();
        SeasonTable selectedSeason = seasonsTable.getSelectionModel().getSelectedItem();
        EpisodeTable selectedEpisode = episodesTable.getSelectionModel().getSelectedItem();
        if (selectedSeason != null) {
            UIManager uiManager = new UIManager();
            Stage currentStage = (Stage) AddEpisodeButton.getScene().getWindow();
            Stage childStage = uiManager.addEpisodeWindow(currentStage, selectedTVShow, selectedSeason,selectedEpisode);
            update();
        } else {
            showNoSeasonSelectedAlert();
        }
    }

    @FXML
    private void handleEditButtonEpisodes() throws IOException {
        TVShowTable selectedTVShow = tvShowTable.getSelectionModel().getSelectedItem();
        SeasonTable selectedSeason = seasonsTable.getSelectionModel().getSelectedItem();
        EpisodeTable selectedEpisode = episodesTable.getSelectionModel().getSelectedItem();
        if (selectedEpisode != null) {
            FXMLLoader EpisodeLoader = new FXMLLoader(getClass().getResource("/rec/EditTvShowWindow.fxml"));
            episodetable.clear();
            episodetable.addAll(FetchTvShowsTables.fetchEpisodes(selectedTVShow.getTitle()));
            Parent root = EpisodeLoader.load();
            TvShowWindowsController TvShowWindowController = EpisodeLoader.getController();
            TvShowWindowController.setSelectedEpisode(selectedEpisode);

            UIManager uiManager = new UIManager();
            Stage currentStage = (Stage) EditEpisodeButton.getScene().getWindow();
            Stage childStage = uiManager.editEpisodeWindow(currentStage, selectedTVShow, selectedSeason, selectedEpisode);
            update();
        } else {
            showNoEpisodeSelectedAlert();
        }
    }

    private void update() {
        try {
            initialize();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showNoTvShowSelectedAlert() {
        TabsUtilitys.showAlert("No TV Show Selected", "Please select a TV show.");
    }

    private void showNoSeasonSelectedAlert() {
        TabsUtilitys.showAlert("No Season Selected", "Please select a season.");
    }

    private void showNoEpisodeSelectedAlert() {
        TabsUtilitys.showAlert("No Episode Selected", "Please select an episode.");
    }

    }
