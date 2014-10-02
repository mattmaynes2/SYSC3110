/**
 * A test class for XMLBuilder
 * 
 * @author Matthew Maynes
 * @version 0.0
 */
public class XMLTester {
	public static void main(String [] args){
		XMLDocument root = new XMLDocument("course");
		XMLElement course = new XMLElement("class");
		XMLNode michael = new XMLNode("student", "Michael");
		michael.addAttribute("id", "12345678");
		michael.addAttribute("year", "3");
		
		
		root.add(new XMLNode("code", "SYSC3110"));
		root.add(new XMLNode("prof", "Babak"));
		root.add(course);
		course.add(michael);		
		course.add(new XMLNode("student", "Alan"));
		
		System.out.println(root);
	}
}
