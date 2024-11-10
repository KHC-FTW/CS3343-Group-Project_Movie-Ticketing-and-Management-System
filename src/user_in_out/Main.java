package user_in_out;

import java.util.*;

import user_in_out.user.UserCenter;

public class Main {
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        UserCenter uc = UserCenter.getInstance();
        
        // Welcome message and list all options
        System.out.println("\n-- Welcome to CityU Cinema Online Ticketing System ^.^\n");
        uc.getCurrentUser().listOptions();
        System.out.println();

        while (true) {
            try {
                // Prompt the user to select option
                System.out.print("Please enter the ID or NAME to select an option (enter \"list\" or \"l\" to view options):\n> ");
    
                // Read the input string
                input = scanner.nextLine().toLowerCase().trim();
                System.out.println();

                // If enter "l", list options available for current user, then receive next input
                if (input.equals("l") || input.equals("list")) {
                    uc.getCurrentUser().listOptions();
                    System.out.println();
                    continue;
                }

                // Try get an option and execute the corresponding command
                if (input.matches("-?\\d+")) 
                    uc.getCurrentUser().getOption(Integer.parseInt(input))
                    .getCmdClass().getDeclaredConstructor().newInstance().execute(scanner);
                else
                    uc.getCurrentUser().getOption(input)
                    .getCmdClass().getDeclaredConstructor().newInstance().execute(scanner);

                // Exit the system based on input
                if (input.equals("exit") || input.equals("0"))
                    break;
            } catch (Exception e) {
                System.out.println(e.getMessage() + "\n");
            }
        }

        scanner.close();
    }
}