package test;

import static org.junit.Assert.*;

import mov.MovieList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;


public class TestEmptyMovieList{
	private MovieList movieList = null;
	
	@Before
	public void setUp()
	{
		movieList = new MovieList();
	}
	
	@Test
	public void testEmptyListSize()
	{
		assertEquals("Size of empty movie list should be 0.", 0, movieList.size());
	}
	
	public static void main(String[] args) 
	{
		JUnitCore.runClasses(TestEmptyMovieList.class);
	}

}
