package release.command;

import release.exception.*;
import release.movie.*;
import release.product.MovieTicket;
import release.user.Member;
import release.user.UserCenter;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CmdBuyMovieTickets implements Command{
    private final BookSeatService bSS = BookSeatService.getInstance();
    private final UserCenter uc = UserCenter.getInstance();
    private final MovieService ms = MovieService.getInstance();
    private Scanner scanner;
    private final Member currentUser = (Member) uc.getCurrentUser();

    @Override
    public void execute(Scanner scanner) {
        this.scanner = scanner;
        selectMovieSessionMenu();
    }

    private void selectMovieSessionMenu(){
        Map<Integer, MovieSession> movieSessions = null;
        try{
            movieSessions = ms.getAllMovieSessions();
            String results = "The following movie sessions are available: \n" + MovieInfoToString.showAllMovieSessions(movieSessions);
            System.out.println(results);
        }catch (ExNoMovieToShow e){
            System.out.println("[Exception] Movie ticketing service is disabled as there are no movie sessions available.\n");
            return;
        }
        boolean stillBuying = true;
        System.out.println("[Remind] You may quit at any time by entering \"q\" or \"quit\", but any unsaved data or unfinished process will be lost.\n");
        do{
            try{
                System.out.print("Please select a movie session:\n> ");
                String input = scanner.nextLine();
                System.out.println();
                if (input.equals("q") || input.equals("quit")) {
                    System.out.println(new ExEarlyQuit("movie session selection menu").getMessage() + "\n");
                    return;
                }
                MovieSession selectedSession = ms.getSelectedMovieSession(movieSessions, Integer.parseInt(input));

                if(selectedSession.getMovie().getClassification().equals("III") && currentUser.getAge() < 18){
                    System.out.println("[Exception] You are not allowed to buy class III movie tickets due to age restriction.\n");
                    continue;
                }
                stillBuying = selectSeatMenu(selectedSession);
            }catch(NumberFormatException | ExInvalidOption e){
                System.out.println(new ExInvalidOption().getMessage() + "\n");
            }catch (ExEarlyQuit e){
                // if early quit, will release all booked seats
                List<MovieTicket> movieTickes = currentUser.getShoppingCart().getMovieTicketCart();
                for (MovieTicket ticket: movieTickes){
                    bSS.releaseSeat(ticket.getMovieSession().getSeats(), ticket.getSeat());
                }
                currentUser.getShoppingCart().clearAllCart();
                System.out.println(e.getMessage() + "\n");
                return;
            }
        }while(stillBuying);
    }

    private boolean selectSeatMenu(MovieSession movieSession) throws ExEarlyQuit {
        SeatingPlan seatingPlan = movieSession.getSeats();
        boolean stillBuying = true;
        System.out.println("[Remind] You may quit at any time by entering \"q\" or \"quit\", but any unsaved data or unfinished process will be lost.\n");
        do {
            try {
                System.out.print("Seating plan: ");
                System.out.println(MovieInfoToString.showSeatingPlan(seatingPlan));
                System.out.print("\nPlease select your seat(s): (Separated by \",\" e.g. A1,A2,C3)\n> ");
                String input = scanner.nextLine();
                System.out.println();
                if (input.equals("q") || input.equals("quit")) {
                    throw new ExEarlyQuit("seat booking menu");
                }
                String[] selectedSeats = input.strip().split(",");
                for (String seat : selectedSeats) {
                    seat = seat.strip();
                    try {
                        if (bSS.bookSeat(seatingPlan, seat)) {
                            currentUser.getShoppingCart().getMovieTicketCart()
                                    .add(new MovieTicket(movieSession.getMovie(), movieSession, seat.toUpperCase()));
                            System.out.println("[State] \"" + seat + "\" has been booked successfully.\n");
                        }else{
                            System.out.println("[State] \"" + seat + "\" was not booked.\n");
                        }
                    } catch (ExInvalidSeat e) {
                        System.out.println(e.getMessage() + "\n");
                    }
                }
                stillBuying = followUpActionMenu(movieSession);
            }catch(NumberFormatException e) {
                System.out.println("[Exception] Invalid input format.\n");
            }
        }while(stillBuying);
        return stillBuying;
    }

    private boolean followUpActionMenu(MovieSession movieSession) throws ExEarlyQuit {

        System.out.println("[Remind] You may quit at any time by entering \"q\" or \"quit\", but any unsaved data or unfinished process will be lost.\n");

        SeatingPlan seatingPlan = movieSession.getSeats();
        System.out.print("Updated seating plan: ");
        System.out.println(MovieInfoToString.showSeatingPlan(seatingPlan));
        System.out.println();
        do{
            System.out.println("Please select your next action:");
            System.out.println("1. Select snacks and drinks.");
            System.out.println("2. Proceed to payment.");
            System.out.println("3. Continue to book seats.");
            System.out.println("4. List shopping cart.");
            System.out.print("> ");
            String input = scanner.nextLine().strip();
            System.out.println();
            switch (input){
                case "q":
                case "quit":
                    throw new ExEarlyQuit("not proceeding to payment");
                case "1":
                    new CmdBuySnacksDrinks().execute(scanner);
                    break;
                case "2":
                    new CmdDoPayment().execute(scanner);
                    return false;
                case "3":
                    return true;
                case "4":
                    new CmdListShoppingCart().execute(scanner);
                    break;
                default:
                    System.out.println("[Exception] Unknown option, please try again.\n");
            }
        }while (true);

    }



//    private boolean earlyQuitCheck(String input) {
//        if (input.equalsIgnoreCase("q")) {
//            System.out.print("\n[Warning] If you quit at this stage, any unsaved data will be lost. Confirm? (Y: yes/ Others: No)\n> ");
//            String option = scanner.nextLine();
//            if(option.equalsIgnoreCase("Y")) {
//                return true;
//            }
//        }
//        return false;
//    }
}
