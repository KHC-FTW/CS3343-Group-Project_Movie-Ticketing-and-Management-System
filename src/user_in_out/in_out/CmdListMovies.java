package user_in_out.in_out;

import java.util.Scanner;

import user_in_out.movie.MovieCenter;

public class CmdListMovies implements Command {
    MovieCenter mc = MovieCenter.getInstance();

    @Override
    public void execute(Scanner scanner) {
        mc.listMovies();
        System.out.println();
    }
}
