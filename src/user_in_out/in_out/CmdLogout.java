package user_in_out.in_out;

import java.util.Scanner;

import user_in_out.user.UserCenter;

public class CmdLogout implements Command {
    UserCenter uc = UserCenter.getInstance();

    @Override
    public void execute(Scanner scanner) {
        uc.logout();
        System.out.println("[State] Logout!\n");
    }
}
