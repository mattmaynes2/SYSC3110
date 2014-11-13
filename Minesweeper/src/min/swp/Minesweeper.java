package min.swp;

import java.util.Observable;
import java.util.Random;

/**
 * This is the model class for Minesweeper. It keeps track of the bombs and user entries
 * in the game. This model implements Observable and can be listened to for state changes.
 * When there is a notify event, a SweeperEvent object is sent to describe the changes 
 * to the model
 * 
 * @author Mathew Maynes
 * @since November 13, 2014
 * @version 1.2
 */
public class Minesweeper extends Observable{
	
	/**
	 * The number of columns in this game
	 */
	private int columns;
	
	/**
	 * The number of rows in this game
	 */
	private int rows;
	
	/**
	 * The game board and the location of all bombs for this game
	 */
	private boolean[][] board;
	
	/**
	 * The user entries for the game. This is simply where the user has clicked
	 */
	private boolean[][] hits;
	
	/**
	 * The number of entries for the user for this game. If the number of entries
	 * is equal to the number of cells - the number of bombs then the user wins
	 */
	private int hitCount;
	
	/**
	 * The number of bombs on the board
	 */
	private int bombs;

	/**
	 * Constructs a Minesweeper game with the given number of bombs in random
	 * locations on the board. The board's size is determined by the given rows
	 * and columns 
	 * 
	 * @param rows The number of rows the board should have
	 * @param columns The number of columns the board should have
	 * @param bombs The number of bombs to randomly place on the board
	 */
	public Minesweeper(int rows, int columns, int bombs){
		this.rows = rows;
		this.columns = columns;
		this.board = new boolean[rows][columns];
		this.hits = new boolean[rows][columns];
		this.hitCount = 0;
		this.bombs = bombs;
		generateBombs();
	}
	
	/**
	 * Constructs a Minesweeper game with the given size in rows and columns
	 * 
	 * @param rows The number of rows the board should have
	 * @param columns The number of columns the board should have
	 */
	public Minesweeper(int rows, int columns){
		this(rows, columns, 1);
	}
	
	/**
	 * Constructs a default Minesweeper game with a size of 3x3
	 */
	public Minesweeper(){
		this(3, 3); 
	}
	
	/**
	 * Trys to place a hit at the given location. If the location is already
	 * occupied then the hit is not placed and false is returned. If the cell 
	 * is free then the hit is placed and the model is updated and updates all 
	 * of its listeners
	 * 
	 * @param row The row to try and hit
	 * @param column The column to try and hit
	 * 
	 * @return If the cell was empty and the hit could be placed or not
	 */
	public boolean tryHit(int row, int column){
		if(this.hits[row][column])
			return false;
		
		this.hits[row][column] = true;
		this.hitCount++;
		updateListeners(row, column);
		return true;
	}
	
	/**
	 * Returns if the given cell in the model is a bomb
	 * 
	 * @param row The row to check
	 * @param column The column to check
	 * 
	 * @return If the given cell is a bomb
	 */
	public boolean isBomb(int row, int column){
		return this.board[row][column];
	}
	
	/**
	 * Counts the number of bombs that are adjacent to the given cell. 
	 * 
	 * @param row The row of the cell to check
	 * @param column The column of the cell to check
	 * 
	 * @return The number of bombs that are adjacent to it
	 */
	public int countAdjacentBombs(int row, int column){
			int bombs = 0;
			for(int i = row - 1; i <= row + 1; i++){
				for(int j = column - 1; j <= column + 1; j++){
					if(i >= 0 && i < this.rows && j >= 0 && j < this.columns){
						bombs += board[i][j] ? 1 : 0;
					}
				}
				
			}
			return bombs;
	}
	
	/**
	 * Returns if the given cell has already been hit
	 * 
	 * @param row The row of the cell to check
	 * @param column The column of the cell to check
	 * 
	 * @return If the user has already hit this cell
	 */
	public boolean hasHit(int row, int column){
		return this.hits[row][column];
	}
	
	/**
	 * Returns the number of rows in this board model
	 * 
	 * @return The number of rows in this model
	 */
	public int getRows(){
		return this.rows;
	}
	
	/**
	 * Returns the number of columns in this board model
	 * 
	 * @return The number of columns in this model
	 */
	public int getColumns(){
		return this.columns;
	}
	
	/**
	 * Resets this model back to its initial state. This can be used
	 * to reset the game if the user wants to play again.
	 */
	public void reset(){
		this.hitCount = 0;
		this.hits = new boolean[this.rows][this.columns];
		this.board = new boolean[this.rows][this.columns];
		generateBombs();
	}
	
	/**
	 * Creates a sweeper event using the given row and column and then notifies
	 * all listeners of the change
	 * 
	 * @param row The row of the cell that has changed
	 * @param column The column of the cell that has changed
	 */
	private void updateListeners(int row, int column){
		SweeperEvent event = new SweeperEvent(row, column);
		event.setBomb(this.isBomb(row, column));
		event.setSurroundings(this.countAdjacentBombs(row, column));
		event.setGameOver(event.isBomb() || hitCount == this.rows * this.columns - 1);
		this.setChanged();
		this.notifyObservers(event);
	}
	
	/**
	 * Randomly generates all of the bombs on this board.
	 */
	private void generateBombs(){
		// Generate bombs
		Random rand = new Random();
		int r, c;
		for(int i = 0; i < bombs; i++){
			r = rand.nextInt(this.rows);
			c = rand.nextInt(this.columns);
			if(!this.board[r][c]){
				this.board[r][c] = true;
			}
			else{
				i--;
			}
		}
	}
	
}
