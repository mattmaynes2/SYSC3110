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
	
	public ABMenuBar(){
		super();
		add(this.fileMenu());
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
		
		return this.editMenu;
		
	}
	
	
	public void addSaveListener(ActionListener li){
		this.saveItem.addActionListener(li);
	}
	
	public void addLoadListener(ActionListener li){
		this.loadItem.addActionListener(li);
	}
	 
}
