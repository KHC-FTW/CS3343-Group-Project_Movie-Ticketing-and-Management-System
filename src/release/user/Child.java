package release.user;

public class Child implements Category {
	// make child category singleton
	private Child() {}
	private static final Child instance = new Child();
	public static Child getInstance() {return instance;}
	
    @Override
    public double getDiscount() { return 0.6; }

    @Override
    public String toString() { return "Child"; }

}
