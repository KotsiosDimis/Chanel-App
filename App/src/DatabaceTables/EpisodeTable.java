package DatabaceTables;

import java.sql.Date;


// Define Episode class
public class EpisodeTable {
	private int seasonId;
    private int episodeNumber;
    private String title;
    private Date airDate;
    private float duration;

    public EpisodeTable(int episodeNumber, String title, Date airDate, float duration) {
        this.episodeNumber = episodeNumber;
        this.title = title;
        this.airDate = airDate;
        this.duration = duration;
    }
    
    public EpisodeTable(int seasonId, int episodeNumber, String title, float duration, Date airDate) {
		super();
		this.seasonId = seasonId;
		this.episodeNumber = episodeNumber;
		this.title = title;
		this.airDate = airDate;
		this.duration = duration;
	}

	public int getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(int seasonId) {
		this.seasonId = seasonId;
	}

	public int getEpisodeNumber() {
		return episodeNumber;
	}

	public void setEpisodeNumber(int episodeNumber) {
		this.episodeNumber = episodeNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getAirDate() {
		return airDate;
	}

	public void setAirDate(Date airDate) {
		this.airDate = airDate;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

}