package selectMovieModule;

public class Children implements CustomerState{
	private double discount = 0.8;
	private Children() {}
	private static final Children instance = new Children();
	
	public static Children getInstance() {return instance;}

	@Override
	public Boolean canViewClassIIIMovies() {return false;}

	@Override
	public double getDiscount() {return discount;}
	
	@Override
	public String toString() {return "children";}

	@Override
	public void setDiscount(double discount) {this.discount = discount;}
}
