package test.min.swp;

import static org.junit.Assert.*;
import min.swp.Minesweeper;

import org.junit.Before;
import org.junit.Test;

public class TestMinesweeper {

	private int bombs;
	private int rows;
	private int columns;
	private Minesweeper model;
	
	@Before
	public void setUp(){
		this.rows = 10;
		this.columns = 10;
		this.bombs = 5;
		model = new Minesweeper(this.rows, this.columns, this.bombs);
	}
	
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
