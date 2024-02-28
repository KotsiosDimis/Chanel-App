package Controllers_Windows;

import DatabaceTables.MovieTable;
import Procudures.MoviesProcuders;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;

public class MovieWindowController extends BaseWindowController {

    private MovieTable selectedMovie;

    @FXML
    private Button EditButton, insertButton, GoBackButton;

    @FXML
    private TextField durationTextField, genreTextField, titleTextField, numTexFieldEpisode;

    @FXML
    private DatePicker datePicker;

    public void setMovie(MovieTable movie) {
        this.selectedMovie = movie;

        titleTextField.setText(selectedMovie.getTitle());
        durationTextField.setText(String.valueOf(selectedMovie.getDuration()));
        genreTextField.setText(selectedMovie.getGenre());
    }

    @FXML
    private void handleGoBackButton(MouseEvent event) {
        closeWindow();
    }

    @FXML
    private void handleInsertButtonAction() {
        handleInsertOrUpdate(false);
    }

    @FXML
    private void handleEditButtonMoviesTab(MouseEvent event) {
        handleInsertOrUpdate(true);
    }

    private void handleInsertOrUpdate(boolean isUpdate) {
        resetStyles();
        LocalDate selectedDate = datePicker.getValue();

        String title = titleTextField.getText();
        String duration = durationTextField.getText();
        String genre = genreTextField.getText();

        if (!validateInput(title, duration, genre, selectedDate)) {
            return;
        }

        String airDate = datePicker.getValue().toString();

        if (isUpdate) {
            int movieId = selectedMovie.getMovieId();
            MoviesProcuders.updateMovie(movieId, title, duration, airDate, genre);
        } else {
            MoviesProcuders.insertMovie(title, duration, airDate, genre);
        }

        closeWindow();
    }
}
