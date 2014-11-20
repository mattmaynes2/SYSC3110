package test;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import addr.AddressBook;
import addr.BuddyInfo;
import static org.junit.Assert.*;

public class SerializableAddressBookTest {

	private BuddyInfo person1;
	private AddressBook book;
	
	@Before
	public void setUp(){
		person1 = new BuddyInfo("Matt");
		book = new AddressBook();
	}
	
	@Test
	public void testAdd(){
		book.addElement(person1);
		assertEquals(1, book.size());
	}
	
	
	@Test
	public void testClear(){
		book.addElement(person1);
		book.clear();
		assertEquals(0, book.size());
	}
	
	@Test
	public void testSave(){
		book.writeObject("test.csv");
		assertTrue(new File("test.csv").exists());
		new File("test.csv").delete();
	}
	
	@Test
	public void testLoad(){
		book.writeObject("test.csv");
		AddressBook newBook = AddressBook.readObject("test.csv");
		
		assertEquals(newBook.size(), book.size());
		for(int i = 0; i < book.size(); i++){
			assertEquals(newBook.get(i), book.get(i));
		}
		new File("test.csv").delete();
	}
}
