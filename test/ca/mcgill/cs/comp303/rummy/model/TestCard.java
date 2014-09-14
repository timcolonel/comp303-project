package ca.mcgill.cs.comp303.rummy.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import ca.mcgill.cs.comp303.rummy.model.Card.Rank;
import ca.mcgill.cs.comp303.rummy.model.Card.Suit;

/**
 * Test for the Card class.
 */
public class TestCard
{
	/**
	 * Test the getRank() method. Test the getSuit() method.
	 */
	@Test
	public void testGetRankAndGetSuit()
	{
		Rank rank = Rank.FIVE;
		Suit suit = Suit.DIAMONDS;
		Card card = new Card(rank, suit);
		assertEquals(card.getRank(), rank);
		assertEquals(card.getSuit(), suit);
	}

	/**
	 * Test the toString() method.
	 */
	@Test
	public void testToString()
	{
		Card card = new Card(Rank.FIVE, Suit.DIAMONDS);
		assertThat(card.toString(), instanceOf(String.class));
	}

	/**
	 * Test the compareTo() method.
	 */
	@Test
	public void testCompareTo()
	{
		Card card = new Card(Rank.FIVE, Suit.DIAMONDS);
		assertEquals(card.compareTo(new Card(Rank.SIX, Suit.DIAMONDS)), -1);
		assertEquals(card.compareTo(new Card(Rank.FOUR, Suit.DIAMONDS)), 1);
		assertEquals(card.compareTo(new Card(Rank.FIVE, Suit.SPADES)), -1);
		assertEquals(card.compareTo(new Card(Rank.FIVE, Suit.CLUBS)), 1);
		assertEquals(card.compareTo(new Card(Rank.FIVE, Suit.DIAMONDS)), 0);
	}
	
	/**
	 * Test the equals() method.
	 */
	@Test
	public void testEquals()
	{
		Card card = new Card(Rank.FIVE, Suit.DIAMONDS);
		assertTrue(card.equals(new Card(Rank.FIVE, Suit.DIAMONDS)));
		assertEquals(card.equals(new Card(Rank.FOUR, Suit.DIAMONDS)), false);
		assertEquals(card.equals(new Card(Rank.FIVE, Suit.CLUBS)), false);
		assertEquals(card.equals(1), false);
	}
	
	/**
	 * Test the equals() method.
	 */
	@Test
	public void testHashCode()
	{
		Card card = new Card(Rank.FIVE, Suit.DIAMONDS);
		assertThat(card.hashCode(), instanceOf(Integer.class));
	}
}
