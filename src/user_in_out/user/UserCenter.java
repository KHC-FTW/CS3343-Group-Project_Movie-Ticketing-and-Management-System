package user_in_out.user;

import java.util.ArrayList;

import user_in_out.Database;
import user_in_out.exception.*;

public class UserCenter {
    private static UserCenter instance = new UserCenter();
    private Database db;

    private User currentUser;

    public UserCenter() {
        db = Database.getInstance();
        currentUser = new Guest(); 
    }

    public static UserCenter getInstance() { return instance; }

    public User getCurrentUser() { return currentUser; }

    public String getUserPassword(String id) throws ExUserNotExist {
        ArrayList<User> foundUsers = db.searchUser(id);
        if (foundUsers.size() == 0)
            throw new ExUserNotExist();

        return foundUsers.get(0).getPassword();
    }

    public void register(String name, String username, String password, int age, String school) throws ExDuplicateUsername {
        User newMember = new Member(db.getSamllestMemberId(), name, username, password, age, school);
        if (db.addMember(newMember) == false)
            throw new ExDuplicateUsername();
            
        currentUser = newMember;
    }

    public void login(String username, String password) throws ExUserNotExist {
        User user = db.verifyUser(username, password);
        if (user == null)
            throw new ExUserNotExist();

        currentUser = user;
    }

    public void logout() {
        currentUser = new Guest();
    }
    
    public User addMember(String name, String username, String password, int age) throws ExDuplicateUsername {
        User newMember = new Member(db.getSamllestMemberId(), name, username, password, age);
        if (db.addMember(newMember) == false)
            throw new ExDuplicateUsername();

        return newMember;
    }

    public User addMember(String name, String username, String password, int age, String school) throws ExDuplicateUsername {
        User newMember = new Member(db.getSamllestMemberId(), name, username, password, age, school);
        if (db.addMember(newMember) == false)
            throw new ExDuplicateUsername();

        return newMember;
    }

    public User addAdmin(String name, String username, String password, int age) throws ExDuplicateUsername {
        User newAdmin = new Admin(db.getSamllestAdminId(), name, username, password, age);
        if (db.addAdmin(newAdmin) == false)
            throw new ExDuplicateUsername();

        return newAdmin;
    }

    public User delUser(String id) throws ExDeleteSelf, ExUserNotExist {
        if (id.equals(currentUser.getId()))
            throw new ExDeleteSelf();

        User user = db.delUser(id);
        if (user == null)
            throw new ExUserNotExist();

        return user;
    }

    public void searchUser(String idOrNameOrUsername) throws ExUserNotExist {
        ArrayList<User> foundUsers = db.searchUser(idOrNameOrUsername);
        if (foundUsers.size() == 0)
            throw new ExUserNotExist();

        System.out.println("Search result:");
        printHeadAndLinebreak(foundUsers, true);
        int index = 0;
        for (User user: db.getAllUsers())
            System.out.printf("%-" + 5 + "s  %s\n", ++index, user.toString());
    }

    public void listPersonalProfile() {
        System.out.println("Personal profile:");
        printHeadAndLinebreak(currentUser, false);
        System.out.println(currentUser.toString());
    }

    public void listAllUser() {
        ArrayList<User> allUsers = db.getAllUsers();
        System.out.println("All users:");
        printHeadAndLinebreak(allUsers, true);
        int index = 0;
        for (User user: allUsers)
            System.out.printf("%-" + 5 + "s  %s\n", ++index, user.toString());
    }

    public void printHeadAndLinebreak(User user, boolean withIndex) {
        print(withIndex, user instanceof Member, 
        user instanceof Member && user.getCategory().toString().equals("Student"));
    }

    public void printHeadAndLinebreak(ArrayList<User> users, boolean withIndex) {
        print(withIndex, isContainMember(users), isContainStudent(users));
    }

    public boolean isContainMember(ArrayList<User> users) {
        for (User user : users)
            if (user instanceof Member)
                return true;

        return false;
    }

    public boolean isContainStudent(ArrayList<User> users) {
        for (User user : users)
            if (user instanceof Member && user.getCategory().toString().equals("Student"))
                return true;

        return false;
    }

    public void print(boolean withIndex, boolean isContainMember, boolean isContainStudent) {
        String minimumColumnLine = "----------------------------------------------";
        String indexLine = "-------", categoryLine = "----------", schoolLine = "--------";

        if (withIndex) {
            System.out.print("Index  ");
            minimumColumnLine += indexLine;
        }

        System.out.printf("%-" + 6 + "s  %-" + 4  + "s  %-" + 12 + "s  %-" + 12 + "s  %-" + 3 + "s", 
        "Type", "ID", "Name", "Username", "Age");

        if (isContainMember) {
            System.out.print("  Category");
            minimumColumnLine += categoryLine;
        }
        
        if (isContainStudent) {
            System.out.print("  School");
            minimumColumnLine += schoolLine;
        } 
        
        System.out.println("\n" + minimumColumnLine);
    }
}