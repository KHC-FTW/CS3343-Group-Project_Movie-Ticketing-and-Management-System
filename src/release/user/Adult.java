package release.user;

public class Adult implements Category {
	// make Student category singleton
	private Adult() {}
	private static Adult instance = new Adult();
	public static Adult getInstance() {return instance;}
		
    @Override
    public double getDiscount() { return 1; }

    @Override
    public String toString() { return "Adult"; }

}
