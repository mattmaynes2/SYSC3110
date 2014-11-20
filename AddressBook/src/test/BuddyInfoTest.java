package test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import addr.BuddyInfo;

public class BuddyInfoTest {

	private BuddyInfo person1;
	private BuddyInfo person2;
	
	@Before
	public void setUp() throws Exception {
		person1 = new BuddyInfo("Tom");
		person2 = new BuddyInfo("Rob");
	}
	@Test
	public void testName(){
		assertEquals("Tom", person1.getName());
		assertEquals("Rob", person2.getName());
		assertNotEquals("Jim", person1.getName());	
	}
	
	@Test
	public void testAddress(){
		person1.setAddress("123 ABC");
		
		assertEquals("123 ABC", person1.getAddress());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testAge(){
		person1.setBirthDate(new Date(1994, 05, 22));
		assertTrue(person1.isOver18());
	}
	
	@Test
	public void testGreeting(){
		String g = "Hello " + person1.getName();
		assertEquals(g, person1.greeting());
	}	
	

}
