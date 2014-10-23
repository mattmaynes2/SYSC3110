package test;

import mov.Movie;
import mov.MovieList;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;


public class TestMovieListWithOneMovie {
	private MovieList movieList = null;
	private Movie starWars = null;
	
	@Before
	public void setUp() 
	{
		starWars = new Movie("Star Wars");
		movieList = new MovieList();
		movieList.add(starWars);
	}
	
	@Test
	public void testSize() {
		assertEquals("Size of one item list should be 1.", 1, movieList.size());
	}
	
	public static void main(String[] args) {
		JUnitCore.runClasses(TestMovieListWithOneMovie.class);
	}

}
