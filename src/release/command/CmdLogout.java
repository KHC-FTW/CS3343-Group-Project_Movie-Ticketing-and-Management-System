package release.command;

import java.util.Scanner;

import release.user.UserCenter;

public class CmdLogout implements Command {
    private final UserCenter uc = UserCenter.getInstance();

    @Override
    public void execute(Scanner scanner) {
        uc.logout();
        System.out.println("[State] Logout!\n");
    }
}
