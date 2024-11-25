package test.movie_test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import release.movie.MovieSession;
import release.movie.House;
import release.movie.Movie;
import release.movie.Schedule;
import release.movie.ProfitMaximizationSchedule;
import release.movie.BalancedSchedule;
import release.database.Database;
import release.exception.ExNoMovieToSchedule;
import release.exception.ExInvalidSeatingPlan;

import org.junit.jupiter.api.Test;



class MovieSchedulingTest {
	@Test
	void testProfitMax1() throws ExNoMovieToSchedule, ExInvalidSeatingPlan {
		Database db = Database.getInstance();
		db.setOpeningHours("10:00");
        db.setClosingHours("24:00");
        List<House> houses = new ArrayList<>(5);
        houses.add(new House(1, 20, 20));
        houses.add(new House(2, 12, 15));
        houses.add(new House(3, 10, 11));
        houses.add(new House(4, 8, 10));
        houses.add(new House(5, 6, 10));
        db.setHouses(houses);
        
        
		List<Movie> movies = new ArrayList<>();
		
		movies.add(new Movie("top1", "a", 360, 100, 100, "III", "chinese", "chinese"));
		movies.add(new Movie("top2", "a", 360, 90, 90, "III", "chinese", "chinese"));
		movies.add(new Movie("m3", "a", 360, 70, 70, "III", "chinese", "chinese"));
		movies.add(new Movie("m4", "a", 360, 60, 60, "III", "chinese", "chinese"));
		movies.add(new Movie("m5", "a", 360, 50, 50, "III", "chinese", "chinese"));
		
		Schedule s = new ProfitMaximizationSchedule(movies);
		s.scheduling();
		String[] arr = {"top1", "top1", "top2", "top2", "m3", "m4", "m5", "m3", "m4", "m5"};
		
		List<MovieSession> movieSessions = s.getScheduleSessions();
		for(int i=0; i<movieSessions.size(); i++) {
			assertEquals(movieSessions.get(i).getMovie().getName(), arr[i]);
		}
	}
	
	@Test
	void testBalanced1() throws ExNoMovieToSchedule, ExInvalidSeatingPlan {
		Database db = Database.getInstance();
		db.setOpeningHours("10:00");
        db.setClosingHours("24:00");
        List<House> houses = new ArrayList<>(5);
        houses.add(new House(1, 20, 20));
        houses.add(new House(2, 12, 15));
        houses.add(new House(3, 10, 11));
        houses.add(new House(4, 8, 10));
        houses.add(new House(5, 6, 10));
        db.setHouses(houses);
        
        
		List<Movie> movies = new ArrayList<>();
		movies.add(new Movie("m4", "a", 360, 60, 60, "III", "chinese", "chinese"));
		movies.add(new Movie("top1", "a", 360, 100, 100, "III", "chinese", "chinese"));
		movies.add(new Movie("m3", "a", 360, 70, 70, "III", "chinese", "chinese"));
		movies.add(new Movie("m5", "a", 360, 50, 50, "III", "chinese", "chinese"));
		movies.add(new Movie("top2", "a", 360, 90, 90, "III", "chinese", "chinese"));
		
		Schedule s = new BalancedSchedule(movies);
		s.scheduling();
		String[] arr = {"m4", "top1", "m3", "m5", "top2", "top1", "top2", "m3", "m4", "m5"};
		
		List<MovieSession> movieSessions = s.getScheduleSessions();
		for(int i=0; i<movieSessions.size(); i++) {
			assertEquals(movieSessions.get(i).getMovie().getName(), arr[i]);
		}
	}
	
	@Test
	void testProfitMax2() throws ExNoMovieToSchedule, ExInvalidSeatingPlan {
		Database db = Database.getInstance();
		db.setOpeningHours("10:00");
        db.setClosingHours("24:00");
        List<House> houses = new ArrayList<>(5);
        houses.add(new House(1, 20, 20));
        houses.add(new House(2, 12, 15));
        houses.add(new House(3, 10, 11));
        houses.add(new House(4, 8, 10));
        houses.add(new House(5, 6, 10));
        db.setHouses(houses);
        
        
		List<Movie> movies = new ArrayList<>();
		movies.add(new Movie("m3", "a", 360, 70, 70, "III", "chinese", "chinese"));
		movies.add(new Movie("m5", "a", 360, 50, 50, "III", "chinese", "chinese"));
		movies.add(new Movie("top2", "a", 360, 90, 90, "III", "chinese", "chinese"));
		movies.add(new Movie("top1", "a", 360, 100, 100, "III", "chinese", "chinese"));
		movies.add(new Movie("m4", "a", 360, 60, 60, "III", "chinese", "chinese"));
		
		Schedule s = new ProfitMaximizationSchedule(movies);
		s.scheduling();
		String[] arr = {"top1", "top1", "top2", "top2", "m3", "m4", "m5", "m3", "m4", "m5"};
		
		List<MovieSession> movieSessions = s.getScheduleSessions();
		for(int i=0; i<movieSessions.size(); i++) {
			assertEquals(movieSessions.get(i).getMovie().getName(), arr[i]);
		}
	}
	
	@Test
	void testProfitMax3() throws ExNoMovieToSchedule, ExInvalidSeatingPlan {
		Database db = Database.getInstance();
		db.setOpeningHours("10:00");
        db.setClosingHours("24:00");
        List<House> houses = new ArrayList<>(5);
        houses.add(new House(1, 20, 20));
        houses.add(new House(2, 12, 15));
        houses.add(new House(3, 10, 11));
        houses.add(new House(4, 8, 10));
        houses.add(new House(5, 6, 10));
        db.setHouses(houses);
        
        
		List<Movie> movies = new ArrayList<>();
		movies.add(new Movie("m3", "a", 120, 70, 70, "III", "chinese", "chinese"));
		movies.add(new Movie("m5", "a", 60, 50, 50, "III", "chinese", "chinese"));
		movies.add(new Movie("top2", "a", 120, 90, 90, "III", "chinese", "chinese"));
		movies.add(new Movie("top1", "a", 110, 100, 100, "III", "chinese", "chinese"));
		movies.add(new Movie("m4", "a", 130, 60, 60, "III", "chinese", "chinese"));
		
		Schedule s = new ProfitMaximizationSchedule(movies);
		s.scheduling();
		String[] arr = {"top1", "top1", "top1", "top1", "top1", "top1", "top2", "top2", "top2", "top2", "top2", "top2",
				        "m3", "m4", "m5", "m3", "m4", "m5", "m3", "m4", "m5", "m3", "m4", "m5", "m3", "m4", "m5", "m3", "m4", "m5", "m3", "m4", "m5"};
		
		List<MovieSession> movieSessions = s.getScheduleSessions();
		for(int i=0; i<movieSessions.size(); i++) {
			assertEquals(movieSessions.get(i).getMovie().getName(), arr[i]);
		}
	}
	
	@Test
	void testBalanced2() throws ExNoMovieToSchedule, ExInvalidSeatingPlan {
		Database db = Database.getInstance();
		db.setOpeningHours("10:00");
        db.setClosingHours("24:00");
        List<House> houses = new ArrayList<>(5);
        houses.add(new House(1, 20, 20));
        houses.add(new House(2, 12, 15));
        houses.add(new House(3, 10, 11));
        houses.add(new House(4, 8, 10));
        houses.add(new House(5, 6, 10));
        db.setHouses(houses);
        
        
		List<Movie> movies = new ArrayList<>();
		movies.add(new Movie("m3", "a", 120, 70, 70, "III", "chinese", "chinese"));
		movies.add(new Movie("m5", "a", 60, 50, 50, "III", "chinese", "chinese"));
		movies.add(new Movie("top2", "a", 120, 90, 90, "III", "chinese", "chinese"));
		movies.add(new Movie("top1", "a", 110, 100, 100, "III", "chinese", "chinese"));
		movies.add(new Movie("m4", "a", 130, 60, 60, "III", "chinese", "chinese"));
		
		Schedule s = new BalancedSchedule(movies);
		s.scheduling();
		String[] arr = {"m3", "m5", "top2", "top1", "m4", "top1", "top2", "m3", "m4", "m5", "top1", "top2",
				        "m3", "m4", "m5", "top1", "top2", "m3", "m4", "m5", "top1", "top2", "m3", "m4", "m5", "top1", "top2", "m3", "m4", "m5", "top1", "top2", "m5", "m5", "m5"};
		
		List<MovieSession> movieSessions = s.getScheduleSessions();
		for(int i=0; i<movieSessions.size(); i++) {
			assertEquals(movieSessions.get(i).getMovie().getName(), arr[i]);
		}
	}
	
	@Test
	void testProfitMax4() throws ExNoMovieToSchedule, ExInvalidSeatingPlan {
		Database db = Database.getInstance();
		db.setOpeningHours("10:00");
        db.setClosingHours("24:00");
        List<House> houses = new ArrayList<>(5);
        houses.add(new House(1, 20, 20));
        houses.add(new House(2, 12, 15));
        houses.add(new House(3, 10, 11));
        houses.add(new House(4, 8, 10));
        houses.add(new House(5, 6, 10));
        db.setHouses(houses);
        
        
		List<Movie> movies = new ArrayList<>();
		movies.add(new Movie("top1", "a", 360, 100, 100, "III", "chinese", "chinese"));
		movies.add(new Movie("m4", "a", 95, 60, 60, "III", "chinese", "chinese"));
		movies.add(new Movie("m5", "a", 125, 50, 50, "III", "chinese", "chinese"));
		movies.add(new Movie("top2", "a", 360, 90, 90, "III", "chinese", "chinese"));
		movies.add(new Movie("m3", "a", 135, 70, 70, "III", "chinese", "chinese"));
		
		
		Schedule s = new ProfitMaximizationSchedule(movies);
		s.scheduling();
		String[] arr = {"top1", "top1", "top2", "top2", "m3", "m4", "m5", "m3", "m4", "m5", "m3", "m4", "m5", "m3", "m4", "m5", "m3", "m4", "m5", "m3", "m4", "m5"};
		
		List<MovieSession> movieSessions = s.getScheduleSessions();
		for(int i=0; i<movieSessions.size(); i++) {
			assertEquals(movieSessions.get(i).getMovie().getName(), arr[i]);
		}
	}
	
	@Test
	void testBalanced3() throws ExNoMovieToSchedule, ExInvalidSeatingPlan {
		Database db = Database.getInstance();
		db.setOpeningHours("10:00");
        db.setClosingHours("24:00");
        List<House> houses = new ArrayList<>(5);
        houses.add(new House(1, 20, 20));
        houses.add(new House(2, 12, 15));
        houses.add(new House(3, 10, 11));
        houses.add(new House(4, 8, 10));
        houses.add(new House(5, 6, 10));
        db.setHouses(houses);
        
        
		List<Movie> movies = new ArrayList<>();
		movies.add(new Movie("top1", "a", 360, 100, 100, "III", "chinese", "chinese"));
		movies.add(new Movie("m4", "a", 95, 60, 60, "III", "chinese", "chinese"));
		movies.add(new Movie("m5", "a", 125, 50, 50, "III", "chinese", "chinese"));
		movies.add(new Movie("top2", "a", 360, 90, 90, "III", "chinese", "chinese"));
		movies.add(new Movie("m3", "a", 135, 70, 70, "III", "chinese", "chinese"));
		
		Schedule s = new BalancedSchedule(movies);
		s.scheduling();
		String[] arr = {"top1", "m4", "m5", "top2", "m3", "top1", "top2", "m3", "m4", "m5", "top1", "top2", "m3", "m4", "m5", "m3", "m4", "m5", "m4"};
		
		List<MovieSession> movieSessions = s.getScheduleSessions();
		for(int i=0; i<movieSessions.size(); i++) {
			assertEquals(movieSessions.get(i).getMovie().getName(), arr[i]);
		}
	}
	
	@Test
	void testProfitMax5() throws ExNoMovieToSchedule, ExInvalidSeatingPlan {
		Database db = Database.getInstance();
		db.setOpeningHours("10:00");
        db.setClosingHours("24:00");
        List<House> houses = new ArrayList<>(5);
        houses.add(new House(1, 20, 20));
        houses.add(new House(2, 12, 15));
        houses.add(new House(3, 10, 11));
        houses.add(new House(4, 8, 10));
        houses.add(new House(5, 6, 10));
        db.setHouses(houses);
        
        
		List<Movie> movies = new ArrayList<>();
		
		movies.add(new Movie("top1", "a", 1440, 100, 100, "III", "chinese", "chinese"));
		movies.add(new Movie("top2", "a", 1440, 90, 90, "III", "chinese", "chinese"));
		movies.add(new Movie("m3", "a", 1440, 70, 70, "III", "chinese", "chinese"));
		movies.add(new Movie("m4", "a", 1440, 60, 60, "III", "chinese", "chinese"));
		movies.add(new Movie("m5", "a", 1440, 50, 50, "III", "chinese", "chinese"));
		
		Schedule s = new ProfitMaximizationSchedule(movies);
		s.scheduling();
		String[] arr = {"top1", "top2", "m3", "m4", "m5"};
		
		List<MovieSession> movieSessions = s.getScheduleSessions();
		for(int i=0; i<movieSessions.size(); i++) {
			assertEquals(movieSessions.get(i).getMovie().getName(), arr[i]);
		}
	}
	
	@Test
	void testBalanced4() throws ExNoMovieToSchedule, ExInvalidSeatingPlan {
		Database db = Database.getInstance();
		db.setOpeningHours("10:00");
        db.setClosingHours("24:00");
        List<House> houses = new ArrayList<>(5);
        houses.add(new House(1, 20, 20));
        houses.add(new House(2, 12, 15));
        houses.add(new House(3, 10, 11));
        houses.add(new House(4, 8, 10));
        houses.add(new House(5, 6, 10));
        db.setHouses(houses);
        
        
		List<Movie> movies = new ArrayList<>();
		
		movies.add(new Movie("m4", "a", 1440, 60, 60, "III", "chinese", "chinese"));
		movies.add(new Movie("top1", "a", 1440, 100, 100, "III", "chinese", "chinese"));
		movies.add(new Movie("m3", "a", 1440, 70, 70, "III", "chinese", "chinese"));
		movies.add(new Movie("m5", "a", 1440, 50, 50, "III", "chinese", "chinese"));
		movies.add(new Movie("top2", "a", 1440, 90, 90, "III", "chinese", "chinese"));
		
		Schedule s = new BalancedSchedule(movies);
		s.scheduling();
		String[] arr = {"m4", "top1", "m3", "m5", "top2"};
		
		List<MovieSession> movieSessions = s.getScheduleSessions();
		for(int i=0; i<movieSessions.size(); i++) {
			assertEquals(movieSessions.get(i).getMovie().getName(), arr[i]);
		}
	}
	
	@Test
	void testNoProfitMax() throws ExNoMovieToSchedule, ExInvalidSeatingPlan {
		Database db = Database.getInstance();
		db.setOpeningHours("10:00");
        db.setClosingHours("24:00");
        List<House> houses = new ArrayList<>(5);
        houses.add(new House(1, 20, 20));
        houses.add(new House(2, 12, 15));
        houses.add(new House(3, 10, 11));
        houses.add(new House(4, 8, 10));
        houses.add(new House(5, 6, 10));
        db.setHouses(houses);
        
        
		List<Movie> movies = new ArrayList<>();
		
		movies.add(new Movie("top1", "a", 1445, 100, 100, "III", "chinese", "chinese"));
		movies.add(new Movie("top2", "a", 1445, 90, 90, "III", "chinese", "chinese"));
		movies.add(new Movie("m3", "a", 1445, 70, 70, "III", "chinese", "chinese"));
		movies.add(new Movie("m4", "a", 1445, 60, 60, "III", "chinese", "chinese"));
		movies.add(new Movie("m5", "a", 1445, 50, 50, "III", "chinese", "chinese"));
		
		Schedule s = new ProfitMaximizationSchedule(movies);
		s.scheduling();
	
		List<MovieSession> movieSessions = s.getScheduleSessions();
		int x = movieSessions.size();
		assertEquals(x, 0);
	}
	
	@Test
	void testNoBalanced() throws ExNoMovieToSchedule, ExInvalidSeatingPlan {
		Database db = Database.getInstance();
		db.setOpeningHours("10:00");
        db.setClosingHours("24:00");
        List<House> houses = new ArrayList<>(5);
        houses.add(new House(1, 20, 20));
        houses.add(new House(2, 12, 15));
        houses.add(new House(3, 10, 11));
        houses.add(new House(4, 8, 10));
        houses.add(new House(5, 6, 10));
        db.setHouses(houses);
        
        
		List<Movie> movies = new ArrayList<>();
		
		movies.add(new Movie("m4", "a", 1445, 60, 60, "III", "chinese", "chinese"));
		movies.add(new Movie("top1", "a", 1445, 100, 100, "III", "chinese", "chinese"));
		movies.add(new Movie("m3", "a", 1445, 70, 70, "III", "chinese", "chinese"));
		movies.add(new Movie("m5", "a", 1445, 50, 50, "III", "chinese", "chinese"));
		movies.add(new Movie("top2", "a", 1445, 90, 90, "III", "chinese", "chinese"));
		
		Schedule s = new BalancedSchedule(movies);
		s.scheduling();
		
		List<MovieSession> movieSessions = s.getScheduleSessions();	
		int x = movieSessions.size();
		
		assertEquals(x, 0);
	}
	
	
	

}
