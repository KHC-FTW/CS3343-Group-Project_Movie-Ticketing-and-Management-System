package user_in_out.user;

public class Child implements Category {
    @Override
    public double getDiscount() { return 0.6; }

    @Override
    public String toString() { return "Child"; }
}
