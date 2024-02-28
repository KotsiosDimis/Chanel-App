package Procudures;

public class AdsProcuders extends ProcedureExecutor {
 
    public static void removeAdvertiser(int id) {
    	executeProcedure("Advertiser_Remove(?)", id);
    }

    public static void insertAdvertiser(String company, String contactPerson, String phone, String email) {
        executeProcedure("Advertiser_Insert(?, ?, ?, ?)", company, contactPerson, phone, email);
    }

    public static void updateAdvertiser(int id, String company, String contactPerson, String phone, String email) {
        executeProcedure("Advertiser_Update(?, ?, ?, ?, ?)", id, company, contactPerson, phone, email);
    }

    public static void insertAd(int id, String adName, int duration, String date) {
        executeProcedure("Advertisement_Insert(?, ?, ?, ?)", id, adName, duration, date);
    }

    public static void updateAd(int id, int adId, String adName, int duration, String date) {
        executeProcedure("Advertisement_Update(?, ?, ?, ?, ?)", id, adId, adName, duration, date);
    }
    
    public static void removeAd(int adId) {
		executeProcedure("Advertisement_Remove(?)", adId);
    }
}
