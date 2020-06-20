
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
	private ArrayList<Movie> myMovies;
	private ArrayList<Rater> myRaters;

	public SecondRatings() {
		// default constructor
		// this("ratedmoviesfull.csv", "ratings.csv");
		this("ratedmovies_short.csv", "ratings_short.csv");
	}

	public SecondRatings(String moviefile, String ratingsfile) {
		FirstRatings ratings = new FirstRatings();
		myMovies = ratings.loadMovies(moviefile);
		myRaters = ratings.loadRaters(ratingsfile);

	}

	public int getMovieSize() {
		return myMovies.size();
	}

	public int getRaterSize() {
		return myRaters.size();
	}

	public double getAverageByID(String movieID, int minimalRaters) {
		FirstRatings ratings = new FirstRatings();
		if (ratings.findRatingTimesForMovieByID(myRaters, movieID) >= minimalRaters) {
			return ratings.findAverageRatingForMovieByID(myRaters, movieID);
		} else
			return 0.0;
	}

	public ArrayList<Rating> getAverageRatings(int minimalRaters) {
		ArrayList<Rating> resultRatings = new ArrayList<Rating>();
		for (Movie movie : myMovies) {
			double rate = getAverageByID(movie.getID(), minimalRaters);
			if (rate > 0) {
				resultRatings.add(new Rating(movie.getID(), rate));
			}
		}
		return resultRatings;
	}

	public String getTitle(String movieId) {
		String movieTitle = null;
		for (Movie movie : myMovies) {
			if (movie.getID().equals(movieId)) {
				return movie.getTitle();
			}
		}
		return "Movie not exist";
	}
	public String getMovieID(String movieTitle) {
		String movieID = null;
		for (Movie movie : myMovies) {
			if (movie.getTitle().equals(movieTitle)) {
				return movie.getID();
			}
		}
		return "NO SUCH TITLE.";
	}
	
}
