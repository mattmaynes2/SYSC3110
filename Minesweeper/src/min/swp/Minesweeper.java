package min.swp;

import java.util.Observable;
import java.util.Random;

public class Minesweeper extends Observable{
	
	private int columns;
	
	private int rows;
	
	private boolean[][] board;
	
	private boolean[][] hits;
	
	private int hitCount;
	
	private int bombs;
	
	public Minesweeper(int rows, int columns, int bombs){
		this.rows = rows;
		this.columns = columns;
		this.board = new boolean[rows][columns];
		this.hits = new boolean[rows][columns];
		this.hitCount = 0;
		this.bombs = bombs;
		generateBombs();
	}
	
	public Minesweeper(int rows, int columns){
		this(rows, columns, 1);
	}
	
	public Minesweeper(){
		this(3, 3); 
	}
	
	
	
	public boolean tryHit(int row, int column){
		if(this.hits[row][column])
			return false;
		
		this.hits[row][column] = true;
		this.hitCount++;
		updateListeners(row, column);
		return true;
	}
	

	public boolean isBomb(int row, int column){
		return this.board[row][column];
	}
	
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
	
	public boolean hasHit(int row, int column){
		return this.hits[row][column];
	}
	
	public int getRows(){
		return this.rows;
	}
	
	public int getColumns(){
		return this.columns;
	}
	
	
	private void updateListeners(int row, int column){
		SweeperEvent event = new SweeperEvent(row, column);
		event.setBomb(this.isBomb(row, column));
		event.setSurroundings(this.countAdjacentBombs(row, column));
		event.setGameOver(event.isBomb() || hitCount == this.rows * this.columns - 1);
		this.setChanged();
		this.notifyObservers(event);
	}
	
	
	public void reset(){
		this.hitCount = 0;
		this.hits = new boolean[this.rows][this.columns];
		this.board = new boolean[this.rows][this.columns];
		generateBombs();
	}

	
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
