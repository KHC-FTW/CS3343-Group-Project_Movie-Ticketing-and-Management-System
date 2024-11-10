package user_in_out.user;

public class Admin extends User {
    public Admin(String id, String name, String username, String password, int age) {
        super("admin", id, name, username, password, age);
        options = oc.getOptions(this);
    }
}
