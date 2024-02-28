package DatabaceTables;

import java.util.Date;

public class AdvertisementsTable{
	private int adId;
    private String adName;
    private double durationSeconds;
    private Date startDate;
    //private int advertiserId;

    public AdvertisementsTable(int adId, String adName, double durationSeconds, Date startDate) {
		this.adId = adId;
        this.adName = adName;
        this.durationSeconds = durationSeconds;
        this.startDate = startDate;
      //  this.advertiserId = advertiserId;
    }

    // Getters and Setters
    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public double getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(double durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

	public int getAdId() {
		return adId;
	}

	public void setAdId(int adId) {
		this.adId = adId;
	}
    
    

 //   public int getAdvertiserId() {
  //      return advertiserId;
  //  }

  //  public void setAdvertiserId(int advertiserId) {
  //      this.advertiserId = advertiserId;
  //  }
}
