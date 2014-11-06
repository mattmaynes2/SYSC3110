package addr;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.DefaultListModel;

public class AddressBook extends DefaultListModel<BuddyInfo>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	// This is the import 
	public void readFile(String file){
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			while(line != null){
				BuddyInfo buddy = BuddyInfo.create(line);
				if(buddy != null)
					this.addElement(buddy);
	
				line = br.readLine();
			}
			br.close();
		} catch(FileNotFoundException e){
			System.out.println("The given file was not found: " + file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	
	public void export(String path){
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter("addresses.csv"));
			bw.write(this.serialize());
			bw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
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
