package gui;

import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import addr.AddressBook;
import addr.BuddyInfo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ABFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private JList<BuddyInfo> contactList;
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
		this.contactPanel.getAddButton().setEnabled(false);
		this.contactList.setModel(addressBook);
	}

	private void setup(){
		JScrollPane sp;
		this.menuBar = new ABMenuBar();
		this.contactList = new JList<BuddyInfo>();
		this.contactPanel = new ABContactPanel();
		
		sp = new JScrollPane(this.contactList);
		sp.setPreferredSize(new Dimension(150, 200));
		this.contactPanel.setPreferredSize(new Dimension(250, 200));
		this.add(sp, BorderLayout.WEST);
		this.add(this.contactPanel, BorderLayout.CENTER);
		
		this.contactPanel.getAddButton().addActionListener(this.addButtonListener());
		this.menuBar.addSaveListener(this.saveListener());
		this.menuBar.addLoadListener(this.loadListener());
		this.menuBar.addAddListener(this.addListener());
		this.menuBar.addRemoveListener(this.removeListener());
		this.contactList.addListSelectionListener(this.listListener());
		setJMenuBar(this.menuBar);
	}

	
	public JList<BuddyInfo> getContactList(){
		return this.contactList;
	}
	
	public ActionListener addButtonListener(){
		return new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(((AbstractButton) e.getSource()).getText().equals("Add")){
					BuddyInfo p = new BuddyInfo(contactPanel.getNameField().getText());
					p.setAddress(contactPanel.getAddressField().getText());
					p.setPhoneNumber(contactPanel.getPhoneField().getText());
					addressBook.addElement(p);
					contactList.updateUI();
					contactPanel.getNameField().setText("");
					contactPanel.getAddressField().setText("");
					contactPanel.getPhoneField().setText("");
					contactPanel.getAddButton().setEnabled(false);
				}
				else{
					((AddressBook)contactList.getModel()).remove(contactList.getSelectedIndex());
					contactPanel.getAddButton().setEnabled(false);
				}
			}			
			
		};
	}
	
	public ActionListener saveListener(){
		return new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//addressBook.export("address.csv");
				addressBook.writeObject("addressbook.dat");
			}
		};
	}
	
	public ActionListener addListener(){
		return new ActionListener(){
			public void actionPerformed(ActionEvent e){
				contactPanel.getNameField().setText("");
				contactPanel.getAddressField().setText("");
				contactPanel.getPhoneField().setText("");
				contactPanel.getAddButton().setText("Add");
				contactPanel.getAddButton().setEnabled(true);
			}
		};
	}

	public ActionListener removeListener(){
		return new ActionListener(){
			public void actionPerformed(ActionEvent e){
				contactPanel.getAddButton().setText("Remove");
				contactPanel.getAddButton().setEnabled(true);
			}
		};
	}
	
	public ActionListener loadListener(){
		return new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//AddressBook newBook = new AddressBook();
				//newBook.readFile("addresses.csv");
				AddressBook newBook = AddressBook.readObject("addressbook.dat");
				contactList.setModel(newBook);
				contactList.updateUI();
				addressBook = newBook;
		
			}
		};
	}
	
	public ListSelectionListener listListener(){
		return new ListSelectionListener(){

			public void valueChanged(ListSelectionEvent e) {
				int index = contactList.getSelectedIndex();
				if(index >= 0 && index < addressBook.size()){
					BuddyInfo p = addressBook.get(index);
					contactPanel.getNameField().setText(p.getName());
					contactPanel.getAddressField().setText(p.getAddress());
					contactPanel.getPhoneField().setText(p.getPhoneNumber());
				}
			}
			
		};
	
	}


}
