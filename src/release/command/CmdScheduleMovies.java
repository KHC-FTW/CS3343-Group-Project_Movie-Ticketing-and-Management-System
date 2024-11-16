package release.command;

import release.database.Database;
import release.movie.ScheduleManagment;

import java.util.Scanner;

public class CmdScheduleMovies implements Command{
    private final ScheduleManagment sm = ScheduleManagment.getInstance();
    private final Database db = Database.getInstance();
    @Override
    public void execute(Scanner scanner) throws Exception {
        if(sm.getScheduleStatus()){
            System.out.println("[Remind] Movies have already been scheduled.\n");
            return;
        }
        // enforce admin to have at least 5 movies before scheduling
        if (db.getMovies().size() < 5){
            System.out.println("[Remind] Scheduling is not performed. It is recommended to have at least 5 movies before performing the action.\n");
            return;
        }

//        System.out.println("[Remind] You may leave the process at any time by entering \"q\" or \"quit\".\n");
        System.out.println("[Remind] Movies can only be scheduled once. Make sure you have added all the required movies before performing the action.\n");
        System.out.println("Please select the scheduling strategy you want to use:");
        String input = "";
        do{
            System.out.println("1. Profit Maximization");
            System.out.println("2. Balanced Schedule");
            System.out.print("> ");
            input = scanner.nextLine().strip();
            System.out.println();
            if (input.equals("q") || input.equals("quit")){
                System.out.println("[State] Scheduling process terminated.\n");
                return;
            } else if (input.equals("1")) {
                break;
            } else if (input.equals("2")) {
                sm.changeSchedulingStrategies(input);
                break;
            }else {
                System.out.println("[Exception] Invalid command. Please try again.\n");
            }
        }while(true);

        sm.setScheduleSlot();
        sm.setHasScheduledStatus();
        System.out.println("[State] The movies have been successfully scheduled with: " + sm.getSchedule().getSchedulingName() + ".\n");
    }
}
