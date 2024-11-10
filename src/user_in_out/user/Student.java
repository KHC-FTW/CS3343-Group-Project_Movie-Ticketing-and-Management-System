package user_in_out.user;

public class Student implements Category {
    @Override
    public double getDiscount() { return 0.8; }

    @Override
    public String toString() { return "Student"; }
}
