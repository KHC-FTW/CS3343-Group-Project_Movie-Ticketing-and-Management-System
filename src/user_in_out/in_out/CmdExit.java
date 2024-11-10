package user_in_out.in_out;

import java.util.Scanner;

public class CmdExit implements Command {
    @Override
    public void execute(Scanner scanner) {
        System.out.println("Exit Tiketing System!\n");
    }
}
