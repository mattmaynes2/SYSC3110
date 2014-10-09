package addr;
import java.util.ArrayList;

public class AddressBook extends ArrayList<Person>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public String toString(){
		String result = "";
		for(Person buddy : this){
			result += buddy.toString() + '\n';
		}
		return result;
	}
	
	
}
