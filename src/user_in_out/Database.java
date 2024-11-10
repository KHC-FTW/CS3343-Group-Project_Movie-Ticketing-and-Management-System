package user_in_out;

import java.util.*;

import user_in_out.user.Admin;
import user_in_out.user.User;

public class Database {
    private static Database instance = new Database();
    private ArrayList<User> users = new ArrayList<User>();
    private Set<Integer> memberIdSet, adminIdSet;
    private int smallestAvailMemberId = 0, smallestAvailAdminId = 0;
    
    public Database() {
        memberIdSet = new HashSet<>();
        adminIdSet = new HashSet<>();
        
        // Default system admin user
        User defaultAdmin = new Admin(getSamllestAdminId(), "Master Admin", "admin", "password", 20);
        addAdmin(defaultAdmin);
    }

    public static Database getInstance() { return instance; }

    public String getSamllestMemberId() {
        return String.format("m%03d", smallestAvailMemberId);
    }

    public String getSamllestAdminId() {
        return String.format("a%03d", smallestAvailAdminId);
    }

    public ArrayList<User> getAllUsers() { return users;}

    public User getUserById(String id) {
        for (User user : users) 
            if (user.getId().equals(id))
                return user;
        
        return null;
    }

    public boolean addMember(User member) { 
        if (isDuplicatedUsername(member.getUsername()))
            return false;

        users.add(member); 

        int newId = Integer.parseInt(member.getId().substring(1));
        memberIdSet.add(newId);
        
        while (memberIdSet.contains(smallestAvailMemberId))
            smallestAvailMemberId++;

        return true;
    }

    public boolean addAdmin(User admin) { 
        if (isDuplicatedUsername(admin.getUsername()))
            return false;

        users.add(admin); 

        int newId = Integer.parseInt(admin.getId().substring(1));
        adminIdSet.add(newId);

        while (adminIdSet.contains(smallestAvailAdminId))
            smallestAvailAdminId++;

        return true;
    }

    public boolean isDuplicatedUsername(String username) {
        for (User user : users) 
            if (user.getUsername().equals(username))
                return true;

        return false;
    }

    public String delUser(String id) {
        User user = getUserById(id);
        if (user == null)
            return "";
        
        int oldId = Integer.parseInt(user.getId().substring(1));
        users.remove(user); 

        if (user.getType().equals("member")) {
            memberIdSet.remove(oldId);

            if (oldId < smallestAvailMemberId)
                smallestAvailMemberId = oldId;
        } else {
            adminIdSet.remove(oldId);

            if (oldId < smallestAvailAdminId)
                smallestAvailAdminId = oldId;
        }

        return user.toString();
    }

    public ArrayList<User> searchUser(String idOrNameOrUsername) {
        ArrayList<User> foundUsers = new ArrayList<>(); 
        for (User user : users)
            if (user.getId().contains(idOrNameOrUsername) ||
                user.getName().contains(idOrNameOrUsername) || 
                user.getUsername().contains(idOrNameOrUsername))
                foundUsers.add(user);

        return foundUsers;
    }

    public User verifyUser(String username, String password) {
        for (User user : users) 
            if (user.getUsername().equals(username) && user.getPassword().equals(password))
                return user;

        return null;
    }
}