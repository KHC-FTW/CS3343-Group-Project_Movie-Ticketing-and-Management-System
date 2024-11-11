package user_in_out.in_out;

import java.util.ArrayList;
import java.util.Scanner;

import user_in_out.exception.FormatChecker;
import user_in_out.user.User;
import user_in_out.user.UserCenter;

public class CmdDelUser implements Command {
    UserCenter uc = UserCenter.getInstance();
    FormatChecker fc = FormatChecker.getInstance();
    
    ArrayList<User> pastUsers = new ArrayList<>();
    String input = "";

    @Override
    public void execute(Scanner scanner) {
        System.out.println("[Remind] Leaving the process at any time by entering \"quit\" or \"q\".\n");
        
        System.out.println("Please provide the ID of the user to be removed:");
        while (true) {
            try {
                System.out.print("> ");
                
                input = scanner.nextLine().trim();
                System.out.println();

                if (input.equals("q") || input.equals("quit"))
                    break;

                pastUsers.add(uc.delUser(input));
                System.out.println("[State] Deleted one user!\n");
            } catch (Exception e) {
                System.out.println(e.getMessage() + "\n");
            }
        }

        if (pastUsers.size() == 0)
            System.out.println("[State] Add user process terminated!\n");
        else {
            System.out.println("All deleted users:");
            uc.printHeadAndLinebreak(pastUsers, true);
            int index = 0;
            for (User user: pastUsers)
                System.out.printf("%-" + 5 + "s  %s\n", ++index, user.toString());
            System.out.println();
        }
    }
}
