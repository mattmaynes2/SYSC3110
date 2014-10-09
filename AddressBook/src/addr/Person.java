package addr;

public class Person {
	
	private String name;
	private String address;
	private String phoneNumber;
	
	public Person(String name){
		this.name = name;
		this.address = "";
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
	
	public String toString(){
		return this.name;
	}
	
}
