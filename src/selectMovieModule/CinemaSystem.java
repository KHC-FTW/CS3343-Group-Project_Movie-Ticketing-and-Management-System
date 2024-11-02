package selectMovieModule;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class CinemaSystem{
	private CinemaSystem() {};
	
	public static void main(String[] args) throws CustomException {
		CinemaDatabase.setUpDummyDB();

		StringBuilder dummyMenu = new StringBuilder("Please select the function to see the demo:\n");
		dummyMenu.append("1. List movies (for guests, and logged in customers; show scheduled movies only)\n")
				.append("2. List movies (specific to admin; show unscheduled movies as well)\n")
				.append("3. Search movies by name (for guests and logged in customers)\n")
				.append("4. Search movies by name (specific to admin)\n")
				.append("5. Select movie (logged in customers only)\n")
				.append("\u001B[31m")
				.append("NOTE: This is a dummy input menu for demo only. Not the actual input menu.")
				.append("\u001B[0m");

		String selectedOption = "";
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println(dummyMenu);
            do {
                System.out.print("> ");
                selectedOption = scanner.nextLine().strip();
                int optionValue = 0;
                try {
                    optionValue = Integer.parseInt(selectedOption);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid option, please try again:");
                    continue;
                }
                switch (optionValue) {
                    case 1: {
                        User guest = Guest.getInstance();
                        System.out.print(guest.listMovies());
                        System.out.println("\nDemo for 1. List movies ended.\n");
                        break;
                    }
                    case 2: {
                        User admin = Admin.getDummyAdmin();
                        System.out.print(admin.listMovies());
                        System.out.println("\nDemo for 2. List movies ended.\n");
                        break;
                    }
                    case 3: {
                        User customer = Customer.getDummyAdultCustomer();
                        System.out.print("Enter a key word to search:\n> ");
                        String searchTerm = scanner.nextLine().strip();
                        try {
                            System.out.print(customer.searchMovieByName(searchTerm));
                        } catch (CustomException e) {
                            System.out.println(e.getMessage());
                        }
                        System.out.println("\nDemo for 3. Search movies by name ended.\n");
                        break;
                    }
                    case 4: {
                        User admin = Admin.getDummyAdmin();
                        System.out.print("Enter a key word to search:\n> ");
                        String searchTerm = scanner.nextLine().strip();
                        try {
                            System.out.print(admin.searchMovieByName(searchTerm));
                        } catch (CustomException e) {
                            System.out.println(e.getMessage());
                        }
                        System.out.println("\nDemo for 4. Search movies by name ended.\n");
                        break;
                    }
                    case 5:
                        testSelectMovieFunctions(scanner);
                        break;
                    default:
                        System.out.println("Invalid option, please try again:");
                        continue;
                }
                System.out.println("Do you want to see the demo of another function? (Y/N)");
                boolean demoAgain = false;
                do {
                    System.out.print("> ");
                    selectedOption = scanner.nextLine().strip();
                    if (selectedOption.equalsIgnoreCase("Y")) {
                        demoAgain = true;
                        System.out.println(dummyMenu);
                        break;
                    } else if (selectedOption.equalsIgnoreCase("N")) {
                        System.out.println("Demo session ended.");
                        break;
                    } else {
                        System.out.println("Invalid input. Please try again:");
                    }
                } while (true);
                if (!demoAgain) break;
            } while (true);
        }
	}
	
	private static Customer dummy_userInputCustomerType(Scanner getInput) {
        String customerType = "";
        do {
            System.out.println("Select your customer type:\n1) Children\n2) Adult\n3) Elderly");
			System.out.print("NOTE: This is a dummy for the customer login function.\nThe actual login function should return a customer object.\n> ");
            customerType = getInput.nextLine().strip();
            if (customerType.equals("1") || customerType.equals("2") || customerType.equals("3")) {
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }while(true);
        Customer customer = switch (customerType) {
            case "1" -> Customer.getDummyChildrenCustomer();
            case "2" -> Customer.getDummyAdultCustomer();
            case "3" -> Customer.getDummyElderlyCustomer();
            default -> null;
        };
        return customer;
	}
	
	private static String dummy_userInputMovieSelection(Map<String, Movie> choosableMovies, Customer customer, Scanner getInput) {
		String selectedMovieOption = "";
		Set<String> keys = choosableMovies.keySet();
		do {
			System.out.print("Please select a movie option:\n> ");
			selectedMovieOption = getInput.nextLine().strip();
			if (keys.contains(selectedMovieOption)) {
				Movie selectedMovie = choosableMovies.get(selectedMovieOption);
				if (selectedMovie.customerAllowToWatch(customer))
					break;
				else{
					System.out.println("You are not allowed to watch class III movies.");
				}
			} else {
				System.out.println("Invalid option. Please try again.");
			}
		}while(true);
		return selectedMovieOption;
	}
	
	private static String dummy_userInputMovieSession(List<MovieSession> movieSessionOptions, Scanner getInput) {
        boolean validInput = false;
        String selectedMovieSession = "";
        do {
            System.out.print("Please select a movie session:\n> ");
            selectedMovieSession = getInput.nextLine().strip();
            int temp = -1;
            
            try {
                temp = Integer.parseInt(selectedMovieSession);
            } catch (NumberFormatException e) {
                System.out.println("Invalid option. Please try again.");
                continue;
            }
            if (temp >= 1 && temp <= movieSessionOptions.size()) {
                validInput = true;
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }while(!validInput);
        return selectedMovieSession;
    }
	
	private static SeatingPlan dummy_userInputSeatSelection(Customer customer, Scanner getInput) throws CustomException {
		boolean selectSeatAgain = false;
		SeatingPlan seatingPlan = customer.getCachedMovieSession().getSeats();
		
		do {
			int bookedCnt = 0;
			System.out.print("Please select your seat(s): (\",\" separated e.g. A1,B2,C3)\n> ");
			String[] selectedSeats = getInput.nextLine().strip().split(",");
			
			for (String seat : selectedSeats) {
				seat = seat.strip();
				try {
					System.out.println(customer.bookSeat(seat));
					bookedCnt++;
				} catch (CustomException e) {
					System.out.println(e.getMessage());
				}
			}
			if (bookedCnt == 0) {
				System.out.print("No seats were booked. Do you want to retry? (Y/N)\n> ");
				do {
					String retry = getInput.nextLine().strip();
                    if (retry.equalsIgnoreCase("Y")) {
                    	selectSeatAgain = true;
                    	System.out.println(seatingPlan.displaySeatingPlan());
                    	break;
                    } else if (retry.equalsIgnoreCase("N")) {
                    	selectSeatAgain = false;
                    	break;
                    } else {
                        System.out.print("Invalid input. Please try again.\n> ");
                    }
				}while(true);
				
			} else if (bookedCnt < selectedSeats.length) {
				System.out.print("Some seats were not booked. Do you want to retry? (Y/N)\n> ");
				
				do {
                    String retry = getInput.nextLine().strip();
                    if (retry.equalsIgnoreCase("Y")) {
                    	selectSeatAgain = true;
                    	System.out.println(seatingPlan.displaySeatingPlan());
                    	break;
                    } else if (retry.equalsIgnoreCase("N")) {
                    	selectSeatAgain = false;
                    	break;
                    } else {
                        System.out.print("Invalid input. Please try again.\n> ");
                    }
				}while(true);

            } else {
                System.out.print("All seats have been booked successfully! Do you want to book more seats? (Y/N)\n> ");
                
                do {
                    String retry = getInput.nextLine().strip();
                    if (retry.equalsIgnoreCase("Y")) {
                    	selectSeatAgain = true;
                    	System.out.println(seatingPlan.displaySeatingPlan());
                    	break;
                    } else if (retry.equalsIgnoreCase("N")) {
                    	selectSeatAgain = false;
                    	break;
                    } else {
                        System.out.print("Invalid input. Please try again.\n> ");
                    }
                }while(true);
            }
            
		}while(selectSeatAgain);
		return seatingPlan;
	}

	private static void dummy_inputGetPaymentStatus(Customer customer, Scanner getInput) throws CustomException {

		String paymentStatus = "";

		do{
			System.out.println("Please select payment status to test seat booking results:\n1) Payment success\n2) Payment failed");
			System.out.print("NOTE: This part depends on the payment module. The current version is a dummy demo only.\n> ");
			paymentStatus = getInput.nextLine().strip();
			if (paymentStatus.equals("1")){
				String movie = customer.getCachedMovieSession().getMovie().getName();
				MovieSession movieSession = customer.getCachedMovieSession();
				System.out.println("All seats have been booked successfully for \"" + movie + "\" of " + movieSession.displayBasicInfo() + ". Thank you!");
				customer.clearCachedSeats();
				customer.clearCachedMovieSession();
				return;
			}else if (paymentStatus.equals("2")){
				System.out.println(customer.undoBookSeats());
				customer.clearCachedMovieSession();
				customer.clearCachedSeats();
				return;
			}else{
				System.out.println("Invalid input. Please try again.");
			}
		}while(true);
	}
	
	private static void testSelectMovieFunctions(Scanner scanner) throws CustomException {
		Customer customer = dummy_userInputCustomerType(scanner);
		Map<String, Movie> queriedOptions = customer.getAvailableMovies();
		System.out.println(Movie.displayMovieOptions(queriedOptions));
		
		String selectedMovieOption = dummy_userInputMovieSelection(queriedOptions, customer, scanner);
		List<MovieSession> movieSessionOptions = customer.getMovieSessionsFromMovie(queriedOptions, selectedMovieOption);
		System.out.println(MovieSession.displayMovieSessions(movieSessionOptions));
		
		String selectedMovieSessionOption = dummy_userInputMovieSession(movieSessionOptions, scanner);
		MovieSession selectedMovieSession = customer.getSelectedMovieSessionFromOptions(movieSessionOptions, selectedMovieSessionOption);
		System.out.println(selectedMovieSession.getSeats().displaySeatingPlan());
		
		SeatingPlan selectedSeatingPlan = dummy_userInputSeatSelection(customer, scanner);
		dummy_inputGetPaymentStatus(customer, scanner);
		System.out.println(selectedSeatingPlan.displaySeatingPlan());
		System.out.println("Select movie functions test ended.");
	}
}