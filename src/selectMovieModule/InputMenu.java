package selectMovieModule;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import MovieScheduling.ScheduleManagment;

public class InputMenu {
	private InputMenu() {}
	
	private static final Scanner sc = new Scanner(System.in);
	private static final CinemaDatabase db = CinemaDatabase.getInstance();
	
	public static void setUp() {
		System.out.println("Welcome to the Movie Management System.");
		DefaultData.loadDefaultDataToDB();
		System.out.println("The system has been set up with default parameters.");
		System.out.println("Please select a movie scheduling strategy:");
		System.out.println("1. Profit Maximization Schedule");
		System.out.print("2. Balanced Schedule.\n> ");
		ScheduleManagment scheduleManagment = new ScheduleManagment(db.getMovies());
		String selection = sc.nextLine();
		if (!selection.equals("1")) {
			scheduleManagment.changeSchedulingStrategies("2");
		}
		scheduleManagment.setScheduleSlot();
		System.out.println("The movies have been scheduled with: " + scheduleManagment.getSchedule().getSchedulingName());
	}
	
	public static void guestStartMenu() {
		System.out.println("\nWelcome guest. Please select your action:");
		StringBuilder menu = new StringBuilder("1. List all movies.").append("\n")
				.append("2. Search movies.").append("\n")
				.append("3. Auto log in as Admin.").append("\n")
				.append("4. Auto log in as dummy child customer.").append("\n")
				.append("5. Auto log in as dummy adult customer.").append("\n")
				.append("6. Auto log in as dummy elderly customer.").append("\n")
				.append("7. Exit.");
		boolean noExit = true;
		User user = Guest.getInstance();
		do {
			System.out.println(menu);
			System.out.print("Your selection > ");
			String input = sc.nextLine();
			try {
				switch(input) {
				case "1" : {
					Map<Integer, Movie> results = user.listMovies();
					System.out.println(ToStringUtil.showMovie(results));
					break;
				}
				case "2" : {
					searchMovieMenu(user);
					break;
				}
				case "3" :{
					user = ((Guest)user).login("admin", "password");
					adminMenu((Admin)user);
					user = Guest.getInstance();
					System.out.println("\nWelcome guest. Please select your action:");
					break;
				}
				case "4" :{
					user = ((Guest)user).login("testChildren", "password");
					customerMenu((Customer)user);
					user = Guest.getInstance();
					System.out.println("\nWelcome guest. Please select your action:");
					break;
				}
				case "5" :{
					user = ((Guest)user).login("testAdult", "password");
					customerMenu((Customer)user);
					user = Guest.getInstance();
					System.out.println("\nWelcome guest. Please select your action:");
					break;
				}
				case "6" :{
					user = ((Guest)user).login("testElderly", "password");
					customerMenu((Customer)user);
					user = Guest.getInstance();
					System.out.println("\nWelcome guest. Please select your action:");
					break;
				}
				case "7" :
					noExit = false;
					System.out.println("Program exits.");
					break;
				default:
					System.out.println("Unknown selection. Please try again.");
					break;
				}
			}catch(CustomException e) {
				System.out.println(e.getMessage());
			}
			
		}while(noExit);
	}
	
	private static void adminMenu(Admin admin) {
		String name = admin.getUserName();
		System.out.println("Welcome " + name + "! Please select your action:");
		StringBuilder menu = new StringBuilder("1. List all movies.").append("\n")
				.append("2. Search movies.").append("\n")
				.append("(Other admin functions not implemented yet. Stay tuned!)").append("\n")
				.append("3. Log out.");
		boolean notLogout = true;
		do {
			System.out.println(menu);
			System.out.print("Your selection > ");
			String input = sc.nextLine();
			try {
				switch(input) {
				case "1" : {
					Map<Integer, Movie> results = admin.listMovies();
					System.out.println(ToStringUtil.showMovieAdmin(results));
					break;
				}
				case "2" : {
					searchMovieMenu(admin);
					break;
				}
				case "3" :{
					notLogout = false;
					System.out.println("You have logged out successfully");
					break;
				}
				default:
					System.out.println("Unknown selection. Please try again.");
					break;
				}
			}catch(CustomException e) {
				System.out.println(e.getMessage());
			}
			
		}while(notLogout);
	}
	
	private static void searchMovieMenu(User user) throws CustomException {
		System.out.print("Please enter the a name to search.\n> ");
		String input = sc.nextLine();
		if (user instanceof Admin) {
			Map<Integer, Movie> results = ((Admin)user).searchMovies(input);
			System.out.println(ToStringUtil.showMovieAdmin(results));
			return;
		}
		Map<Integer, Movie> results = user.searchMovies(input);
		System.out.println(ToStringUtil.showMovie(results));
	}
	
	private static void customerMenu(Customer customer) {
		String name = customer.getUserName();
		System.out.println("Welcome " + name + "! Please select your action:");
		StringBuilder menu = new StringBuilder("1. List all movies.").append("\n")
				.append("2. Search movies.").append("\n")
				.append("3. Buy Movie Tickets.").append("\n")
				.append("4. Log out.");
		boolean notLogout = true;
		do {
			System.out.println(menu);
			System.out.print("Your selection > ");
			String input = sc.nextLine();
			try {
				switch(input) {
				case "1" : {
					Map<Integer, Movie> results = customer.listMovies();
					System.out.println(ToStringUtil.showMovie(results));
					break;
				}
				case "2" : {
					searchMovieMenu(customer);
					break;
				}
				case "3" :{
					buyTicketMenu(customer);
					break;
				}
				case "4" :{
					notLogout = false;
					System.out.println("You have logged out successfully");
					break;
				}
				default:
					System.out.println("Unknown selection. Please try again.");
					break;
				}
			}catch(CustomException e) {
				System.out.println(e.getMessage());
			}
			
		}while(notLogout);
	}
	
	private static void buyTicketMenu(Customer customer) throws CustomException {
		Map<Integer, Movie> movies = customer.listMovies();
		System.out.println(ToStringUtil.showMovie(movies));
		
		boolean stillBuying = true;
		do {
			try {
				System.out.print("Please select a movie:\n> ");
				String input = sc.nextLine();
				if (earlyQuitCheck(input)) {
					return;
				}
				int selection = Integer.parseInt(input);
				Movie movie = customer.getSelectedMovie(movies, selection);
				Map<Integer, MovieSession> movieSessions = customer.getMovieSessionsFromMovie(movie);
				System.out.println(ToStringUtil.showMovieSessions(movieSessions));
				stillBuying = movieSessionMenu(customer, movieSessions);
			}catch(NumberFormatException e) {
				System.out.println("Invalid input format.\n");
			}catch (CustomException e) {
				System.out.println(e.getMessage() + "\n");
			}
		}while(stillBuying);
	}
	
	private static boolean movieSessionMenu(Customer customer, Map<Integer, MovieSession> movieSessions) {
		boolean stillBuying = true;
		do {
			try {
				System.out.print("Please select a movie session:\n> ");
				String input = sc.nextLine();
				if (earlyQuitCheck(input)) {
					return false;
				}
				int selection = Integer.parseInt(input);
				MovieSession movieSession = customer.getSelectedMovieSession(movieSessions, selection);
				customer.cacheMovieSession(movieSession);
				System.out.println(ToStringUtil.showSeatingPlan(movieSession.getSeats()));
				stillBuying = selectSeatMenu(customer, movieSession.getSeats());
			}catch(NumberFormatException e) {
				System.out.println("Invalid input format.\n");
			} catch (CustomException e) {
				System.out.println(e.getMessage() + "\n");
			}
		}while(stillBuying);
		return false;
	}
	
	private static boolean selectSeatMenu(Customer customer, SeatingPlan seatingPlan) {
		boolean stillBuying = true;
		do {
			try {
				System.out.print("Please select your seat(s): (\",\" separated e.g. A1,B2,C3)\n> ");
				String input = sc.nextLine();
				if (earlyQuitCheck(input)) {
					customer.clearCachedMovieSession();
					customer.clearCachedSeats();
					return false;
				}
				String[] selectedSeats = input.strip().split(",");
				for (String seat : selectedSeats) {
					seat = seat.strip();
					try {
						if (customer.bookSeat(seatingPlan, seat)) {
							customer.cacheSelectedSeat(seat);
							System.out.println("\"" + seat + "\" has been booked successfully.");
						}
					} catch (CustomException e) {
						System.out.println(e.getMessage());
					}
				}
				System.out.println("Latest seating plan:");
				System.out.println(ToStringUtil.showSeatingPlan(seatingPlan));
				System.out.println("Want to book more seats? (Y: Yes/ Others: No)");
				input = sc.nextLine();
				if (!input.equalsIgnoreCase("Y")) return false;
			}catch(NumberFormatException e) {
				System.out.println("Invalid input format.\n");
			}
		}while(stillBuying);
		return false;
	}
	
	private static boolean earlyQuitCheck(String input) {
		if (input.equalsIgnoreCase("Q")) {
			System.out.print("\nIf you quit at this stage, any unsaved data will be lost. Confirm? (Y: yes/ Others: No)\n> ");
			String option = sc.nextLine();
			if(option.equalsIgnoreCase("Y")) {
				return true;
			}
		}
		return false;
	}
	
}
