package release.user;

public class Student implements Category {
	// make Student category singleton
	private Student() {}
	private static final Student instance = new Student();
	public static Student getInstance() {return instance;}
	
    @Override
    public double getDiscount() { return 0.8; }

    @Override
    public String toString() { return "Student"; }

}
