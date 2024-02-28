package Controllers_Windows;

import java.time.LocalDate;

import DatabaceTables.EpisodeTable;
import DatabaceTables.SeasonTable;
import DatabaceTables.TVShowTable;
import Procudures.TvShowsProcuders;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class TvShowWindowsController extends BaseWindowController {

    private TVShowTable selectedTvshow;
    private SeasonTable selectedSeason;
    private EpisodeTable selectedEpisode;

    // TV Shows Fields
    @FXML
    private Button Button, GoBackButton;

    @FXML
    private TextField genreTextField, titleTextField, durationTextField, numTexField;

    @FXML
    private DatePicker datePicker;

    public void setSelectedTvshow(TVShowTable tvShow) {
        this.selectedTvshow = tvShow;
            String title = selectedTvshow.getTitle();
            String genre = selectedTvshow.getGenre();
  
            titleTextField.setText(title);

            // Check if generTextField is not null and is part of the current scene
            if (genreTextField != null ) {
            	genreTextField.setText(genre);
            }     
        
    }


    public void setSelectedSeason(SeasonTable season) {
        this.selectedSeason = season;
        
            titleTextField.setText(selectedSeason.getTitle());
        
    }

    public void setSelectedEpisode(EpisodeTable episode) {
        this.selectedEpisode = episode;
        titleTextField.clear();
        String title = selectedEpisode.getTitle();
		String duration = String.valueOf(selectedEpisode.getDuration());
		
        if (titleTextField != null  && durationTextField != null) {
            titleTextField.setText(title);
            durationTextField.setText(duration);
            numTexField.setText(String.valueOf(selectedEpisode.getEpisodeNumber()));
        }
    }

    @FXML
    private void handleGoBackButton(MouseEvent event) {
        closeWindow();
    }

    @FXML
    void handleInsertButtonTvShowsAction(MouseEvent event) {
    	resetStyles();
        
        LocalDate selectedDate = datePicker.getValue();
        String title = titleTextField.getText();
        String genre = genreTextField.getText();
        
        if (!validateTvShowInput(title, genre, selectedDate)) {
			return;
		}
        
        TvShowsProcuders.insertTvShow(title, genre, selectedDate.toString());
        closeWindow();     
    }
    
    @FXML
    void handleEditButtonTvShowsAction(MouseEvent event) {
        resetStyles();
        
        LocalDate selectedDate = datePicker.getValue();
        String title = selectedTvshow.getTitle();
        String newTitle = titleTextField.getText();
        String genre = genreTextField.getText();
            
        if (!validateTvShowInput(title, genre, selectedDate)) {
    			return;
    	}   
        TvShowsProcuders.updateTvShow(title, newTitle, genre, selectedDate.toString());
        closeWindow();   
    }

   

    

    @FXML
    void handleInsertButtonSeasonsAction(MouseEvent event) {
        resetStyles();
        
        LocalDate selectedDate = datePicker.getValue();
        String tvShowTitle = selectedTvshow.getTitle();
        String title = titleTextField.getText();
        
        if (!validateSeasonInput(title,selectedDate)) {
        	return; 
        }
        
        TvShowsProcuders.insertSeason(tvShowTitle, title, selectedDate.toString());
        closeWindow();  
    }

    @FXML
    void handleEditButtonSeasonsAction(MouseEvent event) {
        resetStyles(); 
        
        LocalDate selectedDate = datePicker.getValue();
        String seasonTitle = selectedSeason.getTitle();
        String title = titleTextField.getText();
        
        if (!validateSeasonInput(title,selectedDate)) {
        	return;
        }
        TvShowsProcuders.updateSeason(seasonTitle, title, selectedDate.toString());
        closeWindow();
    }

    @FXML
    void handleEditButtonEpisodesAction(MouseEvent event) {
    	resetStyles();

        LocalDate selectedDate = datePicker.getValue();
        String showTitle = selectedTvshow.getTitle();
        String seasonTitle =  selectedSeason.getTitle();
        String episodeTitle = selectedEpisode.getTitle();
        String Num = numTexField.getText();
        String title = titleTextField.getText();
        String duration = durationTextField.getText();
        
        System.out.println(Num);
        
		if (!validateEpisodeInput(Num, title, duration, selectedDate)) {
			return;
		}
		int episodeNum = Integer.parseInt(numTexField.getText());
        
        TvShowsProcuders.updateEpisode(showTitle, seasonTitle, episodeTitle, episodeNum, title, selectedDate.toString(), duration);
        closeWindow();
     }
    
    @FXML
    void handleInsertButtonEpisodesAction(MouseEvent event) {
    		resetStyles();
    		//clearFields();
    		System.out.println(selectedSeason.getTitle());
              
    		  LocalDate selectedDate = datePicker.getValue();
    	      String showTitle = selectedTvshow.getTitle();
    	      String seasonTitle =  selectedSeason.getTitle();
    	      String episodeTitle = selectedEpisode.getTitle();
    	      String Num = numTexField.getText();
    	      String title = titleTextField.getText();
    	      String duration = durationTextField.getText();
    	      
    	      System.out.println("Num: " + Num);
    	      System.out.println("title: " + title);
    	      System.out.println("duration: " + duration);
            
            if (!validateEpisodeInput(Num, title, duration, selectedDate)) {
	            return;
            }
            int episodeNum = Integer.parseInt(numTexField.getText());
            
            TvShowsProcuders.insertEpisode(showTitle, seasonTitle, episodeNum, title, selectedDate.toString(), duration);
            closeWindow();
    }
      

    private void clearFields() {
    	titleTextField.clear();
        durationTextField.clear();
        numTexField.clear();
		
	}


	private void updateSeasonFields() {
        if (selectedSeason != null) {
            titleTextField.setText(selectedSeason.getTitle());
        }
    }

    private void updateEpisodeFields() {
        titleTextField.setText(selectedEpisode.getTitle());
        durationTextField.setText(String.valueOf(selectedEpisode.getDuration()));
    }

    

    private boolean validateDate(LocalDate selectedDate, DatePicker datePicker2) {
		// TODO Auto-generated method stub
		return true;
	}
  
}
