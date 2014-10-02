import java.util.*;

/**
 * A poker hand is a list of cards, which can be of some "kind" (pair, straight, etc.)
 * 
 * @author babak
 * @version 0.0
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
        for (String s : c.split(" ")) cards.add(new Card(s));
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
     */
    protected boolean hasNKind(int n) {
        List<Card.Rank> ranks = getRanks();
        for (Card.Rank r : ranks) {
            if (Collections.frequency(ranks, r) == n) return true;
        }
        return false;
    }
    
    /**
     * @returns true if the hand has two pairs
     */
    public boolean isTwoPair() {
        boolean onepair = false;
        List<Card.Rank> ranks = getRanks();
        Set<Card.Rank> rankset = new HashSet<Card.Rank>(ranks);
        for (Card.Rank r : rankset) {
            if (Collections.frequency(ranks, r) == 2) {
                if (!onepair) onepair = true;
                else return true;
            }
        }
        return false;
    }   
    
    /**
     * @returns true if the hand is a straight 
     */
    public boolean isStraight() {
        List<Card.Rank> ranks = getRanks();
        Set<Card.Rank> rankset = new HashSet<Card.Rank>(ranks);
        
        //take care of low Ace exceptional case:
        if (ranks.equals((new Hand("AC 2C 3C 4C 5C")).getRanks())) return true;
        
        return (ranks.size() - 1) == (Collections.max(ranks).ordinal() - Collections.min(ranks).ordinal())
        && (rankset.size() == ranks.size());
    }
    
    /**
     * @returns true if the hand is a flush
     */
    public boolean isFlush() {
        Set<Card.Suit> suitset = new HashSet<Card.Suit>();
        for (Card c : cards) suitset.add(c.getSuit());
        return (suitset.size() == 1);
    }
    
    @Override
    public int compareTo(Hand h) {
        //caution: doesn't break ties!
        return this.kind().compareTo(h.kind()); 
    }
    
    /**
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

}
