import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MovieRunnerAverage {
	public static void main(String[] args) {
		MovieRunnerAverage movieRunnerAverage = new MovieRunnerAverage();
		//movieRunnerAverage.printAverageRatings();
		movieRunnerAverage.getAverageRatingOneMovie();
	}

	public void printAverageRatings() {
		SecondRatings secondRatings = new SecondRatings();
		System.out.println("#Movies: " + secondRatings.getMovieSize());
		System.out.println("#Raters: " + secondRatings.getRaterSize());

		ArrayList<Rating> ratings = secondRatings.getAverageRatings(3);

		Collections.sort(ratings, new Comparator<Rating>() {

			@Override
			public int compare(Rating arg0, Rating arg1) {

				return Double.valueOf(arg0.getValue()).compareTo(Double.valueOf(arg1.getValue()));
			}
		});
		for (Rating rate : ratings) {
			System.out.println(rate.getValue() + " " + secondRatings.getTitle(rate.getItem()));
		}
	}
	
	public void getAverageRatingOneMovie() {
		SecondRatings secondRatings = new SecondRatings();
		System.out.println(secondRatings.getAverageByID(secondRatings.getMovieID("The Godfather"), 0));
	}
}
