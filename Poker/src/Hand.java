import java.util.*;

/**
 * A poker hand is a list of cards, which can be of some "kind" (pair, straight, etc.)
 *
 */
public class Hand implements Comparable<Hand> {

    public enum Kind {HIGH_CARD, PAIR, TWO_PAIR, THREE_OF_A_KIND, STRAIGHT,
        FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH}

    private final List<Card> cards;

    /**
     * Create a hand from a string containing all cards (e,g, "5C TD AH QS 2D")
     */
    public Hand(String c) {
    	cards = new ArrayList<Card>();
    	for(String s : c.split(" ")){
    		cards.add(new Card(s));
    	}
    	
    }


    /**                                                                                                                                                                                                     
     * @returns a sorted list of ranks                                                                                                                                                                      
     */
    protected List<Card.Rank> getRanks() {
        List<Card.Rank> ranks = new ArrayList<Card.Rank>();
        for (Card c : cards) ranks.add(c.getRank());

        Collections.sort(ranks); //enums are comparable! more efficient would be to just add the ranks in order                                                                                             
        //for (Card.Rank r : ranks) System.out.println(r + " ");                                                                                                                                            
        return ranks;
    }


    /**
     * @returns true if the hand has n cards of the same rank
	 * e.g., "TD TC TH 3C 7D" returns True for n=2 and n=3, and False for n=1 and n=4
     */
    protected boolean hasNKind(int n) {
    	int matches = 0;
    	for(int i = 0; i < cards.size(); i++){
    		for (int j = i + 1; j < cards.size(); j++){
    			if(cards.get(i).getRank() == cards.get(j).getRank()){
    				matches++;
    			}
    		}
    	}
    	
    	if(matches == n)
			return true;
    	return false;
    }

    /**
	 * Optional: you may skip this one. If so, just make it return False
     * @returns true if the hand has two pairs
     */
    public boolean isTwoPair() {
    	ArrayList<Card> tmp = new ArrayList<Card>(cards);
    	int pairs = 0;
    	for(int i = 0; i < tmp.size(); i++){
    		for (int j = i + 1; j < tmp.size(); j++){
    			if (tmp.get(i).getRank() == tmp.get(j).getRank()){
    				pairs ++;
    				tmp.set(i, null);
    				tmp.set(j, null);
    			}
    		}
    	}
    	return pairs == 2;
    }

    /**
     * @returns true if the hand is a straight
     */
    public boolean isStraight() {
    	Collections.sort(this.cards, new Comparator<Card>(){public int compare(Card obj, Card other){return obj.compareTo(other);}});
    	for(int i = 1; i < this.cards.size(); i++){
    		int delta = this.cards.get(i).getRank().ordinal() - this.cards.get(i - 1).getRank().ordinal();
    		if(delta != 1){
    			if(cards.get(0).getRank() == Card.Rank.DEUCE && cards.get(i).getRank() == Card.Rank.ACE)
    				continue;
    			return false;
    		}
    		
    	}
    	return true;
    }

    /**
     * @returns true if the hand is a flush
     */
    public boolean isFlush() {
    	Card.Suit suit = cards.size() > 0 ? cards.get(0).getSuit() : null;
    	for(int i = 1; i < cards.size(); i++){
    		if(cards.get(i).getSuit() != suit){
    			return false;
    		}
    	}
    	return true;
    }

    //@Override
    //public int compareTo(Hand h) {
    	
    // 	return (int) Math.signum(this.value() - h.value());
    //}

    @Override
    public int compareTo(Hand h) {
        //caution: doesn't break ties!                                                                                                                                                                      
        return this.kind().compareTo(h.kind());
    }

    public String toString(){
    	String repr = "";
    	for(Card c : this.cards){
    		repr += c.toString() + " ";
    	}
    	return repr;
    }
    
    /**
	 * This method is already implemented and could be useful!
     * @returns the "kind" of the hand: flush, full house, etc.
     */
    public Kind kind() {
        if (isStraight() && isFlush()) return Kind.STRAIGHT_FLUSH;
        else if (hasNKind(4)) return Kind.FOUR_OF_A_KIND;
        else if (hasNKind(3) && hasNKind(2)) return Kind.FULL_HOUSE;
        else if (isFlush()) return Kind.FLUSH;
        else if (isStraight()) return Kind.STRAIGHT;
        else if (hasNKind(3)) return Kind.THREE_OF_A_KIND;
        else if (isTwoPair()) return Kind.TWO_PAIR;
        else if (hasNKind(2)) return Kind.PAIR;
        else return Kind.HIGH_CARD;
    }

    public int value(){
    	int exp = this.kind().ordinal();
    	int sum = 0;
    	for (Card c : this.cards){
    		sum += c.getRank().ordinal();
    	}
    	
    	return sum * (int)Math.pow(13, exp);
    }
    
}