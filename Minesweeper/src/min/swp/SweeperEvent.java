package min.swp;

public class SweeperEvent {
	
	private int row;
	private int column;
	
	private int surroundings;
	
	private boolean isBomb;
	
	private boolean gameOver;
	
	public SweeperEvent(int row, int column){
		this(row, column, 0, false);
	}
	
	public SweeperEvent(int row, int column, int surroundings, boolean isBomb){
		this.row = row;
		this.column = column;
		this.surroundings = surroundings;
		this.isBomb = isBomb;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public int getSurroundings() {
		return surroundings;
	}

	public boolean isBomb() {
		return isBomb;
	}

	public boolean isGameOver(){
		return gameOver;
	}
	
	public void setRow(int row) {
		this.row = row;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public void setSurroundings(int surroundings) {
		this.surroundings = surroundings;
	}

	public void setBomb(boolean isBomb) {
		this.isBomb = isBomb;
	}
	
	public void setGameOver(boolean gameOver){
		this.gameOver = gameOver;
	}
	
	
	
	
	
	

}
