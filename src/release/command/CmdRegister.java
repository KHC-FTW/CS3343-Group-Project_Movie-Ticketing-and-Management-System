package release.command;

import java.util.Arrays;
import java.util.Scanner;

import release.exception.ExDuplicateUsername;
import release.exception.ExInsufficientArgs;
import release.helper.*;
import release.user.User;
import release.user.UserCenter;

public class CmdRegister implements Command {
    private final UserCenter uc = UserCenter.getInstance();
    private final FormatChecker fc = FormatChecker.getInstance();

    String[] valueNames = {"name", "username", "password", "age", "school (optional)"};
    String input = "", name, username, password, school;
    int inputCount = 0, age;

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
                        System.out.println("\n[State] Register process terminated!\n");
                        break;
                    }

                    switch (inputCount) {
                        case 0:
                            name = fc.checkName(input);
                            break;
                        case 1:
                            username = fc.checkUsername(input);
                            break;
                        case 2:
                            password = fc.checkPassword(input);
                            break;
                        case 3:
                            age = fc.checkAge(input);
                            break;
                        case 4:
                            school = input.equals("") ? "" : fc.checkSchool(input);
                            break;
                    }

                    inputCount++;
                    continue;
                }

                if(school.equals("")){
                    uc.register(name, username, password, age);
                }else{
                    uc.register(name, username, password, age, school);
                }
                System.out.println("\n[State] Welcome! " + uc.getCurrentUser().getName() + " ^.^\n");
                break;
            } catch (ExDuplicateUsername e) {
                System.out.println("\n" + e.getMessage() + "\n");
                inputCount = 0;
                System.out.println("Please provide:");
            } catch (Exception e) {
                System.out.println("\n" + e.getMessage() + "\n");
            }
        }
    }
}
