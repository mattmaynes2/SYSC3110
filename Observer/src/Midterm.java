import java.util.Date;

public class Midterm {

	private Date date;
	private int status;
	
	public Midterm(Date date){
		this(date, 0);
	}
	public Midterm(Date date, int status){
		this.date = date;
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public int getStatus() {
		return status;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
