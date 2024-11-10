package user_in_out.in_out;

import java.util.ArrayList;

import user_in_out.user.Admin;
import user_in_out.user.Guest;
import user_in_out.user.Member;

public class OptionCenter {
    public static OptionCenter instance = new OptionCenter();
    
    private ArrayList<Option> guestOptions = new ArrayList<>();
    private ArrayList<Option> memberOptions = new ArrayList<>();
    private ArrayList<Option> adminOptions = new ArrayList<>();

    public OptionCenter() {
        // Order the options under this order: guest - member - admin, except option 0, can perform fast copy
        Option[] allOptions = {
            new Option(0, "Exit", CmdExit.class),
            new Option(1, "Register", CmdRegister.class),
            new Option(2, "Login", CmdLogin.class),
            new Option(3, "List Movies", CmdListMovies.class),
            new Option(4, "Personal Profile", CmdListPersonalProfile.class),
            new Option(5, "Logout", CmdLogout.class),
            new Option(6, "List Movies (Admin)", CmdListMoviesAdmin.class),
            new Option(7, "List All Users", CmdListAllUsers.class),
            new Option(8, "Search User", CmdSearchUsers.class),
            new Option(9, "Add User", CmdAddUser.class),
            new Option(10, "Delete User", CmdDelUser.class)
        };

        // Guest options initialization
        guestOptions.add(allOptions[0]);
        guestOptions.add(allOptions[1]);
        guestOptions.add(allOptions[2]);
        guestOptions.add(allOptions[3]);

        // Member options initialization
        memberOptions.add(allOptions[0]);
        memberOptions.add(allOptions[3]);
        memberOptions.add(allOptions[4]);
        memberOptions.add(allOptions[5]);

        // Admin options initialization
        adminOptions.add(allOptions[0]);
        adminOptions.add(allOptions[4]);
        adminOptions.add(allOptions[5]);
        adminOptions.add(allOptions[6]);
        adminOptions.add(allOptions[7]);
        adminOptions.add(allOptions[8]);
        adminOptions.add(allOptions[9]);
        adminOptions.add(allOptions[10]);
        
        // guestOptions = new Option[4];
        // System.arraycopy(allOptions, 0, guestOptions, 0, 4);

        // memberOptions = new Option[4];
        // memberOptions[0] = allOptions[0];
        // System.arraycopy(allOptions, 3, memberOptions, 1, 3);

        // adminOptions = new Option[8];
        // adminOptions[0] = allOptions[0];
        // System.arraycopy(allOptions, 4, adminOptions, 1, 7);
    }

    public static OptionCenter getInstance() {
        return instance;
    }
    
    public ArrayList<Option> getOptions(Guest user) { 
        return guestOptions; 
    }

    public ArrayList<Option> getOptions(Member user) { 
        return memberOptions; 
    }

    public ArrayList<Option> getOptions(Admin user) { 
        return adminOptions; 
    }
}