import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class FirstRatings {

	public static void main(String[] args) {
		FirstRatings firstRatings = new FirstRatings();
		firstRatings.testLoadMovies();
		// firstRatings.testloadRaters();
	}

	public ArrayList<Rater> loadRaters(String filename) {
		ArrayList<Rater> raters = new ArrayList<Rater>();
		try {

			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("resources/" + filename);

			BufferedReader resReader = new BufferedReader(new InputStreamReader(inputStream));

			CSVParser csvParser = new CSVParser(resReader, CSVFormat.DEFAULT);
			csvParser.iterator().next();
			for (CSVRecord csvRecord : csvParser) {
				// rater_id,movie_id,rating,time
				Rater rater = new Rater(csvRecord.get(0));

				if (!raters.contains(rater)) {
					rater.addRating(csvRecord.get(1), Double.parseDouble(csvRecord.get(2)));
					raters.add(rater);
				} else {
					raters.get(Integer.parseInt(rater.getID()) - 1).addRating(csvRecord.get(1),
							Double.parseDouble(csvRecord.get(2)));

				}
			}
			csvParser.close();
			return raters;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public void testloadRaters() {
		ArrayList<Rater> raters = loadRaters("ratings_short.csv");
		System.out.println("SZ#Raters: " + raters.size());
//		for (Rater rater : raters) {
//			System.out.println(rater.toString());
//		}
		ArrayList<Rater> fullraters = loadRaters("ratings.csv");
		System.out.println("SZ#Full Raters: " + fullraters.size());
//		for (Rater rater : raters) {
//			System.out.println(rater.toString());
//		}

		// System.out.println("#Ratings for Rater with ID 193: " + fullraters.get(193 -
		// 1).getItemsRated().size()); -- get rater by id then get number of ratings.

		/// sort raters by num of ratings then print out all to discover or to determine
		/// all with first rater only..
		// System.out.println(numRatersWithMaxNumOfRatings + " rater(s) rated " +
		/// maxNumOfRatings + " movie(s)");

		// String movie_id = "1798709";
		/// call findRatingTimesForMovieByID
		// System.out.println("Movie with ID " + movie_id + " has rated " + numRatings +
		// " times");

		/// find average rating for movie
	}

	public Rater findRaterById(ArrayList<Rater> raters, String raterID) {
		for (Rater rater : raters) {
			if (rater.getID().equals(raterID))
				return rater;
		}
		System.err.println("findRaterById couldn't find rater with ID: " + raterID);
		return null;
	}

	public int findUniqueMovies(ArrayList<Rater> raters) {
		Set<String> movies = new HashSet<String>();

		for (Rater rater : raters) {
			for (String ratedMovie : rater.getItemsRated()) {
				movies.add(ratedMovie);
			}
		}
		return movies.size();
	}

	public int findRatingTimesForMovieByID(ArrayList<Rater> raters, String movieID) {
		int ratingTimes = 0;

		for (Rater rater : raters) {
			for (String ratedMovie : rater.getItemsRated()) {
				if (ratedMovie.equals(movieID))
					ratingTimes++;
			}
		}
		return ratingTimes;
	}

	public double findAverageRatingForMovieByID(ArrayList<Rater> raters, String movieID) {
		int sum = 0;
		int numRaters = 0;
		for (Rater rater : raters) {
			double rate = rater.getRating(movieID);
			if (rate != -1) {
				numRaters++;
				sum += rate;
			}

		}
		return sum / numRaters;
	}

	public ArrayList<Rater> sortRatersByNumberOfRatings(ArrayList<Rater> raters) {

		// Sort the list
		Collections.sort(raters, new Comparator<Rater>() {

			@Override
			public int compare(Rater rater1, Rater rater2) {
				return (Integer.valueOf(rater2.getItemsRated().size()).compareTo(rater1.getItemsRated().size()));
			}
		});
		return raters;

	}

	public ArrayList<Movie> loadMovies(String filename) {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		try {

			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("resources/" + filename);

			BufferedReader resReader = new BufferedReader(new InputStreamReader(inputStream));

			CSVParser csvParser = new CSVParser(resReader, CSVFormat.DEFAULT);
			csvParser.iterator().next(); // first line contains header
			for (CSVRecord csvRecord : csvParser) {
				movies.add(new Movie(csvRecord.get(0), csvRecord.get(1), csvRecord.get(2), csvRecord.get(3),
						csvRecord.get(4), csvRecord.get(5), Integer.parseInt(csvRecord.get(6)), csvRecord.get(7)));
			}
			csvParser.close();
			return movies;
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}

	public void testLoadMovies() {
		ArrayList<Movie> movies = loadMovies("ratedmoviesfull.csv");
		System.out.println("SZ#Movies: " + movies.size());
//		for (Movie movie : movies) {
//			System.out.println(movie.toString());
//		}

		ArrayList<Movie> fullMovies = loadMovies("ratedmoviesfull.csv");
		System.out.println("SZ#Movies(full): " + fullMovies.size());
//		for (Movie movie : fullMovies) {
//			System.out.println(movie.toString());
//		}

		// System.out.println("#Comedy movies (in short list): ");

		// System.out.println(comedyMovies);
//		System.out.println("#movies (in short list) with length > 150 mins: ");
//		System.out.println(moviesWithLongLengthCounter);

		System.out.println("Max movies per director: ");

//		ArrayList<Director> resultDirectors = sortDirectorsByMovies(fullMovies, findDirctors(fullMovies));
//
//		for (Director dir : resultDirectors) {
//			System.out.println(dir.name + " directed " + dir.getMovies().size());
//		}
	}

	public ArrayList<Movie> findMoviesWithGenre(ArrayList<Movie> movies, String genre) {
		ArrayList<Movie> genreMovies = new ArrayList<Movie>();
		for (Movie movie : movies) {
			if (movie.getGenres().contains(genre)) {
				genreMovies.add(movie);
			}
		}
		return genreMovies;
	}

	/// return any movie greater than given duration, could be enhanced to include
	/// operator sign for much more dynamic
	public ArrayList<Movie> findMoviesWithDuration(ArrayList<Movie> movies, int duration) {
		ArrayList<Movie> durationMovies = new ArrayList<Movie>();
		for (Movie movie : movies) {
			if (movie.getMinutes() > duration) {
				durationMovies.add(movie);
			}
		}
		return durationMovies;
	}

	public ArrayList<Movie> findMoviesByDirector(ArrayList<Movie> movies, String director) {
		ArrayList<Movie> moviesByDirector = new ArrayList<Movie>();
		for (Movie movie : movies) {
			if (movie.getDirector().contains(director)) {
				moviesByDirector.add(movie);
			}
		}
		return moviesByDirector;
	}

	public Set<String> findDirctors(ArrayList<Movie> movies) {
		Set<String> directors = new HashSet<String>();
		for (Movie movie : movies) {
			String[] movieDirectors = movie.getDirector().split(",");

			for (String arg0 : movieDirectors) {
				directors.add(arg0.trim());
			}
		}
		return directors;
	}

	public ArrayList<Movie> sortMoviesByDirector(ArrayList<Movie> movies) {

		Collections.sort(movies, new Comparator<Movie>() {

			@Override
			public int compare(Movie arg0, Movie arg1) {

				return Integer.valueOf(arg1.getDirector().split(",").length)
						.compareTo(arg0.getDirector().split(",").length);
			}
		});
		return movies;
	}

	public ArrayList<Director> sortDirectorsByMovies(ArrayList<Movie> movies, Set<String> dirctors) {
		ArrayList<Director> resultDirectors = new ArrayList<Director>();
		HashMap<String, ArrayList<Movie>> directorsMap = new HashMap<String, ArrayList<Movie>>();
		for (String director : dirctors) {
			directorsMap.put(director, new ArrayList<Movie>());
			for (Movie movie : movies) {
				if (movie.getDirector().contains(director))
					directorsMap.get(director).add(movie);
			}
		}
		Iterator<Entry<String, ArrayList<Movie>>> iterator = directorsMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, ArrayList<Movie>> entry = iterator.next();
			resultDirectors.add(new Director(entry.getValue(), entry.getKey()));
		}
		Collections.sort(resultDirectors, new Comparator<Director>() {

			@Override
			public int compare(Director arg0, Director arg1) {
				return Integer.valueOf(arg1.movies.size()).compareTo(arg0.movies.size());
			}
		});
		return resultDirectors;

	}

}
