package Controllers_Windows;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BaseWindowController implements IWindowController {
	
	@FXML 
	private Button GoBackButton;
	
	@FXML
	private TextField genreTextField, titleTextField ,durationTextField, numTexField,adNameTexField ;
	    
	@FXML 
	private TextField ContactTextFieldAdvertisers ,companyTexFieldAdvertisers, emailTextFieldAdvertisers, phoneTextFieldAdvertisers;
	
	@FXML
	private DatePicker datePicker;

	@Override
	public void closeWindow() {
		// Get the current stage
        Stage stage = (Stage) GoBackButton.getScene().getWindow();
        // Close the current stage (window)
        stage.close();
	}

	@Override
	public void resetStyles() {
		 if (genreTextField != null && genreTextField.getScene()!=null) genreTextField.setStyle("");
	     if (datePicker != null && datePicker.getScene()!=null) datePicker.setStyle("");
	     if (numTexField != null && numTexField.getScene()!=null) numTexField.setStyle("");
	     if (titleTextField != null && titleTextField.getScene()!=null) titleTextField.setStyle("");
	     if (durationTextField != null && durationTextField.getScene()!=null) durationTextField.setStyle("");
	     if (adNameTexField != null && adNameTexField.getScene()!=null) adNameTexField.setStyle("");
		
	}
	 
	@Override
	public boolean validateInput(String title, String duration, String genre, LocalDate selectedDate) {
	        if (!isValidString(title)) {
	            setInvalidStyle(titleTextField);
	            return false;
	        }if (!isValidNumber(duration)) {
	            setInvalidStyle(durationTextField);
	            return false;
	        }if (!isValidString(genre)) {
	            setInvalidStyle(genreTextField);
	            return false;
	        }if (selectedDate == null) {
	            setInvalidStyle(datePicker);
	            return false;
	        } return true;
	    }
	

 	@Override
 	public boolean validateEpisodeInput(String Num, String title, String duration, LocalDate Date) {
	     	if (!isValidString(Num)) {  
	     		setInvalidStyle(numTexField);
	            return false;        
	        }if (!isValidString(title)) {
	            setInvalidStyle(titleTextField);
	            return false;
	        }if (!isValidNumber(duration)) {
	            setInvalidStyle(durationTextField);
	            return false;
	        }if (Date == null) {
	            setInvalidStyle(datePicker);
	            return false;
	        } return true;
    }
	
 	@Override
	public boolean validateAdvertiserInput(String company, String contact, String phone, String email) {
		if (!isValidString(company)) {
			setInvalidStyle(companyTexFieldAdvertisers);
			return false;
		}
		if (!isValidString(contact)) {
			setInvalidStyle(ContactTextFieldAdvertisers);
			return false;
		}
		if (!isValidString(phone)) {	
			setInvalidStyle(phoneTextFieldAdvertisers);
			return false;
		}
		if (!isValidString(email)) {
			setInvalidStyle(emailTextFieldAdvertisers);
			return false;
		}
		return true;
	}
 	
 	@Override
 	public boolean validateAdsInput(String adName,LocalDate startDate,String duration) {
 		
 		if (!isValidString(adName)) {
	 		setInvalidStyle(adNameTexField);
	 		return false;
 		} 
 		if (startDate == null) {
 			setInvalidStyle(datePicker);
 			return false;
 		}
 		if (!isValidNumber(duration)) {
 			setInvalidStyle(durationTextField);
 			return false;
 		} 
 		return true;
 	}
 	
 	
 	
 	@Override
	public boolean validateTvShowInput(String title, String genre, LocalDate selectedDate) {
 		
 		if (!isValidString(title)) {
 			setInvalidStyle(titleTextField);
 			return false;
 		} 
 		if (!isValidString(genre)) {
 			setInvalidStyle(genreTextField);
 			return false;
 		} 
 		if (selectedDate == null) {
 			setInvalidStyle(datePicker);
 			return false;
 		} 
 		return true;
        
    }
 	
 	@Override
 	public boolean validateSeasonInput(String title, LocalDate selectedDate) {
 		if (!isValidString(title)) {
 			setInvalidStyle(titleTextField);
 			return false;
 		} 
 		if (selectedDate == null) {
 			setInvalidStyle(datePicker);
 			return false;
 		} 
 		return true;
	 	
 	}

 	 static public boolean isValidString(String input) {
         return !input.isEmpty();
     }

     
    static public boolean isValidNumber(String input) {
    	 if (!isValidString(input)) {
    		 return false;
    	 }
         try {
             Double.parseDouble(input);
             return true;
         } catch (NumberFormatException e) {
             return false;
         }
     }
    
    static public boolean isValidInteger(String input) {
 	   try {
 		   Integer.parseInt(input);
 		   return true;
 	   } catch(NumberFormatException e) {
 		   return false;
 	   }
    }

     
     static public void setInvalidStyle(TextField textField) {
         textField.setStyle("-fx-border-color: red;");
     }
     
     static public void setInvalidStyle(DatePicker textField) {
         textField.setStyle("-fx-border-color: red;");
     }

	

		
		
	


}
