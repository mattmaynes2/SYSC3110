

/**
 * The test class PokerTest.
 *
 * @author  babak
 * @version 0.0
 */
public class PokerTest extends junit.framework.TestCase
{
    Poker poker1;
    Hand sf, sa, fk, fh, pair, high, tp;

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp() {
        poker1 = new Poker();
        sf = new Hand("6C 7C 8C 9C TC");
        sa = new Hand("2D 3D 4D 5D AD");
        fk = new Hand("9D 9H 9S 9C 7D");
        fh = new Hand("TD TC TH 7C 7D");
        pair = new Hand("2C 3C 4D 6D 2D");
        high = new Hand("2D 3C 4D 6D JH");
        tp = new Hand("JD 3C JH AD 3S");
    }
    
    public void testRank() {
        assertEquals(pair.getRanks().get(0), Card.Rank.DEUCE);
        assertEquals(Card.Rank.DEUCE.ordinal(), 0);
        assertEquals(pair.getRanks().get(pair.getRanks().size() - 1), Card.Rank.SIX);        
        assertEquals(pair.kind(), Hand.Kind.PAIR);
        assertEquals(high.kind(), Hand.Kind.HIGH_CARD);
        assertEquals(fk.kind(), Hand.Kind.FOUR_OF_A_KIND);
        assertEquals(tp.kind(), Hand.Kind.TWO_PAIR);
    }
    
    public void testHand() {
        assertFalse(pair.isFlush());
        assertFalse(pair.isStraight());
        assertTrue(sf.isStraight());
        assertTrue(sf.isFlush());
        assertTrue(sa.isStraight());
    }

    public void testBestHand() {
        assertTrue(fh.compareTo(fk) <= 0); 
        assertTrue(fh.compareTo(fh) <= 0);
        
        poker1.addHand(fh);
        assertEquals(fh, poker1.bestHand());
        poker1.addHand(fk);
        assertEquals(fk, poker1.bestHand());
        poker1.addHand(sf);
        assertEquals(sf, poker1.bestHand()); // == would work too...
    }
}

