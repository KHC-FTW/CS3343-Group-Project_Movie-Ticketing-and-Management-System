package release.in_out;

import java.util.ArrayList;
import java.util.List;

import release.command.*;
import release.user.Admin;
import release.user.Guest;
import release.user.Member;

public class OptionCenter {
    public static OptionCenter instance = new OptionCenter();

    private final List<Option> guestOptions = new ArrayList<>();
    private final List<Option> memberOptions = new ArrayList<>();
    private final List<Option> adminOptions = new ArrayList<>();

    public OptionCenter() {
        // Order the options under this order: guest - member - admin, except option 0, can perform fast copy
        Option[] allOptions = {
                new Option("Exit", CmdExit.class),  //0
                new Option("Register", CmdRegister.class),  //1
                new Option("Login", CmdLogin.class),    //2
                new Option("List Movies", CmdListMovies.class), //3
                new Option("Personal Profile", CmdListPersonalProfile.class),   //4
                new Option("Logout", CmdLogout.class),  //5
                new Option("List Movies (Admin)", CmdListMoviesAdmin.class),    //6
                new Option("List All Users", CmdListAllUsers.class),    //7
                new Option("Search User", CmdSearchUsers.class),//  8
                new Option("Add User", CmdAddUser.class),// 9
                new Option("Delete User", CmdDelUser.class),    //10
                // newly added options
                new Option("Search Movies", CmdSearchMovies.class), //11
                new Option("Search Movies (Admin)", CmdSearchMovies.class), //12
                new Option("Buy Movie Tickets", CmdBuyMovieTickets.class),   //13
                new Option("Add Movies", CmdAddMovie.class),   //14
                new Option("Add Snacks/Drinks", CmdAddSnacksDrinks.class),  //15
                new Option("Reset Password", CmdResetPassword.class),   //16
                new Option("Schedule Movies", CmdScheduleMovies.class),    //17
                new Option("Add Default Movies", CmdAddDefaultMovies.class),    //18
                new Option("List Payment Records", CmdListPaymentRecords.class),   //19
                new Option("List Movie Sessions", CmdListMovieSessions.class),    //20
                new Option("List Snacks/Drinks", CmdListSnacksDrinks.class)    //21
        };

        // Guest options initialization
        guestOptions.add(allOptions[0]);
        guestOptions.add(allOptions[1]);
        guestOptions.add(allOptions[2]);
        guestOptions.add(allOptions[3]);
        guestOptions.add(allOptions[20]);
        guestOptions.add(allOptions[21]);
        guestOptions.add(allOptions[11]);

        // Member options initialization
        memberOptions.add(allOptions[0]);
        memberOptions.add(allOptions[3]);
        memberOptions.add(allOptions[20]);
        memberOptions.add(allOptions[21]);
        memberOptions.add(allOptions[11]);
        memberOptions.add(allOptions[13]);
        memberOptions.add(allOptions[4]);
        memberOptions.add(allOptions[19]);
        memberOptions.add(allOptions[16]);
        memberOptions.add(allOptions[5]);

        // Admin options initialization
        adminOptions.add(allOptions[0]);
        adminOptions.add(allOptions[6]);
        adminOptions.add(allOptions[20]);
        adminOptions.add(allOptions[21]);
        adminOptions.add(allOptions[7]);
        adminOptions.add(allOptions[8]);
        adminOptions.add(allOptions[9]);
        adminOptions.add(allOptions[10]);
        adminOptions.add(allOptions[12]);
        adminOptions.add(allOptions[14]);
        adminOptions.add(allOptions[18]);
        adminOptions.add(allOptions[17]);
        adminOptions.add(allOptions[15]);
        adminOptions.add(allOptions[4]);
        adminOptions.add(allOptions[16]);
        adminOptions.add(allOptions[5]);

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

    public List<Option> getOptions(Guest user) {
        return guestOptions;
    }

    public List<Option> getOptions(Member user) {
        return memberOptions;
    }

    public List<Option> getOptions(Admin user) {
        return adminOptions;
    }
}