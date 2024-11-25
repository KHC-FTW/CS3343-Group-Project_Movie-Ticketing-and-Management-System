package release.database;

import java.util.*;

import release.movie.House;
import release.movie.Movie;
import release.product.ProductWithPortion;
import release.user.Admin;
import release.user.Member;
import release.user.User;


public class Database {
    private static final Database instance = new Database();
    private String openHours;
	private String closeHours;
	private List<House> houses = new ArrayList<>();
	private List<Movie> movies = new ArrayList<>();
    private List<ProductWithPortion> products = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    private Set<Integer> memberIdSet, adminIdSet;
    private int smallestAvailMemberId = 0, smallestAvailAdminId = 0;
    
    private Database() {
        memberIdSet = new HashSet<>();
        adminIdSet = new HashSet<>();
        
        // Default system admin user
        User defaultAdmin = new Admin(getSmallestAdminId(), "Master Admin", "admin", "password", 20);
        addAdmin(defaultAdmin);

        // Add a default dummy member for quick testing purposes
        // save the time to register each time when the program runs
        User dummyMember = new Member(getSmallestMemberId(), "Dummy Member", "dummy", "password", 12, "Dummy School");
        addMember(dummyMember);
    }

    public static Database getInstance() { return instance; }

    public String getSmallestMemberId() {
        return String.format("m%03d", smallestAvailMemberId);
    }

    public String getSmallestAdminId() {
        return String.format("a%03d", smallestAvailAdminId);
    }

    public List<User> getAllUsers() { return users;}

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

    public User delUser(String id) {
        User user = getUserById(id);
        if (user == null)
            return null;

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

        return user;
    }

    public List<User> searchUser(String idOrNameOrUsername) {
        List<User> foundUsers = new ArrayList<>();
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
    
    //Section separation ------------------------------------------------------------------------------------------

    public void setOpeningHours(String openingHours) {this.openHours = openingHours;}
    public void setClosingHours(String closingHours) {this.closeHours = closingHours;}
    public void setHouses(List<House> houses) {this.houses = houses;}
    public void setMovies(List<Movie> movies) {
        this.movies.addAll(movies);
    }
    public void setProducts(List<ProductWithPortion> products){this.products = products;}

    public String getOpenHours() {return openHours;}
    public String getCloseHours() {return closeHours;}
    public List<Movie> getMovies() {return movies;}
    public List<House> getHouses() {return houses;}
    public List<ProductWithPortion> getProducts() {return products;}

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

//    public void addHouse(House house) {
//        houses.add(house);
//    }

    public void addProduct(ProductWithPortion product) {
        products.add(product);
    }

    // newly added for writing Junit test cases
    public void resetDB() {
        openHours = null;
        closeHours = null;
        houses.clear();
        movies.clear();
        products.clear();
        users.clear();
        memberIdSet.clear();
        adminIdSet.clear();
        smallestAvailMemberId = 0;
        smallestAvailAdminId = 0;
    }
    
}