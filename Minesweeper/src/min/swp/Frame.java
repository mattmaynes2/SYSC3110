package min.swp;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * The Frame is the view for this game. It displays to the user the underlying 
 * Minesweeper model that it represents. The panel also contains a grid of buttons
 * that listen for user input and notify the model of user updates.
 * 
 * @author Matthew Maynes
 * @since November 13, 2014
 * @version 1.1
 */
public class Frame extends JFrame implements Observer{

	/**
	 * A default generated serial for JFrame serialization
	 */
	private static final long serialVersionUID = -575282668550029480L;
	
	/**
	 * Stores the text that will be displayed when a bomb is clicked
	 */
	private static final String BOMB = "B";
	
	/**
	 * Stores the model for this view. The view listens to this model for state changes
	 */
	private Minesweeper model;
	
	/**
	 * A list of all of the input buttons that the user can click. These references are 
	 * used to reflect the view of the game when the model changes
	 */
	private JButton[][] cells;
	
	/**
	 * Stores the panel that the input buttons are displayed on
	 */
	private JPanel buttonPanel;
	
	/**
	 * Constructs a frame for this game that will be a representation of the given model
	 * 
	 * @param model The model that this view should listen to and update to match when there is a change
	 */
	public Frame(Minesweeper model){
		super("Minesweeper");
		this.model = model;
		this.cells = new JButton[this.model.getRows()][this.model.getColumns()];
		this.buttonPanel = new JPanel();
		this.buttonPanel.setLayout(new GridLayout(this.model.getRows(), this.model.getColumns()));
		
		for(int i = 0 ; i < this.model.getRows(); i++){
			for(int j = 0; j < this.model.getColumns(); j++){				
				JButton button = new JButton();
				button.addActionListener(this.cellListener());
				button.setActionCommand("" + i + "," + j);
				this.cells[i][j] = button;
				buttonPanel.add(button);
			}
		}
			
		this.add(buttonPanel);
		model.addObserver(this);
	}
	

	/**
	 * Updates the view to reflect the latest change in the model. This method
	 * should be called by the model that this view is representing
	 * 
	 * @param sender The sender of the update event
	 * @param e A sweeper object that describes the latest change to the view
	 */
	@Override
	public void update(Observable sender, Object e) {
		if(e instanceof SweeperEvent){
			boolean win = true;
			SweeperEvent event = (SweeperEvent)e;
			JButton button = this.cells[event.getRow()][event.getColumn()];
			
			if(event.isBomb()){
				button.setText(BOMB);
				win = false;
			}
			else{
				button.setText(Integer.toString(event.getSurroundings()));
			}
			
			if(event.isGameOver()){
				gameOver(win);
			}
		}
		
	}
	
	/**
	 * Displays a message to the user when the game is over.
	 * 
	 * @param win If the user has won or not
	 */
	public void gameOver(boolean win){
		JOptionPane.showMessageDialog(this, "You " + (win ? "win!" : "hit a bomb, you loose"), "Game Over", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}
	
	/**
	 * Returns an ActionListener object that controls the model when there is user input
	 * 
	 * @return A controller for the model
	 */
	private ActionListener cellListener(){
		return new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String[] cell = e.getActionCommand().split(",");
				if(cell.length < 2)
					return;
				
				model.tryHit(Integer.parseInt(cell[0]), Integer.parseInt(cell[1]));	
			}
			
		};
	}

}
