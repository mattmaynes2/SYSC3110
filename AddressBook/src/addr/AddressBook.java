package addr;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.DefaultListModel;

public class AddressBook extends DefaultListModel<BuddyInfo> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	public static AddressBook readObject(String file){
		try{
			ObjectInputStream oStream = new ObjectInputStream(new FileInputStream(file));
			AddressBook book = (AddressBook)oStream.readObject();
			oStream.close();
			return book;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public void writeObject(String file){
		try{
			ObjectOutputStream oStream = new ObjectOutputStream(new FileOutputStream(file));
			oStream.writeObject(this);
			oStream.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

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
			bw = new BufferedWriter(new FileWriter(path));
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
