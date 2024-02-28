package FetchTables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Connection.DBConnectionUtil;
import DatabaceTables.EpisodeTable;
import DatabaceTables.SeasonTable;
import DatabaceTables.TVShowTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FetchTvShowsTables {
	
	
	// Fetch TVShow data from the database
    public static ObservableList<TVShowTable> fetchTVShows() {
        ObservableList<TVShowTable> tvShows = FXCollections.observableArrayList();

        try {
            // Establish database connection
            Connection connection = DBConnectionUtil.getConnection();
            
            String sqlQuery = "SELECT * FROM TABLE(get_tvshows)";

            // Fetch TVShow data from the database
            PreparedStatement tvShowStatement = connection.prepareStatement(sqlQuery);
            ResultSet tvShowResultSet = tvShowStatement.executeQuery();

            while (tvShowResultSet.next()) {
                TVShowTable tvShow = new TVShowTable(
                        tvShowResultSet.getString("TITLE"),
                        tvShowResultSet.getString("GENRE"),
                        tvShowResultSet.getDate("RELEASE_DATE")
                        
                );
                
                // Add TVShow to ObservableList
                tvShows.add(tvShow);
                
            }

            // Close resources
            tvShowResultSet.close();
            tvShowStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        return tvShows;
    }
    
 // Fetch Seasons data from the database
    public static ObservableList<SeasonTable> fetchSeasons(String TvshowTitle) {
        ObservableList<SeasonTable> seasons = FXCollections.observableArrayList();

        try {
            // Establish database connection
            Connection connection = DBConnectionUtil.getConnection();
            
            String sqlQuery = "select * from table(get_Seasons(?))";

            // Fetch TVShow data and individual episode counts for each season from the database
            PreparedStatement seasonStatement = connection.prepareStatement(sqlQuery);
            seasonStatement.setString(1, TvshowTitle);
            
            ResultSet seasonResultSet = seasonStatement.executeQuery();

            while (seasonResultSet.next()) {
            	SeasonTable season = new SeasonTable(
            			seasonResultSet.getString("TITLE"),
            			seasonResultSet.getDate("AIRDATE"),
            			seasonResultSet.getString("AMOUNT")
            		
                ); 
            	System.out.println("Fetch season");

                // Add Season to ObservableList
                
                seasons.add(season);
            }

            // Close resources
            seasonResultSet.close();
            seasonStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seasons;
    }
    
    
    
    public static ObservableList<EpisodeTable> fetchEpisodes(String season) {
        ObservableList<EpisodeTable> episodes = FXCollections.observableArrayList();

        try {
            // Establish database connection
            Connection connection = DBConnectionUtil.getConnection();

            // Fetch episode data from the database
            String sqlQuery = "SELECT * FROM TABLE(Get_Episodes(?))";

            
            PreparedStatement episodeStatement = connection.prepareStatement(sqlQuery);
            episodeStatement.setString(1, season);
            


            ResultSet episodeResultSet = episodeStatement.executeQuery();

            while (episodeResultSet.next()) {
            	EpisodeTable episode = new EpisodeTable(
            			episodeResultSet.getInt("EPISODE_NUMBER"),
                        episodeResultSet.getString("TITLE"),
                        episodeResultSet.getDate("AIRDATE"),
                        episodeResultSet.getFloat("DURATION_MIN")
                        
                 );
                
                
                episodes.add(episode);
            }

            // Close resources
            episodeResultSet.close();
            episodeStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return episodes;
    }
    


}
