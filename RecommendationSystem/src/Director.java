import java.util.ArrayList;

public class Director {
	ArrayList<Movie> movies;
	String name;
	public ArrayList<Movie> getMovies() {
		return movies;
	}
	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Director(ArrayList<Movie> movies, String name) {
		super();
		this.movies = movies;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Director [movies=" + movies + ", name=" + name + "]";
	}
	
	
}
