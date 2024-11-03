package selectMovieModule;

import java.util.List;

public class BookSeatService {
	private BookSeatService() {}
	private static final BookSeatService bookSeatService = new BookSeatService();
	public static BookSeatService getInstance() {return bookSeatService;}
	
	public boolean bookSeat(SeatingPlan seatingPlan, String option) throws CustomException {
		if (!isSeatAvailable(seatingPlan, option)) {
			return false;
		}
		int col = option.toLowerCase().charAt(0) - (int) 'a';
		int row = Integer.parseInt(option.substring(1)) - 1;
		seatingPlan.getSeatingPlan().get(row).set(col, seatingPlan.getBookedSymbol());
		return true;
	}
	
	public boolean releaseSeat(SeatingPlan seatingPlan, String option) throws CustomException {
		int col = option.toLowerCase().charAt(0) - (int) 'a';
		int row = Integer.parseInt(option.substring(1)) - 1;
		List<List<String>> seatPlan = seatingPlan.getSeatingPlan();
		String availableSymbol = seatingPlan.getAvailableSymbol();
		if (seatPlan.get(row).get(col).equals(availableSymbol)) {
			return false;
		}
		seatingPlan.getSeatingPlan().get(row).set(col, availableSymbol);
		return true;
	}
	
	private Boolean isSeatAvailable(SeatingPlan seatingPlan, String option) throws CustomException {
		
		if (option.length() < 2 || option.length() > 3 || !Character.isAlphabetic(option.charAt(0)) || Character.isAlphabetic(option.charAt(1)))
			throw new CustomException("Invalid format for \"" + option + "\".");

		int col = option.toLowerCase().charAt(0) - (int)'a';
		int row = Integer.parseInt(option.substring(1)) - 1;
		
		if (row < 0 || row >= seatingPlan.getRows() || col < 0 || col >= seatingPlan.getColumns()) 
			throw new CustomException("Invalid format for \"" + option + "\".");
		
		return seatingPlan.getSeatingPlan().get(row).get(col).equals(seatingPlan.getAvailableSymbol());
	}

}
