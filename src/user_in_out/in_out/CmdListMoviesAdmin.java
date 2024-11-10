package user_in_out.in_out;

import java.util.Scanner;

import user_in_out.movie.MovieCenter;

public class CmdListMoviesAdmin implements Command {
    MovieCenter mc = MovieCenter.getInstance();

    @Override
    public void execute(Scanner scanner) {
        mc.listMoviesAdmin();
        System.out.println();
    }
}