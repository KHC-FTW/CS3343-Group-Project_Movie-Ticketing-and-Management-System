package user_in_out.in_out;

import java.util.Scanner;

public class CmdDelUser implements Command {
    @Override
    public void execute(Scanner scanner) {
        System.out.println("[State] Delete user complete! (Dummy Message)");
        System.out.println();
    }
}
