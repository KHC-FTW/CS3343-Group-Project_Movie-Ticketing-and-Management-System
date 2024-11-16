package release.user;

public class Elder implements Category {
	// make elder category singleton
	private Elder() {}
	private static final Elder instance = new Elder();
	public static Elder getInstance() {return instance;}
		
    @Override
    public double getDiscount() { return 0.5; }

    @Override
    public String toString() { return "Elder"; }

}
