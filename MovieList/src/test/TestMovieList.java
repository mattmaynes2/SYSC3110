package test;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.JUnit4TestAdapter;



import mov.MovieList;

public class TestMovieList{
	
	@Test
	public void testEmptyListSize() {
		MovieList emptyList = new MovieList();
		assertEquals("Size of empty movie list should be 0.", 0, emptyList.size()); 
	}

	public static void main(String [] args){
		//JUnitCore.runClasses(TestMovieList.class);
		new JUnit4TestAdapter(TestMovieList.class);
	}
}

