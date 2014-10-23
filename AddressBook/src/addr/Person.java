package addr;

import java.util.Calendar;
import java.util.Date;

public class Person {
	
	private String name;
	private String address;
	private String phoneNumber;
	private Date birthDate;
	
	public Person(String name){
		this.name = name;
		this.address = "";
	}
	
	public Person(Person p){
		this.name = new String(p.getName());
		this.address = new String(p.getAddress());
		this.phoneNumber = new String(p.getPhoneNumber());
	}
	
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhoneNumber(){
		return this.phoneNumber;
	}
	
	public void setPhoneNumber(String phone){
		this.phoneNumber = phone;
	}

	public String greeting(){
		return "Hello " + this.name;
	}
	
	@SuppressWarnings("deprecation")
	public boolean isOver18(){
		return this.birthDate.before(new Date(Calendar.getInstance().get(Calendar.YEAR) - 18,  this.birthDate.getMonth(), this.birthDate.getDay()));
	}
	
	public void setBirthDate(Date date){
		this.birthDate = date;
	}
	
	public Date getBirthDate(){
		return this.birthDate;
	}
	
	public String toString(){
		return this.name;
	}
	
	@Override
	public boolean equals(Object object){
		if(object != null && object instanceof Person){
			Person p = (Person)object;
			return p.getName().equals(getName()) && p.getAddress().equals(getAddress()) && p.getBirthDate().equals(getBirthDate());
		}
		return false;
	}
	
	public String serialize(){
		return this.name + ", " + this.address + ", " + this.phoneNumber;
	}
	
	
}
