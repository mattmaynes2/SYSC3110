package test;

import org.junit.Before;
import org.junit.Test;

import addr.AddressBook;
import addr.Person;
import static org.junit.Assert.*;

public class TestAddressBook {

	private Person person1;
	private AddressBook book;
	
	@Before
	public void setUp(){
		person1 = new Person("Matt");
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
