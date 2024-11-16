package release.command;

import java.util.Scanner;

public class CmdExit implements Command {
    @Override
    public void execute(Scanner scanner) {
        System.out.println("Exit CityU Cinema Ticketing System!\n");
    }
}
