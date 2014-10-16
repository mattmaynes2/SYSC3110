package addr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import javax.swing.DefaultListModel;

public class AddressBook extends DefaultListModel<Person>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
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
					this.addElement(p);
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
		for(int i = 0; i < this.size(); i++){
			s += this.get(i).serialize() + "\n";
		}
		return s;
	}
	
}
