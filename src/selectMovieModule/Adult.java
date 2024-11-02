package selectMovieModule;

public class Adult implements CustomerState{
	private double discount = 1.0;
	private Adult() {}
	private static final Adult instance = new Adult();
	
	public static Adult getInstance() {return instance;}

	@Override
	public Boolean canViewClassIIIMovies() {return true;}

	@Override
	public double getDiscount() {return discount;}
	
	@Override
	public String toString() {return "adult";}
	
	@Override
	public void setDiscount(double discount) {this.discount = discount;}
}
