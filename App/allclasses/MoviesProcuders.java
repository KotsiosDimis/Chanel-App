package Procudures;

public class MoviesProcuders extends ProcedureExecutor  {
	
	public static void removeMovie(int movieId) {
        executeProcedure("Movie_Remove(?)", movieId);
    }

    public static void insertMovie(String title, String duration, String dateOfAir, String genre) {
        executeProcedure("Movie_Insert(?, ?, ?, ?)", title, duration, dateOfAir, genre);
    }

    public static void updateMovie(int movieId, String title, String duration, String dateOfAir, String genre) {
        executeProcedure("Movie_Update(?, ?, ?, ?, ?)", movieId, title, duration, dateOfAir, genre);
    }
	
}
