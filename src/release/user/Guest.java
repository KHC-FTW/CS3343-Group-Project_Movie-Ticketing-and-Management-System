package release.user;

public class Guest extends User {
    private Guest() {
        super("guest", "g001", "Guest", "no username", "no password", 0);
        options = oc.getOptions(this);
    }
    
    private static final Guest instance = new Guest();
    public static Guest getInstance() {return instance;}
}
