package release.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import release.exception.*;
import release.helper.FormatChecker;
import release.user.*;

public class CmdAddUser implements Command {
    UserCenter uc = UserCenter.getInstance();
    FormatChecker fc = FormatChecker.getInstance();

    List<User> newUsers = new ArrayList<>();
    String input = "";

    @Override
    public void execute(Scanner scanner) {
        System.out.println("[Remind] Leaving the process at any time by entering \"quit\" or \"q\".\n");

        System.out.print("Enter the user data unedr this format:\n" +
        "[type], [name], [username], [password], [age], [school] (specified for category Student)\n");
        
        while(true) {
            try {
                System.out.print("> ");
                
                String[] args;
                input = scanner.nextLine().trim();
                System.out.println();

                if (input.equals("q") || input.equals("quit"))
                    break;
            
                args = Arrays.stream(input.split(","))
                             .map(String::trim)
                             .toArray(String[]::new);

                if (args.length < 5)
                    throw new ExInsufficientArgs("[Exception] 5 values should be provided at least.");

                String type = fc.checkType(args[0]);
                String name = fc.checkName(args[1]);
                String username = fc.checkUsername(args[2]);
                String password = fc.checkPassword(args[3]);
                int age = fc.checkAge(args[4]);
                String school = args.length > 5 ? fc.checkSchool(args[5]) : "";

                switch(type) {
                    case "member":
                        if (school.equals(""))
                            newUsers.add(uc.addMember(name, username, password, age));
                        else
                            newUsers.add(uc.addMember(name, username, password, age, school));
                        break;
                    case "admin":
                        newUsers.add(uc.addAdmin(name, username, password, age));
                        break;
                }

                System.out.println("[State] Added one user!\n");
            } catch (Exception e) {
                System.out.println(e.getMessage() + "\n");
            }
        }

        if (newUsers.isEmpty())
            System.out.println("[State] Add user process terminated!\n");
        else {
            System.out.println("All new users:");
            uc.printHeadAndLinebreak(newUsers, true);
            int index = 0;
            for (User user: newUsers)
                System.out.printf("%-" + 5 + "s  %s\n", ++index, user.toString());
            System.out.println();
        }
    }
}