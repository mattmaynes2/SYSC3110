public class Item {
	
	private String description;
	private int weight;
	
	
	public Item(String description){
		this.description = description;
	}

	public Item(String description, int weight){
		this.description = description;
		this.weight = weight;
	}

	public String getDescription() {
		return description;
	}


	public int getWeight() {
		return weight;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public String toString(){
		return description;
	}
	
}
