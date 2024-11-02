package selectMovieModule;

import java.util.Map;

import Product.Product;

public class Admin extends RegisteredUser{
		
	private Admin() {super("admin", "password");}
	private static final Admin admin = new Admin();
	public static Admin getInstance() {return admin;}
	
	@Override
	public Map<Integer, Movie> listMovies() throws CustomException{
		return super.movieService.getAllMoviesAdmin();
	}
	
	@Override
	public Map<Integer, Movie> searchMovies(String name) throws CustomException {
		return super.movieService.searchMoviesAdmin(name);
	}
	
	public void addItem(Object item) {
		//TODO: add item to the database
		if (item instanceof Movie) {
			return;
		}
		if (item instanceof Product) {
			return;
		}
	}
	
}
