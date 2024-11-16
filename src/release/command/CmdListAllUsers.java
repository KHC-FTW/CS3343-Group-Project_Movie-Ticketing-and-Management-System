package release.command;

import java.util.Scanner;

import release.user.UserCenter;

public class CmdListAllUsers implements Command {
    private final UserCenter uc = UserCenter.getInstance();

    @Override
    public void execute(Scanner scanner) {
        uc.listAllUser();
        System.out.println();
    }
}
