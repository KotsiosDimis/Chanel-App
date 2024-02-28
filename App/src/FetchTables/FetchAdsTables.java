package FetchTables;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Connection.DBConnectionUtil;
import DatabaceTables.AdvertisementsTable;
import DatabaceTables.AdvertiserTable;

public class FetchAdsTables {
	
	
	public static ObservableList<AdvertiserTable> fetchAdvertisers() {
	    ObservableList<AdvertiserTable> Advertisertable = FXCollections.observableArrayList();

	    try {
	        // Establish database connection
	        Connection connection = DBConnectionUtil.getConnection();
	        
	        String sqlQuery = "SELECT * FROM TABLE(get_advertisers)";

	        // Fetch advertiser data from the database
	        PreparedStatement advertiserStatement = connection.prepareStatement(sqlQuery);
	        ResultSet advertiserResultSet = advertiserStatement.executeQuery();

	        while (advertiserResultSet.next()) {
	        	AdvertiserTable advertiser = new AdvertiserTable(
	                    advertiserResultSet.getInt("advertiser_id"),
	                    advertiserResultSet.getString("company_name"),
	                    advertiserResultSet.getString("contact_person"),
	                    advertiserResultSet.getString("phone_number"),
	                    advertiserResultSet.getString("email")
	            );

	            // Add advertiser to ObservableList
	        	Advertisertable.add(advertiser);
	        }

	        // Close resources
	        advertiserResultSet.close();
	        advertiserStatement.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return Advertisertable;
	}
	
	public static ObservableList<AdvertisementsTable> fetchAdvertisements(int advertiserId) {
	    ObservableList<AdvertisementsTable> advertisements = FXCollections.observableArrayList();

	    try {
	        // Establish database connection
	        Connection connection = DBConnectionUtil.getConnection();

	        String sqlQuery = "SELECT * FROM TABLE(get_advertisements(?))";
	        
	        // Fetch advertisement data from the database
	        PreparedStatement advertisementStatement = connection.prepareStatement(sqlQuery);
	        
	        advertisementStatement.setInt(1,advertiserId);
	        
	        ResultSet advertisementResultSet = advertisementStatement.executeQuery();
			while (advertisementResultSet.next()) {
	            AdvertisementsTable advertisement = new AdvertisementsTable(   
	                    advertisementResultSet.getInt("ad_id"),
	                    advertisementResultSet.getString("ad_name"),
	                    advertisementResultSet.getDouble("duration_seconds"),
	                    advertisementResultSet.getDate("start_date")
	            );

	            // Add advertisement to ObservableList
	            advertisements.add(advertisement);
	        }

	        // Close resources
	        advertisementResultSet.close();
	        advertisementStatement.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return advertisements;
	}


}
