package DatabaceTables;

import java.sql.Date;

public class SeasonTable {
	
	
    private String title;
    private Date airdate;
    private String totalEpisodes;

    public SeasonTable(String title, Date airdate, String totalEpisodes) {
        this.title = title;
        this.airdate = airdate;
        this.totalEpisodes = totalEpisodes;
    }

	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getAirdate() {
		return airdate;
	}

	public void setAirdate(Date airdate) {
		this.airdate = airdate;
	}

	public String getTotalEpisodes() {
		return totalEpisodes;
	}

	public void setTotalEpisodes(String totalEpisodes) {
		this.totalEpisodes = totalEpisodes;
	}

   
}
