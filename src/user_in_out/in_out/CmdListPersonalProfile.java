package user_in_out.in_out;

import java.util.Scanner;

import user_in_out.user.UserCenter;

public class CmdListPersonalProfile implements Command {
    UserCenter uc = UserCenter.getInstance();

    @Override
    public void execute(Scanner scanner) {
        uc.listPersonalProfile();
        System.out.println();
    }
}
