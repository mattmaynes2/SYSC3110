package test.min.swp;

import static org.junit.Assert.*;

import min.swp.Minesweeper;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Minesweeper model 
 * 
 * @author Matthew Maynes
 * @since November 13, 2014
 * @version 1.0
 */
public class TestMinesweeper {

	/**
	 * Stores the number of bombs that will be placed on the model to test
	 */
	private int bombs;
	
	/**
	 * Stores the number of rows that will be used in the testing of this model
	 */
	private int rows;
	
	/**
	 * Stores the number of columns that will be used in the testing of this model
	 */
	private int columns;
	
	/**
	 * Stores the model that is being tested
	 */
	private Minesweeper model;
	
	/**
	 * Sets up the model with a 10x10 size and 5 bombs
	 */
	@Before
	public void setUp(){
		this.rows = 10;
		this.columns = 10;
		this.bombs = 5;
		model = new Minesweeper(this.rows, this.columns, this.bombs);
	}
	
	/**
	 * Tests the count function to ensure that it correctly calculates the number of 
	 * adjacent bombs to each cell. This is done by iterating through each cell in the board
	 * and when a bomb is encountered add a value to all of the surrounding cells. The result
	 * of this calculation is then compared to the value produced by the model. 
	 */
	@Test
	public void testSurroundings(){
		int[][] surroundings = new int[this.rows][this.columns];
		for(int i = 0; i < this.rows; i++){
			for(int j = 0; j < this.columns; j++){
				if(model.isBomb(i, j)){
					for(int m = i - 1; m <= i + 1; m++){
						for(int n = j - 1; n <= j + 1; n++){
							if(m >= 0 && m < this.rows && n >= 0 && n < this.columns){
								surroundings[m][n]++;
							}
						}
						
					}
				}
				
			}
		}
		
		for(int i = 0; i < this.rows; i++){
			for(int j = 0; j < this.columns; j++){
				assertEquals(surroundings[i][j], model.countAdjacentBombs(i, j));
			}
		}
		
	}
	
	/**
	 * Tests that there are the correct number of bombs generated on the board
	 */
	@Test
	public void testBombs(){
		int bombs = 0;
		for(int i = 0; i < this.rows; i++){
			for(int j = 0; j < this.columns; j++){
				bombs += model.isBomb(i, j) ? 1 : 0;
			}
		}
		assertEquals(this.bombs, bombs);
	}
	
	/**
	 * Tests that when a user hits a cell the model registers the hit and is updated
	 */
	@Test
	public void testHit(){
		for(int i = 0; i < this.rows; i++){
			for(int j = 0; j < this.columns; j++){
				model.tryHit(i, j);
				assertTrue(model.hasHit(i, j));
			}
		}
		model.reset();
	}
	
	/**
	 * Tests that when the model is reset it has no hits
	 */
	@Test
	public void testReset(){
		for(int i = 0; i < this.rows; i++){
			for(int j = 0; j < this.columns; j++){
				model.tryHit(i, j);
			}
		}
		model.reset();
		for(int i = 0; i < this.rows; i++){
			for(int j = 0; j < this.columns; j++){
				assertFalse(model.hasHit(i, j));
			}
		}
	}
	

}
