package release.user;

import java.util.ArrayList;
import java.util.List;

import release.in_out.*;
import release.exception.*;


public class User {
    private String type, id, name, username, password;
    private int age;

    protected OptionCenter oc = OptionCenter.getInstance();
    protected List<Option> options = new ArrayList<>();

//     Only used for initializing guest
//    public User() {
//        type = "guest";
//        id = "g001";
//        name = "Guest";
//        username = "no username";
//        password = "no password";
//        age = 0;
//    }
    // !!! replaced with and initialized in singleton guest !!!

    public User(String type, String id, String name, String username, String password, int age) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.age = age;
    }
    
    public String getType() { return type; }

    public String getId() { return id; }

    public String getName() { return name; }

    public String getUsername() { return username; }

    public void setPassword(String password) { this.password = password; }

    public String getPassword() { return password; }

    public int getAge() { return age; }
//
//    public Category getCategory() { return null; }

    public Option getOption(int index) throws ExInvalidOption {
        try {
            return options.get(index);
        } catch (Exception e) {
            throw new ExInvalidOption();
        }
    }

    public Option getOption(String optName) throws ExInvalidOption {
        for (Option option : options)
            if (option.getName().toLowerCase().equals(optName.toLowerCase()))
                return option;

        throw new ExInvalidOption();
    }

    public void listOptions() {
        System.out.println("Options available:");
        int index = 0;
        for (Option option : options)
            System.out.printf("%-" + 3 + "s %s\n", index++ + ".", option.toString());
    }

    public String toString() {
        return String.format("%-" + 6 + "s  %-" + 4  + "s  %-" + 12 + "s  %-" + 12 + "s  %-" + 3 + "s  ",
        type.substring(0, 1).toUpperCase() + type.substring(1),
        id, name, username, age);
    }
}