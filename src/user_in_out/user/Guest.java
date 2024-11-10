package user_in_out.user;

public class Guest extends User {
    public Guest() {
        options = oc.getOptions(this);
    }
}
