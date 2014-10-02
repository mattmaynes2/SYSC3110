

/**
 * The test class CardTest.
 *
 * @author  babak
 * @version 0.0
 */
public class CardTest extends junit.framework.TestCase
{

    public CardTest()
    {
    }

	public void testRank()
	{
		assertEquals(Card.Rank.DEUCE, Card.getRankFromAbbrev('2'));
		assertEquals(Card.Rank.ACE, Card.getRankFromAbbrev('1'));
		assertEquals(Card.Rank.TEN, Card.getRankFromAbbrev('0'));
		assertEquals(Card.Rank.TEN, Card.getRankFromAbbrev('T'));
		assertEquals(Card.Rank.ACE, Card.getRankFromAbbrev('A'));
		assertEquals(Card.Rank.QUEEN, Card.getRankFromAbbrev('Q'));
		assertEquals(Card.Rank.NINE, Card.getRankFromAbbrev('9'));
	}

	public void testSuit()
	{
		assertEquals(Card.Suit.CLUBS, Card.getSuitFromAbbrev('C'));
		assertEquals(Card.Suit.DIAMONDS, Card.getSuitFromAbbrev('D'));
	}
}


