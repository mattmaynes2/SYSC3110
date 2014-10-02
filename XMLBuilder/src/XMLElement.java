/**
 * XMLElement
 * 
 * An XML element can contain sub elements allowing it to build an XML tree
 * 
 * @author Matthew Maynes
 * @version 0.0
 *
 */

import java.util.HashMap;
import java.util.ArrayList;

public class XMLElement {

	/**
	 * The name of the element and will serve as the tag.
	 */
	private String name;
	
	/**
	 * This is collection of the child elements of this element
	 * 
	 * @see value
	 */
	private ArrayList<XMLElement> children;
	
	/**
	 * A list of the attributes and their values for this node
	 */
	private HashMap<String, String> attributes;
	
	/**
	 * Constructs an element with just a name and no value
	 * 
	 * @param name - The name of this element
	 */
	public XMLElement (String name){
		this.name = name;
		this.attributes = new HashMap<String, String>();
		this.children = new ArrayList<XMLElement>();
		
	}

	
	/**
	 * Adds a child to the body of this element.
	 * 
	 * @see children
	 */
	public void add(XMLElement elem){
		this.children.add(elem);
	}
	
	/**
	 * Removes the given element from this nodes body if it exists
	 * 
	 * @param elem - The element to remove
	 */
	public void removeNode(XMLElement elem){
		this.children.remove(elem);
	}
	
	/**
	 * Adds an attribute with a value to this node
	 * 
	 * @param name - The name of the attribute
	 * @param value - The value of the attribute
	 */
	public void addAttribute(String name, String value){
		this.attributes.put(name, value);
	}
	
	/**
	 * Removes the specified attribute from this element if it exists and returns it
	 * 
	 * @param name - The name of the attribute to remove
	 * @return The value of the attribute that was removed or null
	 */
	public String removeAttribute(String name){
		return this.attributes.remove(name);
	}
	
	/**
	 * Return the name of this node	
	 * @return The name of the node
	 * @see name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of this node
	 * @param name - the new name
	 * @see name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns all of the children that this element contains
	 * 
	 * @return A list of the immediate children that this element contains
	 * @see children
	 */
	public ArrayList<XMLElement> getChildren() {
		return this.children;
	}


	/**
	 * Returns a map of all of the attributes that this node has
	 * 
	 * @return All of the attributes owned by this node
	 */
	public HashMap<String, String> getAttributes() {
		return attributes;
	}
	
	/**
	 * Prints a "pretty-print" version of this XML tree starting at this node
	 * 
	 * @return A text representation of this XML tree
	 */
	public String print(){
		return this.print("");
	}
	
	/**
	 * Prints the values of this element
	 * @see print()
	 */
	protected String print(String tabs){
		String out = tabs + "<" + this.getName();
		
		if(this.getAttributes().size() > 0){
			for(String key : this.getAttributes().keySet()){
				out += " " + key + "=\"" + this.getAttributes().get(key) + "\"";
			}
		}	
		out +=">";
		
		if(this.getChildren().size() > 0){
			out += "\n";
			for(XMLElement n : this.getChildren()){
				out += n.print(tabs + "\t");			
			}
		}
		return out + tabs + "</" + this.getName() + ">\n";
	}
	
	@Override
	public String toString(){
		return this.print();
	}
}
