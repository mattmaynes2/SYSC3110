package min.swp;

/**
 * A SweeperEvent stores all of the information about this models latest update
 * 
 * @author Matthew Maynes
 * @since November 13, 2014
 * @version 1.0
 */
public class SweeperEvent {
	
	/**
	 * The row that is being updated in this event
	 */
	private int row;
	
	/**
	 * The column that is being updated in this event
	 */
	private int column;
	
	/**
	 * A value that represents the count of adjacent bombs to this cell
	 */
	private int surroundings;
	
	/**
	 * If this cell is a bomb
	 */
	private boolean isBomb;
	
	/**
	 * If this event ends the game
	 */
	private boolean gameOver;
	
	/**
	 * Constructs a sweeper event for the cell at the given row and column
	 * 
	 * @param row The row of the cell this event represents
	 * @param column The column of the cell this event represents
	 */
	public SweeperEvent(int row, int column){
		this(row, column, 0, false);
	}
	
	
	/**
	 * Constructs a sweeper event for the cell at the given row and column. It
	 * also adds the number of adjacent bombs surrounding this cell and if this 
	 * cell is a bomb 
	 *
	 * @param row The row of the cell this event represents
	 * @param column The column of the cell this event represents
	 * @param surroundings The number of adjacent bombs surrounding this cell
	 * @param isBomb If this cell is a bomb
	 */
	public SweeperEvent(int row, int column, int surroundings, boolean isBomb){
		this.row = row;
		this.column = column;
		this.surroundings = surroundings;
		this.isBomb = isBomb;
	}

	/**
	 * Returns the row of the cell being updated in this event
	 * 
	 * @return The row of this events cell
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Returns the column of the cell being updated in this event
	 * 
	 * @return The column of this events cell
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Returns the number of adjacent bombs surrounding this cell
	 * 
	 * @return The number of bombs surrounding this cell
	 */
	public int getSurroundings() {
		return surroundings;
	}

	/**
	 * Returns if this cell is a bomb
	 * 
	 * @return If this cell is a bomb
	 */
	public boolean isBomb() {
		return isBomb;
	}

	/**
	 * Returns if the last turn ended the game
	 * 
	 * @return If the game is over
	 */
	public boolean isGameOver(){
		return gameOver;
	}
	
	/**
	 * Sets the row for this event
	 * 
	 * @param row The row that this event is updating
	 */
	public void setRow(int row) {
		this.row = row;
	}
	
	/**
	 * Sets the column for this event
	 * 
	 * @param row The column that this event is updating
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * Sets the number of adjacent bombs that surround this cell
	 * 
	 * @param surroundings The number of adjacent bombs
	 */
	public void setSurroundings(int surroundings) {
		this.surroundings = surroundings;
	}

	/**
	 * Sets if this cell is a bomb
	 * 
	 * @param isBomb If this is a bomb
	 */
	public void setBomb(boolean isBomb) {
		this.isBomb = isBomb;
	}
	
	/**
	 * Sets the status of the game to be over
	 * 
	 * @param gameOver If the game is over or not
	 */
	public void setGameOver(boolean gameOver){
		this.gameOver = gameOver;
	}
	
	
	
	
	
	

}
