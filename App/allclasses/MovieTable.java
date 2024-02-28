package DatabaceTables;

import java.util.Date;

public class MovieTable {
	
	private int movieId;
    private String title;
    private int duration;
    private Date dateOfAir;
    private String genre;

    // Constructor
    public MovieTable(int movieId ,String title, int duration, Date dateOfAir, String genre) {
    	this.movieId = movieId;
        this.title = title;
        this.duration = duration;
        this.dateOfAir = dateOfAir;
        this.genre = genre;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public Date getDateOfAir() {
        return dateOfAir;
    }

    public String getGenre() {
        return genre;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setDateOfAir(Date dateOfAir) {
        this.dateOfAir = dateOfAir;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
    
}
