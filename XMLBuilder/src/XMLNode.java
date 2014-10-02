/**
 * XMLNode
 * 
 * An XML node can contain a value, attributes or other nodes. Using these nodes
 * an XML tree can be built.
 * 
 * @author Matthew Maynes
 * @version 0.0
 *
 */
import java.util.HashMap;
import java.util.ArrayList;

public class XMLNode {
	
	/**
	 * The name of the node and will serve as the tag.
	 */
	private String name;
	
	/**
	 * If the node has children nodes then its value will not be displayed. Children are
	 * sub nodes that builds the tree structure of the XML document
	 * 
	 * @see value
	 */
	private ArrayList<XMLNode> children;
	
	/**
	 * This is the value that the node contains. If the node has children the value will
	 * not be added
	 * 
	 * @see payload
	 */
	private String value;
	
	/**
	 * A list of the attributes and their values for this node
	 */
	private HashMap<String, String> attributes;

	/**
	 * Constructs a node with just a name and no value
	 * @param name - The name of this node
	 */
	public XMLNode (String name){
		this(name, "");
		
	}
	
	/**
	 * Constructs a node with a name and a value
	 * 
	 * @param name - The name of this node
	 * @param value - The value this node holds
	 */
	public XMLNode(String name, String value){
		this.name = name;
		this.value = value;
		this.attributes = new HashMap<String, String>();
		this.children = new ArrayList<XMLNode>();
	}
	
	
	
	/**
	 * Adds a child to the body of this node.
	 * 
	 * @see children
	 */
	public void addNode(XMLNode node){
		this.children.add(node);
	}
	
	/**
	 * Removes the given node from this nodes body if it exists
	 * 
	 * @param node - The node to remove
	 */
	public void removeNode(XMLNode node){
		this.children.remove(node);
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
	 * Removes the specified attribute from this node if it exists and returns it
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
	 * Returns all of the children that this node contains
	 * 
	 * @return A list of the immediate children that this node contains
	 * @see children
	 */
	public ArrayList<XMLNode> getChildren() {
		return children;
	}

	/**
	 * Returns the value of this node
	 * 
	 * @return the value of this node
	 * @see value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value of this node
	 * 
	 * @param value - The new value
	 * @see value
	 */
	public void setValue(String value) {
		this.value = value;
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
	 * Prints the values of this node
	 * @see print()
	 */
	private String print(String tabs){
		String out = tabs + "<" + this.name;
		
		if(this.attributes.size() > 0){
			for(String key : this.attributes.keySet()){
				out += " " + key + "=\"" + this.attributes.get(key) + "\"";
			}
		}	
		out +=">";
		
		if(this.children.size() > 0){
			out += "\n";
			for(XMLNode n : this.children){
				out += n.print(tabs + "\t");			
			}
		}
		else{
			return out + this.value + "</" + this.name + ">\n";
		}
		return out + tabs + "</" + this.name + ">\n";
	}
	
	
	@Override
	public String toString(){
		return this.print();
	}
	
	public static void main(String [] args){
		XMLNode root = new XMLNode("course");
		XMLNode course = new XMLNode("class");
		XMLNode michael = new XMLNode("student", "Michael");
		michael.addAttribute("id", "12345678");
		michael.addAttribute("year", "3");
		
		
		root.addNode(new XMLNode("code", "SYSC3110"));
		root.addNode(new XMLNode("prof", "Babak"));
		root.addNode(course);
		course.addNode(michael);		
		course.addNode(new XMLNode("student", "Alan"));
		
		System.out.println(root);
	}
}
