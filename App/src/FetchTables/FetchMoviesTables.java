package FetchTables;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Connection.DBConnectionUtil;
import DatabaceTables.MovieTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FetchMoviesTables {
	
    public static ObservableList<MovieTable> fetchMovies() {
        ObservableList<MovieTable> movies = FXCollections.observableArrayList();

        try {
            // Establish database connection
            Connection connection = DBConnectionUtil.getConnection();

            // Fetch movie data from the database
            PreparedStatement movieStatement = connection.prepareStatement(
                    "SELECT * FROM TABLE(get_movies())");
            ResultSet movieResultSet = movieStatement.executeQuery();

            while (movieResultSet.next()) {
            	int movieId =  movieResultSet.getInt("MOVIEID");
                String title = movieResultSet.getString("TITLE");
                int duration = movieResultSet.getInt("DURATION");
                Date dateOfAir = movieResultSet.getDate("DATEOFAIR");
                String genre = movieResultSet.getString("GENRE");
                
                //System.out.println(movieId);

                // Add MovieTable to ObservableList
                MovieTable movieTable = new MovieTable(movieId,title, duration, dateOfAir, genre);
                movies.add(movieTable);
            }

            // Close resources
            movieResultSet.close();
            movieStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movies;
    }


}
