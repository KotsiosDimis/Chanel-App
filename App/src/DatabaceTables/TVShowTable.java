package DatabaceTables;

import java.sql.Date;


public class TVShowTable{
    private String title;
    private String genre;
    private Date releaseDate;

    public TVShowTable(String title, String genre, Date releaseDate) {
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }

    // Getters and setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    // StringProperty methods for JavaFX properties
    


    // Add and remove methods

    public static void add(TVShowTable tvShow) {
        // Add logic to add the TV show to your data model (e.g., database, list, etc.)
        // For simplicity, assuming tvShows is a list
        TVShowTable.add(tvShow);
    }

	public static void remove(TVShowTable tvShow) {
        // Add logic to remove the TV show from your data model (e.g., database, list, etc.)
        // For simplicity, assuming tvShows is a list
        TVShowTable.remove(tvShow);
    }
}
