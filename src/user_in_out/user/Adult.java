package user_in_out.user;

public class Adult implements Category {
    @Override
    public double getDiscount() { return 1; }

    @Override
    public String toString() { return "Adult"; }
}
