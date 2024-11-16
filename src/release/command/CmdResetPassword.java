package release.command;

import release.user.User;
import release.user.UserCenter;

import java.util.Scanner;

public class CmdResetPassword implements Command{

    private final UserCenter uc = UserCenter.getInstance();
    private final User currentUser = uc.getCurrentUser();

    @Override
    public void execute(Scanner scanner) {

        String[] requiredInputs = {"Enter your current password: ", "Enter new password: ", "Confirm new password: "};
        int inputCount = 0;
        boolean correctCurrentPassword = false;
        String input = "";
        String newPassword = "";

        System.out.println("[Remind] Leaving the process at any time by entering \"q\" or \"quit\".\n");

        do{
            System.out.print(requiredInputs[inputCount]);
            input = scanner.nextLine();

            if (input.equals("q") || input.equals("quit")) {
                System.out.println("\n[State] Reset password process terminated!\n");
                break;
            }
            if (!correctCurrentPassword) {
                if (!currentUser.getPassword().equals(input)) {
                    System.out.println("Incorrect current password.");
                } else {
                    correctCurrentPassword = true;
                    inputCount++;
                }
                continue;
            }
            if (inputCount == 1) {
                newPassword = input;
                inputCount++;
                continue;
            }
            if (inputCount == 2){
                if (input.equals(newPassword)){
                    currentUser.setPassword(input);
                    System.out.println("Password reset successfully.");
                    break;
                } else {
                    System.out.println("Passwords do not match. Please try again.");
                    inputCount = 1;
                }
            }
        }while(true);
    }
}
