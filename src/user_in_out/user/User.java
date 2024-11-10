package user_in_out.user;

import java.util.ArrayList;

import user_in_out.exception.*;
import user_in_out.in_out.Option;
import user_in_out.in_out.OptionCenter;

public class User {
    private String type, id, name, username, password;
    private int age;

    protected OptionCenter oc = OptionCenter.getInstance();
    protected ArrayList<Option> options = new ArrayList<>();

    // Only used for initializing guest
    public User() {
        type = "guest";
        id = "g001";
        name = "Guest";
        username = "no username";
        password = "no password";
        age = 0;
    }

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

    public String getPassword() { return password; }

    public int getAge() { return age; }

    public Category getCategory() { return null; }

    public Option getOption(int optId) throws ExInvalidOption { 
        for (Option option : options)
            if (option.getId() == optId)
                return option;

        throw new ExInvalidOption();
    }

    public Option getOption(String optName) throws ExInvalidOption { 
        for (Option option : options)
            if (option.getName().toLowerCase().equals(optName))
                return option;

        throw new ExInvalidOption();
    }

    public void listOptions() {
        System.out.println("Options available:");
        for (Option option : options)
            System.out.println(option.toString());
    }

    public String toString() { 
        return String.format("%-" + 6 + "s  %-" + 4  + "s  %-" + 12 + "s  %-" + 12 + "s  %-" + 3 + "s  ", 
        type.substring(0, 1).toUpperCase() + type.substring(1), 
        id, name, username, age);
    }
}