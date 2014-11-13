package min.swp;

public class Main {
	
	public static void main(String[] args){
		Minesweeper model = new Minesweeper(3, 3);
		Frame game = new Frame(model);
		game.setSize(600, 600);
		game.setVisible(true);
				
		
	}

}
