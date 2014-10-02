/** SYSC 2101 - Prof-Student-TA Example
 * 
 *
 */

import java.util.ArrayList;
import java.util.Date;

public class Prof {
	private String name;
	private Date midtermDate;
	private ArrayList<CourseListener> listeners;

	public Prof(String aName) {
		this.name = aName;
		this.listeners = new ArrayList<CourseListener>();
	}

	public Date getMidterm() {
		return this.midtermDate;
	}

	public String getName() {
		return this.name;
	}

	public void setMidterm(Date date) {
		CourseEvent event = new CourseEvent(this);
		event.setMidtermDate(date);
		for(CourseListener c: this.listeners){
			c.midtermAnnounced(event);
		}
	}
	
	public void postponeMidterm(Date date){
		CourseEvent event = new CourseEvent(this);
		event.setMidtermDate(date);
		for(CourseListener c: this.listeners){
			c.midtermPostponed(event);
		}
	}

	public void addCourseListener(CourseListener c){
		this.listeners.add(c);
	}
	
	public void removeCourseListener(CourseListener c){
		this.listeners.remove(c);
	}


	public static void main(String[] args) {

		Prof p = new Prof("Babak");
		Student s = new Student("Homer");
		Student s2 = new Student("Bart");
		TeachingAssistant ta = new TeachingAssistant("Michael");
	
	
		p.addCourseListener(s);
		p.addCourseListener(s2);
		p.addCourseListener(ta);
	
		Date midterm = new Date();
		p.setMidterm(midterm);
	
		p.postponeMidterm(new Date(midterm.getTime() + 1000000000));
	}

}