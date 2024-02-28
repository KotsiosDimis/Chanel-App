package Procudures;

public class TvShowsProcuders extends ProcedureExecutor {

    // TVSHOWS

    public static void insertTvShow(String title, String genre, String releaseDate) {
        executeProcedure("TvShow_Insert(?, ?, ?)", title, genre, releaseDate);
    }

    public static void updateTvShow(String title, String newTitle, String genre, String date) {
        executeProcedure("TvShow_Update(?, ?, ?, ?)", title, newTitle, genre, date);
    }

    public static void removeTvShow(String title) {
        executeProcedure("TvShow_Remove(?)", title);
    }

    // SEASONS

    public static void insertSeason(String tvShow, String title, String airdate) {
        executeProcedure("Season_Insert(?, ?, ?)", tvShow, title, airdate);
    }

    public static void updateSeason(String title, String newTitle, String airdate) {
        executeProcedure("Season_Update(?, ?, ?)", title, newTitle, airdate);
    }

    public static void removeSeason(String season) {
        executeProcedure("Season_Remove(?)", season);
    }

    // EPISODES

    public static void removeEpisode(int seasonId, int episodeId) {
        executeProcedure("Episode_Remove(?, ?)", seasonId, episodeId);
    }

    public static void insertEpisode(String showTitle, String seasonTitle, int episodeNum, String title, String date, String duration) {
        executeProcedure("Episode_Insert(?, ?, ?, ?, ?, ?)", showTitle, seasonTitle, episodeNum, title, date, duration);
        
    }

    public static void updateEpisode(String showTitle, String seasonTitle, String episodeTitle, int episodeNum, String newTitle, String date, String duration) {
        executeProcedure("Episode_Update(?, ?, ?, ?, ?, ?, ?)", showTitle, seasonTitle, episodeTitle, episodeNum, newTitle, date, duration);
    }
}
