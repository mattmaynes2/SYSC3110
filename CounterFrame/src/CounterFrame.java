import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;


public class CounterFrame extends JFrame implements ActionListener, Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3881979761623878350L;
	
	private CounterController counterController;
	private JTextField counterField;
	private JButton incButton;
	
    public CounterFrame(){
    	counterController = new CounterController(10);
    	counterController.addObserver(this);
        counterField = new JTextField("0");
        incButton = new JButton("increment");
        
        this.add(counterField, BorderLayout.CENTER);
        this.add(incButton, BorderLayout.SOUTH); 
        
        incButton.addActionListener(this);
    }
    
    public static void main(String[] args) {
    	CounterFrame cf = new CounterFrame();
    	cf.setSize(200,100);
    	cf.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		counterController.increment();
	}

	@Override
	public void update(Observable obs, Object value) {
		if(value instanceof Integer)
			counterField.setText(value.toString());
		
	}

}
