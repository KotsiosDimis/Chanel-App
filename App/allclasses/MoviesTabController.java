package Controllers_Tabs;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;


import DatabaceTables.MovieTable;
import FetchTables.FetchMoviesTables;
import Procudures.MoviesProcuders;
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

public class MoviesTabController {

    // ObservableList to hold MovieTable data
    private ObservableList<MovieTable> movieTableData = FXCollections.observableArrayList();
    private FXMLLoader editMovieLoader = new FXMLLoader(getClass().getResource("/rec/EditAdsWindow.fxml"));

    // Selected movie to be edited
    private MovieTable selectedMovie;

    // FXML elements
    @FXML private Tab moviesTab;

    @FXML private TableView<MovieTable> moviesTable;

    @FXML private TableColumn<MovieTable, Date> dateOfAirColumnMovies;

    @FXML private TableColumn<MovieTable, Number> durationColumnMovies, IdColumnMovies;

    @FXML private TableColumn<MovieTable, String> genreColumnMovies, titleColumnMovies;

    @FXML private Button addButtonMoviesTab,editButtonMoviesTab;

    @FXML
    public void initialize() throws SQLException {
    	// Clear the ObservableList before fetching data
        movieTableData.clear();
        movieTableData.addAll(FetchMoviesTables.fetchMovies());
        // Set up the table columns
        titleColumnMovies.setCellValueFactory(new PropertyValueFactory<>("title"));
        genreColumnMovies.setCellValueFactory(new PropertyValueFactory<>("genre"));
        durationColumnMovies.setCellValueFactory(new PropertyValueFactory<>("duration"));
        dateOfAirColumnMovies.setCellValueFactory(new PropertyValueFactory<>("dateOfAir"));
        IdColumnMovies.setCellValueFactory(new PropertyValueFactory<>("movieId"));

        // Set the items in the table
        moviesTable.setItems(movieTableData);

        // Set up the initial selection
        moviesTable.requestFocus();
        moviesTable.getSelectionModel().selectFirst();
    }

    @FXML
    private void handleAddButtonMoviesTab() {
        // Open the add movie window
		UIManager uiManager = new UIManager();
		Stage currentStage = (Stage) addButtonMoviesTab.getScene().getWindow();
		Stage childStage = uiManager.addMoviesWindow(currentStage);
		update();
    }

    @FXML
    private void handleEditButtonMoviesTab() {
        MovieTable selectedMovie = moviesTable.getSelectionModel().getSelectedItem();

        if (selectedMovie != null) {
            try {
                //editMovieLoader = new FXMLLoader(getClass().getResource("/rec/EditMovieWindow.fxml"));
                movieTableData.clear();
                movieTableData.addAll(FetchMoviesTables.fetchMovies());

                // Load the edit movie window and pass the selected movie data
                Parent root = editMovieLoader.load();

                // Open the edit movie window
                UIManager uiManager = new UIManager();
                Stage currentStage = (Stage) editButtonMoviesTab.getScene().getWindow();
                Stage childStage = uiManager.editMoviesWindow(currentStage, selectedMovie);

            } catch (IOException e) {
                e.printStackTrace();
            } 
        }update();
    }

    @FXML
    private void handleRemoveMoviesButton() throws SQLException {
        MovieTable selectedMovie = moviesTable.getSelectionModel().getSelectedItem();
        
        if (selectedMovie != null) {
            // Get movie id
            int movieId = selectedMovie.getMovieId();
            // Call your function to remove the movie with the obtained movieId
            MoviesProcuders.removeMovie(movieId);
            // Update the table after removal
            update();
        }
    }
    
    private void update() {
        try {
            initialize();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Setter for selectedMovie
    public void setSelectedMovie(MovieTable selectedMovie) {
        this.selectedMovie = selectedMovie;
    }
}
