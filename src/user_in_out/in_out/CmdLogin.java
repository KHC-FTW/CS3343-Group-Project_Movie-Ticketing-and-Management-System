package user_in_out.in_out;

import java.util.Scanner;

import user_in_out.exception.ExUserNotExist;
import user_in_out.exception.FormatChecker;
import user_in_out.user.UserCenter;

public class CmdLogin implements Command {
    UserCenter uc = UserCenter.getInstance();
    FormatChecker fc = FormatChecker.getInstance();
    
    String[] valueNames = {"username", "password"};
    String input = "", username, password;
    int inputCount = 0;

    @Override
    public void execute(Scanner scanner) {
        System.out.println("[Remind] Leaving the process at any time by entering \"quit\" or \"q\".\n");

        System.out.println("Please provide:");
        while(true) {
            try {
                if (inputCount < valueNames.length) {
                    System.out.print(valueNames[inputCount] + ": ");
                    input = scanner.nextLine().trim();
    
                    if (input.equals("q") || input.equals("quit")) {
                        System.out.println("\n[State] Login process terminated!\n");
                        break;
                    }
    
                    switch (inputCount) {
                        case 0:
                            username = fc.checkUsername(input);
                            break;
                        case 1:
                            password = fc.checkPassword(input);
                            break;
                    }

                    inputCount++;
                    continue;
                }

                uc.login(username, password);
                System.out.println("\n[State] Wellcome back! " + uc.getCurrentUser().getName() + " ^.^\n");
                break;
            } catch (ExUserNotExist e) {
                System.out.println("\n" + e.getMessage() + "\n");
                inputCount = 0;
                System.out.println("Please provide:");
            } catch (Exception e) {
                System.out.println("\n" + e.getMessage() + "\n");
            }
        }
    }
}