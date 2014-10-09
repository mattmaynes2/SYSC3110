package gui;

import addr.AddressBook;
import addr.Person;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class ABContactList extends JList<String>{

	private ArrayList<AddressBook> books;
	
	private static final long serialVersionUID = 1L;

	public ABContactList(){
		super(new DefaultListModel<String>());
		this.books = new ArrayList<AddressBook>();
	}
	
	public void addBook(AddressBook book){		
		DefaultListModel<String> model = new  DefaultListModel<String>();
		this.books.add(book);
		for(Person p : book){
			model.addElement(p.toString());
		}
		setModel(model);
	}
	
	public void updateList(){
		DefaultListModel<String> model = new  DefaultListModel<String>();
		for(AddressBook book : this.books){
			for(Person p : book){
				model.addElement(p.toString());
			}
		}
		setModel(model);
	}
	

}
