package Controllers_Windows;

import Procudures.AdsProcuders;

public class AdsController {

    public static void insertAd(int advertiserId, String adName, int duration, String startDate) {
        AdsProcuders.insertAd(advertiserId, adName, duration, startDate);
    }

    public static void updateAd(int advertiserId, int adId, String adName, int duration, String startDate) {
        AdsProcuders.updateAd(advertiserId, adId, adName, duration, startDate);
    }

    // Add other advertisement-related methods...
}
