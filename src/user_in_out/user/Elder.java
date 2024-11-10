package user_in_out.user;

public class Elder implements Category {
    @Override
    public double getDiscount() { return 0.5; }

    @Override
    public String toString() { return "Elder"; }
}
