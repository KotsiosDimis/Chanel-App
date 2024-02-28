package Controllers_Windows;

import java.time.LocalDate;

import DatabaceTables.AdvertisementsTable;
import DatabaceTables.AdvertiserTable;
import Procudures.AdsProcuders;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class AdsWindowController extends BaseWindowController {

    private AdvertiserTable selectedAdvertiser;
    private AdvertisementsTable selectedAd;

    @FXML
    private TextField ContactTextFieldAdvertisers, companyTexFieldAdvertisers, emailTextFieldAdvertisers,  phoneTextFieldAdvertisers, adNameTexField ,durationTextField;

    @FXML
    private Button GoBackButton, insertButtonAdvertisers, insertButtonAds, editButtonAdvertisers;

    @FXML
    private DatePicker datePicker;


    @FXML
    void handleGoBackButton(MouseEvent event) {
        closeWindow();
    }

    public void setSelectedAdvertiser(AdvertiserTable advertiser) {
        this.selectedAdvertiser = advertiser;

        if (companyTexFieldAdvertisers != null) {
            companyTexFieldAdvertisers.setText(selectedAdvertiser.getCompanyName());
            ContactTextFieldAdvertisers.setText(selectedAdvertiser.getContactPerson());
            emailTextFieldAdvertisers.setText(selectedAdvertiser.getEmail());
            phoneTextFieldAdvertisers.setText(selectedAdvertiser.getPhoneNumber());
        }
    }

    public void setSelectedAd(AdvertisementsTable Ad) {
        this.selectedAd = Ad;
        adNameTexField.setText(selectedAd.getAdName());
		durationTextField.setText(String.valueOf(selectedAd.getDurationSeconds()));
        
    }

    @FXML
    void handleInsertButtonAdvertisers(MouseEvent event) {
        resetStyles();

        String company = companyTexFieldAdvertisers.getText();
        String contact = ContactTextFieldAdvertisers.getText();
        String phone = phoneTextFieldAdvertisers.getText();
        String email = emailTextFieldAdvertisers.getText();

        if (!validateAdvertiserInput(company, contact, phone, email)) {
            return;
        }

        AdvertisersController.insertAdvertiser(company, contact, phone, email);
        closeWindow();
    }

    @FXML
    private void handleEditButtonAdvertisers(MouseEvent event) {
        resetStyles();

        int advertiserId = selectedAdvertiser.getAdvertiserId();
        String company = companyTexFieldAdvertisers.getText();
        String contact = ContactTextFieldAdvertisers.getText();
        String phone = phoneTextFieldAdvertisers.getText();
        String email = emailTextFieldAdvertisers.getText();

        if (!validateAdvertiserInput(company, contact, phone, email)) {
            return;
        }

        AdvertisersController.updateAdvertiser(advertiserId, company, contact, phone, email);
        closeWindow();
    }

    @FXML
    private void handleInsertButtonAds(MouseEvent event) {
        if (selectedAdvertiser != null) {
            resetStyles();

            int advertiserId = selectedAdvertiser.getAdvertiserId();
            String adName = adNameTexField.getText();
            LocalDate startDate = datePicker.getValue();
            String duration = durationTextField.getText();

            if (!validateAdsInput(adName, startDate, duration)) {
                return;
            }

            String date = datePicker.getValue().toString();
            int durNum = Integer.parseInt(duration);
            AdsProcuders.insertAd(advertiserId, adName, durNum, date);
            closeWindow();
        }
    }

    @FXML
    private void handleEditButtonAds(MouseEvent event) {
        resetStyles();

        if (selectedAdvertiser != null && selectedAd != null) {
        	
            int advertiserId = selectedAdvertiser.getAdvertiserId();
            int adId = selectedAd.getAdId();
            String adName = adNameTexField.getText();
            LocalDate startDate = datePicker.getValue();
            String duration = durationTextField.getText();

           if (!validateAdsInput(adName, startDate, duration)) {
               return;
            }

            
            int durNum = Integer.parseInt(duration);
            
            AdsProcuders.updateAd(advertiserId, adId, adName, durNum, startDate.toString());
            closeWindow();
        } else {
            // Handle the case when either selectedAdvertiser or selectedAd is null
            System.out.println("Error: selectedAdvertiser or selectedAd is null.");
        }
    }

}
