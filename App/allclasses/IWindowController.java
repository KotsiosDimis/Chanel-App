package Controllers_Windows;

import java.time.LocalDate;

public interface IWindowController {
	 void closeWindow();
	 void resetStyles();
	 boolean validateEpisodeInput(String Num, String title, String duration, LocalDate Date);
	 boolean validateInput(String title, String duration, String genre, LocalDate selectedDate);
	 boolean validateAdvertiserInput(String company, String contact, String phone, String email);
	 boolean validateAdsInput(String adName,LocalDate startDate,String duration);
	 boolean validateTvShowInput(String title, String genre, LocalDate selectedDate);
	 boolean validateSeasonInput(String title, LocalDate selectedDate);
}
