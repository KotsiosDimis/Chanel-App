package Controllers_Windows;

import Procudures.AdsProcuders;

public class AdvertisersController {

    public static void insertAdvertiser(String company, String contact, String phone, String email) {
        AdsProcuders.insertAdvertiser(company, contact, phone, email);
    }

    public static void updateAdvertiser(int advertiserId, String company, String contact, String phone, String email) {
        AdsProcuders.updateAdvertiser(advertiserId, company, contact, phone, email);
    }

    // Add other advertiser-related methods...
}
