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

public class Frame extends JFrame implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -575282668550029480L;
	
	private int rows;
	private int columns;
	private Minesweeper model;
	private JButton[][] cells;
	private JPanel buttonPanel;
	
	
	public Frame(Minesweeper model){
		super("Minesweeper");
		this.model = model;
		this.rows = model.getRows();
		this.columns = model.getColumns();
		this.cells = new JButton[this.rows][this.columns];
		this.buttonPanel = new JPanel();
		this.buttonPanel.setLayout(new GridLayout(this.rows, this.columns));
		
		for(int i = 0 ; i < this.rows; i++){
			for(int j = 0; j < this.columns; j++){				
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


	@Override
	public void update(Observable sender, Object e) {
		if(e instanceof SweeperEvent){
			boolean win = true;
			SweeperEvent event = (SweeperEvent)e;
			JButton button = this.cells[event.getRow()][event.getColumn()];
			if(event.isBomb()){
				button.setText("B");
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
	
	
	public void gameOver(boolean win){
		JOptionPane.showMessageDialog(this, "You " + (win ? "win!" : "hit a bomb, you loose"), "Game Over", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}
	
	
}
