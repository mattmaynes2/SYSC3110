package gui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import addr.AddressBook;
import addr.Person;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ABFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private ABContactList contactList;
	private ABMenuBar menuBar;
	private ABContactPanel contactPanel;
	private AddressBook addressBook;
	
	public ABFrame(){
		this("Address Book");	
	}
	
	public ABFrame(String title){
		super(title);
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(400, 200));
		setLocation(new Point(100, 100));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setDefaultLookAndFeelDecorated(true);
		this.addressBook = new AddressBook();
		setup();
		pack();
		this.contactList.addBook(addressBook);
	}

	private void setup(){
		JScrollPane sp;
		this.menuBar = new ABMenuBar();
		this.contactList = new ABContactList();
		this.contactPanel = new ABContactPanel();
		
		sp = new JScrollPane(this.contactList);
		sp.setPreferredSize(new Dimension(150, 200));
		this.contactPanel.setPreferredSize(new Dimension(250, 200));
		this.add(sp, BorderLayout.WEST);
		this.add(this.contactPanel, BorderLayout.CENTER);
		
		this.contactPanel.getAddButton().addActionListener(this.addButtonListener());
		this.menuBar.addSaveListener(this.saveListener());
		this.menuBar.addLoadListener(this.loadListener());
		this.contactList.addListSelectionListener(this.listListener());
		setJMenuBar(this.menuBar);
	}

	
	public ABContactList getContactList(){
		return this.contactList;
	}
	
	public ActionListener addButtonListener(){
		return new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Person p = new Person(contactPanel.getNameField().getText());
				p.setAddress(contactPanel.getAddressField().getText());
				p.setPhoneNumber(contactPanel.getPhoneField().getText());
				addressBook.add(p);
				contactList.updateList();
				contactPanel.getNameField().setText("");
				contactPanel.getAddressField().setText("");
				contactPanel.getPhoneField().setText("");
			}			
			
		};
	}
	
	public ActionListener saveListener(){
		return new ActionListener(){
			public void actionPerformed(ActionEvent e){
				BufferedWriter bw;
				try {
					bw = new BufferedWriter(new FileWriter("addresses.csv"));
					bw.write(addressBook.serialize());
					bw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		
			}
		};
	}
	
	public ActionListener loadListener(){
		return new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					addressBook.readFile(new FileReader("addresses.csv"));
					contactList.updateList();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
		
			}
		};
	}
	
	public ListSelectionListener listListener(){
		return new ListSelectionListener(){

			public void valueChanged(ListSelectionEvent e) {
				int index = contactList.getSelectedIndex();
				if(index >= 0 && index < addressBook.size()){
					Person p = addressBook.get(index);
					contactPanel.getNameField().setText(p.getName());
					contactPanel.getAddressField().setText(p.getAddress());
					contactPanel.getPhoneField().setText(p.getPhoneNumber());
				}
			}
			
		};
	
	}


}
