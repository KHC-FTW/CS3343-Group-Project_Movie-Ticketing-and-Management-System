package release.command;

import java.util.Scanner;

import release.helper.*;
import release.user.UserCenter;

public class CmdSearchUsers implements Command {
    private final UserCenter uc = UserCenter.getInstance();
    private final FormatChecker fc = new FormatChecker();
    private String input = "";

    @Override
    public void execute(Scanner scanner) {
        System.out.println("[Remind] Leaving the process at any time by entering \"quit\" or \"q\".\n");
        
        while (true) {
            try {
                System.out.print("Please provide the ID or NAME or USERNAME of the user:\n> ");
                
                input = scanner.nextLine().trim();
                System.out.println();

                if (input.equals("q") || input.equals("quit")) {
                    System.out.println("[State] Search process terminated!\n");
                    break;
                }

                uc.searchUser(fc.checkSearch(input));
                System.out.println();
            } catch (Exception e) {
                System.out.println(e.getMessage() + "\n");
            }
        }
    }
}
