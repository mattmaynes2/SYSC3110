import java.util.ArrayList;

public class AddressBook{
	
	private ArrayList<BuddyInfo> buddies;
	
	public AddressBook(){
		this.buddies = new ArrayList<BuddyInfo>();
	}
	
	public void addBuddy(BuddyInfo buddy){
		this.buddies.add(buddy);
	}
	
	public void removeBuddy(BuddyInfo buddy){
		this.buddies.remove(buddy);
	}
	
	public String toString(){
		String result = "";
		for(BuddyInfo buddy : this.buddies){
			result += buddy.toString() + '\n';
		}
		return result;
	}
	
	public static void main(String [] args){
		BuddyInfo mike = new BuddyInfo("Mike");
		BuddyInfo tom = new BuddyInfo("Tom");
		AddressBook addr = new AddressBook();
		addr.addBuddy(mike);
		addr.addBuddy(tom);
		System.out.println(addr);
		
	}
	
	
}
