package release.command;

import java.util.Scanner;

import release.database.*;
import release.helper.*;
import release.movie.Movie;
import release.movie.MovieService;
import release.user.UserCenter;

public interface Command{
    void execute(Scanner scanner) throws Exception;
}