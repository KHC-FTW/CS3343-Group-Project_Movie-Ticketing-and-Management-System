package selectMovieModule;

public class Elderly implements CustomerState {
	private double discount = 0.5;
	private Elderly() {}
	private static final Elderly instance = new Elderly();

	public static Elderly getInstance() {return instance;}

	@Override
	public Boolean canViewClassIIIMovies() {return true;}

	@Override
	public double getDiscount() {return discount;}

	@Override
	public String toString() {return "elderly";}

	@Override
	public void setDiscount(double discount) {this.discount = discount;}
}
