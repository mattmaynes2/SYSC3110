package test;

import org.junit.Before;
import org.junit.Test;

import addr.AddressBook;
import addr.BuddyInfo;
import static org.junit.Assert.*;

public class AddressBookTest {

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
	
	
}
