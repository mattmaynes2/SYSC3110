/**
 * XMLNode
 * 
 * An XML node can contain a value, attributes or other nodes. Using these nodes
 * an XML tree can be built.
 * 
 * @author Matthew Maynes
 * @version 0.1
 *
 */

public class XMLNode extends XMLElement{
	
	
	/**
	 * This is the value that the node contains. If the node has children the value will
	 * not be added
	 * 
	 * @see payload
	 */
	private String value;

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
	 * Constructs a node with a name but no value
	 * 
	 * @param name - The name of this node
	 */
	public XMLNode(String name){
		this(name, "");
	}
	
	/**
	 * Constructs a node with a name and a value
	 * 
	 * @param name - The name of this node
	 * @param value - The value this node holds
	 */
	public XMLNode(String name, String value){
		super(name);
		this.value = value;
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
	@Override
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
		else{
			return out + this.value + "</" + this.getName() + ">\n";
		}
		return out + tabs + "</" + this.getName() + ">\n";
	}
	

}
