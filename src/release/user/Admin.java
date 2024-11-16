package release.user;

import release.in_out.*;

public class Admin extends User {
    public Admin(String id, String name, String username, String password, int age) {
        super("admin", id, name, username, password, age);
        options = oc.getOptions(this);
    }
}
