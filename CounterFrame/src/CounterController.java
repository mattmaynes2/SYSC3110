import java.util.Observer;


public class CounterController {

	private Counter counter;
	
	public CounterController(int max){
		counter = new Counter(max);
	}
	
	public void addObserver(Observer obs){
		counter.addObserver(obs);
	}
	
	public void increment(){
		counter.increment();
	}
	
}
