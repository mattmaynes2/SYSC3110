import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/**
 * Poker distributes the hands and can determine the winner
 *
 * @author babak
 * @version 0.0
 */
public class Poker {

    private Collection<Hand> hands;

    /**
     * Create a new game of poker (empty at first)
     */
    public Poker(){
        hands = new ArrayList<Hand>();
    }

    /**
     * Add a new hand
     */
    public void addHand(Hand h) {
        hands.add(h);
    }

    /**
     * @return the best hand
     */
    public Hand bestHand(){
       return Collections.max(hands);
    }
    
    public static void main (String[] args){
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String in1 = "", in2 = "";
    	try {
    		System.out.println("Enter the first hand");
    		in1 = br.readLine();
    		System.out.println("Enter the second hand");    	
			in2 = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	Poker p = new Poker();
    	Hand hand1 = new Hand(in1);
    	Hand hand2 = new Hand(in2);
    	p.addHand(hand1);
    	p.addHand(hand2);
    	System.out.println(p.bestHand());
    	
    }
}