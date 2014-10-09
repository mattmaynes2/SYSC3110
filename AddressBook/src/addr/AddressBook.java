package addr;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

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
	
	
	public void readFile(Reader reader){
		BufferedReader br = new BufferedReader(reader);
		try {
			String line = br.readLine();
			while(line != null){
				String[] el = line.split(",");
				if(el.length < 3){
					continue;
				}
				else{
					Person p = new Person(el[0]);
					p.setAddress(el[1]);
					p.setPhoneNumber(el[2]);
					this.add(p);
				}
				
				line = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

	
	public String serialize(){
		String s = "";
		for(Person p : this){
			s += p.serialize() + "\n";
		}
		return s;
	}
	
}
