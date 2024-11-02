package selectMovieModule;

import java.util.ArrayList;
import java.util.List;

public class SeatingPlan{
	private final List<List<String>> seatingPlan = new ArrayList<>();
	private final int rows;
	private final int columns;
	private static final String bookedSymbol = "X";
	private static final String availableSymbol = "O";

	private static boolean validateConstructorParameters(int rows, int columns){
		// limit the seating plan size to be 5 x 5 - 20 x 20
		return rows >= 5 && rows <= 20 && columns >= 5 && columns <= 20;
	}

	public SeatingPlan() throws CustomException {
		// default seating plan
		this(10, 10);
	}

	public SeatingPlan(int rows, int columns) throws CustomException {
		if (!validateConstructorParameters(rows, columns)) {
			throw new CustomException(
					"Invalid seating plan size. The seating plan size should be between 5 x 5 and 20 x 20.");
		}
		this.rows = rows;
		this.columns = columns;
		for (int i = 0; i < rows; i++) {
			this.seatingPlan.add(new ArrayList<>(columns));
			for (int j = 0; j < columns; j++) {
				this.seatingPlan.get(i).add(availableSymbol);
			}
		}
	}
	
	public List<List<String>> getSeatingPlan() {return seatingPlan;	}
	
	public int getRows() {return rows;}
	
	public int getColumns() {return columns;}
	
	public String getBookedSymbol() {return bookedSymbol;}
	
	public String getAvailableSymbol() {return availableSymbol;}
	
	@Override
	public String toString() {
		return "Seating plan with " + rows + " rows and " + columns + " columns.";
	}
}
