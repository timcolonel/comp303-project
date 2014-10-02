package ca.mcgill.cs.comp303.rummy.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import ca.mcgill.cs.comp303.rummy.testutils.AllCards;

/**
 * Test the CardSet class.
 * 
 * @author tim
 *
 */
public class TestCardSet
{
	private CardSet aSet;

	/**
	 * Setup.
	 */
	@Before
	public void setUp()
	{
		Set<Card> cardSet = new HashSet<Card>();
		cardSet.add(AllCards.C2C);
		aSet = new CardSet(cardSet);
	}

	/**
	 * 
	 */
	@Test
	public void getIterator()
	{
		Iterator<Card> aIterator = aSet.iterator();
		assertEquals(true, aIterator.hasNext());
		// TODO: test other methods of iterator
	}

	/**
	 * 
	 */
	@Test
	public void setContainsAndSize()
	{
		HashSet<Card> cardSet = new HashSet<Card>();
		cardSet.add(AllCards.C2C);
		aSet = new CardSet(cardSet);
		assertEquals(true, aSet.contains(AllCards.C2C));
		assertEquals(false, aSet.contains(AllCards.C2D));

		// test that card was NOT added after initialization(immutable)
		cardSet.add(AllCards.C2D);
		assertEquals(false, aSet.contains(AllCards.C2D));
		assertEquals(1, aSet.size());

		cardSet = new HashSet<Card>();
		cardSet.add(AllCards.C2C);
		cardSet.add(AllCards.C2D);
		// test that cards were added before initialization
		aSet = new CardSet(cardSet);
		assertEquals(true, aSet.contains(AllCards.C2D));
		assertEquals(2, aSet.size());

		// test for null cards in set
		cardSet = new HashSet<Card>();
		cardSet.add(null);
		aSet = new CardSet(cardSet);
		assertEquals(false, aSet.contains(AllCards.C2D));
		assertEquals(0, aSet.size());

		// test for null set
		cardSet = null;
		aSet = new CardSet(cardSet);
		assertEquals(false, aSet.contains(AllCards.C2D));
		assertEquals(0, aSet.size());

	}

	/**
	 * 
	 */
	@Test
	public void isGroup()
	{
		HashSet<Card> cardSet = new HashSet<Card>();
		cardSet.add(AllCards.C2C);
		cardSet.add(AllCards.C2H);
		cardSet.add(AllCards.C2D);
		aSet = new CardSet(cardSet);
		assertEquals(true, aSet.isGroup());

		cardSet = new HashSet<Card>();
		cardSet.add(AllCards.C2C);
		cardSet.add(AllCards.C3H);
		cardSet.add(AllCards.C2D);
		aSet = new CardSet(cardSet);
		assertEquals(false, aSet.isGroup());

		cardSet = new HashSet<Card>();
		cardSet.add(AllCards.C2C);
		cardSet.add(AllCards.C2D);
		cardSet.add(AllCards.C3D);
		aSet = new CardSet(cardSet);
		assertEquals(false, aSet.isGroup());

	}

	/**
	 * 
	 */
	@Test
	public void isRun()
	{
		HashSet<Card> cardSet = new HashSet<Card>();
		cardSet.add(AllCards.C2C);
		cardSet.add(AllCards.C2H);
		cardSet.add(AllCards.C2D);
		aSet = new CardSet(cardSet);
		assertEquals(false, aSet.isRun());

		cardSet = new HashSet<Card>();
		cardSet.add(AllCards.C2C);
		cardSet.add(AllCards.C3C);
		cardSet.add(AllCards.C4C);
		aSet = new CardSet(cardSet);
		assertEquals(true, aSet.isRun());

		cardSet = new HashSet<Card>();
		cardSet.add(AllCards.C2C);
		cardSet.add(AllCards.C4C);
		cardSet.add(AllCards.C3C);
		aSet = new CardSet(cardSet);
		assertEquals(true, aSet.isRun());

		cardSet = new HashSet<Card>();
		cardSet.add(AllCards.C2C);
		cardSet.add(AllCards.C5C);
		cardSet.add(AllCards.C3C);
		aSet = new CardSet(cardSet);
		assertEquals(false, aSet.isRun());

		cardSet = new HashSet<Card>();
		cardSet.add(AllCards.C2C);
		cardSet.add(AllCards.C3C);
		cardSet.add(AllCards.C4H);
		aSet = new CardSet(cardSet);
		assertEquals(false, aSet.isRun());

	}

}
