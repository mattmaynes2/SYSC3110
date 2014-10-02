import java.util.ArrayList;

public class Inventory{

	private ArrayList<Item> items;
	
	private int maxWeight;
	private int currentWeight;
	
	public Inventory(int maxWeight){
		this.maxWeight = maxWeight;
		this.currentWeight = 0;
		this.items = new ArrayList<Item>();
	}
	
	public Inventory(){
		this(10);
	}
	
	public boolean addItem(Item item){
		if ((currentWeight + item.getWeight()) <= maxWeight){
			items.add(item);
			currentWeight += item.getWeight();
			return true;
		}
		return false;
	}
	
	public void removeItem(Item item){
		if(items.size() > 0){
			currentWeight -= Math.max(item.getWeight(), 0);
			items.remove(item);
		}
	}
	
	public ArrayList<Item> getItems(){
		return items;
	}

	public int getMaxWeight() {
		return maxWeight;
	}

	public int getCurrentWeight() {
		return currentWeight;
	}

	public void setMaxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
	}

	public void setCurrentWeight(int currentWeight) {
		this.currentWeight = currentWeight;
	}
	

}