package gui;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ABMenuBar extends JMenuBar{
	
	private static final long serialVersionUID = 1L;
	
	private JMenu fileMenu;
	private JMenu editMenu;
	
	private JMenuItem saveItem;
	private JMenuItem loadItem;
	
	private JMenuItem addItem;
	private JMenuItem removeItem;

	
	public ABMenuBar(){
		super();
		add(this.fileMenu());
		add(this.editMenu());
	}
	
	public JMenu fileMenu(){
		this.fileMenu = new JMenu("File");
		this.saveItem = new JMenuItem("Save");
		this.loadItem = new JMenuItem("Load");
		fileMenu.add(this.saveItem);
		fileMenu.add(this.loadItem);
		return this.fileMenu;
	}
	
	
	public JMenu editMenu(){
		this.editMenu = new JMenu("Edit");
		this.addItem = new JMenuItem("Add Buddy");
		this.removeItem = new JMenuItem("Remove Buddy");
		editMenu.add(this.addItem);
		editMenu.add(this.removeItem);
		return this.editMenu;
		
	}
	
	
	public void addSaveListener(ActionListener li){
		this.saveItem.addActionListener(li);
	}
	
	public void addLoadListener(ActionListener li){
		this.loadItem.addActionListener(li);
	}
	 
	public void addAddListener(ActionListener li){
		this.addItem.addActionListener(li);
	}
	
	public void addRemoveListener(ActionListener li){
		this.removeItem.addActionListener(li);
	}
}
