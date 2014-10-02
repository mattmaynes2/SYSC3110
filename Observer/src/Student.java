/** SYSC 2101 - Prof-Student-TA Example
 * 
 *
 */

import java.util.Date;
import java.util.Observer;
import java.util.Observable;

public class Student implements Observer{
	private String name;
	private Date midterm;

	public Student(String aName) {
		this.name = aName;
	}

	public String getName() {
		return this.name;
	}

	public void study(Date date){
		this.midterm = date;
		System.out.println(name + " : Doh! I have to study hard for my midterm on " + this.midterm);
	}
	
	public void party(Date date){
		this.midterm = date;
		System.out.println(name + " : Alright! I get to party since my midterm isn't until " + this.midterm);
	}
	
	public void update(Observable obs, Object obj){
		if(obj instanceof Midterm){
			if(((Midterm)obj).getStatus() == 0){
				this.study(((Midterm)obj).getDate());
			}else{
				this.party(((Midterm)obj).getDate());
			}
		}
	}
	
}