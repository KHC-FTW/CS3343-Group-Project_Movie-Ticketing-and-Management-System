package release.command;

import java.util.Scanner;

import release.user.UserCenter;

public class CmdListPersonalProfile implements Command {
    private final UserCenter uc = UserCenter.getInstance();

    @Override
    public void execute(Scanner scanner) {
        uc.listPersonalProfile();
        System.out.println();
    }
}
